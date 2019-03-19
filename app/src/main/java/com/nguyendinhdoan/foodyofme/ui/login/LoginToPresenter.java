package com.nguyendinhdoan.foodyofme.ui.login;

import android.app.Activity;
import android.content.Intent;

import com.nguyendinhdoan.foodyofme.ui.base.ToPresenter;

public interface LoginToPresenter extends ToPresenter<LoginToView> {

    void isLoggedIn();

    void performLoginWithEmailAndPassword(String email, String password);

    void performLoginWithGoogle(Activity activity);

    void handleActivityResult(int requestCode, int resultCode, Intent data);


    void performLoginWithFacebook(Activity activity);
}
