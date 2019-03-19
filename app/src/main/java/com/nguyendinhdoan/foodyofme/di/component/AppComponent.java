package com.nguyendinhdoan.foodyofme.di.component;

import android.app.Application;
import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.nguyendinhdoan.foodyofme.FoodyApplication;
import com.nguyendinhdoan.foodyofme.di.ApplicationContext;
import com.nguyendinhdoan.foodyofme.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(FoodyApplication foodyApplication);

     FoodyApplication application();

     @ApplicationContext
     Context context();

     FirebaseAuth firebaseAuth();
}
