package com.location.mvp.mvp_route_demo.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.location.mvp.mvp_route_demo.R;
import com.location.mvp.mvp_route_demo.base.BaseToActivity;
import com.location.mvp.mvproutelibrary.adapter.BaseViewHolder;
import com.location.mvp.mvproutelibrary.adapter.GroupBaseAdapter;
import com.location.mvp.mvproutelibrary.base.BasePresenter;
import com.location.mvp.mvproutelibrary.base.Layout;
import com.location.mvp.mvproutelibrary.error.ExceptionHandle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tianxiaolong
 *         time：2019/1/10 0:27
 *         description：
 */
@Layout(R.layout.activity_group_recyclerview)
public class GroupAdapterActivity extends BaseToActivity {
	@Override
	protected String getTooBarTitle() {
		return "v1.0.3版本测试分组模式";
	}
private RecyclerView recyclerView;
	@Override
	public void onShowError(ExceptionHandle.ResponseThrowable throwable) {

	}

	@Override
	protected void loadData() {

	}

	@Override
	protected void initView(@Nullable Bundle savedInstanceState) {
		super.initView(savedInstanceState);
		recyclerView = findViewById(R.id.main_recyclerView);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		List<String> group = new ArrayList<>();
		List<List<String>> child = new ArrayList<>();
		for(int i=0;i<20;i++){
			group.add("dsa");
			List<String> list = new ArrayList<>();
			for(int j=0;j<10;j++){
				list.add("dsa");
			}
			child.add(list);
		}
		MyAdapter myAdapter = new MyAdapter(R.layout.item_group,R.layout.item_child,group,child);
		recyclerView.setAdapter(myAdapter);

	}

	@Override
	protected BasePresenter createPresenter() {
		return null;
	}

	class MyAdapter extends GroupBaseAdapter<String,String,BaseViewHolder>{

		public MyAdapter(int groupLayout, int childLayout, List<String> groupList, List<List<String>> childGroupList) {
			super(groupLayout, childLayout, groupList, childGroupList);
		}

		@Override
		public void onBindGroup(String response, int groupPosition) {

		}

		@Override
		public void onBindChild(String response, int groupPosition, int childPosition) {

		}


	}
}