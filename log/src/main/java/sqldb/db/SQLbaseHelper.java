package sqldb.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 描述：APP与H5数据传输帮助类
 * 作者：小辉
 * 时间：2018/1/221138
 */

public class SQLbaseHelper extends SQLiteOpenHelper {
    //数据库版本号
    private static int DATABASE_VISION = 2;
    //数据库名称
//    private static String DATABASE_NAME = "H5.db";
    private static String DATABASE_NAME = "greendao.db";

    /**
     * 构造函数，调用父类SQLiteOpenHelper的构造函数
     *
     * @param context 上下文环境
     * @param name    数据库名字
     * @param factory 游标工厂（可选）
     * @param version 数据库模型版本号
     */
    public SQLbaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * @param context      上下文环境
     * @param name         数据库名字
     * @param factory      游标工厂（可选）
     * @param version      数据库模型版本号
     * @param errorHandler 数据库模型版本号
     */
    public SQLbaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public SQLbaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VISION);
        // 数据库实际被创建是在getWritableDatabase()或getReadableDatabase()方法调用时
        Log.d("SQLiteOpenHelper", "DatabaseHelper Constructor");
        // CursorFactory设置为null,使用系统默认的工厂类
    }

    // 继承SQLiteOpenHelper类,必须要覆写的三个方法：onCreate(),onUpgrade(),onOpen()
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // 调用时间：数据库第一次创建时onCreate()方法会被调用

        // onCreate方法有一个 SQLiteDatabase对象作为参数，根据需要对这个对象填充表和初始化数据
        // 这个方法中主要完成创建数据库后对数据库的操作

//        Log.d("SQLiteOpenHelper", "DatabaseHelper onCreate");
//        // 构建创建表的SQL语句（可以从SQLite Expert工具的DDL粘贴过来加进StringBuffer中）
//
////        String createDB = "CREATE TABLE [" + TABLE_NAME + "] ([_id] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,[name] TEXT,[age] INTEGER, [info] TEXT)";
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("CREATE TABLE [" + TABLE_NAME + "] (");
//        stringBuilder.append("[_id] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
//        stringBuilder.append("[name] TEXT,");
//        stringBuilder.append("[age] INTEGER,");
//        stringBuilder.append("[info] TEXT)");
//        // 执行创建表的SQL语句
//        sqLiteDatabase.execSQL(stringBuilder.toString());
        // 即便程序修改重新运行，只要数据库已经创建过，就不会再进入这个onCreate方法
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // 调用时间：如果DATABASE_VERSION值被改为别的数,系统发现现有数据库版本不同,即会调用onUpgrade

        // onUpgrade方法的三个参数，一个 SQLiteDatabase对象，一个旧的版本号和一个新的版本号
        // 这样就可以把一个数据库从旧的模型转变到新的模型
        // 这个方法中主要完成更改数据库版本的操作

        Log.d("SQLiteOpenHelper", "DatabaseHelper onUpgrade");

//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        // 上述做法简单来说就是，通过检查常量值来决定如何，升级时删除旧表，然后调用onCreate来创建新表
        // 一般在实际项目中是不能这么做的，正确的做法是在更新数据表结构时，还要考虑用户存放于数据库中的数据不丢失
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        // 每次打开数据库之后首先被执行

        Log.d("SQLiteOpenHelper", "DatabaseHelper onOpen");
    }
}
