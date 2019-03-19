package com.nguyendinhdoan.foodyofme.ui.register;

import com.nguyendinhdoan.foodyofme.ui.base.BasePresenter;
import com.nguyendinhdoan.foodyofme.ui.base.ToPresenter;

public interface RegisterToPresenter extends ToPresenter<RegisterToView> {

    void performRegisterByEmailAndPassword(String email, String password, String confirmPassword);
}
