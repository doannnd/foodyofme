package com.nguyendinhdoan.foodyofme.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;

import com.nguyendinhdoan.foodyofme.R;
import com.nguyendinhdoan.foodyofme.ui.base.BaseActivity;
import com.nguyendinhdoan.foodyofme.ui.register.RegisterActivity;
import com.nguyendinhdoan.foodyofme.ui.splash.SplashActivity;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.Objects;

import javax.inject.Inject;

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
    @BindView(R.id.avl_loading)
    AVLoadingIndicatorView avlLoading;

    @Inject
    LoginContract.LoginToPresenter<LoginContract.LoginToView> loginPresenter;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setUnbinder(ButterKnife.bind(this));
        getActivityComponent().inject(this);
        loginPresenter.attachView(this);

        setupUi();
    }

    @Override
    public void setupUi() {

    }

    @OnClick(R.id.btn_login)
     void handleLogin() {
        String email = Objects.requireNonNull(etEmail.getText()).toString();
        String password = Objects.requireNonNull(etPassword.getText()).toString();
        loginPresenter.loginByEmailAndPassword(this, email, password);
    }

    @OnClick(R.id.btn_start_register)
    void launchRegisterActivity() {
        Intent intentRegisterActivity = new Intent(this, RegisterActivity.class);
        intentRegisterActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intentRegisterActivity);
        finish();
    }

    @OnClick(R.id.btn_login_facebook)
    void handleLoginFacebook() {

    }

    @OnClick(R.id.btn_login_google)
    private void handleLoginGoogle() {

    }

    @Override
    public void showLoading() {
        avlLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        avlLoading.setVisibility(View.GONE);
    }

    @Override
    public void onLoginSuccess(boolean isLoginSuccess) {

    }

    @Override
    public void onLoginFailed(String message) {

    }

    @Override
    protected void onDestroy() {
        loginPresenter.detachView();
        super.onDestroy();
    }
}
