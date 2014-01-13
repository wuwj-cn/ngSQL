package org.ng12306.ngsql.server.handler;

import java.nio.ByteBuffer;

import org.ng12306.ngsql.net.mysql.OkPacket;
import org.ng12306.ngsql.server.ServerConnection;

/**
 * 
 * @author <a href="mailto:wenjie.0617@gmail.com">wuwj-cn</a>
 */
public final class RollbackHandler {

    public static void handle(String stmt, ServerConnection c) {
        ByteBuffer buffer = c.allocate();
        c.write(c.writeToBuffer(OkPacket.OK, buffer));
    }

}
