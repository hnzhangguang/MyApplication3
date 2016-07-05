package com.yonyou.jrmob.activity.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * 功能: 基类 fragment
 * @author zhangg
 * 
 *         下午5:20:56
 */
public abstract class BaseFragment extends Fragment {

	public Activity mActivity;
	public static SharedPreferences mShare = null;
	protected Toast mToast;

	// fragment创建
	@SuppressLint("ShowToast")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = getActivity();
		mToast = Toast.makeText(mActivity, "???", Toast.LENGTH_LONG);
		// 初始化
		mShare = mActivity.getSharedPreferences("scheduleshared",
				Context.MODE_WORLD_WRITEABLE);
	}

	// 处理fragment的布局
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return initViews();
	}

	// 依附的activity创建完成
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		initData();
	}

	/**
	 * 清空本地值
	 * 
	 * @param key
	 */
	public static void clearLocationFile(String key) {
		Editor edit = mShare.edit();
		edit.remove(key);
		edit.commit();
	}

	/**
	 * 本地取值
	 * 
	 * @param key
	 * @return
	 */
	protected static String readLocationValue(String key) {
		String value = mShare.getString(key, "");
		return value;
	}

	// 子类必须实现初始化布局的方法
	public abstract View initViews();

	// 初始化数据, 可以不实现
	public void initData() {

	}

}