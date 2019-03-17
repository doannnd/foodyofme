package com.nguyendinhdoan.foodyofme.ui.login;

import android.app.Activity;

import com.nguyendinhdoan.foodyofme.ui.base.BaseView;
import com.nguyendinhdoan.foodyofme.ui.base.ToPresenter;

public interface LoginContract {

    interface LoginToView extends BaseView {
        void showLoading();

        void hideLoading();

        void onLoginSuccess(boolean isLoginSuccess);

        void onLoginFailed(String message);
    }

    interface LoginToPresenter<V extends BaseView> extends ToPresenter<V> {
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
