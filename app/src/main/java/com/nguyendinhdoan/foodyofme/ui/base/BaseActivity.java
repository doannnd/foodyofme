package com.nguyendinhdoan.foodyofme.ui.base;

import android.support.v7.app.AppCompatActivity;

import com.nguyendinhdoan.foodyofme.FoodyApplication;
import com.nguyendinhdoan.foodyofme.di.component.ActivityComponent;
import com.nguyendinhdoan.foodyofme.di.component.DaggerActivityComponent;
import com.nguyendinhdoan.foodyofme.di.module.ActivityModule;

import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

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
}
