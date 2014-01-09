package org.ng12306.ngsql.config;

/**
 * 事务隔离级别定义
 * @author <a href="mailto:wenjie.0617@gmail.com">wuwj-cn</a>
 */
public interface Isolations {

    int READ_UNCOMMITTED = 1;
    int READ_COMMITTED = 2;
    int REPEATED_READ = 3;
    int SERIALIZABLE = 4;

}
