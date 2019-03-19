package com.nguyendinhdoan.foodyofme.di.module;

import android.app.Activity;
import android.content.Context;

import com.nguyendinhdoan.foodyofme.di.ActivityContext;
import com.nguyendinhdoan.foodyofme.di.PerActivity;
import com.nguyendinhdoan.foodyofme.ui.login.LoginPresenter;
import com.nguyendinhdoan.foodyofme.ui.login.LoginToPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityContext
    Activity provideActivity() {
        return activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return activity;
    }


}
