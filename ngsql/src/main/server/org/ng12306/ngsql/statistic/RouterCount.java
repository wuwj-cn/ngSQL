package org.ng12306.ngsql.statistic;

/**
 * 
 * @author <a href="mailto:wenjie.0617@gmail.com">wuwj-cn</a>
 */
public final class RouterCount {

    private long routeCount;
    private long timeCount;
    private long maxRouteTime;
    private long maxRouteSQL;

    public void doRoute(long sqlId, long time) {
        routeCount++;
        timeCount += time;
        if (time > maxRouteTime) {
            maxRouteTime = time;
            maxRouteSQL = sqlId;
        }
    }

    public long getRouteCount() {
        return routeCount;
    }

    public long getTimeCount() {
        return timeCount;
    }

    public long getMaxRouteTime() {
        return maxRouteTime;
    }

    public long getMaxRouteSQL() {
        return maxRouteSQL;
    }

}
