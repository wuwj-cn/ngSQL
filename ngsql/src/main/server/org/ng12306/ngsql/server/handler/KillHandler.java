/*
 * Copyright 1999-2012 Alibaba Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ng12306.ngsql.server.handler;

import org.ng12306.ngsql.NgSQLServer;
import org.ng12306.ngsql.config.ErrorCode;
import org.ng12306.ngsql.net.FrontendConnection;
import org.ng12306.ngsql.net.NIOProcessor;
import org.ng12306.ngsql.net.mysql.OkPacket;
import org.ng12306.ngsql.server.ServerConnection;
import org.ng12306.ngsql.util.StringUtil;

/**
 * @author xianmao.hexm
 */
public class KillHandler {

    public static void handle(String stmt, int offset, ServerConnection c) {
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

            // kill myself
            if (value == c.getId()) {
                getOkPacket().write(c);
                c.write(c.allocate());
                return;
            }

            // get connection and close it
            FrontendConnection fc = null;
            NIOProcessor[] processors = NgSQLServer.getInstance().getProcessors();
            for (NIOProcessor p : processors) {
                if ((fc = p.getFrontends().get(value)) != null) {
                    break;
                }
            }
            if (fc != null) {
                fc.close();
                getOkPacket().write(c);
            } else {
                c.writeErrMessage(ErrorCode.ER_NO_SUCH_THREAD, "Unknown connection id:" + id);
            }
        }
    }

    private static OkPacket getOkPacket() {
        OkPacket packet = new OkPacket();
        packet.packetId = 1;
        packet.affectedRows = 0;
        packet.serverStatus = 2;
        return packet;
    }

}
