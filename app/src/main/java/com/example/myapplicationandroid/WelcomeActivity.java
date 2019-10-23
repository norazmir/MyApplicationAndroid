package com.example.myapplicationandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;


/*
An activity calss to use bundle concept
 */
public class WelcomeActivity extends AppCompatActivity {

    @BindView(R.id.textData)
    TextView txtData;
    @BindView(R.id.buttonList)
    Button btnList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent list = new Intent(getApplicationContext(), ViewDataActivity.class);
                startActivity(list);
            }
        });

        Bundle bundle = getIntent().getExtras();
        String email = bundle.getString("keyValue");

        if (email.contains("lyasyafiqah@gmail.com"))
        {
            txtData.setText("I love you with all my life");
        }
        else {
            txtData.setText(email);
        }
    }
}
