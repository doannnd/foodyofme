package com.nguyendinhdoan.foodyofme.di.module;

import android.app.Activity;

import com.nguyendinhdoan.foodyofme.di.ActivityContext;

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
}
