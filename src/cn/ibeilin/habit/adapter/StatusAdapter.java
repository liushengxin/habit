package cn.ibeilin.habit.adapter;

import java.util.List;

import cn.ibeilin.habit.entity.Habit;
import cn.ibeilin.habit.entity.Status;
import cn.ibeilin.habit.ui.view.ViewHolder;
import cn.ibeilin.habit.util.PhotoUtil;
import cn.ibeilin.habit.R;
import android.content.Context;
import android.util.TimeUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

	public class StatusAdapter extends BaseListAdapter<Status> {

	  Context mContext;

	  public StatusAdapter(Context ctx, List<Status> status) {
		    super(ctx, status);    
	  }
	  
	public View getView(int position, View conView, ViewGroup parent) {
		if (conView == null) {
			conView = inflater.inflate(R.layout.view_item_status_list, null,
					false);
		}

		TextView timeTV = ViewHolder.findViewById(conView, R.id.status_time_tv);
		TextView contentTV = ViewHolder.findViewById(conView,
				R.id.status_content_tv);
		ImageView imageIV = ViewHolder.findViewById(conView,
				R.id.status_image_iv);
		ImageView avatarTV = ViewHolder.findViewById(conView,
				R.id.avatar_status_iv);

		
		Status status = datas.get(position);

		// 从status对象中 get数据
		String statusMessage = status.getInnerStatus().getMessage();
		String imageUrl = status.getInnerStatus().getImageUrl();
		String statusTime = status.getInnerStatus().getCreatedAt().toString();

		
		// 显示内容
		contentTV.setText(statusMessage);
		// 设置图片
		PhotoUtil.displayImageByUri(imageIV, "", imageUrl);
		// 显示时间
		timeTV.setText(statusTime);
		//显示头像
	
		return conView;	
	  }
	
	}
	 
	
	 
 