package com.nguyendinhdoan.foodyofme.ui.login;

import android.support.annotation.StringRes;

import com.nguyendinhdoan.foodyofme.ui.base.BaseView;

public interface LoginToView extends BaseView {

    void showLoading();

    void hideLoading();

    void onLoginSuccess(boolean isLoginSuccess);

    void onError(@StringRes int resId);

    void onLoginFailed(String message);

    void onLoggedIn(boolean isLoggedIn);
}
