package edu.itstep.a08recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import edu.itstep.a08recyclerview.ContactDB;
public class FullInfoActivity extends AppCompatActivity {
    private TextView firstName;
    private TextView lastName;
    private EditText phone;
    private Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_info);
        firstName = findViewById(R.id.etFirstName);
        lastName = findViewById(R.id.etLastName);
        phone = findViewById(R.id.etPhone);
        btnSend = findViewById(R.id.btnSend);

        firstName.setText(getIntent().getStringExtra("FirstName"));
        lastName.setText(getIntent().getStringExtra("LastName"));
        phone.setText(getIntent().getStringExtra("Phone"));
        int id = getIntent().getIntExtra("id", 0);
        btnSend.setOnClickListener( (view)->{
            ContactDB db = new ContactDB();
            Contact contact = new Contact(firstName.getText().toString(),
                    lastName.getText().toString()
                    ,phone.getText().toString());

            db.setContactById(id, contact);
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        });
    }
}