package com.nguyendinhdoan.foodyofme;

import android.app.Application;
import android.content.Context;

import com.nguyendinhdoan.foodyofme.di.component.AppComponent;
import com.nguyendinhdoan.foodyofme.di.component.DaggerAppComponent;
import com.nguyendinhdoan.foodyofme.di.module.AppModule;

public class FoodyApplication extends Application {

    private AppComponent appComponent;

    public static FoodyApplication get(Context context) {
        return (FoodyApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        appComponent.inject(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
