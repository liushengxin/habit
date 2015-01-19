package cn.ibeilin.habit.ui.activity;

import java.util.ArrayList;
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
import cn.ibeilin.habit.entity.MyHabit;
import cn.ibeilin.habit.entity.User;
import cn.ibeilin.habit.service.HabitService;
import cn.ibeilin.habit.service.MyHabitService;
import cn.ibeilin.habit.service.UserService;
import cn.ibeilin.habit.util.SimpleNetTask;
import cn.ibeilin.habit.util.Utils;

public class MyHabitDetailActivity extends BaseActivity implements View.OnClickListener  {

	public static final String HABIT_ID = "habitId";
	public static final String HABIT_NAME = "habitName";
	
	TextView habitNameView;
	TextView habitReecordView;
	Button  checkInBtn;
    User curuser;
    MyHabit myHabit;
    String myHabitId;
    String habitName;
    String habitRecord;
    
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_habits_detail);
		initData();  //��ʼ������
		findView();	 //�ҵ�View
		initView();  //��ʼ��View
		userLogin();
		initActionBar(App.ctx.getString(R.string.group));
		
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
	        Utils.toast("�ѵ�½"+User.getCurrentUser().getUsername());
	        }
	      }
	    }.execute();
	}
	
	
	private void initData() {
		myHabitId = getIntent().getStringExtra(HABIT_ID);
		habitName = getIntent().getStringExtra(HABIT_NAME);
		initActionBar(habitName);     //��ʼ�������� ����������Ϊϰ�ߵ�����
		//�ӻ�����MyHabit����
		myHabit=App.lookupMyHabit(myHabitId);  //�ӻ����л��
		habitName=myHabit.getName();
		habitRecord=myHabit.getRecord().toString();
	}
	
	
	private void findView(){
       habitNameView=(TextView) findViewById(id.tx_my_habit_detail_name);
       habitReecordView=(TextView)findViewById(id.tx_my_habit_record);
       checkInBtn=(Button) findViewById(id.btn_check_in);        //ǩ����ť  
       
	   checkInBtn.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			Utils.toast("ǩ��");
	    	checkInHabit();//ǩ��
	    	refresh();//ˢ��
			}
	   	});
	}
	
	private void initView() {	 
	    habitNameView.setText(habitName);
	    habitReecordView.setText(habitRecord);
	}

 //ˢ�½���
	private void refresh() {
		
	    updateMyHabit(); 	    
		initData();
		initView();
	
	}
	
	
	private void  checkInHabit() {
		new SimpleNetTask(ctx) {
		    @Override
			protected void doInBack() throws Exception {
		    	Log.i("leo", "����ǩ������");
		    	MyHabitService.CheckInById(myHabit.getObjectId());;	    	
			}	   
		    
			@Override
			protected void onSucceed() {
				Log.i("leo", "�����ɹ�");
			
				//habitAdapter.notifyDataSetChanged();
			}
		}.execute();
	}
	
	/*
	 *�������� Habit
	 * 
	*/
	private void updateMyHabit() {
		   
		new SimpleNetTask(ctx) {
		 
		    @Override
			protected void doInBack() throws Exception {
		    	myHabit = MyHabitService.getMyHabitById(myHabitId);
			}

			@Override
			protected void onSucceed() {	
				App.registerMyHabitCache(myHabit.getObjectId(), myHabit);
				Log.i("leo",App.lookupMyHabit(myHabitId).getRecord().toString());
			}
		}.execute();
	}
	
    public void onClick(View v) {
		    int id = v.getId();
		  }
};
