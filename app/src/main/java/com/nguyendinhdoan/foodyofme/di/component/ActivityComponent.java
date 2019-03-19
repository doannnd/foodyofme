package com.nguyendinhdoan.foodyofme.di.component;

import com.nguyendinhdoan.foodyofme.di.PerActivity;
import com.nguyendinhdoan.foodyofme.di.module.ActivityModule;
import com.nguyendinhdoan.foodyofme.ui.login.LoginActivity;
import com.nguyendinhdoan.foodyofme.ui.main.MainActivity;
import com.nguyendinhdoan.foodyofme.ui.register.RegisterActivity;
import com.nguyendinhdoan.foodyofme.ui.splash.SplashActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SplashActivity splashActivity);

    void inject(LoginActivity loginActivity);

    void inject(RegisterActivity registerActivity);

    void inject(MainActivity mainActivity);
}
