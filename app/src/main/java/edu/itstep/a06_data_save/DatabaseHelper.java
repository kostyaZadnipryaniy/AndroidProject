package edu.itstep.a06_data_save;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "PhoneBook", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE contacts(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone TEXT)");
        db.execSQL("INSERT INTO contacts(name, phone) VALUES('Ivan Ivanov', '+38-066-666-66-66')");
        db.execSQL("INSERT INTO contacts(name, phone) VALUES('Petr Petrov', '+38-077-777-77-77')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
