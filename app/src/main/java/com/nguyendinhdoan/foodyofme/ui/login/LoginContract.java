package com.nguyendinhdoan.foodyofme.ui.login;

import android.app.Activity;

public interface LoginContract {

    interface LoginToView {
        void showLoading();

        void hideLoading();

        void onLoginSuccess(boolean isLoginSuccess);

        void onLoginFailed(String message);
    }

    interface LoginToPresenter {
        void loginByEmailAndPassword(Activity activity, String email, String password);
    }

    interface LoginToInteractor {
        void loginByEmailAndPassword(Activity activity, String email, String password, OnLoginListener listener);
    }

    interface OnLoginListener {
        void showLoading();

        void hideLoading();

        void onLoginSuccess(boolean isLoginSuccess);

        void onLoginFailed(String message);
    }
}
