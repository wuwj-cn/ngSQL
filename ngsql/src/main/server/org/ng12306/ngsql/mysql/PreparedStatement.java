package org.ng12306.ngsql.mysql;

/**
 * 
 * @author <a href="mailto:wenjie.0617@gmail.com">wuwj-cn</a>
 */
public class PreparedStatement {

    private long id;
    private String statement;
    private int columnsNumber;
    private int parametersNumber;
    private int[] parametersType;

    public PreparedStatement(long id, String statement, int columnsNumber, int parametersNumber) {
        this.id = id;
        this.statement = statement;
        this.columnsNumber = columnsNumber;
        this.parametersNumber = parametersNumber;
        this.parametersType = new int[parametersNumber];
    }

    public long getId() {
        return id;
    }

    public String getStatement() {
        return statement;
    }

    public int getColumnsNumber() {
        return columnsNumber;
    }

    public int getParametersNumber() {
        return parametersNumber;
    }

    public int[] getParametersType() {
        return parametersType;
    }

}
