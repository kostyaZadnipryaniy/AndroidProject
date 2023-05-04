package edu.itstep.a06_data_save;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import edu.itstep.a06_data_save.databinding.ActivityContactBinding;

public class ContactActivity extends AppCompatActivity {
    private ActivityContactBinding binding;

    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContactBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        database = databaseHelper.getWritableDatabase();

        binding.btnSaveContact.setOnClickListener(this::save);
        binding.btnAllContacts.setOnClickListener(this::showAll);
        binding.btnUpdate.setOnClickListener(this::update);

    }

    private void save(View view) {

        String name = binding.etFullName.getText().toString();
        String phone = binding.etPhone.getText().toString();
//        database.execSQL("INSERT INTO contacts(name, phone) VALUES ('" + name + "','" + phone + "')");
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        database.insert("contacts", null, contentValues);


        binding.etFullName.setText("");
        binding.etPhone.setText("");
        Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();
    }

    private void showAll(View view) {
        startActivity(new Intent(this, ListContactsActivity.class));
    }

    private void update(View view) {
        String id = binding.etFullName.getText().toString();
        String newPhone = binding.etPhone.getText().toString();
        ContentValues contentValues = new ContentValues();
        contentValues.put("phone", newPhone);
        database.update("contacts", contentValues, "_id=?", new String[]{id});

        binding.etFullName.setText("");
        binding.etPhone.setText("");
        Toast.makeText(this, "updated", Toast.LENGTH_SHORT).show();
    }
}