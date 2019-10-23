package com.example.myapplicationandroid;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplicationandroid.View.ProgressBarAnimation;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProgressBarActivity extends AppCompatActivity {

    @BindView(R.id.loadingLogin)
    ProgressBar progressBar;
    @BindView(R.id.textPercent)
    TextView textPercent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        ButterKnife.bind(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        progressBar.setMax(100);
        progressBar.setScaleY(3f);

        progressAnimation();
    }

    private void progressAnimation() {
        ProgressBarAnimation animation = new ProgressBarAnimation(this, progressBar, textPercent, 0f, 100f);
        animation.setDuration(8000);
        progressBar.setAnimation(animation);

    }
}
