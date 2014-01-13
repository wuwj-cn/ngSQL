package org.ng12306.ngsql.server.response;

import org.ng12306.ngsql.NgSQLServer;
import org.ng12306.ngsql.config.ErrorCode;
import org.ng12306.ngsql.net.mysql.ErrorPacket;
import org.ng12306.ngsql.net.mysql.HeartbeatPacket;
import org.ng12306.ngsql.net.mysql.OkPacket;
import org.ng12306.ngsql.server.ServerConnection;
import org.ng12306.ngsql.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Heartbeat {

    private static final Logger HEARTBEAT = LoggerFactory.getLogger("heartbeat");

    public static void response(ServerConnection c, byte[] data) {
        HeartbeatPacket hp = new HeartbeatPacket();
        hp.read(data);
        if (NgSQLServer.getInstance().isOnline()) {
            OkPacket ok = new OkPacket();
            ok.packetId = 1;
            ok.affectedRows = hp.id;
            ok.serverStatus = 2;
            ok.write(c);
            if (HEARTBEAT.isInfoEnabled()) {
                HEARTBEAT.info(responseMessage("OK", c, hp.id));
            }
        } else {
            ErrorPacket error = new ErrorPacket();
            error.packetId = 1;
            error.errno = ErrorCode.ER_SERVER_SHUTDOWN;
            error.message = String.valueOf(hp.id).getBytes();
            error.write(c);
            if (HEARTBEAT.isInfoEnabled()) {
                HEARTBEAT.info(responseMessage("ERROR", c, hp.id));
            }
        }
    }

    private static String responseMessage(String action, ServerConnection c, long id) {
        return new StringBuilder("RESPONSE:").append(action).append(", id=").append(id).append(", host=")
                .append(c.getHost()).append(", port=").append(c.getPort()).append(", time=")
                .append(TimeUtil.currentTimeMillis()).toString();
    }

}
