package com.example.gymapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gymapplication.MyApplication;
import com.example.gymapplication.R;
import com.example.gymapplication.model.network.RegisterReq;
import com.example.gymapplication.model.network.RegisterResponse;
import com.example.gymapplication.network.Retrofit;
import com.example.gymapplication.network.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button register, login_username_clear, login_password_clear;
    private MyApplication myApplication;

    private EditText login_username, login_email, login_password, login_password_verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myApplication = MyApplication.getInstance();
        initView();
    }

    private void initView() {
        register = findViewById(R.id.register);
        login_username = findViewById(R.id.login_username);
        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);
        login_password_verify = findViewById(R.id.login_password_verify);
        login_username_clear = findViewById(R.id.login_username_clear);
        login_password_clear = findViewById(R.id.login_password_clear);

        login_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 获取文本框中的用户名
                String username = login_username.getText().toString().trim();
                if (username.equals("")) {
                    login_username_clear.setVisibility(View.INVISIBLE);
                } else {
                    login_username_clear.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        login_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 获取文本框中的密码
                String password = login_password.getText().toString();
                if (password.equals("")) {
                    login_password_clear.setVisibility(View.INVISIBLE);
                } else {
                    login_password_clear.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        login_username.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                } else {
                    // 此处为失去焦点时的处理内容
                }
            }
        });

        login_email.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                } else {
                    // 此处为失去焦点时的处理内容
                }
            }
        });

        login_password.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                } else {
                    // 此处为失去焦点时的处理内容
                }
            }
        });

        login_password_verify.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                } else {
                    // 此处为失去焦点时的处理内容
                }
            }
        });
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.register:
                // 注册
                // 判断资料是否填写完全
                if (login_username.getText().toString().equals("") || login_email.getText().toString().equals("") ||
                        login_password.getText().toString().equals("") || login_password_verify.getText().toString().equals("")) {
                    Toast.makeText(RegisterActivity.this, "不能有非空项", Toast.LENGTH_SHORT).show();
                } else if (!login_password.getText().toString().equals(login_password_verify.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                } else {
                    UserService userService = Retrofit.getUserRetrofit().create(UserService.class);

                    RegisterReq registerReq = new RegisterReq();
                    registerReq.setUsername(login_username.getText().toString().trim());
                    registerReq.setPassword(login_password.getText().toString().trim());
                    registerReq.setEmail(login_email.getText().toString().trim());
                    Call<RegisterResponse> call = userService.register(registerReq);


                    call.enqueue(new Callback<RegisterResponse>() {
                        @Override
                        public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                            // Get result Repo from response.body()
                            if (response.body().getErrNo() == 0) {
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<RegisterResponse> call, Throwable t) {

                        }
                    });
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
