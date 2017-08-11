package com.example.asmitas.workshop4.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.asmitas.workshop4.data.Student;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by AsmitaS on 7/8/2017.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "workshop4";
    private static final int VERSION = 1;

    private static final String TABLE = "student";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String ADDRESS = "address";
    private static final String ROLL = "roll";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE "+TABLE+ " ("
                +ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +NAME + " TEXT," + ADDRESS + " TEXT,"
                +ROLL + " INTEGER)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, student.getName());
        contentValues.put(ADDRESS, student.getAddress());
        contentValues.put(ROLL, student.getRoll());
        long status = db.insert(TABLE, ID, contentValues);
        db.close();
        return (status!=-1);
    }

    public List<Student> getStudents(){
        List<Student> list = new LinkedList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        db.beginTransaction();
        Cursor cursor = db.query(TABLE, new String[]{ID, NAME, ADDRESS, ROLL},
                null, null, null, null, null);
        if(cursor.getCount() !=0){
            cursor.moveToFirst();
            do {
                Student student = new Student(cursor.getInt(0), cursor.getString(1),cursor.getString(2), cursor.getInt(3));
                list.add(student);

            }while (cursor.moveToNext());
        }
        cursor.close();
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        return list;
    }

    public boolean deleteStudents(Student student){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long status = sqLiteDatabase.delete(TABLE, ID + " =?", new String[]{ String.valueOf(student.getId())});
        sqLiteDatabase.close();
        return  (status != -1);
    }

    public boolean updateStudents(Student student) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, student.getName());
        values.put(ADDRESS, student.getAddress());
        values.put(ROLL, student.getRoll());


        // updating row
        long status = sqLiteDatabase.update(TABLE, values, ID + " = ?",
                new String[] { String.valueOf(student.getId()) });

        sqLiteDatabase.close();
        return  (status != -1);
    }

}
