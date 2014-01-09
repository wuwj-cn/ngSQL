package org.ng12306.ngsql.net;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;

/**
 * 
 * @author <a href="mailto:wenjie.0617@gmail.com">wuwj-cn</a>
 */
public interface NIOConnection {

    /**
     * 注册网络事件
     */
    void register(Selector selector) throws IOException;

    /**
     * 读取数据
     */
    void read() throws IOException;

    /**
     * 处理数据
     */
    void handle(byte[] data);

    /**
     * 写出一块缓存数据
     */
    void write(ByteBuffer buffer);

    /**
     * 基于处理器队列的方式写数据
     */
    void writeByQueue() throws IOException;

    /**
     * 基于监听事件的方式写数据
     */
    void writeByEvent() throws IOException;

    /**
     * 发生错误
     */
    void error(int errCode, Throwable t);

    /**
     * 关闭连接
     */
    boolean close();

}
