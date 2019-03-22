package com.nguyendinhdoan.foodyofme.ui.register;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.nguyendinhdoan.foodyofme.R;
import com.nguyendinhdoan.foodyofme.ui.base.BasePresenter;
import com.nguyendinhdoan.foodyofme.util.CommonUtils;

import java.util.Objects;

public class RegisterPresenter<V extends RegisterToView> extends BasePresenter<V> implements RegisterToPresenter<V> {

    private static final String TAG = "RegisterPresenter";

    @Override
    public void performRegisterByEmailAndPassword(String email, final String password, String confirmPassword) {

        if (email == null || email.isEmpty()) {
            getmView().onError(R.string.error_empty_email);
            return;
        }

        if (password == null || password.isEmpty()) {
            getmView().onError(R.string.error_empty_password);
            return;
        }

        if (!password.equals(confirmPassword)) {
            getmView().onError(R.string.error_confirm_password);
            return;
        }

        if (!CommonUtils.validateEmail(email)) {
            getmView().onError(R.string.error_email);
            return;
        }

        if (!CommonUtils.validatePassword(password)) {
            getmView().onError(R.string.error_password);
            return;
        }

        getmView().showLoading();
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    getmView().onRegisterSuccess(true);
                } else {
                    Log.w(TAG, "Register failed: " + task.getException() );
                    getmView().onRegisterFailed(Objects.requireNonNull(task.getException()).getMessage());
                }
                getmView().hideLoading();
            }
        });
    }

}
