package com.example.gymapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gymapplication.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText loginUsername, loginPassword;
    private Button loginUsernameClear, loginPasswordClear, loginPasswordVisible, login;
    private TextView passwordForget, loginRegister;
    private boolean passwordVisibleIsOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        loginUsername = findViewById(R.id.login_username);
        loginPassword = findViewById(R.id.login_password);
        loginUsernameClear = findViewById(R.id.login_username_clear);
        loginPasswordClear = findViewById(R.id.login_password_clear);
        loginPasswordVisible = findViewById(R.id.login_password_visible);
        login = findViewById(R.id.login);
        passwordForget = findViewById(R.id.password_forget);
        loginRegister = findViewById(R.id.login_register);

        loginUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 获取文本框中的用户名
                String username = loginUsername.getText().toString().trim();
                if (username.equals("")) {
                    loginUsernameClear.setVisibility(View.INVISIBLE);
                } else {
                    loginUsernameClear.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        loginPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 获取文本框中的密码
                String password = loginPassword.getText().toString();
                if (password.equals("")) {
                    loginPasswordClear.setVisibility(View.INVISIBLE);
                } else {
                    loginPasswordClear.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        loginUsername.setOnClickListener(this);
        loginPassword.setOnClickListener(this);
        loginUsernameClear.setOnClickListener(this);
        loginPasswordClear.setOnClickListener(this);
        loginPasswordVisible.setOnClickListener(this);
        login.setOnClickListener(this);
        passwordForget.setOnClickListener(this);
        loginRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_username_clear:
                // 清除用户名
                loginUsername.setText("");
                break;
            case R.id.login_password_clear:
                // 清除密码
                loginPassword.setText("");
                break;
            case R.id.login_password_visible:
                // 密码是否可见
                passwordVisibleIsOpen = !passwordVisibleIsOpen;
                changePwdVisibleSwitch(passwordVisibleIsOpen);
                break;
            case R.id.login:
                // 登录
                //Intent intent = new Intent(this, DisplayMessageActivity.class);
                //startActivity(intent);
                onBackPressed();
                break;
            case R.id.password_forget:
                // 忘记密码
                break;
            case R.id.login_register:
                // 注册
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void changePwdVisibleSwitch(boolean flag) {
        if (flag) {
            // 设置密码可见，并将按钮修改为密码不可见
            loginPasswordVisible.setBackgroundResource(R.drawable.visible);
            loginPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            // 设置密码不可见，并将按钮修改为密码可见
            loginPasswordVisible.setBackgroundResource(R.drawable.invisible);
            loginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }

    }
}

