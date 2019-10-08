package com.example.myapplicationandroid;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplicationandroid.Database.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    public String TAG = "Main2Activity";

    private TextView txtRegister;
    private Button btnLogin;
     EditText edtEmail;
     EditText edtPassword;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        db = new DatabaseHelper(this);

        txtRegister = (TextView) findViewById(R.id.textRegister);
        btnLogin = (Button) findViewById(R.id.buttonLogin);
        edtEmail = (EditText) findViewById(R.id.editEmail);
        edtPassword = (EditText) findViewById(R.id.editPassword);

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                Log.d(TAG,"email " + email);
                Log.d(TAG,"password " + password);
                Boolean checkEmailPass = db.emailPassword(email, password);
                if (checkEmailPass == true){
                    Toast.makeText(getApplicationContext(), "Successfully Login. Good Job ! ", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(), "Wrong email or password", Toast.LENGTH_SHORT).show();
            }
        });



    }

}
