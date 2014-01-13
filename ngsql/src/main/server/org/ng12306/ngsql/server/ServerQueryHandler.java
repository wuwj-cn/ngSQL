package org.ng12306.ngsql.server;

import org.ng12306.ngsql.config.ErrorCode;
import org.ng12306.ngsql.net.handler.FrontendQueryHandler;
import org.ng12306.ngsql.server.handler.BeginHandler;
import org.ng12306.ngsql.server.handler.KillHandler;
import org.ng12306.ngsql.server.handler.SavepointHandler;
import org.ng12306.ngsql.server.handler.StartHandler;
import org.ng12306.ngsql.server.handler.UseHandler;
import org.ng12306.ngsql.server.parser.ServerParse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author <a href="mailto:wenjie.0617@gmail.com">wuwj-cn</a>
 */
public class ServerQueryHandler implements FrontendQueryHandler {
    private static final Logger log = LoggerFactory.getLogger(ServerQueryHandler.class);

    private final ServerConnection source;

    public ServerQueryHandler(ServerConnection source) {
        this.source = source;
    }

	@Override
	public void query(String sql) {
		ServerConnection c = this.source;
        if (log.isDebugEnabled()) {
            log.debug(new StringBuilder().append(c).append(sql).toString());
        }
        int rs = ServerParse.parse(sql);
        switch (rs & 0xff) {
        case ServerParse.EXPLAIN:
//            ExplainHandler.handle(sql, c, rs >>> 8);
            break;
        case ServerParse.SET:
//            SetHandler.handle(sql, c, rs >>> 8);
            break;
        case ServerParse.SHOW:
//            ShowHandler.handle(sql, c, rs >>> 8);
            break;
        case ServerParse.SELECT:
//            SelectHandler.handle(sql, c, rs >>> 8);
            break;
        case ServerParse.START:
            StartHandler.handle(sql, c, rs >>> 8);
            break;
        case ServerParse.BEGIN:
            BeginHandler.handle(sql, c);
            break;
        case ServerParse.SAVEPOINT:
            SavepointHandler.handle(sql, c);
            break;
        case ServerParse.KILL:
            KillHandler.handle(sql, rs >>> 8, c);
            break;
        case ServerParse.KILL_QUERY:
            c.writeErrMessage(ErrorCode.ER_UNKNOWN_COM_ERROR, "Unsupported command");
            break;
        case ServerParse.USE:
            UseHandler.handle(sql, c, rs >>> 8);
            break;
        case ServerParse.COMMIT:
            c.commit();
            break;
        case ServerParse.ROLLBACK:
            c.rollback();
            break;
        default:
            c.execute(sql, rs);
        }
	}
}
