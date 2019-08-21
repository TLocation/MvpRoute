/*
 * Copyright 2018 location
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.location.mvp.mvproutelibrary.base;

import android.app.Activity;
import android.content.Context;
import androidx.fragment.app.Fragment;

import com.location.mvp.mvproutelibrary.error.ExceptionHandle;
import com.location.mvp.mvproutelibrary.http.INetWorkLoadingView;
import com.location.mvp.mvproutelibrary.http.RetrofitClient;
import com.location.mvp.mvproutelibrary.manager.RxManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author location
 *         默认的RxJava  Observer 回调
 *         如果使用带进度的上传文件请使用  {@link BaseProgressObserver}
 *         The default RxJava Observer callback
 *         Network request callback{@link RetrofitClient}
 *         Use with progress if uploading and downloading are used{@link BaseProgressObserver}
 */


public abstract class BaseObserver<T> implements Observer<T> {
	private RxManager rxManager;
	private BaseView baseView;
	private INetWorkLoadingView loadingView;

	public BaseObserver(RxManager rxManager, BaseView baseView) {
		this.rxManager = rxManager;
		this.baseView = baseView;
		loadingView = RetrofitClient.getInstance().getLoadingView();
		if (loadingView != null) {
			Context context = null;
			if (baseView instanceof Activity) {
				context = (Activity) baseView;
			} else if (baseView instanceof Fragment) {
				if (((Fragment) baseView).getActivity() == null) {
					throw new RuntimeException("fragment must be bind Activity");
				}
				context = ((Fragment) baseView).getActivity();
			} else if (baseView instanceof android.app.Fragment) {
				if (((android.app.Fragment) baseView).getActivity() == null) {
					throw new RuntimeException("fragment must be bind Activity");
				}
				context = ((android.app.Fragment) baseView).getActivity();
			}
			if (context != null) {
				loadingView.createLoadingView(context);
			}
		}
	}

	@Override
	public void onSubscribe(Disposable d) {
		rxManager.add(d);
		if (baseView == null) {
			rxManager.clear();
		} else {
			loadingView.showLoading();
		}
	}

	@Override
	public final void onError(Throwable e) {
		if (e instanceof ExceptionHandle.ResponseThrowable) {
			baseView.onShowError((ExceptionHandle.ResponseThrowable) e);
		}
		if (loadingView != null) loadingView.dismissLoading();
		showError(e);
	}

	protected void showError(Throwable e) {}


	@Override
	public void onComplete() {
		if (loadingView != null) loadingView.dismissLoading();
	}
}
