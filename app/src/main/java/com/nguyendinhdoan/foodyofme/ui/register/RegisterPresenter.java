package com.nguyendinhdoan.foodyofme.ui.register;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.nguyendinhdoan.foodyofme.R;
import com.nguyendinhdoan.foodyofme.ui.base.BasePresenter;
import com.nguyendinhdoan.foodyofme.ui.login.LoginToView;
import com.nguyendinhdoan.foodyofme.util.CommonUtils;

import java.util.Objects;

import javax.inject.Inject;

public class RegisterPresenter extends BasePresenter<RegisterToView> implements RegisterToPresenter {

    private static final String TAG = "RegisterPresenter";
    private FirebaseAuth firebaseAuth;

    @Inject
    public RegisterPresenter(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }

    @Override
    public void performRegisterByEmailAndPassword(String email, String password, String confirmPassword) {

        if (email == null || email.isEmpty()) {
            getView().onError(R.string.error_empty_email);
            return;
        }

        if (password == null || password.isEmpty()) {
            getView().onError(R.string.error_empty_password);
            return;
        }

        if (!password.equals(confirmPassword)) {
            getView().onError(R.string.error_confirm_password);
            return;
        }

        if (!CommonUtils.validateEmail(email)) {
            getView().onError(R.string.error_email);
            return;
        }

        if (!CommonUtils.validatePassword(password)) {
            getView().onError(R.string.error_password);
            return;
        }

        getView().showLoading();
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    getView().onRegisterSuccess(true);
                } else {
                    Log.w(TAG, "Register failed: " + task.getException() );
                    getView().onRegisterFailed(Objects.requireNonNull(task.getException()).getMessage());
                }
                getView().hideLoading();
            }
        });
    }
}
