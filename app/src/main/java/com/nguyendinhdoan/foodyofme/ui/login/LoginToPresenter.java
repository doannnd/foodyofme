package com.nguyendinhdoan.foodyofme.ui.login;

import android.app.Activity;
import android.content.Intent;

import com.nguyendinhdoan.foodyofme.ui.base.BaseToPresenter;

public interface LoginToPresenter<V extends LoginToView>  extends BaseToPresenter<V> {

    void performLoginWithEmailAndPassword(String email, String password);

    void performLoginWithGoogle(Activity activity);

    void performLoginWithFacebook(Activity activity);

    void isLoggedIn();

    void handleActivityResult(int requestCode, int resultCode, Intent data);


}
