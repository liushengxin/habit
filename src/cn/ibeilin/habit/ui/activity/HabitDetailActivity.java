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
		initData();  //��ʼ������
		findView();	 //�ҵ�View
		initView();  //��ʼ��View
		refresh();   //ˢ������
		//initActionBar(App.ctx.getString(R.string.group));
		//GroupMsgReceiver.addListener(this);
	}

	
	private void initView() {
	    initActionBar(habit.getName()); //��ʼ�������� ����������Ϊϰ�ߵ�����
		
	}



	private void initData() {
		habitId = getIntent().getStringExtra(HABIT_ID);
		 
		
	}



	private void findView() {
       TextView habitNameView=(TextView) findViewById(id.habit_name);
       Button joinHabitBtn=(Button) findViewById(id.join_habit_btn);     
	}

	private void refresh() {
		// TODO �Զ����ɵķ������
		
	}
	
	
	private void  JoinHabit() {
        
		
	}
	
    public void onClick(View v) {
		    int id = v.getId();
		    if (id == R.id.join_habit_btn) {
		    	  JoinHabit();
		   
		  }

}
