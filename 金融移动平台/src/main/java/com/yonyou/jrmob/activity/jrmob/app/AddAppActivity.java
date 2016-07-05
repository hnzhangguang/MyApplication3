package com.yonyou.jrmob.activity.jrmob.app;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yonyou.jrmob.R;
import com.yonyou.jrmob.base.BaseActivity;
import com.yonyou.jrmob.util.LogUtils;
import com.yonyou.jrmob.view.view.ToggleButton;
import com.yonyou.jrmob.view.view.ToggleButton.OnToggleStateChangeListner;
import com.yonyou.jrmob.view.view.ToggleButton.ToggleState;

/**
 * 功能: 添加应用界面
 * 
 * @author zhangg
 * 
 *         上午10:55:16
 */
public class AddAppActivity extends BaseActivity {

	TextView top_tv_title; // title
	ImageView top_img_back; // 返回键
	Button btn_close;
	ListView lv_addapp;
	ToggleButton switchs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initActionbar();
		setContentView(R.layout.activity_addapp);
		initView(); // 初始化控件
		initListener(); // 添加监听
		initData(); // 默认初始数据
	}

	@Override
	protected void initView() {
		super.initView();
		top_tv_title = (TextView) findViewById(R.id.top_tv_title);
		top_img_back = (ImageView) findViewById(R.id.top_img_back);
		btn_close = (Button) findViewById(R.id.btn_close);
		lv_addapp = (ListView) findViewById(R.id.lv_addapp);
		top_img_back.setVisibility(View.VISIBLE);
		btn_close.setVisibility(View.INVISIBLE);
		btn_close.setEnabled(false);
		top_tv_title.setText("添加应用");

	}

	@Override
	protected void initListener() {
		super.initListener();
		btn_close.setOnClickListener(this);
		top_img_back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.top_img_back: // 返回箭头
			finish(); // 直接关闭
			break;
		case R.id.btn_close: // 关闭按钮
			finish(); // 直接关闭
			break;

		default:
			break;
		}
	}

	@Override
	protected void initData() {
		super.initData();
		List<AddAppVO> list = new ArrayList<AddAppActivity.AddAppVO>();
		for (int i = 0; i < 8; i++) {
			list.add(new AddAppVO(null, "小呆系统们儿" + i, i + "", ToggleState.Open));
		}
		myAdapter adapter = new myAdapter(list);
		lv_addapp.setAdapter(adapter);

	}

	@Override
	public void backMethod() {
		finish(); // 物理返回键直接关闭掉
	}

	class AddAppVO {
		String iv_icon;
		String tv_appname;
		String app_key;
		// boolean switchss;
		ToggleState switchs;

		public AddAppVO() {
		}

		public AddAppVO(String iv_icon, String tv_appname, String app_key, ToggleState switchs) {
			this.app_key = app_key;
			this.tv_appname = tv_appname;
			this.iv_icon = iv_icon;
			// this.switchss = switchss;
			this.switchs = switchs;
		}

		public ToggleState getSwitchs() {
			return switchs;
		}

		public void setSwitchs(ToggleState switchs) {
			this.switchs = switchs;
		}

		public String getIv_icon() {
			return iv_icon;
		}

		public void setIv_icon(String iv_icon) {
			this.iv_icon = iv_icon;
		}

		public String getTv_appname() {
			return tv_appname;
		}

		public void setTv_appname(String tv_appname) {
			this.tv_appname = tv_appname;
		}

		public String getApp_key() {
			return app_key;
		}

		public void setApp_key(String app_key) {
			this.app_key = app_key;
		}

		// public boolean isSwitchss() {
		// return switchss;
		// }
		//
		// public void setSwitchss(boolean switchss) {
		// this.switchss = switchss;
		// }

	}

	class myAdapter extends BaseAdapter {

		List<AddAppVO> list;

		public myAdapter(List<AddAppVO> list) {
			this.list = list;
		}

		@Override
		public int getCount() {
			if (null == list) {
				return 0;
			}
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			final viewholder holder;
			if (convertView == null) {
				convertView = View
						.inflate(AddAppActivity.this, R.layout.activity_addapp_item, null);
				holder = new viewholder();
				holder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);// 图片
				holder.tv_appname = (TextView) convertView.findViewById(R.id.tv_appname);//
				holder.app_key = (TextView) convertView.findViewById(R.id.app_key);//
				// holder.switchss = (CheckBox)
				// convertView.findViewById(R.id.switchss); // result
				holder.switchs = (ToggleButton) convertView.findViewById(R.id.switchs); // result
				convertView.setTag(holder);
			} else {
				holder = (viewholder) convertView.getTag();
			}

			AddAppVO vo = list.get(position);
			holder.iv_icon.setBackground(getResources().getDrawable(R.drawable.ic_launcher));
			holder.tv_appname.setText(vo.getTv_appname());
			holder.app_key.setText(vo.getApp_key());
			// holder.switchss.setChecked(vo.isSwitchss());

			holder.switchs.setToggleState(ToggleState.Close);
			holder.switchs.setSlideBackgroudResource(R.drawable.switch_slide);
			holder.switchs.setToggleState(ToggleState.Open);
			holder.switchs.setSwitchBackgroudResource(R.drawable.switch_bg_off);
			holder.switchs.setSwitchBackgroudResource(R.drawable.switch_bg_on);
			holder.switchs.setOnToggleStateChangeListener(new OnToggleStateChangeListner() {
				@Override
				public void onToggleStateChange(ToggleState state) {
					if (ToggleState.Open == state) {
						LogUtils.e(state + "open");
						holder.switchs.setSwitchBackgroudResource(R.drawable.switch_bg_on);
					} else {
						LogUtils.e(state + "close");
						holder.switchs.setSwitchBackgroudResource(R.drawable.switch_bg_off);
					}
				}
			});

			return convertView;

		}
	}

	class viewholder {
		ImageView iv_icon;
		TextView tv_appname;
		TextView app_key;
		// CheckBox switchss;
		ToggleButton switchs;

	}

}
