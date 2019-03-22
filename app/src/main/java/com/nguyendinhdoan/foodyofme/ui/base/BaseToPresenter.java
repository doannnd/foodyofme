package com.nguyendinhdoan.foodyofme.ui.base;

public interface BaseToPresenter<V extends BaseToView> {

    void onAttach(V view);

    void onDetach();
}
