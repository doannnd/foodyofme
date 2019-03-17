package com.nguyendinhdoan.foodyofme.ui.base;

public interface ToPresenter<V extends BaseView> {
    void attachView(V view);

    void detachView();
}
