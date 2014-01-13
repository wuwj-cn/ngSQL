package org.ng12306.ngsql.server.handler;

import org.ng12306.ngsql.config.ErrorCode;
import org.ng12306.ngsql.server.ServerConnection;

/**
 * 
 * @author <a href="mailto:wenjie.0617@gmail.com">wuwj-cn</a>
 */
public final class BeginHandler {

    public static void handle(String stmt, ServerConnection c) {
        c.writeErrMessage(ErrorCode.ER_UNKNOWN_COM_ERROR, "Unsupported statement");
    }

}
