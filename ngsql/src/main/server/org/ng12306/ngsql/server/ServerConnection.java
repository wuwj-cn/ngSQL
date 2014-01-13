package org.ng12306.ngsql.server;

import java.io.EOFException;
import java.nio.channels.SocketChannel;

import org.ng12306.ngsql.config.ErrorCode;
import org.ng12306.ngsql.net.FrontendConnection;
import org.ng12306.ngsql.server.response.Heartbeat;
import org.ng12306.ngsql.server.response.Ping;
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

/*    public BlockingSession getSession() {
        return session;
    }

    public void setSession(BlockingSession session) {
        this.session = session;
    }

    public NonBlockingSession getSession2() {
        return session2;
    }

    public void setSession2(NonBlockingSession session2) {
        this.session2 = session2;
    }*/

    @Override
    public void ping() {
        Ping.response(this);
    }

    @Override
    public void heartbeat(byte[] data) {
        Heartbeat.response(this, data);
    }

    public void execute(String sql, int type) {
        // 状态检查
        if (txInterrupted) {
            writeErrMessage(ErrorCode.ER_YES, "Transaction error, need to rollback.");
            return;
        }

        // 检查当前使用的DB
        String db = this.schema;
        if (db == null) {
            writeErrMessage(ErrorCode.ER_NO_DB_ERROR, "No database selected");
            return;
        }
        /*SchemaConfig schema = NgSQLServer.getInstance().getConfig().getSchemas().get(db);
        if (schema == null) {
            writeErrMessage(ErrorCode.ER_BAD_DB_ERROR, "Unknown database '" + db + "'");
            return;
        }

        // 路由计算
        RouteResultset rrs = null;
        try {
            rrs = ServerRouter.route(schema, sql, this.charset, this);
        } catch (SQLNonTransientException e) {
            StringBuilder s = new StringBuilder();
            log.warn(s.append(this).append(sql).toString(), e);
            String msg = e.getMessage();
            writeErrMessage(ErrorCode.ER_PARSE_ERROR, msg == null ? e.getClass().getSimpleName() : msg);
            return;
        }*/

        // session执行
//        session.execute(rrs, type);
    }

    /**
     * 提交事务
     */
    public void commit() {
        if (txInterrupted) {
            writeErrMessage(ErrorCode.ER_YES, "Transaction error, need to rollback.");
        } else {
//            session.commit();
        }
    }

    /**
     * 回滚事务
     */
    public void rollback() {
        // 状态检查
        if (txInterrupted) {
            txInterrupted = false;
        }

        // 执行回滚
//        session.rollback();
    }

    /**
     * 撤销执行中的语句
     * 
     * @param sponsor
     *            发起者为null表示是自己
     */
    public void cancel(final FrontendConnection sponsor) {
        processor.getExecutor().execute(new Runnable() {
            @Override
            public void run() {
//                session.cancel(sponsor);
            }
        });
    }

    @Override
    public void error(int errCode, Throwable t) {
        // 根据异常类型和信息，选择日志输出级别。
        if (t instanceof EOFException) {
            if (log.isDebugEnabled()) {
            	log.debug(toString(), t);
            }
        } else if (isConnectionReset(t)) {
            if (log.isInfoEnabled()) {
            	log.info(toString(), t);
            }
        } else {
        	log.warn(toString(), t);
        }

        // 异常返回码处理
        switch (errCode) {
        case ErrorCode.ERR_HANDLE_DATA:
            String msg = t.getMessage();
            writeErrMessage(ErrorCode.ER_YES, msg == null ? t.getClass().getSimpleName() : msg);
            break;
        default:
            close();
        }
    }

    @Override
    public boolean close() {
        if (super.close()) {
            processor.getExecutor().execute(new Runnable() {
                @Override
                public void run() {
//                    session.terminate();
                }
            });
            return true;
        } else {
            return false;
        }
    }

}
