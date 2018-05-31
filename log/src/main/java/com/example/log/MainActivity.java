package com.example.log;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import greendao.User;
import greendao.UserDao;
import sqldb.db.DBoperating;

public class MainActivity extends Activity {
    private Button bt_create, bt_insert, bt_update, bt_del, bt_query, bt_del_all;
    private TextView tv_query, tv_code;
    UserDao userDao;
    private String create = "{\n" +
            "   \"cid\" : 111,\n" +
            "   \"method\" : \"db.create\",\n" +
            "   \"query\" : {\n" +
            "       \"sql\" : \"create table usertable(_id integer primary key autoincrement,name text,number text,age integer)\"\n" +
            "    }\n" +
            "}";
    private String add = "{\n" +
            "   \"cid\" : 111,\n" +
            "   \"method\" : \"db.insert\",\n" +
            "   \"query\" : {\n" +
            "       \"sql\" : \"insert into usertable(name,number,age) values('xiaoming','01005',10)\"\n" +
            "    }\n" +
            "}";
    private String delete = "{\n" +
            "   \"cid\" : 111,\n" +
            "   \"method\" : \"db.delete\",\n" +
            "   \"query\" : {\n" +
            "       \"sql\" : \"delete from usertable where _id = 6\"\n" +
            "    }\n" +
            "}";
    private String update = "{\n" +
            "   \"cid\" : 111,\n" +
            "   \"method\" : \"db.update\",\n" +
            "   \"query\" : {\n" +
            "       \"sql\" : \"update usertable set number = '13' where _id = 1\"\n" +
            "    }\n" +
            "}";
    private String query = "{\n" +
            "   \"cid\" : 111,\n" +
            "   \"method\" : \"db.select\",\n" +
            "   \"query\" : {\n" +
            "       \"sql\" : \"SELECT * FROM usertable where _id != 1\"\n" +
            "    }\n" +
            "}";
    private String drop = "{\n" +
            "   \"cid\" : 111,\n" +
            "   \"method\" : \"db.drop\",\n" +
            "   \"query\" : {\n" +
            "       \"sql\" : \"DROP TABLE usertable\"\n" +
            "    }\n" +
            "}";
    private String alter = "{\n" +
            "   \"cid\" : 111,\n" +
            "   \"method\" : \"db.alter\",\n" +
            "   \"query\" : {\n" +
            "       \"sql\" : \"ALTER TABLE user RENAME TO usertable  \"\n" +
            "    }\n" +
            "}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvnt();
    }

    private void initView() {
        bt_create = (Button) findViewById(R.id.bt_create);
        bt_insert = (Button) findViewById(R.id.bt_insert);
        bt_update = (Button) findViewById(R.id.bt_update);
        bt_del = (Button) findViewById(R.id.bt_del);
        bt_del_all = (Button) findViewById(R.id.bt_del_all);
        bt_query = (Button) findViewById(R.id.bt_query);
        tv_query = (TextView) findViewById(R.id.tv_query);
        tv_code = (TextView) findViewById(R.id.tv_code);
        userDao = BaseApplication.getInstance().getDaoSession().getUserDao();
    }

    private void initEvnt() {
        bt_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = DBoperating.getIntences().operatingDatabase(create, MainActivity.this);
                Log.e("GREEN", "数据" + s);
            }
        });
        bt_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = DBoperating.getIntences().operatingDatabase(add, MainActivity.this);
//                User user = new User();
//                user.setName("猪");
//                user.setAge("12");
//                user.setContent("我是猪！");
//                User user1 = new User();
//                user1.setName("鸡");
//                user1.setAge("10");
//                user1.setContent("我是鸡！");
//                User user2 = new User();
//                user2.setName("鸭");
//                user2.setAge("10");
//                user2.setContent("我是鸭！");
//                try {
//                    userDao.insert(user);
//                    userDao.insert(user1);
//                    userDao.insert(user2);
//                } catch (Exception ex) {
//
//                }
                tv_code.setText("instert");
            }
        });
        bt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                try {
//                   /* //单一满足条件查询
//                    User user = userDao.queryBuilder()
//                            .where(UserDao.Properties.Age.ge(12)).build().unique();
//                    if (user != null) {
//                        user.setName("虾");
//                        userDao.update(user);
//                    }*/
//                    List<User> list = userDao.queryBuilder()
//                            .where(UserDao.Properties.Age.ge(12)).build().list();
//                    for (int i = 0; i < list.size(); i++) {
//                        list.get(i).setName("虾");
//                    }
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
                tv_code.setText("update where key = 0");
            }
        });
        bt_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                userDao.deleteByKey((long) 1);
                tv_code.setText("delete where key = 0");
            }
        });
        bt_del_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                userDao.deleteAll();
            }
        });
        bt_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String text = "";
//                List<User> users = userDao.loadAll();
//                for (int i = 0; i < users.size(); i++) {
//                    text = text + "姓名：" + users.get(i).getName() + "\n" + "年龄：" + users.get(i).getAge() + "\n" + "自我介绍：" + users.get(i).getContent() + "\n";
//                }
                String s = DBoperating.getIntences().operatingDatabase(query, MainActivity.this);
                tv_query.setText(s);
                tv_code.setText("query");
            }
        });
    }

    public class DBManager {
        public long insert(UserDao userDao, User bean) {
            long insert = 0;
            insert = userDao.insert(bean);
            return insert;
        }

        public void del(UserDao userDao, User bean) {
            userDao.delete(bean);
            userDao.deleteAll();
            userDao.deleteByKey(Long.parseLong("1"));
        }

        public void update(UserDao userDao, User bean) {
            userDao.update(bean);
        }

        public void query(UserDao userDao) {
            List<User> users = userDao.loadAll();
        }
    }
}
