package sqldb;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.insert.Insert;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：解析SQL的添加语句
 * 作者：小辉
 * 时间：2018/01/25
 */

public class SqlInsertAnalysis {
    public SqlInsertAnalysis() {
    }

    private static volatile SqlInsertAnalysis sqlInsertAnalysis;

    //安全的单例模式
    public static SqlInsertAnalysis getSqlInsertAnalysis() {
        if (sqlInsertAnalysis == null) {
            synchronized (SqlInsertAnalysis.class) {
                if (sqlInsertAnalysis == null) {
                    sqlInsertAnalysis = new SqlInsertAnalysis();
                }
            }
        }
        return sqlInsertAnalysis;
    }

    /**
     * 数据库插入数据并返回值
     *
     * @param db
     * @param sql
     * @return error：错误；success：成功；其他
     */
    public String SqlInsert(SQLiteDatabase db, String sql, int cid, String method) {
        String result = "";
        JSONObject jsonObject = new JSONObject();
        try {
            String tableName = insert_table(sql);
            List<String> keys = insert_key(sql);
            List<String> values = insert_value(sql);
            //实例化常量值
            ContentValues cValue = new ContentValues();
            for (int i = 0; i < keys.size(); i++) {
                cValue.put(keys.get(i), values.get(i));
            }
            db.beginTransaction(); // 开始事务
            long tableName1 = db.insert(tableName, null, cValue);
            db.setTransactionSuccessful(); // 设置事务成功完成
            if (tableName1 == -1) {//出错了
                result = "error";
            } else {//没问题
                result = "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = getJsonObject(e);
        } finally {
            db.endTransaction(); // 结束事务
        }
        try {
            jsonObject.put("cid", cid);
            jsonObject.put("method", method);
            jsonObject.put("result", result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    /**
     * 解析插入数据得到表名
     *
     * @param sql
     * @return 插入数据的表名
     * @throws JSQLParserException
     */
    private String insert_table(String sql)
            throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse(sql);
        Insert insertStatement = (Insert) statement;
        String string_tablename = insertStatement.getTable().getName();
        return string_tablename;
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
     * 解析插入数据得到key集合
     *
     * @param sql
     * @return key集合
     * @throws JSQLParserException
     */
    private List<String> insert_key(String sql)
            throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse(sql);
        Insert insertStatement = (Insert) statement;
        List<Column> table_column = insertStatement.getColumns();
        List<String> str_column = new ArrayList<String>();
        for (int i = 0; i < table_column.size(); i++) {
            str_column.add(table_column.get(i).toString());
        }
        return str_column;
    }

    /**
     * 解析插入数据得到value集合
     *
     * @param sql
     * @return value集合
     * @throws JSQLParserException
     */
    private List<String> insert_value(String sql)
            throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse(sql);
        Insert insertStatement = (Insert) statement;
        List<Expression> insert_values_expression = ((ExpressionList) insertStatement
                .getItemsList()).getExpressions();
        List<String> str_values = new ArrayList<String>();
        for (int i = 0; i < insert_values_expression.size(); i++) {
            str_values.add(insert_values_expression.get(i).toString());
        }
        return str_values;
    }

}
