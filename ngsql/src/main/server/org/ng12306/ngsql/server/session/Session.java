package org.ng12306.ngsql.server.session;

import org.ng12306.ngsql.net.FrontendConnection;

/**
 * 
 * @author <a href="mailto:wenjie.0617@gmail.com">wuwj-cn</a>
 */
public interface Session {

    /**
     * 取得源端连接
     */
    FrontendConnection getSource();

    /**
     * 取得当前目标端数量
     */
    int getTargetCount();

    /**
     * 开启一个会话执行
     */
//    void execute(RouteResultset rrs, int type);

    /**
     * 提交一个会话执行
     */
    void commit();

    /**
     * 回滚一个会话执行
     */
    void rollback();

    /**
     * 取消一个正在执行中的会话
     * 
     * @param sponsor
     *            如果发起者为null，则表示由自己发起。
     */
    void cancel(FrontendConnection sponsor);

    /**
     * 终止会话，必须在关闭源端连接后执行该方法。
     */
    void terminate();

}
