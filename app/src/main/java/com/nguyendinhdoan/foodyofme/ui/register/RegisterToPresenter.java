package com.nguyendinhdoan.foodyofme.ui.register;

import com.nguyendinhdoan.foodyofme.ui.base.BaseToPresenter;

public interface RegisterToPresenter<V extends RegisterToView> extends BaseToPresenter<V> {

    void performRegisterByEmailAndPassword(String email, String password, String confirmPassword);
}
