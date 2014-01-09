package org.ng12306.ngsql.config.model;

/**
 * 系统基础配置项
 * 
 * @author wuwj-cn 2014-1-7 下午12:46:24
 *
 */
public final class SystemConfig {

    private static final int DEFAULT_PORT = 12306;
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final int DEFAULT_PROCESSORS = Runtime.getRuntime().availableProcessors();
    private static final long DEFAULT_IDLE_TIMEOUT = 8 * 3600 * 1000L;

    private int serverPort;
    private String charset;
    private int processors;
    private int processorHandler;
    private int processorExecutor;
    private int initExecutor;
    private int timerExecutor;
    private int managerExecutor;
    private long idleTimeout;

    public SystemConfig() {
        this.serverPort = DEFAULT_PORT;
        this.charset = DEFAULT_CHARSET;
        this.processors = DEFAULT_PROCESSORS;
        this.processorHandler = DEFAULT_PROCESSORS;
        this.processorExecutor = DEFAULT_PROCESSORS;
        this.managerExecutor = DEFAULT_PROCESSORS;
        this.timerExecutor = DEFAULT_PROCESSORS;
        this.initExecutor = DEFAULT_PROCESSORS;
        this.idleTimeout = DEFAULT_IDLE_TIMEOUT;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public int getProcessors() {
        return processors;
    }

    public void setProcessors(int processors) {
        this.processors = processors;
    }

    public int getProcessorHandler() {
        return processorHandler;
    }

    public void setProcessorHandler(int processorExecutor) {
        this.processorHandler = processorExecutor;
    }

    public int getProcessorExecutor() {
        return processorExecutor;
    }

    public void setProcessorExecutor(int processorExecutor) {
        this.processorExecutor = processorExecutor;
    }

    public int getManagerExecutor() {
        return managerExecutor;
    }

    public void setManagerExecutor(int managerExecutor) {
        this.managerExecutor = managerExecutor;
    }

    public int getTimerExecutor() {
        return timerExecutor;
    }

    public void setTimerExecutor(int timerExecutor) {
        this.timerExecutor = timerExecutor;
    }

    public int getInitExecutor() {
        return initExecutor;
    }

    public void setInitExecutor(int initExecutor) {
        this.initExecutor = initExecutor;
    }

    public long getIdleTimeout() {
        return idleTimeout;
    }

    public void setIdleTimeout(long idleTimeout) {
        this.idleTimeout = idleTimeout;
    }
}
