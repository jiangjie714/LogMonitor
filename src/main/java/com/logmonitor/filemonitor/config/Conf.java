package com.logmonitor.filemonitor.config;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Conf {
	private List<ConfItem> itemList;
	private ConfHandler confHandler = new ConfHandler();
	private int flushInterval = 1;//seconds
	private int dataSaveInterval = 3;//seconds
	private int mainBufferSize = 10;//lines
	private String recoverPath = null;//日志文件读取状态保存地址
	private boolean enableRecover = false;
	
	public static class ConfHandler {
		private boolean useStdoutHandler = false;
		private boolean useNetHandler = false;
		private boolean useRedisHandler = false;
		private String netIp = "127.0.0.1";
		private int netPort = 3333;
		private String redisHost = "127.0.0.1";
		private int redisPort = 6379;
		private long redisQueueMaxSize = 1000;
		private String redisQueueKeyName = "logmonitor.msg.queue";
		
		public boolean isUseStdoutHandler() {
			return useStdoutHandler;
		}
		public void setUseStdoutHandler(boolean useStdoutHandler) {
			this.useStdoutHandler = useStdoutHandler;
		}
		public boolean isUseNetHandler() {
			return useNetHandler;
		}
		public void setUseNetHandler(boolean useNetHandler) {
			this.useNetHandler = useNetHandler;
		}
		public String getNetIp() {
			return netIp;
		}
		public void setNetIp(String netIp) {
			this.netIp = netIp;
		}
		public int getNetPort() {
			return netPort;
		}
		public void setNetPort(int netPort) {
			this.netPort = netPort;
		}

		public String getRedisHost() {
			return redisHost;
		}

		public void setRedisHost(String redisHost) {
			this.redisHost = redisHost;
		}

		public int getRedisPort() {
			return redisPort;
		}

		public void setRedisPort(int redisPort) {
			this.redisPort = redisPort;
		}

		public long getRedisQueueMaxSize() {
			return redisQueueMaxSize;
		}

		public void setRedisQueueMaxSize(long redisQueueMaxSize) {
			this.redisQueueMaxSize = redisQueueMaxSize;
		}

		public String getRedisQueueKeyName() {
			return redisQueueKeyName;
		}

		public void setRedisQueueKeyName(String redisQueueKeyName) {
			this.redisQueueKeyName = redisQueueKeyName;
		}

		public boolean isUseRedisHandler() {
			return useRedisHandler;
		}

		public void setUseRedisHandler(boolean useRedisHandler) {
			this.useRedisHandler = useRedisHandler;
		}
	}
	
	public static class ConfItem {
		private String logPath = "";
		private String logNameFilter = ".+\\.log";
		private boolean useLogNameFilter = true;
		private long scanInterval = TimeUnit.SECONDS.toMillis(1);
		private String keyCode = null;
		
		public ConfItem(String logPath) {
			this.logPath = logPath;
		}
		
		public ConfItem(String logPath,String logNameFilter) {
			this.logPath = logPath;
			this.logNameFilter = logNameFilter;
		}
		
		public void setLogPath(String logPath) {
			this.logPath = logPath;
		}
		
		public String getLogPath() {
			return this.logPath;
		}
		
		public void setLogNameFilter(String logNameFilter) {
			this.logNameFilter = logNameFilter;
		}
		
		public String getLogNameFilter() {
			return this.logNameFilter;
		}
		
		public void enableLogNameFilter() {
			this.useLogNameFilter = true;
		}
		
		public void disableLogNameFilter() {
			this.useLogNameFilter = false;
		}
		
		@Override
		public boolean equals(Object item) {
			return EqualsBuilder.reflectionEquals(this, item, "logPath", "logNameFilter");
		}
		
		@Override
		public int hashCode() {
			return HashCodeBuilder.reflectionHashCode(this, "logPath", "logNameFilter");
		}

		public boolean isUseLogNameFilter() {
			return useLogNameFilter;
		}

		public long getScanInterval() {
			return scanInterval;
		}
		
		public void setScanInterval(long seconds) {
			this.scanInterval = TimeUnit.SECONDS.toMillis(seconds);
		}

		public void setScanInterval(long seconds,long millis) {
			this.scanInterval = TimeUnit.SECONDS.toMillis(seconds) + millis;
		}
		
		public void setKeyCode(String keyCode) {
			this.keyCode = keyCode;
		}
		
		public String getKeyCode() {
			return this.keyCode;
		}
	}
	
	public Conf() {
		this.itemList = new ArrayList<ConfItem>();
	}
	
	public boolean validate() {
		return true;
	}
	
	public void addConfItem(ConfItem item) {
		this.itemList.add(item);
	}
	
	public void removeConfItem(ConfItem item) {
		this.itemList.remove(item);
	}
	
	public List<ConfItem> getItems() {
		return this.itemList;
	}

	public int getFlushInterval() {
		return flushInterval;
	}

	public void setFlushInterval(int flushInterval) {
		if (flushInterval <= 0) {
			flushInterval = 2;
		}
		this.flushInterval = flushInterval;
	}

	public int getMainBufferSize() {
		return mainBufferSize;
	}

	public void setMainBufferSize(int mainBufferSize) {
		if (mainBufferSize <= 0) {
			mainBufferSize = 10;
		}
		this.mainBufferSize = mainBufferSize;
	}

	public String getRecoverPath() {
		return recoverPath;
	}

	public void setRecoverPath(String recoverPath) {
		this.recoverPath = recoverPath.trim();
		if (!this.recoverPath.endsWith("/")) {
			this.recoverPath += "/";
		}
	}

	public boolean isEnableRecover() {
		return enableRecover;
	}

	public void setEnableRecover(boolean enableRecover) {
		this.enableRecover = enableRecover;
	}

	public ConfHandler getConfHandler() {
		return confHandler;
	}

	public void setConfHandler(ConfHandler confHandler) {
		this.confHandler = confHandler;
	}

	public int getDataSaveInterval() {
		return dataSaveInterval;
	}

	public void setDataSaveInterval(int dataSaveInterval) {
		if (dataSaveInterval <= 0) {
			dataSaveInterval = 3;
		}
		this.dataSaveInterval = dataSaveInterval;
	}

}
