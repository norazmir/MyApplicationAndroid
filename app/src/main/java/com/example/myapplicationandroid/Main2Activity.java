package com.example.myapplicationandroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.myapplicationandroid.Database.DatabaseHelper;
import com.example.myapplicationandroid.View.ProgressBarAnimation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

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
    @BindView(R.id.loadingLogin)
    ProgressBar progressBar;
    @BindView(R.id.textPercent)
    TextView textPercent;

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
                validation();
                break;
            case R.id.textRegister:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
        }


    }

    public void validation(){
        if (!validate()){
            Toast.makeText(getApplicationContext(), "Wrong email or password", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            startLogin();
        }
    }

    public void startLogin(){
        ExampleAsyncTask task = new ExampleAsyncTask(this);
        task.execute(10);
    }

    public boolean validate(){
        boolean valid = true;

        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        Boolean checkEmailPass = db.emailPassword(email, password);

        if (checkEmailPass == false){
            valid = false;
        }

        return valid;
    }

    private class ExampleAsyncTask extends AsyncTask<Integer, Integer, String> {
        private WeakReference<Main2Activity> activityWeakReference;

        ExampleAsyncTask(Main2Activity activity){
            activityWeakReference = new WeakReference<Main2Activity>(activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            Main2Activity activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing()){
                return;
            }

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

            activity.progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Integer... integers) {
            for (int i = 0; i < integers[0]; i++){
                publishProgress((i * 100) / integers[0]);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return "Welcome !";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            Main2Activity activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing()){
                return;
            }

            activity.progressBar.setProgress(values[0]);
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Main2Activity activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing()){
                return;
            }

            String email = edtEmail.getText().toString();

            Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
            intent.putExtra("keyValue", email);
            startActivity(intent);
            if (email.equals("lyasyafiqah@gmail.com")){
                Toast.makeText(Main2Activity.this, "Hi Beautiful", Toast.LENGTH_SHORT).show();
                finish();
            }
            else {
                Toast.makeText(Main2Activity.this, email, Toast.LENGTH_SHORT).show();
                finish();
            }



        }


    }

}
