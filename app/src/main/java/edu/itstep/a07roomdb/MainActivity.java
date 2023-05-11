package edu.itstep.a07roomdb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ContactDAO contactDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.ListView);
        contactDAO = App.getInstance().getPhoneBookDB().contactDAO();

        //insert
//        contactDAO.add(new Contact("Ivan Ivanov", "066-666-66-66"));
//        contactDAO.add(new Contact("Petr Petrov", "073-777-77-77"));
//        contactDAO.add(new Contact("Stepan Stepanov", "073-888-88-88"));

        //select
        List<String> fullNames = contactDAO.getAllFullNames();

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,
                fullNames){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                if(position%2==1)view.setBackgroundColor(Color.BLUE);
                if(position%2==0)view.setBackgroundColor(Color.YELLOW);
                return view;
            }
        };

        listView.setAdapter(adapter);


        listView.setOnItemClickListener((parent, view, position, id)->{
            Contact contact = contactDAO.getById(Long.valueOf(position + 1));
            Intent intent = new Intent(this, FullInfo.class);
            intent.putExtra("_id", Long.valueOf(position+1));
            intent.putExtra("fullName", contact.getFullName());
            intent.putExtra("phone", contact.getPhone());
            startActivity(intent);
        });

//        for (Contact contact : contacts) {
//            textView.append(contact.toString() + "\n");
//        }
//
//        Contact contact = contactDAO.getById(1);
//        textView.setText(contact.toString());

        //update
//        Contact contact = contactDAO.getById(1);
//        contact.setFullName("vania ivaniv");
//        contact.setPhone("066-123-12-12");
//        contactDAO.update(contact);

        //delete
//        Contact contact = contactDAO.getById(1);
//        contactDAO.remove(contact);


    }
}