package org.ng12306.ngsql.server.handler;

import org.ng12306.ngsql.NgSQLServer;
import org.ng12306.ngsql.config.ErrorCode;
import org.ng12306.ngsql.net.FrontendConnection;
import org.ng12306.ngsql.net.NIOProcessor;
import org.ng12306.ngsql.server.ServerConnection;
import org.ng12306.ngsql.util.StringUtil;

/**
 * 
 * @author <a href="mailto:wenjie.0617@gmail.com">wuwj-cn</a>
 */
public class KillQueryHandler {

    public static void handle(String stmt, int offset, final ServerConnection c) {
        String id = stmt.substring(offset).trim();
        if (StringUtil.isEmpty(id)) {
            c.writeErrMessage(ErrorCode.ER_NO_SUCH_THREAD, "NULL connection id");
        } else {
            // get value
            long value = 0;
            try {
                value = Long.parseLong(id);
            } catch (NumberFormatException e) {
                c.writeErrMessage(ErrorCode.ER_NO_SUCH_THREAD, "Invalid connection id:" + id);
                return;
            }

            // kill query itself
            if (value == c.getId()) {
                c.cancel(null);
                return;
            }

            // get the connection and kill query
            FrontendConnection fc = null;
            NIOProcessor[] processors = NgSQLServer.getInstance().getProcessors();
            for (NIOProcessor p : processors) {
                if ((fc = p.getFrontends().get(value)) != null) {
                    break;
                }
            }
            if (fc != null) {
                if (fc instanceof ServerConnection) {
                    ((ServerConnection) fc).cancel(c);
                } else {
                    c.writeErrMessage(ErrorCode.ER_UNKNOWN_COM_ERROR, "Unknown command");
                }
            } else {
                c.writeErrMessage(ErrorCode.ER_NO_SUCH_THREAD, "Unknown connection id:" + id);
            }
        }
    }

}
