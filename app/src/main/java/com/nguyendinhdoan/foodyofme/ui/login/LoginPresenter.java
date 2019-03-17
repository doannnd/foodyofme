package com.nguyendinhdoan.foodyofme.ui.login;

import android.app.Activity;

import com.nguyendinhdoan.foodyofme.ui.base.BasePresenter;

import javax.inject.Inject;

public class LoginPresenter<V extends LoginContract.LoginToView> extends BasePresenter<V>
            implements LoginContract.LoginToPresenter<V>, LoginContract.OnLoginListener {

    private LoginContract.LoginToView view;
    private LoginContract.LoginToInteractor model;

    @Inject
    public LoginPresenter(LoginContract.LoginToView view, LoginInteractor model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void loginByEmailAndPassword(Activity activity, String email, String password) {
        model.loginByEmailAndPassword(activity, email, password, this);
    }

    @Override
    public void showLoading() {
        view.showLoading();
    }

    @Override
    public void hideLoading() {
        view.hideLoading();
    }

    @Override
    public void onLoginSuccess(boolean isLoginSuccess) {
        view.onLoginSuccess(isLoginSuccess);
    }

    @Override
    public void onLoginFailed(String message) {
        view.onLoginFailed(message);
    }
}
