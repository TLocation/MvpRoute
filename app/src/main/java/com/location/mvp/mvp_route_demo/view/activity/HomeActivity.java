package com.location.mvp.mvp_route_demo.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.location.mvp.mvp_route_demo.R;
import com.location.mvp.mvp_route_demo.adapter.HomeAdapter;
import com.location.mvp.mvp_route_demo.base.BaseToActivity;
import com.location.mvp.mvp_route_demo.bean.LoginResponse;
import com.location.mvp.mvp_route_demo.view.activity.adapteractivity.HomeAdaptrActivity;
import com.location.mvp.mvp_route_demo.view.activity.adapteractivity.SingleAdapterActivity;
import com.location.mvp.mvproutelibrary.base.BasePresenter;
import com.location.mvp.mvproutelibrary.base.Layout;
import com.location.mvp.mvproutelibrary.adapter.OnHeaderClickListener;
import com.location.mvp.mvproutelibrary.adapter.OnItemClickListener;
import com.location.mvp.mvproutelibrary.adapter.BaseViewHolder;
import com.location.mvp.mvproutelibrary.error.ExceptionHandle;
import com.location.mvp.mvproutelibrary.utils.DividerItemDecoration;
import com.location.mvp.mvproutelibrary.utils.LogUtils;


import java.util.Arrays;
import java.util.List;

/**
 * @author location
 *         创建时间: 2018/7/21 0021 16:46
 *         修改人:
 *         修改内容:
 *         修改时间:
 */

@Layout(R.layout.activity_home)
public class HomeActivity extends BaseToActivity implements OnItemClickListener {
	private RecyclerView recyclerView;
	private HomeAdapter homeAdapter;

	@Override
	public void onShowError(ExceptionHandle.ResponseThrowable baseThrowable) {

	}


	@Override
	protected String getTooBarTitle() {
		return "Mvproute简易的项目框架";
	}

	@Override
	protected void initView(@Nullable Bundle savedInstanceState) {
		super.initView(savedInstanceState);
		findViewById(R.id.bar_back).setVisibility(View.GONE);
		recyclerView = findViewById(R.id.home_RecyclerView);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration
				.VERTICAL_LIST, 5, Color.parseColor("#999999")));

		homeAdapter = new HomeAdapter(R.layout.item_home);
		homeAdapter.setEmptyView(R.layout.view_empty);
		recyclerView.setAdapter(homeAdapter);
		homeAdapter.setOnItemClickListener(this);
//		homeAdapter.setOnChildClickListener(R.id.id_1, new OnChildClickListener() {
//			@Override
//			public void onChildClcik(BaseViewHolder viewHolder, View view, int position) {
//
//			}
//		});
		homeAdapter.setOnHeaderClickListener(new OnHeaderClickListener() {
			@Override
			public void onHeaderClick(int layout, View view, @Nullable Object data, int position, boolean isHeader) {

			}
		});

	}

	@Override
	protected void loadData() {
		String[] stringArray = getResources().getStringArray(R.array.home_list);
		List<String> list = Arrays.asList(stringArray);
		homeAdapter.refresh(list);

		LoginResponse loginResponse = new LoginResponse();
	}

	@NonNull
	@Override
	protected BasePresenter createPresenter() {
		return null;
	}


	@Override
	public void onItemClick(BaseViewHolder viewHolder, View view, int position) {
		switch (position) {
			case 0:
				startActivity(HomeAdaptrActivity.class);
				break;
			case 1:
				startActivity(FragmentSimpleActivity.class);
				break;
			case 2:
				//Spanutils
				startActivity(SpanActivity.class);
				break;
			//BobPopwindow

			case 3:
				startActivity(BobActivity.class);
				break;
			case 4:
				startActivity(SpActivity.class);
				break;
			case 6:
				//activity回传分发
				startActivity(new Intent(this, ResultActivity.class));
				break;
			case 7:
				startActivity(NetActivity.class);
				break;
			case 8:
				startActivity(BaseExalpActivity.class);
				break;
			case 9:
				startActivity(GroupAdapterActivity.class);
//				startActivity(FoorActivity.class);
				break;
			case 10:
				startActivity(SingleAdapterActivity.class);
				break;
			default:
				LogUtils.d("未知");
		}
	}
}
