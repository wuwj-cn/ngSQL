package org.ng12306.ngsql.server.handler;

import org.ng12306.ngsql.config.ErrorCode;
import org.ng12306.ngsql.server.ServerConnection;

/**
 * @author xianmao.hexm
 */
public final class SavepointHandler {

    public static void handle(String stmt, ServerConnection c) {
        c.writeErrMessage(ErrorCode.ER_UNKNOWN_COM_ERROR, "Unsupported statement");
    }

}
