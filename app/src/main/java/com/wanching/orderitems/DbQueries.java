package com.wanching.orderitems;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WanChing on 19/8/2017.
 */

public class DbQueries {

    private DbHelper helper;

    public DbQueries(DbHelper helper){
        this.helper = helper;
    }

    public List<Product> listProducts(){
        String sql = "select * from " + DbContract.DbEntry.TABLE_NAME;
        SQLiteDatabase db = helper.getReadableDatabase();
        List<Product> storedProducts = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                int quantity = Integer.parseInt(cursor.getString(2));
                storedProducts.add(new Product(id, name, quantity));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storedProducts;
    }

    public long addProduct(Product product){
        SQLiteDatabase db = helper.getWritableDatabase();

        long id = db.insert(DbContract.DbEntry.TABLE_NAME, null, insertValues(product));
        product.setId(id);

        return id;
    }

    public int updateProduct(Product product){
        SQLiteDatabase db = helper.getWritableDatabase();

        String selection = DbContract.DbEntry.COLUMN_ID + " = ?";
        String[] selectionArgs = {Long.toString(product.getId())};

        return db.update(
                DbContract.DbEntry.TABLE_NAME,
                insertValues(product),
                selection, selectionArgs
        );
    }

    public void deleteProduct(long id){
        SQLiteDatabase db = helper.getWritableDatabase();

        String selection = DbContract.DbEntry.COLUMN_ID + " = ?";
        String[] selectionArgs = {Long.toString(id)};

        db.delete(DbContract.DbEntry.TABLE_NAME, selection, selectionArgs);
    }

    private ContentValues insertValues(Product product){
        ContentValues values = new ContentValues();
        values.put(DbContract.DbEntry.COLUMN_ID, product.getId());
        values.put(DbContract.DbEntry.COLUMN_PRODUCTNAME, product.getName());
        values.put(DbContract.DbEntry.COLUMN_QUANTITY, product.getQuantity());

        return values;
    }

}
