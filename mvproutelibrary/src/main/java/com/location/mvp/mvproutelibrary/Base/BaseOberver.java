package com.location.mvp.mvproutelibrary.Base;

import android.util.Log;

import com.location.mvp.mvproutelibrary.manager.RxManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 项目名称: MvpRoute
 * 类描述:
 * 创建人: 田晓龙
 * 创建时间: 2018/5/13 0013 21:49
 * 修改人:
 * 修改内容:
 * 修改时间:
 */


public abstract class BaseOberver<T> implements Observer<T> {
    private RxManager rxManager;

    public BaseOberver(RxManager rxManager) {
        this.rxManager = rxManager;
    }

    @Override
    public void onSubscribe(Disposable d) {
        rxManager.add(d);
    }

    @Override
    public void onError(Throwable e) {
        Log.e("TAG", "error===>" + e.getMessage());
    }

    @Override
    public void onComplete() {

    }
}
