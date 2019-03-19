package com.nguyendinhdoan.foodyofme.ui.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.nguyendinhdoan.foodyofme.R;
import com.nguyendinhdoan.foodyofme.ui.base.BaseActivity;

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

    @Inject
    RegisterToPresenter registerPresenter;

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
        launchLoginActivity();
    }

    @OnClick(R.id.btn_start_login)
    void handleStartLogin() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();

    }

    @Override
    public void setupUi() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(int resId) {

    }

    @Override
    public void onRegisterSuccess(boolean isRegisterSuccess) {

    }

    @Override
    public void onRegisterFailed(String message) {

    }

    @Override
    protected void onDestroy() {
        registerPresenter.detachView();
        super.onDestroy();
    }
}
