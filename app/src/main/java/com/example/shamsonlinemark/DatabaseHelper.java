package com.example.shamsonlinemark;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
static String name="stores";
static int version=1;
String createTable="CREATE TABLE if not exists`stores` (\n" +
        "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
        "\t`Name`\tTEXT,\n" +
        "\t`Price`\tTEXT,\n" +
        "\t`Categories`\tTEXT,\n" +
        "\t`image`\tBLOB\n" +
        ");";
String anotherTAble="CREATE TABLE if not exists `cart` (\n" +
        "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
        "\t`name`\tTEXT,\n" +
        "\t`quantity`\tINTEGER,\n" +
        "\t`price`\tINTEGER,\n" +
        "\t`sum`\tINTEGER,\n" +
        "\t`Total`\tINTEGER\n" +
        ")";
    public DatabaseHelper(@Nullable Context context) {
        super(context, name, null, version);
        getWritableDatabase().execSQL(createTable);
        getWritableDatabase().execSQL(anotherTAble);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }
    public void insertStore(ContentValues contentValues){
        getWritableDatabase().insert("stores","",contentValues);
    }
    public void insertcart(ContentValues contentValues){
        getWritableDatabase().insert("cart","",contentValues);
    }
    public void deletecart(String id){
        getWritableDatabase().delete("cart","id="+id,null);
    }
    public void update(String id,ContentValues contentValues){
        getWritableDatabase().update("cart",contentValues,"id="+id,null);
    }
public ArrayList<store> getStorelist(){
        String sql="Select * from stores";
    Cursor cursor=getReadableDatabase().rawQuery(sql,null);
    ArrayList<store> list=new ArrayList<>();
    while (cursor.moveToNext()){
        store Store=new store();
        Store.setId(cursor.getString(cursor.getColumnIndex("id")));
        Store.setName(cursor.getString(cursor.getColumnIndex("Name")));
        Store.setPrice(cursor.getString(cursor.getColumnIndex("Price")));
        Store.setCategory(cursor.getString(cursor.getColumnIndex("Categories")));
        Store.setImage(cursor.getBlob(cursor.getColumnIndex("image")));
list.add(Store);
    }
    cursor.close();

    return list;
}
    public ArrayList<cart> getcartlist(){
        String sql="Select * from cart";
        Cursor cursor=getReadableDatabase().rawQuery(sql,null);
        ArrayList<cart> cartlist=new ArrayList<>();
        while (cursor.moveToNext()){
            cart Cart=new cart();
            Cart.setId(cursor.getString(cursor.getColumnIndex("id")));
            Cart.setName(cursor.getString(cursor.getColumnIndex("name")));
            Cart.setPrice(cursor.getString(cursor.getColumnIndex("price")));
            Cart.setQuantity(cursor.getString(cursor.getColumnIndex("quantity")));
            Cart.setTotal(cursor.getString(cursor.getColumnIndex("Total")));
           // Store.setImage(cursor.getBlob(cursor.getColumnIndex("image")));
            cartlist.add(Cart);
        }
        cursor.close();

        return cartlist;
    }
    public store getUserInfo(String id) {
        String sql = "Select * from stores where id=" + id;
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        store Store = new store();

        while (cursor.moveToNext()) {
            Store.setId(cursor.getString(cursor.getColumnIndex("id")));
            Store.setName(cursor.getString(cursor.getColumnIndex("Name")));
            Store.setPrice(cursor.getString(cursor.getColumnIndex("Price")));
            Store.setCategory(cursor.getString(cursor.getColumnIndex("Categories")));
            Store.setImage(cursor.getBlob(cursor.getColumnIndex("image")));

        }
        cursor.close();

        return Store;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
