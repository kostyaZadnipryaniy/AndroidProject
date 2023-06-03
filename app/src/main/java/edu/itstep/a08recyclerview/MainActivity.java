package edu.itstep.a08recyclerview;

import static edu.itstep.a08recyclerview.ContactDB.contacts;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvNumbers;
    private NumberAdapter adapter;
    private LinearLayout linearLayout;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = findViewById(R.id.linear_layout);
        rvNumbers = findViewById(R.id.rvNumbers);
        ContactDB contacts = new ContactDB();
        contacts.addContact(new Contact("Vasia", "Pupkin", "011 111 11 11"));
        contacts.addContact(new Contact("Masha", "Pupkina", "011 111 11 12"));
        contacts.addContact(new Contact("Ivan", "Ivanov", "011 111 11 13"));
        contacts.addContact(new Contact("Petia", "Petrov", "011 111 11 14"));
        contacts.addContact(new Contact("Stiven", "Jobs", "011 111 11 15"));
        contacts.addContact(new Contact("Bill", "Geits", "011 111 11 16"));
        contacts.addContact(new Contact("Vasia", "Pupkin", "011 111 11 11"));
        contacts.addContact(new Contact("Masha", "Pupkina", "011 111 11 12"));
        contacts.addContact(new Contact("Ivan", "Ivanov", "011 111 11 13"));
        contacts.addContact(new Contact("Petia", "Petrov", "011 111 11 14"));
        contacts.addContact(new Contact("Stiven", "Jobs", "011 111 11 15"));
        contacts.addContact(new Contact("Bill", "Geits", "011 111 11 16"));
        contacts.addContact(new Contact("Vasia", "Pupkin", "011 111 11 11"));
        contacts.addContact(new Contact("Masha", "Pupkina", "011 111 11 12"));
        contacts.addContact(new Contact("Ivan", "Ivanov", "011 111 11 13"));
        contacts.addContact(new Contact("Petia", "Petrov", "011 111 11 14"));
        contacts.addContact(new Contact("Stiven", "Jobs", "011 111 11 15"));
        contacts.addContact(new Contact("Bill", "Geits", "011 111 11 16"));
        contacts.addContact(new Contact("Vasia", "Pupkin", "011 111 11 11"));
        contacts.addContact(new Contact("Masha", "Pupkina", "011 111 11 12"));
        contacts.addContact(new Contact("Ivan", "Ivanov", "011 111 11 13"));
        contacts.addContact(new Contact("Petia", "Petrov", "011 111 11 14"));
        contacts.addContact(new Contact("Stiven", "Jobs", "011 111 11 15"));
        contacts.addContact(new Contact("Bill", "Geits", "011 111 11 16"));

        adapter = new NumberAdapter(this, R.layout.list_item, contacts.getContacts(), (position, model) -> {
            showToast(position);
            changeActivity(model, position);
        });
        rvNumbers.setAdapter(adapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(rvNumbers);
        LinearLayoutManager layoutManager = new LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        );
        rvNumbers.setLayoutManager(layoutManager);

    }
private void showToast(int pos){
    Toast.makeText(this, String.valueOf(pos),Toast.LENGTH_SHORT).show();
}
private void changeActivity(Contact contact, int position){
    Intent intent = new Intent(this, FullInfoActivity.class);
    intent.putExtra("FirstName", contact.getFirstName());
    intent.putExtra("LastName", contact.getLastName());
    intent.putExtra("Phone", contact.getPhone());
    intent.putExtra("id", position);
    startActivity(intent);
}

ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper
        .SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        showToast(viewHolder.getAdapterPosition());

        contacts.remove(viewHolder.getAdapterPosition());
        adapter.notifyDataSetChanged();
    }
};
}
