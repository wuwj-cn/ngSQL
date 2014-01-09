package org.ng12306.ngsql.util;

/**
 * 弱精度的计时器，考虑性能不使用同步策略。
 * @author <a href="mailto:wenjie.0617@gmail.com">wuwj-cn</a>
 */
public class TimeUtil {
    private static long CURRENT_TIME = System.currentTimeMillis();

    public static final long currentTimeMillis() {
        return CURRENT_TIME;
    }

    public static final void update() {
        CURRENT_TIME = System.currentTimeMillis();
    }

}
