package org.ng12306.ngsql.server;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NgSQLServer {
	private static final Logger log = LoggerFactory.getLogger(NgSQLServer.class);
	private static final NgSQLServer INSTANCE = new NgSQLServer();
	private static final String NAME = "NgSQL";
	
	public static final NgSQLServer getInstance() {
		return INSTANCE;
	}

	private NgSQLServer(){
		
	}
	
	public void beforeStart(String dateFormat) {
		String home = System.getProperty("ngsql.home");
        if (home == null) {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            log.warn(sdf.format(new Date()) + " [ngsql.home] is not set.");
        } else {
            //读取日志配置
        }
	}
	
	public void startup() {
		log.info("ngsql startup...");
	}
}
