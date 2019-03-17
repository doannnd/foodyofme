package com.nguyendinhdoan.foodyofme.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.nguyendinhdoan.foodyofme.R;
import com.nguyendinhdoan.foodyofme.ui.base.BaseActivity;
import com.nguyendinhdoan.foodyofme.ui.register.RegisterActivity;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginContract.LoginToView {

    @BindView(R.id.et_email)
    TextInputEditText etEmail;
    @BindView(R.id.layout_email)
    TextInputLayout layoutEmail;
    @BindView(R.id.et_password)
    TextInputEditText etPassword;
    @BindView(R.id.layout_password)
    TextInputLayout layoutPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        getActivityComponent().inject(this);
        setupUi();
    }

    @Override
    public void setupUi() {

    }

    @OnClick({R.id.btn_login, R.id.btn_login_facebook, R.id.btn_login_google, R.id.btn_start_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                handleLogin();
                break;
            case R.id.btn_login_facebook:
                handleLoginFacebook();
                break;
            case R.id.btn_login_google:
                handleLoginGoogle();
                break;
            case R.id.btn_start_register:
                launchRegisterActivity();
                break;
        }
    }

    private void handleLogin() {
        String email = Objects.requireNonNull(etEmail.getText()).toString();
        String password = Objects.requireNonNull(etPassword.getText()).toString();
    }

    private void launchRegisterActivity() {
        Intent intentRegisterActivity = new Intent(this, RegisterActivity.class);
        intentRegisterActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intentRegisterActivity);
        finish();
    }

    private void handleLoginFacebook() {

    }

    private void handleLoginGoogle() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onLoginSuccess(boolean isLoginSuccess) {

    }

    @Override
    public void onLoginFailed(String message) {

    }
}
