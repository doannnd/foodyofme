package com.nguyendinhdoan.foodyofme.ui.base;

import android.view.View;

public class BasePresenter<V extends BaseToView> implements BaseToPresenter<V> {

    private V mView;

    @Override
    public void onAttach(V view) {
        this.mView = view;
    }

    @Override
    public void onDetach() {
        this.mView = null;
    }

    public V getmView() {
        return this.mView;
    }

    public boolean isViewAttached() {
        return this.mView != null;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new ViewNotAttachedException();
    }

    public static class ViewNotAttachedException extends RuntimeException {
        public ViewNotAttachedException() {
            super("Please call attach before request data to the presenter");
        }
    }
}
