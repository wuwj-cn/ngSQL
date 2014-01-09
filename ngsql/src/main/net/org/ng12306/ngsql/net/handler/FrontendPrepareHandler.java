package org.ng12306.ngsql.net.handler;

/**
 * SQL预处理处理器
 * 
 * @author xianmao.hexm 2012-8-28
 */
public interface FrontendPrepareHandler {

    void prepare(String sql);

    void execute(byte[] data);

    void close();

}
