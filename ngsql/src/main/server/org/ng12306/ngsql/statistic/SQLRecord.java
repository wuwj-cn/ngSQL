package org.ng12306.ngsql.statistic;

/**
 * 
 * @author <a href="mailto:wenjie.0617@gmail.com">wuwj-cn</a>
 */
public final class SQLRecord implements Comparable<SQLRecord> {

    public String host;
    public String schema;
    public String statement;
    public long startTime;
    public long executeTime;
    public String dataNode;
    public int dataNodeIndex;

    @Override
    public int compareTo(SQLRecord o) {
        return (int) (executeTime - o.executeTime);
    }

}
