package com.example.myapplicationandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplicationandroid.Database.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    EditText etEmail, etPassword, etConfirmPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        etEmail = (EditText) findViewById(R.id.editEmail);
        etPassword = (EditText) findViewById(R.id.editPassword);
        etConfirmPassword = (EditText) findViewById(R.id.editConfirmPassword);
        btnLogin = (Button) findViewById(R.id.buttonRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String confPassword = etConfirmPassword.getText().toString();

                if (email.equals("")||password.equals("")){
                    Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (password.equals(confPassword)){
                        Boolean checkEmail = databaseHelper.checkEmail(email);

                        if (checkEmail == true){
                            Boolean insert = databaseHelper.insert(email, password);
                            if (insert == true){
                                Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Email Already exists", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }

            }
        });
    }
}
