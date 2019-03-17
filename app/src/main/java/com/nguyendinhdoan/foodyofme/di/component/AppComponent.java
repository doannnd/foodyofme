package com.nguyendinhdoan.foodyofme.di.component;

import android.app.Application;

import com.nguyendinhdoan.foodyofme.FoodyApplication;
import com.nguyendinhdoan.foodyofme.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(FoodyApplication foodyApplication);

     FoodyApplication application();
}
