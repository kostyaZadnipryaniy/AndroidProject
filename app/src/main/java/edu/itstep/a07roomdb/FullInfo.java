package edu.itstep.a07roomdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FullInfo extends AppCompatActivity {
    private EditText idView;
    private EditText fullNameView;
    private EditText phoneView;
    private Button btn_ok;
    private  long _id;
    private ContactDAO contactDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_info);
        idView = findViewById(R.id.etId);
        fullNameView = findViewById(R.id.etFullName);
        phoneView = findViewById(R.id.etPhone);
        contactDAO = App.getInstance().getPhoneBookDB().contactDAO();
        btn_ok = findViewById(R.id.btn_ok);

        Intent intent = getIntent();
        _id =intent.getLongExtra("_id", 1);
        idView.setText(String.valueOf(_id));
        fullNameView.setText(intent.getStringExtra("fullName"));
        phoneView.setText(intent.getStringExtra("phone"));

        btn_ok.setOnClickListener(v -> {
            Contact contact = contactDAO.getById(_id);

            contact.setFullName(fullNameView.getText().toString());
            contact.setPhone(phoneView.getText().toString());
            contactDAO.update(contact);
            Intent back = new Intent(this, MainActivity.class);
            startActivity(back);

        });
    }


}