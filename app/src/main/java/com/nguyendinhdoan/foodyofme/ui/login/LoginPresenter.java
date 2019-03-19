package com.nguyendinhdoan.foodyofme.ui.login;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

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

public class LoginPresenter extends BasePresenter<LoginToView> implements LoginToPresenter{

    private static final String TAG = "LoginPresenter";
    private static final int RC_SIGN_IN = 9001;
    private FirebaseAuth mFirebaseAuth;
    private CallbackManager callbackManager = CallbackManager.Factory.create();

    @Inject
    public LoginPresenter(FirebaseAuth firebaseAuth) {
        this.mFirebaseAuth = firebaseAuth;
    }

    @Override
    public void performLoginWithEmailAndPassword(String email, String password) {

        if (email == null || email.isEmpty()) {
            getView().onError(R.string.error_empty_email);
            return;
        }

        if (password == null || password.isEmpty()) {
            getView().onError(R.string.error_empty_password);
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
        mFirebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    getView().onLoginSuccess(true);
                } else {
                    Log.w(TAG, "Login failed: " + Objects.requireNonNull(task.getException()).getMessage());
                    getView().onLoginFailed(Objects.requireNonNull(task.getException()).getMessage());
                }
                getView().hideLoading();
            }
        });
    }

    @Override
    public void isLoggedIn() {
        FirebaseUser user = mFirebaseAuth.getCurrentUser();
        if (user != null) {
            getView().onLoggedIn(true);
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
                getView().onLoginFailed(e.getMessage());
            }
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
        getView().showLoading();

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mFirebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    getView().onLoginSuccess(true);
                }  else {
                    Log.w(TAG, "Sign in with google failed: " + task.getException());
                    getView().onLoginFailed(Objects.requireNonNull(task.getException()).getMessage());
                }
            }
        });
    }

    @Override
    public void performLoginWithFacebook(Activity activity) {
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("public_profile"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
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
        getView().showLoading();
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            getView().onLoginSuccess(true);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            getView().onLoginFailed(Objects.requireNonNull(task.getException()).getMessage());
                        }
                        getView().hideLoading();
                    }
                });
    }
}
