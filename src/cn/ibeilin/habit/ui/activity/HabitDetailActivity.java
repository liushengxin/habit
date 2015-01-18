package cn.ibeilin.habit.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import cn.ibeilin.habit.R;
import cn.ibeilin.habit.R.id;
import cn.ibeilin.habit.entity.Habit;
import cn.ibeilin.habit.entity.User;

public class HabitDetailActivity extends BaseActivity implements View.OnClickListener  {

	private static final String HABIT_ID = "habitId";
    User curuser;
    String habitId;
    Habit habit;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_habit_detail);
		initData();  //初始化数据
		findView();	 //找到View
		initView();  //初始化View
		refresh();   //刷新数据
		//initActionBar(App.ctx.getString(R.string.group));
		//GroupMsgReceiver.addListener(this);
	}

	
	private void initView() {
	    initActionBar(habit.getName()); //初始化动作栏 将标题设置为习惯的名字
		
	}



	private void initData() {
		habitId = getIntent().getStringExtra(HABIT_ID);
		 
		
	}



	private void findView() {
       TextView habitNameView=(TextView) findViewById(id.habit_name);
       Button joinHabitBtn=(Button) findViewById(id.join_habit_btn);     
	}

	private void refresh() {
		// TODO 自动生成的方法存根
		
	}
	
	
	private void  JoinHabit() {
        
		
	}
	
    public void onClick(View v) {
		    int id = v.getId();
		    if (id == R.id.join_habit_btn) {
		    	  JoinHabit();
		   
		  }

}
