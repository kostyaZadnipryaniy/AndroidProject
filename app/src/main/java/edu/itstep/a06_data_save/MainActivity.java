package edu.itstep.a06_data_save;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import edu.itstep.a06_data_save.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        String login = sharedPreferences.getString("login", "user");
        binding.tvMessage.setText("Hello, " + login);


        binding.btnSaveMessage.setOnClickListener(v -> {
            String name = binding.etLogin.getText().toString();
            binding.tvMessage.setText("Hello, " + name);
            editor = sharedPreferences.edit();
            editor.putString("login", name);
            editor.apply();
            Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();
            binding.etLogin.setText("");
            binding.etLogin.setInputType(InputType.TYPE_NULL);
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(binding.etLogin.getWindowToken(), 0);
        });


        binding.btnDelete.setOnClickListener(v -> {
          sharedPreferences.edit().remove("login").apply();
            Toast.makeText(this, "deleted", Toast.LENGTH_SHORT).show();
        });

        binding.btnNext.setOnClickListener(v -> {
            startActivity(new Intent(this, ContactActivity.class));
        });
    }
}