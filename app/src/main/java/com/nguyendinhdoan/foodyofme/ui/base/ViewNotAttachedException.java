package com.nguyendinhdoan.foodyofme.ui.base;

public class ViewNotAttachedException extends RuntimeException{
    public ViewNotAttachedException() {
        super("Please call attachView() before proceeding!");
    }
}
