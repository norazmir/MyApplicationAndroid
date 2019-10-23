package com.example.myapplicationandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplicationandroid.Database.DatabaseHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.editEmail)
    EditText etEmail;
    @BindView(R.id.editPassword)
    EditText etPassword;
    @BindView(R.id.editConfirmPassword)
    EditText etConfirmPassword;
    @BindView(R.id.loadingRegister)
    ProgressBar progressBar;

    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        databaseHelper = new DatabaseHelper(this);

    }

    @OnClick(R.id.buttonRegister)
    public void onLoginClicked(){
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String confPassword = etConfirmPassword.getText().toString();

        if (email.equals("")||password.equals("")) {
            Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
        }else {
            if (password.equals(confPassword)) {
                Boolean checkEmail = databaseHelper.checkEmail(email);

                if (checkEmail == true) {
                    Boolean insert = databaseHelper.insert(email, password);
                    if (insert == true) {
                        progressBar.setVisibility(View.VISIBLE);
                        progressBar.setProgress(100);
                        Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Email Already exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }
}
