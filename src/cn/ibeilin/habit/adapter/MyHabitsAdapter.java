package cn.ibeilin.habit.adapter;

import java.util.List;

 


 





import cn.ibeilin.habit.entity.Habit;
import cn.ibeilin.habit.entity.MyHabit;
import cn.ibeilin.habit.ui.view.ViewHolder;
import cn.ibeilin.habit.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

	public class MyHabitsAdapter extends BaseListAdapter<MyHabit> {

	  Context mContext;

	  public MyHabitsAdapter(Context ctx, List<MyHabit> habits) {
		    super(ctx, habits);
	  }

	 public View getView(int position, View conView, ViewGroup parent) {
		    if (conView == null) {
		      //conView = View.inflate(ctx, R.layout.group_item,null);
		      conView = inflater.inflate(R.layout.view_habit_list_name, null, false);
		    }
		    TextView nameView = ViewHolder.findViewById(conView, R.id.text);
		    MyHabit habit = datas.get(position);
		    nameView.setText(habit.getName());
		    return conView;
		  }
	};