package org.ng12306.ngsql.server;

import org.ng12306.ngsql.net.handler.FrontendQueryHandler;
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
		// TODO Auto-generated method stub
		
	}
}
