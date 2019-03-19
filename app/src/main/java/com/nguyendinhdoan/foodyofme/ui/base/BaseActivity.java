package com.nguyendinhdoan.foodyofme.ui.base;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.nguyendinhdoan.foodyofme.FoodyApplication;
import com.nguyendinhdoan.foodyofme.R;
import com.nguyendinhdoan.foodyofme.di.component.ActivityComponent;
import com.nguyendinhdoan.foodyofme.di.component.DaggerActivityComponent;
import com.nguyendinhdoan.foodyofme.di.module.ActivityModule;
import com.nguyendinhdoan.foodyofme.ui.login.LoginActivity;
import com.nguyendinhdoan.foodyofme.ui.main.MainActivity;
import com.nguyendinhdoan.foodyofme.util.NetworkUtils;

import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity implements BaseView{

    private Unbinder unbinder;
    private ActivityComponent activityComponent;

    public ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .appComponent(FoodyApplication.get(this).getAppComponent())
                    .build();
        }
        return activityComponent;
    }

    public void setUnbinder(Unbinder unbinder) {
        this.unbinder = unbinder;
    }

    @Override
    protected void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }

    public abstract void setupUi();

    public void launchMainActivity() {
        Intent intentMainActivity = MainActivity.getStartIntent(this);
        intentMainActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intentMainActivity);
        finish();
    }

    public void launchLoginActivity() {
        Intent intentLoginActivity = LoginActivity.getStartIntent(this);
        intentLoginActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intentLoginActivity);
        finish();
    }

    public void showSnackBar(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show();
    }

}
