package com.nguyendinhdoan.foodyofme.ui.register;

import android.support.annotation.StringRes;

import com.nguyendinhdoan.foodyofme.ui.base.BaseView;

public interface RegisterToView extends BaseView {

    void showLoading();

    void hideLoading();

    void onError(@StringRes int resId);

    void onRegisterSuccess(boolean isRegisterSuccess);

    void onRegisterFailed(String message);
}
