package org.ng12306.ngsql.net.mysql;

/**
 * @author xianmao.hexm
 */
public class QuitPacket extends MySQLPacket {
    public static final byte[] QUIT = new byte[] { 1, 0, 0, 0, 1 };

    @Override
    public int calcPacketSize() {
        return 1;
    }

    @Override
    protected String getPacketInfo() {
        return "MySQL Quit Packet";
    }

}
