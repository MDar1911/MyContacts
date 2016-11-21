package cn.edu.gdmec.a07150841.mycontacts3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.Vector;

/**
 * Created by 马达 on 2016/11/19.
 */
public class ContactsTable {

    private final static String TABLENAME="contactsTable";
    private MyDB db;

    public ContactsTable(Context context){

        db=new MyDB(context);

        if (!db.isTableExits(TABLENAME)){
            String  createTableSql="create Table if not  exists"+TABLENAME+"(id_DB integer "
                    +"primary key autoincrement,"+
                    User.NAME +" VARCHAR,"+
                    User.MOBILE +" VARCHAR,"+
                    User.DANWEI +" VARCHAR,"+
                    User.QQ +" VARCHAR,"+
                    User.ADDRESS +" VARCHAR)";
            db.creatTable(createTableSql);

        }
    }
    public boolean addData(User user){
        ContentValues values = new ContentValues();
        values.put(User.NAME,user.getName());
        values.put(User.MOBILE,user.getMobile());
        values.put(User.DANWEI,user.getDanwei());
        values.put(User.QQ,user.getQq());
        values.put(User.ADDRESS,user.getAddress());
        return db.save(TABLENAME,values);
    }
    public User[] getAllUser(){
        Vector<User> v=new Vector<>();
        Cursor cursor=null;
        try{
            cursor=db.find("select * from "+TABLENAME ,null);
            while (cursor.moveToNext()){
                User temp=new User();
                temp.setId_DB(cursor.getInt(cursor.getColumnIndex("id_DB")));
                temp.setName(cursor.getString(cursor.getColumnIndex(User.NAME)));
                temp.setAddress(cursor.getString(cursor.getColumnIndex(User.ADDRESS)));
                temp.setMobile(cursor.getString(cursor.getColumnIndex(User.MOBILE)));
                temp.setDanwei(cursor.getString(cursor.getColumnIndex(User.DANWEI)));
                temp.setQq(cursor.getString(cursor.getColumnIndex(User.QQ)));
                v.add(temp);
            }

        } catch (Exception e){
            e.printStackTrace();
        }finally{
            if (cursor!=null){
                cursor.close();
            }
            db.closeConnection();
        }
        if (v.size()>0)
        {
            return v.toArray(new User[]{});
        }else {
            User [] users=new User[1];
            User user=new User();
            user.setName("没有结果");
            users[0]=user;
            return users;
        }
    }
    public User getUserByID(int id){
        Cursor cursor=null;
        User temp=new User();
        try{
            cursor=db.find("select * from "+TABLENAME +"where "+
            " id_DB=?",new String[]{id+""});

                cursor.moveToNext();
                temp.setId_DB(cursor.getInt(cursor.getColumnIndex("id_DB")));
                temp.setName(cursor.getString(cursor.getColumnIndex(User.NAME)));
                temp.setAddress(cursor.getString(cursor.getColumnIndex(User.ADDRESS)));
                temp.setMobile(cursor.getString(cursor.getColumnIndex(User.MOBILE)));
                temp.setDanwei(cursor.getString(cursor.getColumnIndex(User.DANWEI)));
                temp.setQq(cursor.getString(cursor.getColumnIndex(User.QQ)));
                Log.d("aa",temp.getName());
                return temp;
            } catch (Exception e){
            e.printStackTrace();
            }finally{
            if (cursor!=null){
                cursor.close();
            }
            db.closeConnection();
        }
        return null;
    }
    public boolean updateUser(User user){
        ContentValues values=new ContentValues();
        values.put(User.NAME,user.getName());
        values.put(User.DANWEI,user.getDanwei());
        values.put(User.MOBILE,user.getMobile());
        values.put(User.ADDRESS,user.getAddress());
        values.put(User.QQ,user.getQq());
        return db.update(TABLENAME,values," id_DB?",new String[]{user.getId_DB()+""});
    }
    public User[] findUserByKey(String key){
        Vector<User> v= new Vector<>();
        Cursor cursor=null;
        try{
            cursor=db.find("select * from "+ TABLENAME +" where "+
            " or "+ User.MOBILE +" like '%"+key +"%' ",null);
            while (cursor.moveToNext()){
                User temp =new User();
                temp.setId_DB(cursor.getInt(cursor.getColumnIndex("id_DB")));
                temp.setName(cursor.getString(cursor.getColumnIndex(User.NAME)));
                temp.setAddress(cursor.getString(cursor.getColumnIndex(User.ADDRESS)));
                temp.setMobile(cursor.getString(cursor.getColumnIndex(User.MOBILE)));
                temp.setDanwei(cursor.getString(cursor.getColumnIndex(User.DANWEI)));
                temp.setQq(cursor.getString(cursor.getColumnIndex(User.QQ)));
                v.add(temp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (cursor!=null){
                cursor.close();
            }
            db.closeConnection();
        }
        if (v.size()>0){
            return v.toArray(new User[]{});
        }else {
            User [] users=new User[1];
            User user=new User();
            user.setName("没有结果");
            users[0]=user;
            return users;
        }
    }
    public boolean deleteByUser(User user){
        return  db.delete(TABLENAME," id_DB=?",new String[]{user.getId_DB()+""});
    }
}
