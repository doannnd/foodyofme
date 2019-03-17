package com.nguyendinhdoan.foodyofme.di.module;

import android.app.Application;

import com.nguyendinhdoan.foodyofme.FoodyApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule  {

    private FoodyApplication application;

    public AppModule(FoodyApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    FoodyApplication provideApplication() {
        return application;
    }

}
