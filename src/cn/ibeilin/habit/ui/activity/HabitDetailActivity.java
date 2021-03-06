package cn.ibeilin.habit.ui.activity;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
 
import android.widget.Toast;
import cn.ibeilin.habit.R;
import cn.ibeilin.habit.R.id;
import cn.ibeilin.habit.base.App;
import cn.ibeilin.habit.entity.Habit;
import cn.ibeilin.habit.entity.User;
import cn.ibeilin.habit.service.HabitService;
import cn.ibeilin.habit.service.UserService;
import cn.ibeilin.habit.util.SimpleNetTask;
import cn.ibeilin.habit.util.Utils;

public class HabitDetailActivity extends BaseActivity implements View.OnClickListener  {

	public static final String HABIT_ID = "habitId";
	public static final String HABIT_NAME = "habitName";
	
	Button joinHabitBtn;
	TextView habitNameView;
	TextView habitContentView;
    User curuser;
    Habit habit;
    String habitId;
   
    String habitName;
    String habitContent;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_habit_detail);
		initData();  //初始化数据
		findView();	 //找到View
		initView();  //初始化View
	//	refresh();   //刷新数据
		userLogin();
		//initActionBar(App.ctx.getString(R.string.group));
		//GroupMsgReceiver.addListener(this);
	}
	
	public void  userLogin()
	{

	    new cn.ibeilin.habit.util.NetAsyncTask(ctx) {
	      @Override
	      protected void doInBack() throws Exception {
	        User.logIn("1", "1");
	      }

	      @Override
	      protected void onPost(Exception e) {
	        if (e != null) {
	          Utils.toast(e.getMessage());
	        } else {
	        Utils.toast("已登陆"+User.getCurrentUser().getUsername());
	        }
	      }
	    }.execute();
		
		
	}
	
	
	
	
	private void initData() {
		habitId = getIntent().getStringExtra(HABIT_ID);
		habitName = getIntent().getStringExtra(HABIT_NAME);
		initActionBar(habitName); //初始化动作栏 将标题设置为习惯的名字
		//从缓存获得habit对象
		habit=App.lookupHabit(habitId);
		habitName=habit.getName()+1;
		habitContent=habit.getContent();
		
	}
	
	private void findView() {
       habitNameView=(TextView) findViewById(id.tx_habit_detail_name);
       habitContentView=(TextView)findViewById(id.tx_habit_detail_content);
       joinHabitBtn=(Button) findViewById(id.btn_join_habit);    
       joinHabitBtn.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Utils.toast("准备加入习惯");
	    	joinHabit();
		}
	});
	}
	
	private void initView() {
	 
	    habitNameView.setText(habitName);
	    habitContentView.setText(habitContent);
	}
	

	private void refresh() {
	    
		
		
	}
	
	
	private void  joinHabit() {
		new SimpleNetTask(ctx) {
		    @Override
			protected void doInBack() throws Exception {
		    	Utils.toast("开始joinHabit");
		    	UserService.joinHabit(habit);
			}
		      
			@Override
			protected void onSucceed() {
				Log.i("leo", "加入一习惯成功");
				Utils.toast("加入成功");
				//habitAdapter.notifyDataSetChanged();
			}
		}.execute();
		
	}
	
	
    public void onClick(View v) {
		    int id = v.getId();
		
		  }

};
