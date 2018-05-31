package sqldb.db;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * 类：JsonUtil JSON工具类
 * 作者： qxc
 * 日期：2017/4/19.
 */
public class JsonUtil {
    /**
     * 解析出错时，返回的默认数据
     */
    public static int defaultErrorNum = 0;

    /**
     * 判断一个字符串是否是json格式
     * @param jsonStr 字符串
     * @return 如果是，返回true；否则，返回false
     */
    public static boolean isJson(String jsonStr){
        if(jsonStr==null || jsonStr.length()==0){
            return false;
        }
        try{
            JSONTokener jsonTokener = new JSONTokener(jsonStr);
            Object object = jsonTokener.nextValue();
            if(object instanceof JSONObject || object instanceof JSONArray){
                return true;
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * 获得JSONObject
     *
     * @param jsonObject Json对象
     * @param key        key
     * @return 如果存在，则解析并返回Json对象，否则，创建新的Json对象并返回
     */
    public static JSONObject getJsonObject(JSONObject jsonObject, String key) {
        if (jsonObject != null && !jsonObject.isNull(key)) {
            try {
                return jsonObject.getJSONObject(key);
            } catch (Exception ex) {
                return new JSONObject();
            }
        }
        return new JSONObject();
    }

    /**
     * 获得JSONArray
     *
     * @param jsonObject Json对象
     * @param key        key
     * @return 如果存在，则解析并返回JSONObject，否则，创建新的JSONObject并返回
     */
    public static JSONArray getJSONArray(JSONObject jsonObject, String key) {
        if (jsonObject != null && !jsonObject.isNull(key)) {
            try {
                return jsonObject.getJSONArray(key);
            } catch (Exception ex) {
                return new JSONArray();
            }
        }
        return new JSONArray();
    }

    /**
     * 获得Json字符串
     *
     * @param jsonObject Json对象
     * @param key        key
     * @return 如果存在，则解析并返回结果，否则，返回""
     */
    public static String getString(JSONObject jsonObject, String key) {
        if (jsonObject != null && !jsonObject.isNull(key)) {
            try {
                return jsonObject.getString(key).trim();
            } catch (Exception ex) {
                return "";
            }
        }
        return "";
    }

    /**
     * 获得Json整形数值
     *
     * @param jsonObject Json对象
     * @param key        key
     * @return 如果存在，则解析并返回结果，否则，返回errorNum
     */
    public static int getInt(JSONObject jsonObject, String key) {
        if ("error_code".equals(key)) {
            defaultErrorNum = -1;
        }else{
            defaultErrorNum = 0;
        }
        if (jsonObject != null && !jsonObject.isNull(key)) {
            try {
                return jsonObject.getInt(key);
            } catch (Exception ex) {
                return defaultErrorNum;
            }
        }
        return defaultErrorNum;
    }

    /**
     * 获得Json浮点数数值
     *
     * @param jsonObject Json对象
     * @param key        key
     * @return 如果存在，则解析并返回结果，否则，返回errorNum
     */
    public static double getDouble(JSONObject jsonObject, String key) {
        if (jsonObject != null && !jsonObject.isNull(key)) {
            try {
                return jsonObject.getDouble(key);
            } catch (Exception ex) {
                return defaultErrorNum;
            }
        }
        return defaultErrorNum;
    }

    /**
     * 获得Json长整形数值
     *
     * @param jsonObject Json对象
     * @param key        key
     * @return 如果存在，则解析并返回结果，否则，返回errorNum
     */
    public static long getLong(JSONObject jsonObject, String key) {
        if (jsonObject != null && !jsonObject.isNull(key)) {
            try {
                return jsonObject.getLong(key);
            } catch (Exception ex) {
                return defaultErrorNum;
            }
        }
        return defaultErrorNum;
    }

    /**
     * 获得Json布尔值
     *
     * @param jsonObject Json对象
     * @param key        key
     * @return 如果存在，则解析并返回结果，否则，返回false
     */
    public static boolean getBoolean(JSONObject jsonObject, String key) {
        if (jsonObject != null && !jsonObject.isNull(key)) {
            try {
                return jsonObject.getBoolean(key);
            } catch (Exception ex) {
                return false;
            }
        }
        return false;
    }
}
