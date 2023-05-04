package edu.itstep.a06_data_save;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ListContactsActivity extends AppCompatActivity {
    private ListView lvContacts;
    private SQLiteDatabase database;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contacts);

        lvContacts = findViewById(R.id.lvContacts);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        database = databaseHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from contacts", null);
        adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.two_line_list_item,
                cursor,
                new String[]{"name","phone"},
                new int[]{android.R.id.text1, android.R.id.text2},
                0
        );
        lvContacts.setAdapter(adapter);

        lvContacts.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(this, position + " " + id, Toast.LENGTH_LONG).show();
        });

        lvContacts.setOnItemLongClickListener((parent, view, position, id) -> {
            database = databaseHelper.getWritableDatabase();
            database.delete("contacts","_id="+'"'+Long.toString(id)+'"', null);
            Cursor c = database.rawQuery("select * from contacts", null);
            adapter = new SimpleCursorAdapter(
                    this,
                    android.R.layout.two_line_list_item,
                    c,
                    new String[]{"name","phone"},
                    new int[]{android.R.id.text1, android.R.id.text2},
                    0
            );
            lvContacts.setAdapter(adapter);
            Toast.makeText(this, String.valueOf(id), Toast.LENGTH_LONG).show();
            return false;
        });
    }
}