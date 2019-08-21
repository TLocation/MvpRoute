package com.location.mvp.mvp_route_demo.view.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.location.mvp.mvp_route_demo.R;
import com.location.mvp.mvp_route_demo.base.BaseToActivity;
import com.location.mvp.mvproutelibrary.adapter.BaseViewHolder;
import com.location.mvp.mvproutelibrary.adapter.BaseGroupAdapter;
import com.location.mvp.mvproutelibrary.adapter.OnGroupItemClickListener;
import com.location.mvp.mvproutelibrary.base.BasePresenter;
import com.location.mvp.mvproutelibrary.base.Layout;
import com.location.mvp.mvproutelibrary.error.ExceptionHandle;
import com.location.mvp.mvproutelibrary.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author tianxiaolong
 *         time：2019/1/10 0:27
 *         description：
 */
@Layout(R.layout.activity_group_recyclerview)
public class GroupAdapterActivity extends BaseToActivity {

	private Random random;
	private MyAdapter myAdapter;
private EditText numText;
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
		numText = findViewById(R.id.group_edittext);
		List<String> group = new ArrayList<>();
		List<List<String>> child = new ArrayList<>();
		random = new Random();
		for (int i = 0; i < 15; i++) {
			group.add("分组");
			List<String> list = new ArrayList<>();
			for (int j = 0; j < random.nextInt(20) + 1; j++) {
				list.add("child");
			}
			child.add(list);
		}
		myAdapter = new MyAdapter(R.layout.item_group, R.layout.item_child, group, child);
		recyclerView.setAdapter(myAdapter);
		recyclerView.getItemAnimator().setAddDuration(0);
		recyclerView.getItemAnimator().setRemoveDuration(0);
		recyclerView.getItemAnimator().setChangeDuration(0);
		recyclerView.getItemAnimator().setMoveDuration(0);
		myAdapter.setOnGroupClickListener(new OnGroupItemClickListener() {
			@Override
			public void onGroupItemClick(View itemView, int groupPosition) {
				ToastUtils.showShort("点击分组view");
			}

			@Override
			public void onChildItemClick(View itemView, int groupPosition, int childPosition) {
				ToastUtils.showShort("点击分组item");
			}
		});

	}

	public void addData(View view){

		myAdapter.loadGroup(1,"增加的分组头", Arrays.asList("1","2","#"),false);


	}
public void removeData(View view){
		myAdapter.removeGroup(1);
}
	@Override
	protected BasePresenter createPresenter() {
		return null;
	}



	class MyAdapter extends BaseGroupAdapter<String, String, BaseViewHolder> {

		public MyAdapter(int groupLayout, int childLayout, List<String> groupList, List<List<String>> childGroupList) {
			super(groupLayout, childLayout, groupList, childGroupList);
		}

		@Override
		public void onBindGroup(BaseViewHolder holder, String response, int groupPosition) {
                   holder.setText(R.id.item_group_name,response);


		}

		@Override
		public void onBindChild(BaseViewHolder holder, String response, int groupPosition, int childPosition) {
               holder.setText(R.id.item_child_name,response);
		}

		@Override
		public void showAnim(BaseViewHolder holder, boolean state) {
			Log.d(TAG, "调用动画方法  state===>" + state);
			ImageView imageView = holder.findViewById(R.id.group_image);
			ObjectAnimator rotation = ObjectAnimator.ofFloat(imageView, "rotation", 0.0f, -90.0f);
			rotation
					.setRepeatMode(ValueAnimator.REVERSE);
			rotation.setDuration(500);
			rotation.setRepeatCount(1);
			rotation.start();
		}
	}
public void refreshData(View view){
	myAdapter.refreshGroup(1,"新的内容",Arrays.asList("a","b","c"));



}
	public void close(View view){
		String s = numText.getText().toString();
		int num = Integer.parseInt(s);
		if(num>=5){
			return;
		}
		ToastUtils.showShort("关闭索引为"+num+"分组");
		myAdapter.close(num);
	}


	public void open(View view){
		String s = numText.getText().toString();
		int num = Integer.parseInt(s);
		if(num>=5){
			return;
		}
		ToastUtils.showShort("打开索引为"+num+"分组");
		myAdapter.open(num);
	}

	public void openAll(View view){
		myAdapter.openAll();
	}
	public void closeAll(View view){
		myAdapter.closeAll();
	}
}
