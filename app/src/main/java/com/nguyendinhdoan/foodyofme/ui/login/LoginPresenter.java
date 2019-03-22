package com.nguyendinhdoan.foodyofme.ui.login;


import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.nguyendinhdoan.foodyofme.R;
import com.nguyendinhdoan.foodyofme.ui.base.BasePresenter;
import com.nguyendinhdoan.foodyofme.util.CommonUtils;

import java.util.Arrays;
import java.util.Objects;

import javax.inject.Inject;

public class LoginPresenter<V extends LoginToView> extends BasePresenter<V> implements LoginToPresenter<V> {

    private static final String TAG = "LoginPresenter";
    private static final int RC_SIGN_IN = 9001;

    private CallbackManager mCallbackManager;

    @Override
    public void performLoginWithEmailAndPassword(String email, String password) {

        if (email == null || email.isEmpty()) {
            getmView().onError(R.string.error_empty_email);
            return;
        }

        if (password == null || password.isEmpty()) {
            getmView().onError(R.string.error_empty_password);
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
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    getmView().onLoginSuccess(true);
                } else {
                    Log.w(TAG, "Login failed: " + Objects.requireNonNull(task.getException()).getMessage());
                    getmView().onLoginFailed(Objects.requireNonNull(task.getException()).getMessage());
                }
                getmView().hideLoading();
            }
        });
    }

    @Override
    public void isLoggedIn() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            getmView().onLoggedIn(true);
        }
    }

    @Override
    public void performLoginWithGoogle(Activity activity) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(activity.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(activity, gso);

        Intent signInIntent = googleSignInClient.getSignInIntent();
        activity.startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void handleActivityResult(int requestCode, int resultCode, Intent data) {
        if (RC_SIGN_IN == requestCode && data != null) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
            }
        } else {
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
        getmView().showLoading();
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    getmView().onLoginSuccess(true);
                }  else {
                    Log.w(TAG, "Sign in with google failed: " + task.getException());
                    getmView().onLoginFailed(Objects.requireNonNull(task.getException()).getMessage());
                }
                getmView().hideLoading();
            }
        });
    }

    @Override
    public void performLoginWithFacebook(Activity activity) {
        mCallbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("public_profile"));
        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
            }
        });
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        getmView().showLoading();
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            getmView().onLoginSuccess(true);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            getmView().onLoginFailed(Objects.requireNonNull(task.getException()).getMessage());
                        }
                        getmView().hideLoading();
                    }
                });
    }
}
