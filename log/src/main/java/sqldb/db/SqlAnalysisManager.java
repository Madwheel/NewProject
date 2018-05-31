package sqldb.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import sqldb.SqlAlterAnalysis;
import sqldb.SqlCreateAnalysis;
import sqldb.SqlDeteleAnalysis;
import sqldb.SqlDropAnalysis;
import sqldb.SqlInsertAnalysis;
import sqldb.SqlQueryAnalysis;
import sqldb.SqlUpdateAnalysis;


/**
 * 描述：APP与h5数据传输管理类
 * 作者：小辉
 * 时间：2018/01/25
 */

public class SqlAnalysisManager {
    public static SqlAnalysisManager sqlAnalysisManager;

    public static SqlAnalysisManager getIntences() {
        if (sqlAnalysisManager == null) {
            synchronized (SqlAnalysisManager.class) {
                if (sqlAnalysisManager == null) {
                    sqlAnalysisManager = new SqlAnalysisManager();
                }
            }

        }
        return sqlAnalysisManager;
    }

    /**
     * 执行SQL语句
     *
     * @param db     数据库对象
     * @param method 执行的方法
     * @param cid    调用标识号
     * @param sql    SQL语句
     * @return
     */
    public String execSQL(SQLiteDatabase db, int cid, String method, String sql) {
        String result = "";
        if (method.equals(H5Constants.CREATE_WZAPP)) {//创建表
            result = SqlCreateAnalysis.getSqlCreateAnalysis().SqlCreate(db, sql, cid, method);
        } else if (method.equals(H5Constants.INSERT_WZAPP)) {//添加记录
            result = SqlInsertAnalysis.getSqlInsertAnalysis().SqlInsert(db, sql, cid, method);
        } else if (method.equals(H5Constants.DELETE_WZAPP)) {//删除记录
            result = SqlDeteleAnalysis.getSqlDeleteAnalysis().SqlDelete(db, sql, cid, method);
        } else if (method.equals(H5Constants.DROP_WZAPP)) {//删除表
            result = SqlDropAnalysis.getSqlDropAnalysis().SqlDeleteTable(db, sql, cid, method);
        } else if (method.equals(H5Constants.ALTER_WZAPP)) {//修改表字段名
            result = SqlAlterAnalysis.getSqlAlterAnalysis().SqlAlter(db, sql, cid, method);
        } else if (method.equals(H5Constants.UPDATE_WZAPP)) {//更新记录
            result = SqlUpdateAnalysis.getSqlUpdateAnalysis().updateSql(db, sql, cid, method);
        } else if (method.equals(H5Constants.SELECT_WZAPP)) {//查询记录
            result = SqlQueryAnalysis.getSqlQueryAnalysis().SqlQueryTable(db, sql, cid, method);
        }
        return result;
    }
}
