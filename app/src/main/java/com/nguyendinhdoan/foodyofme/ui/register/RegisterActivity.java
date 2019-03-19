package com.nguyendinhdoan.foodyofme.ui.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.nguyendinhdoan.foodyofme.R;
import com.nguyendinhdoan.foodyofme.ui.base.BaseActivity;
import com.wang.avi.AVLoadingIndicatorView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements RegisterToView{

    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_confirm_password)
    EditText etConfirmPassword;
    @BindView(R.id.avl_loading)
    AVLoadingIndicatorView alvLoading;

    @Inject
    RegisterPresenter registerPresenter;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, RegisterActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getActivityComponent().inject(this);
        setUnbinder(ButterKnife.bind(this));
        registerPresenter.attachView(this);
    }

    @OnClick(R.id.btn_register)
    void handleRegister() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();
        registerPresenter.performRegisterByEmailAndPassword(email, password, confirmPassword);
    }

    @OnClick(R.id.btn_start_login)
    void handleStartLogin() {
        launchLoginActivity();
    }

    @Override
    public void setupUi() {

    }

    @Override
    public void showLoading() {
        alvLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        alvLoading.setVisibility(View.GONE);
    }

    @Override
    public void onError(int resId) {
        showSnackBar(getString(resId));
    }

    @Override
    public void onRegisterSuccess(boolean isRegisterSuccess) {
        launchMainActivity();
    }

    @Override
    public void onRegisterFailed(String message) {
        showSnackBar(message);
    }

    @Override
    protected void onDestroy() {
        registerPresenter.detachView();
        super.onDestroy();
    }
}
