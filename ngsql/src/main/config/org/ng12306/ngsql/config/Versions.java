package org.ng12306.ngsql.config;

/**
 * 
 * @author <a href="mailto:wenjie.0617@gmail.com">wuwj-cn</a>
 */
public interface Versions {

    /** 协议版本 */
    byte PROTOCOL_VERSION = 10;

    /** 服务器版本 */
    byte[] SERVER_VERSION = "1.0.0-ngsql-1.0.0".getBytes();

}
