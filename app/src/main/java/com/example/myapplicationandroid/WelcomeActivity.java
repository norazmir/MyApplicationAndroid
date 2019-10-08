package com.example.myapplicationandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity {

    @BindView(R.id.textData)
    TextView txtData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        Bundle extras = getIntent().getExtras();
        String emailValue = extras.getString("keyValue");
        String value = getIntent().getStringExtra("Value");

        txtData.setText(emailValue);
        Toast.makeText(getApplicationContext(), "Password " + value, Toast.LENGTH_SHORT).show();
    }
}
