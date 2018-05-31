package sqldb;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.update.Update;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：解析SQL的更新语句
 * 作者：小辉
 * 时间：2018/01/25
 */

public class SqlUpdateAnalysis {
    public SqlUpdateAnalysis() {
    }

    private static volatile SqlUpdateAnalysis sqlUpdateAnalysis;

    //安全的单例模式
    public static SqlUpdateAnalysis getSqlUpdateAnalysis() {
        if (sqlUpdateAnalysis == null) {
            synchronized (SqlUpdateAnalysis.class) {
                if (sqlUpdateAnalysis == null) {
                    sqlUpdateAnalysis = new SqlUpdateAnalysis();
                }
            }
        }
        return sqlUpdateAnalysis;
    }

    /**
     * 更新数据库数据，并返回结果
     *
     * @param db
     * @param sql
     * @return error：出现错误；success：成功；其他
     */
    public String updateSql(SQLiteDatabase db, String sql, int cid, String method) {
        String result = "";
        try {
            List<String> tableName = update_table(sql);
            List<String> keys = update_key(sql);
            List<String> values = update_value(sql);
            String str_where = update_where(sql);
            //实例化内容值
            ContentValues value = new ContentValues();
            //在values中添加内容
            for (int i = 0; i < keys.size(); i++) {
                value.put(keys.get(i), values.get(i));
            }
            //修改条件
            String whereClause = str_where.substring(0, str_where.indexOf("=")) + "=?";
            Object whereArg = str_where.substring(str_where.indexOf("=") + 1, str_where.length());
            //修改添加参数
            String[] whereArgs = {String.valueOf(whereArg)};
            //修改
            int usertable = db.update(tableName.get(0), value, whereClause, whereArgs);
            if (usertable == -1) {//出错了
                result = "error";
            } else {//正确
                result = "success";
            }
        } catch (Exception e) {
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
     * 更新数据解析出表名字
     *
     * @param sql
     * @return 表名
     * @throws JSQLParserException
     */
    private List<String> update_table(String sql)
            throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse(sql);
        Update updateStatement = (Update) statement;
        List<Table> update_table = updateStatement.getTables();
        List<String> str_table = new ArrayList<String>();
        if (update_table != null) {
            for (int i = 0; i < update_table.size(); i++) {
                str_table.add(update_table.get(i).toString());
            }
        }
        return str_table;

    }

    /**
     * 更新数据解析出key集合
     *
     * @param sql
     * @return key集合
     * @throws JSQLParserException
     */
    private List<String> update_key(String sql)
            throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse(sql);
        Update updateStatement = (Update) statement;
        List<Column> update_column = updateStatement.getColumns();
        List<String> str_column = new ArrayList<String>();
        if (update_column != null) {
            for (int i = 0; i < update_column.size(); i++) {
                str_column.add(update_column.get(i).toString());
            }
        }
        return str_column;
    }

    /**
     * 更新数据解析出value集合
     *
     * @param sql
     * @return value集合
     * @throws JSQLParserException
     */
    private List<String> update_value(String sql)
            throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse(sql);
        Update updateStatement = (Update) statement;
        List<Expression> update_values = updateStatement.getExpressions();
        List<String> str_values = new ArrayList<String>();
        if (update_values != null) {
            for (int i = 0; i < update_values.size(); i++) {
                str_values.add(update_values.get(i).toString());
            }
        }
        return str_values;

    }

    /**
     * 更新数据解析出条件
     *
     * @param sql
     * @return 条件
     * @throws JSQLParserException
     */
    private String update_where(String sql)
            throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse(sql);
        Update updateStatement = (Update) statement;
        Expression where_expression = updateStatement.getWhere();
        String str = where_expression.toString();
        return str;
    }
}
