package sqldb;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 描述：解析SQL的删除语句
 * 作者：小辉
 * 时间：2018/01/25
 */

public class SqlDeteleAnalysis {
    public SqlDeteleAnalysis() {
    }

    private static volatile SqlDeteleAnalysis sqlDeteleAnalysis;

    //安全的单例模式
    public static SqlDeteleAnalysis getSqlDeleteAnalysis() {
        if (sqlDeteleAnalysis == null) {
            synchronized (SqlDeteleAnalysis.class) {
                if (sqlDeteleAnalysis == null) {
                    sqlDeteleAnalysis = new SqlDeteleAnalysis();
                }
            }
        }
        return sqlDeteleAnalysis;
    }

    /**
     * 删除数据,并返回值
     *
     * @param db
     * @param sql
     * @return error：错误；success：成功；其他
     */
    public String SqlDelete(SQLiteDatabase db, String sql, int cid, String method) {
        String result = "";
        try {
            String tableName = delete_table(sql);
            String str_where = delete_where(sql);

            //修改条件
            String whereClause = str_where.substring(0, str_where.indexOf("=")) + "=?";
            Object whereArg = str_where.substring(str_where.indexOf("=") + 1, str_where.length());
            //修改添加参数
            String[] whereArgs = {String.valueOf(whereArg)};

            //执行删除
            int delete = db.delete(tableName, whereClause, whereArgs);
            //修改
            if (delete == 1) {//出错了
                result = "error";
            } else if (delete == 0) {//正确
                result = "success";
            }
        } catch (JSQLParserException e) {
            e.printStackTrace();
            result = getJsonObject(e);
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("cid", cid);
            jsonObject.put("method", method);
            jsonObject.put("result", result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
    @NonNull
    private String getJsonObject(Exception e) {
        JSONObject json = new JSONObject();
        try {
            json.put("异常类型", "Exception");
            json.put("异常信息", e.getMessage());
            JSONArray jsonArray = new JSONArray();
            //异常信息收集：异常消息、异常堆栈追踪
            StackTraceElement[] stackTraces = e.getStackTrace();
            for (StackTraceElement ste : stackTraces) {
                jsonArray.put(ste.toString());
            }
            json.put("异常堆栈追踪", jsonArray);
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return json.toString();
    }
    /**
     * 解析删除语句的表名
     *
     * @param sql
     * @return 要删除的表名
     * @throws JSQLParserException
     */
    public static String delete_table(String sql) throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse(sql);
        Delete updateStatement = (Delete) statement;
        Table table = updateStatement.getTable();
        return table.getName();
    }

    /**
     * 解析删除语句的条件
     *
     * @param sql
     * @return 删除的条件
     * @throws JSQLParserException
     */
    public static String delete_where(String sql)
            throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse(sql);
        Delete updateStatement = (Delete) statement;
        Expression where_expression = updateStatement.getWhere();
        String str = where_expression.toString();
        return str;
    }
}
