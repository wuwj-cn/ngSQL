package org.ng12306.ngsql.statistic;

/**
 * 
 * @author <a href="mailto:wenjie.0617@gmail.com">wuwj-cn</a>
 */
public final class ParserCount {

    private long parseCount;
    private long timeCount;
    private long maxParseTime;
    private long maxParseSQL;
    private long cachedCount;
    private int cacheSizeCount;

    public void doParse(long sqlId, long time) {
        parseCount++;
        timeCount += time;
        if (time > maxParseTime) {
            maxParseTime = time;
            maxParseSQL = sqlId;
        }
    }

    public long getParseCount() {
        return parseCount;
    }

    public long getTimeCount() {
        return timeCount;
    }

    public long getMaxParseTime() {
        return maxParseTime;
    }

    public long getMaxParseSQL() {
        return maxParseSQL;
    }

    public void doCached() {
        cachedCount++;
    }

    public long getCachedCount() {
        return cachedCount;
    }

    public void setCacheSizeCount(int cacheSizeCount) {
        this.cacheSizeCount = cacheSizeCount;
    }

    public int getCacheSizeCount() {
        return cacheSizeCount;
    }

}
