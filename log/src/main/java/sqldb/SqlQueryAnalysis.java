package sqldb;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 描述：SQL查询
 * 作者：小辉
 * 时间：2018/01/25
 */

public class SqlQueryAnalysis {
    public SqlQueryAnalysis() {
    }

    private static volatile SqlQueryAnalysis sqlQueryAnalysis;

    //安全的单例模式
    public static SqlQueryAnalysis getSqlQueryAnalysis() {
        if (sqlQueryAnalysis == null) {
            synchronized (SqlQueryAnalysis.class) {
                if (sqlQueryAnalysis == null) {
                    sqlQueryAnalysis = new SqlQueryAnalysis();
                }
            }
        }
        return sqlQueryAnalysis;
    }

    public String SqlQueryTable(SQLiteDatabase db, String sql, int cid, String method) {
        String result = "";
        //查询获得游标
        Cursor cursor = db.query(sql, null, null, null, null, null, null);
        //判断游标是否为空
        if (cursor.moveToFirst()) {
            JSONArray jsonArray = new JSONArray();
            //遍历游标
            for (int i = 0; i < cursor.getCount(); i++) {
                String[] columnNames = cursor.getColumnNames();
                try {
                    JSONObject jsonObject = new JSONObject();
                    for (int j = 0; j < columnNames.length; j++) {
                        jsonObject.put(columnNames[j], getValueData(cursor, j));
                    }
                    jsonArray.put(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //输出用户信息
                cursor.move(1);
            }
            result = jsonArray.toString();
            cursor.close();
        } else {
            result = null;
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

    /**
     * 从cursor中取出数据
     *
     * @param cursor
     * @param count
     * @return
     */
    private Object getValueData(Cursor cursor, int count) {
        int type = cursor.getType(count);
        if (type == 0) {
            return null;
        } else if (type == 1) {
            return cursor.getInt(count);
        } else if (type == 2) {
            return cursor.getFloat(count);
        } else if (type == 3) {
            return cursor.getString(count);
        } else {
            return cursor.getBlob(count);
        }
    }
}
