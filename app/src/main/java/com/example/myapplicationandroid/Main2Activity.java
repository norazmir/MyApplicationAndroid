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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends AppCompatActivity {

    public String TAG = "Main2Activity";

    @BindView(R.id.textRegister)
    TextView txtRegister;
    @BindView(R.id.editEmail)
    EditText edtEmail;
    @BindView(R.id.editPassword)
    EditText edtPassword;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);

        db = new DatabaseHelper(this);
    }

    @OnClick({R.id.buttonLogin, R.id.textRegister})
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.buttonLogin:
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                Boolean checkEmailPass = db.emailPassword(email, password);
                if (checkEmailPass == true){
//                    Toast.makeText(getApplicationContext(), "Successfully Login. Good Job ! ", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
                    intent.putExtra("Value", password);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(), "Wrong email or password", Toast.LENGTH_SHORT).show();
                break;
            case R.id.textRegister:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
        }


    }

}
