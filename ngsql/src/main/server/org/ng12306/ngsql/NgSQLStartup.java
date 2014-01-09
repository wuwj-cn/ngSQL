package org.ng12306.ngsql;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ngSQL server启动入口
 * @author <a href="mailto:wenjie.0617@gmail.com">wuwj-cn</a>
 */
public class NgSQLStartup {
	private static final Logger log = LoggerFactory.getLogger(NgSQLStartup.class);
	private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";
	
	public static void main(String[] args) {
        try {
            // init
        	NgSQLServer server = NgSQLServer.getInstance();
            //server.beforeStart(dateFormat);

            // startup
            server.startup();
        } catch (Throwable e) {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            log.error(sdf.format(new Date()) + " startup error", e);
            System.exit(-1);
        }
    }
}
