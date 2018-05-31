package sqldb.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 描述：数据库操作管理类
 * 作者：小辉
 * 时间：2018/05/24
 */

public class DBoperating {
    public static DBoperating dBoperating;

    public static DBoperating getIntences() {
        if (dBoperating == null) {
            synchronized (DBoperating.class) {
                if (dBoperating == null) {
                    dBoperating = new DBoperating();
                }
            }

        }
        return dBoperating;
    }

    /**
     * 操作数据库
     *
     * @param paramsData
     */
    public String operatingDatabase(String paramsData, Context context) {
        String result = "";
        try {
            JSONObject jo = new JSONObject(paramsData);
            int cid = JsonUtil.getInt(jo, "cid");
            String method = JsonUtil.getString(jo, "method");
            JSONObject query = JsonUtil.getJsonObject(jo, "query");
            if (method.startsWith("db.")) {
                String sql = JsonUtil.getString(query, "sql");
                SQLbaseHelper sqLbaseHelper = new SQLbaseHelper(context);
                SQLiteDatabase sqLiteDatabase = sqLbaseHelper.getReadableDatabase();
                result = SqlAnalysisManager.getIntences().execSQL(sqLiteDatabase, cid, method, sql);
            } else if (method.startsWith("fs.")) {
            }
        } catch (Exception ex) {
            result = getJsonObject(ex);
        }
        return result;
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
}
