package com.nguyendinhdoan.foodyofme.ui.base;

public class BasePresenter<V extends BaseView> implements ToPresenter<V> {

    private V view;

    @Override
    public void attachView(V view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    public V getView() {
        return this.view;
    }

    public boolean isViewAttached() {
        return this.view != null;
    }

    public void checkViewAttached() {
        if(!isViewAttached()) throw new ViewNotAttachedException();
    }
}
