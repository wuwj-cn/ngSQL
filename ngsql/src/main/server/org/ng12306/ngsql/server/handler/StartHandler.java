package org.ng12306.ngsql.server.handler;

import org.ng12306.ngsql.config.ErrorCode;
import org.ng12306.ngsql.server.ServerConnection;
import org.ng12306.ngsql.server.parser.ServerParse;
import org.ng12306.ngsql.server.parser.ServerParseStart;

/**
 * @author xianmao.hexm
 */
public final class StartHandler {

    public static void handle(String stmt, ServerConnection c, int offset) {
        switch (ServerParseStart.parse(stmt, offset)) {
        case ServerParseStart.TRANSACTION:
            c.writeErrMessage(ErrorCode.ER_UNKNOWN_COM_ERROR, "Unsupported statement");
            break;
        default:
            c.execute(stmt, ServerParse.START);
        }
    }

}
