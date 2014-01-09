package org.ng12306.ngsql;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicBoolean;

import org.ng12306.ngsql.config.model.SystemConfig;
import org.ng12306.ngsql.net.NIOAcceptor;
import org.ng12306.ngsql.net.NIOConnector;
import org.ng12306.ngsql.net.NIOProcessor;
import org.ng12306.ngsql.server.ServerConnectionFactory;
import org.ng12306.ngsql.util.ExecutorUtil;
import org.ng12306.ngsql.util.NameableExecutor;
import org.ng12306.ngsql.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author <a href="mailto:wenjie.0617@gmail.com">wuwj-cn</a>
 */
public class NgSQLServer {
	private static final Logger log = LoggerFactory.getLogger(NgSQLServer.class);
	private static final NgSQLServer INSTANCE = new NgSQLServer();
	private static final String NAME = "ngSQL";
	
	public static final NgSQLServer getInstance() {
		return INSTANCE;
	}

	private final NgSQLConfig config;
    private final Timer timer;
    private final NameableExecutor managerExecutor;
    private final NameableExecutor timerExecutor;
    private final NameableExecutor initExecutor;
    private final AtomicBoolean isOnline;
    private final long startupTime;
    private NIOProcessor[] processors;
    private NIOConnector connector;
    private NIOAcceptor manager;
    private NIOAcceptor server;
    
	private NgSQLServer(){
		this.config = new NgSQLConfig();
        SystemConfig system = config.getSystem();
        this.timer = new Timer(NAME + "Timer", true);
        this.initExecutor = ExecutorUtil.create("InitExecutor", system.getInitExecutor());
        this.timerExecutor = ExecutorUtil.create("TimerExecutor", system.getTimerExecutor());
        this.managerExecutor = ExecutorUtil.create("ManagerExecutor", system.getManagerExecutor());
        this.isOnline = new AtomicBoolean(true);
        this.startupTime = TimeUtil.currentTimeMillis();
	}
	
    public NgSQLConfig getConfig() {
        return config;
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
	
	public void startup() throws IOException {
		// server startup
		log.info("===============================================");
		log.info(NAME + " is ready to startup ...");
		
		SystemConfig system = config.getSystem();
		
		// startup processors
		log.info("Startup processors ...");
        int handler = system.getProcessorHandler();
        int executor = system.getProcessorExecutor();
        processors = new NIOProcessor[system.getProcessors()];
        for (int i = 0; i < processors.length; i++) {
            processors[i] = new NIOProcessor("Processor" + i, handler, executor);
            processors[i].startup();
        }
        
        // startup connector
        log.info("Startup connector ...");
        connector = new NIOConnector(NAME + "Connector");
        connector.setProcessors(processors);
        connector.start();
        
        // startup server
        ServerConnectionFactory sf = new ServerConnectionFactory();
        sf.setCharset(system.getCharset());
        sf.setIdleTimeout(system.getIdleTimeout());
        server = new NIOAcceptor(NAME + "Server", system.getServerPort(), sf);
        server.setProcessors(processors);
        server.start();
        
        // server started
        log.info(server.getName() + " is started and listening on " + server.getPort());
        log.info("===============================================");
	}
}
