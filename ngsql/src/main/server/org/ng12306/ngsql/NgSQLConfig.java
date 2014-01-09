package org.ng12306.ngsql;

import org.ng12306.ngsql.config.model.SystemConfig;


/**
 * 
 * @author <a href="mailto:wenjie.0617@gmail.com">wuwj-cn</a>
 */
public class NgSQLConfig {
    private static final int RELOAD = 1;
    private static final int ROLLBACK = 2;
    
    private volatile SystemConfig system;

    public NgSQLConfig() {
        system = new SystemConfig();
    }

    public SystemConfig getSystem() {
        return system;
    }
}
