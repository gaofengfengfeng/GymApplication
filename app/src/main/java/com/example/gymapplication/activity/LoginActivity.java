package com.example.gymapplication.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.gymapplication.MyApplication;
import com.example.gymapplication.R;
import com.example.gymapplication.model.Trainer;
import com.example.gymapplication.model.User;
import com.example.gymapplication.model.network.LoginReq;
import com.example.gymapplication.model.network.LoginResponse;
import com.example.gymapplication.network.UserService;
import com.example.gymapplication.network.Retrofit;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static String TENCENT_ID = "1108069492";
    private EditText loginUsername, loginPassword;
    private Button loginUsernameClear, loginPasswordClear, loginPasswordVisible, login, qqLogin;
    private TextView passwordForget, loginRegister;
    private boolean passwordVisibleIsOpen = false;
    private MyApplication myApplication;
    private Tencent mTencent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myApplication = MyApplication.getInstance();
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
        qqLogin = findViewById(R.id.qq_login);

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
        qqLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.qq_login:
                mTencent = Tencent.createInstance(TENCENT_ID, this.getApplicationContext());
                mTencent.login(LoginActivity.this, "all", new BaseUiListener());
                break;
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
                UserService userService = Retrofit.getUserRetrofit().create(UserService.class);

                LoginReq loginReq = new LoginReq();
                loginReq.setUsername(loginUsername.getText().toString().trim());
                loginReq.setPassword(loginPassword.getText().toString().trim());
                Call<LoginResponse> call = userService.login(loginReq);

                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        // Get result Repo from response.body()
                        if (response.body().getErrNo() == 0) {
                            User user = new User();
                            user.setUsername(response.body().getData().getUsername());

                            myApplication.setLoginUser(user);
                            myApplication.setTrainerList(response.body().getData().getFriendList());

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "用户名/密码错误", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        System.out.println(call.request().url().toString());
                        System.out.println(t.fillInStackTrace());
                    }
                });
                break;
            case R.id.password_forget:
                // 忘记密码
                break;
            case R.id.login_register:
                // 注册
                intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    // 设置密码是否可见
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

    //qq登录后，回调的类
    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            System.out.println("*****************************complete");
            Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
//            try {
//                Log.v("----TAG--", "-------------"+response.toString());
//                String openidString = ((JSONObject) response).getString("openid");
//                mTencent.setOpenId(openidString);
//                mTencent.setAccessToken(((JSONObject) response).getString("access_token"),((JSONObject) response).getString("expires_in"));
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
//                Log.v("TAG", "-------------"+openidString);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
        }

        @Override
        public void onError(UiError uiError) {
            System.out.println("*****************************error");
            Toast.makeText(getApplicationContext(), "onError", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }

        @Override
        public void onCancel() {
            System.out.println("*****************************cancel");
            Toast.makeText(getApplicationContext(), "onCancel", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Tencent.onActivityResultData(requestCode, resultCode,data, new BaseUiListener());
    }
}

