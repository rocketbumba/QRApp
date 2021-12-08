package rocketbumba.com.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import rocketbumba.com.myapplication.model.Book;
import rocketbumba.com.myapplication.model.BookAuthen;

public class DBAuthen extends SQLiteOpenHelper {
    public DBAuthen(@Nullable Context context) {
        super(context,"CuoiKi.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public BookAuthen load(String maID) {
        BookAuthen bookAuthen = new BookAuthen();
        SQLiteDatabase db = this.getReadableDatabase();
        System.out.println("Da tim thay DB");
        String query = "SELECT AU.MAID, AU.AUTHEN from AUTHENTICATION AS AU  WHERE AU.MAID = '" +maID+ "' ";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {

                bookAuthen.setId(cursor.getString(0));
                bookAuthen.setAuthen(cursor.getInt(1));

                //if (drugInput.getDrugName().equals(""))
                //drugInput.setImg(R.drawable.thuoc_mo);


            } while (cursor.moveToNext());
        }
        return bookAuthen;
    }
    public void update(String id)
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String sql = "Update  AUTHENTICATION  set AUTHEN = '1' WHERE MAID = '"+id+"'";
            System.out.println("Update dc DB");
            db.execSQL(sql);
            Log.d("update","4. Update");}
        catch (SQLException ex) {
            Log.d("update", " Loi la" +String.valueOf(ex));
        }
    }
}
