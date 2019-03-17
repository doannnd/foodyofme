package com.nguyendinhdoan.foodyofme.ui.splash;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.nguyendinhdoan.foodyofme.R;
import com.nguyendinhdoan.foodyofme.ui.base.BaseActivity;
import com.nguyendinhdoan.foodyofme.ui.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity implements SplashToView{

    public static final long SPLASH_SCREEN_TIME_OUT = 2000;

    @BindView(R.id.activity_splash_tv_version_name)
    TextView tvVersionName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        setUnbinder( ButterKnife.bind(this));
        getActivityComponent().inject(this);
        setupUi();
    }

    @Override
    public void setupUi() {
        getPackageVersionName();
        launchLoginActivity();
    }

    @Override
    public void launchLoginActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intentLoginActivity = LoginActivity.getStartIntent(SplashActivity.this);
                intentLoginActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentLoginActivity);
                finish();
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }

    @Override
    public void getPackageVersionName() {
        try {
            PackageManager packageManager = getApplicationContext().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(getApplicationContext().getPackageName(), 0);
            tvVersionName.setText(getString(R.string.label_version_name, packageInfo.versionName));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
