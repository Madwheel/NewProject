package sqldb;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.drop.Drop;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 描述：SQL删除表
 * 作者：小辉
 * 时间：2018/01/25
 */

public class SqlDropAnalysis {
    public SqlDropAnalysis() {
    }

    private static volatile SqlDropAnalysis sqlDropAnalysis;

    //安全的单例模式
    public static SqlDropAnalysis getSqlDropAnalysis() {
        if (sqlDropAnalysis == null) {
            synchronized (SqlDropAnalysis.class) {
                if (sqlDropAnalysis == null) {
                    sqlDropAnalysis = new SqlDropAnalysis();
                }
            }
        }
        return sqlDropAnalysis;
    }

    public String SqlDeleteTable(SQLiteDatabase db, String sql, int cid, String method) {
        String result = "";
        try {
            //执行SQL语句
            db.execSQL(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String table = drop_table(sql);
            boolean isExist = sqlTableIsExist(db, table);
            if (!isExist) {
                result = "success";
            } else {
                result = "error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            result =getJsonObject(e);
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
     * 判断数据库中某张表是否存在
     */
    private boolean sqlTableIsExist(SQLiteDatabase db, String tableName) {
        boolean result = false;
        if (tableName == null) {
            return false;
        }
        Cursor cursor = null;
        try {
            //search.db数据库的名字
            String sql = "select count(*) as c from Sqlite_master  where type ='table' and name ='" + tableName.trim() + "' ";
            cursor = db.rawQuery(sql, null);
            if (cursor.moveToNext()) {
                int count = cursor.getInt(0);
                if (count > 0) {
                    result = true;
                }
            }

        } catch (Exception e) {
        }
        return result;
    }

    /**
     * 解析创建表语句的表名
     *
     * @param sql
     * @return 要创建的表名
     * @throws JSQLParserException
     */
    public String drop_table(String sql) throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse(sql);
        Drop updateStatement = (Drop) statement;
        String name = updateStatement.getName();
        return name;
    }
}
