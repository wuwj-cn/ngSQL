package org.ng12306.ngsql.server;

import java.nio.channels.SocketChannel;

import org.ng12306.ngsql.net.FrontendConnection;
import org.ng12306.ngsql.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author <a href="mailto:wenjie.0617@gmail.com">wuwj-cn</a>
 */
public class ServerConnection extends FrontendConnection {
    private static final Logger log = LoggerFactory.getLogger(ServerConnection.class);
    private static final long AUTH_TIMEOUT = 15 * 1000L;

    private volatile int txIsolation;
    private volatile boolean autocommit;
    private volatile boolean txInterrupted;
    private long lastInsertId;
//    private BlockingSession session;
//    private NonBlockingSession session2;

    public ServerConnection(SocketChannel channel) {
        super(channel);
        this.txInterrupted = false;
        this.autocommit = true;
    }

    @Override
    public boolean isIdleTimeout() {
        if (isAuthenticated) {
            return super.isIdleTimeout();
        } else {
            return TimeUtil.currentTimeMillis() > Math.max(lastWriteTime, lastReadTime) + AUTH_TIMEOUT;
        }
    }

    public int getTxIsolation() {
        return txIsolation;
    }

    public void setTxIsolation(int txIsolation) {
        this.txIsolation = txIsolation;
    }

    public boolean isAutocommit() {
        return autocommit;
    }

    public void setAutocommit(boolean autocommit) {
        this.autocommit = autocommit;
    }

    public long getLastInsertId() {
        return lastInsertId;
    }

    public void setLastInsertId(long lastInsertId) {
        this.lastInsertId = lastInsertId;
    }

    /**
     * 设置是否需要中断当前事务
     */
    public void setTxInterrupt() {
        if (!autocommit && !txInterrupted) {
            txInterrupted = true;
        }
    }

	@Override
	public void error(int errCode, Throwable t) {
		// TODO Auto-generated method stub
		
	}
}
