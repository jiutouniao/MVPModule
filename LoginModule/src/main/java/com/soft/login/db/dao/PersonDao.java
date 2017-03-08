package com.soft.login.db.dao;

import android.content.Context;
import android.util.Log;
import com.j256.ormlite.stmt.UpdateBuilder;
import debug.LoginApplication;
import com.soft.login.db.dto.PersonDto;
import com.soft.login.db.helper.DatabaseHelper;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * description: Person数据库操作
 * Date: 2016/9/8 17:59
 * User: shaobing
 */
public class PersonDao
{
    private static final String TAG = "PersonDao";
    private static Context mContext = LoginApplication.getAppContext() ;
    /**
     * 添加数据
     * @param user  add
      */
    public static void  add(PersonDto user) {
        try {
            DatabaseHelper.getHelper(mContext).getPersonDao().create(user);
        } catch (SQLException e) {
            Log.e(TAG," add :",e);
        }
    }

    /**
     * 获取所有的数据
     * @return data
     */
    public static List<PersonDto> getAll() {
        List<PersonDto> persons = new ArrayList<>();
        try {
            DatabaseHelper helper = DatabaseHelper.getHelper(mContext);
            try {
                persons = helper.getPersonDao().queryForAll();
                Log.e("TAG", persons.toString());
            } catch (SQLException e) {
                e.printStackTrace();
                Log.e(TAG," getAll :"+e.fillInStackTrace());
            }
        } catch (Exception e) {
            Log.e(TAG," getAll :",e);
        }
        return  persons;
    }

    /**
     * 获取所有的数据
     * @return data
     */
    public static List<PersonDto> getAllByName(String name ) {
        DatabaseHelper helper = DatabaseHelper.getHelper(mContext);
        List<PersonDto> persons = null;
        try {
            persons = helper.getPersonDao().queryBuilder()
                    .where().eq("name",name)
                    .query();
            Log.e("TAG", persons.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e(TAG," getAllByName :",e);
        }
        return  persons;
    }


    /**
     * 删除所有的数据
     */
    public static void deleteAll() {
        DatabaseHelper helper = DatabaseHelper.getHelper(mContext);
        try {
            helper.getPersonDao().deleteBuilder().delete();
        } catch (SQLException e) {
            Log.e(TAG," deleteAll :",e);
        }
    }
    /**
     * 更新数据
     * @param person 更新参数和条件
     * @return  -1代表更新失败  1.代表更新成功
     */
    public static int updateById(PersonDto person) {
        DatabaseHelper helper = DatabaseHelper.getHelper(mContext);
        try {
            UpdateBuilder<PersonDto, Integer> updateBuilder= helper.getPersonDao().updateBuilder();
            updateBuilder
                    .updateColumnValue("name",person.getName())
                    .updateColumnValue("desc",person.getDesc())
                    .where()
                    .eq("id",person.getId());
            return  updateBuilder.update();
        } catch (SQLException e) {
            Log.e(TAG," updateById :",e);
        }
        return  -1;
    }

}