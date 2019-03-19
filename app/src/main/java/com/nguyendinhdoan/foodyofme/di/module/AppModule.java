package com.nguyendinhdoan.foodyofme.di.module;

import android.app.Application;
import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.nguyendinhdoan.foodyofme.FoodyApplication;
import com.nguyendinhdoan.foodyofme.di.ApplicationContext;

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

    @Provides
    @ApplicationContext
    Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    FirebaseAuth provideFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

}
