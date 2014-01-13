package org.ng12306.ngsql.server.handler;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.ng12306.ngsql.config.ErrorCode;
import org.ng12306.ngsql.mysql.ByteUtil;
import org.ng12306.ngsql.mysql.PreparedStatement;
import org.ng12306.ngsql.net.handler.FrontendPrepareHandler;
import org.ng12306.ngsql.net.mysql.ExecutePacket;
import org.ng12306.ngsql.server.ServerConnection;

/**
 * 
 * @author <a href="mailto:wenjie.0617@gmail.com">wuwj-cn</a>
 */
public class ServerPrepareHandler implements FrontendPrepareHandler {

    private ServerConnection source;
    private volatile long pstmtId;
    private Map<String, PreparedStatement> pstmtForSql;
    private Map<Long, PreparedStatement> pstmtForId;

    public ServerPrepareHandler(ServerConnection source) {
        this.source = source;
        this.pstmtId = 0L;
        this.pstmtForSql = new HashMap<String, PreparedStatement>();
        this.pstmtForId = new HashMap<Long, PreparedStatement>();
    }

    @Override
    public void prepare(String sql) {
        PreparedStatement pstmt = null;
        if ((pstmt = pstmtForSql.get(sql)) == null) {
            pstmt = new PreparedStatement(++pstmtId, sql, 0, 0);
            pstmtForSql.put(pstmt.getStatement(), pstmt);
            pstmtForId.put(pstmt.getId(), pstmt);
        }
//        PreparedStmtResponse.response(pstmt, source);
    }

    @Override
    public void execute(byte[] data) {
        long pstmtId = ByteUtil.readUB4(data, 5);
        PreparedStatement pstmt = null;
        if ((pstmt = pstmtForSql.get(pstmtId)) == null) {
            source.writeErrMessage(ErrorCode.ER_ERROR_WHEN_EXECUTING_COMMAND, "Unknown pstmtId when executing.");
        } else {
            ExecutePacket packet = new ExecutePacket(pstmt);
            try {
                packet.read(data, source.getCharset());
            } catch (UnsupportedEncodingException e) {
                source.writeErrMessage(ErrorCode.ER_ERROR_WHEN_EXECUTING_COMMAND, e.getMessage());
                return;
            }
            // TODO ...
        }
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub

    }

}
