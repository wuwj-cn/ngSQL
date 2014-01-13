package org.ng12306.ngsql.server.response;

import org.ng12306.ngsql.NgSQLServer;
import org.ng12306.ngsql.mysql.PacketUtil;
import org.ng12306.ngsql.net.FrontendConnection;
import org.ng12306.ngsql.net.mysql.ErrorPacket;
import org.ng12306.ngsql.net.mysql.OkPacket;

/**
 * 加入了offline状态推送，用于心跳语句。
 * @author <a href="mailto:wenjie.0617@gmail.com">wuwj-cn</a>
 */
public class Ping {

    private static final ErrorPacket error = PacketUtil.getShutdown();

    public static void response(FrontendConnection c) {
        if (NgSQLServer.getInstance().isOnline()) {
            c.write(c.writeToBuffer(OkPacket.OK, c.allocate()));
        } else {
            error.write(c);
        }
    }

}
