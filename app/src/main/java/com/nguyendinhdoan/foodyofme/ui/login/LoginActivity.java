package com.nguyendinhdoan.foodyofme.ui.login;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.nguyendinhdoan.foodyofme.R;
import com.nguyendinhdoan.foodyofme.ui.base.BaseActivity;
import com.nguyendinhdoan.foodyofme.ui.main.MainActivity;
import com.nguyendinhdoan.foodyofme.ui.register.RegisterActivity;
import com.nguyendinhdoan.foodyofme.ui.splash.SplashActivity;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginToView {

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
    LoginPresenter loginPresenter;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getActivityComponent().inject(this);
        setUnbinder(ButterKnife.bind(this));
        loginPresenter.attachView(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //loginPresenter.isLoggedIn();
    }

    @Override
    public void setupUi() {

    }

    @OnClick(R.id.btn_login)
     void handleLogin() {
        String email = Objects.requireNonNull(etEmail.getText()).toString();
        String password = Objects.requireNonNull(etPassword.getText()).toString();
        loginPresenter.performLoginWithEmailAndPassword(email, password);
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
        loginPresenter.performLoginWithFacebook(this);
    }

    @OnClick(R.id.btn_login_google)
     void handleLoginGoogle() { loginPresenter.performLoginWithGoogle(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginPresenter.handleActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        loginPresenter.detachView();
        super.onDestroy();
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
        if (isLoginSuccess) {
            launchMainActivity();
        }
    }

    @Override
    public void onError(@StringRes  int resId) {
        showSnackBar(getString(resId));
    }

    @Override
    public void onLoginFailed(String message) {
        showSnackBar(message);
    }

    @Override
    public void onLoggedIn(boolean isLoggedIn) {
        if (isLoggedIn) {
            launchMainActivity();
        }
    }
}
