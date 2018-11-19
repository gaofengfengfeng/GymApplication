package com.example.gymapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gymapplication.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    private void initView() {
        login = findViewById(R.id.login);

        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.login:
                // 登录
                //Intent intent = new Intent(this, DisplayMessageActivity.class);
                //startActivity(intent);
                onBackPressed();
                break;
            default:
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
