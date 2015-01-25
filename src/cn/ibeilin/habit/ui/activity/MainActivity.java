package cn.ibeilin.habit.ui.activity;

import javax.security.auth.callback.Callback;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import cn.ibeilin.habit.entity.User;
import cn.ibeilin.habit.ui.activity.test.VideoActivity;
import cn.ibeilin.habit.util.Utils;

//习惯列表
public class MainActivity extends BaseActivity implements  OnClickListener {
	 
	
	 
	 Class testActvts[]={
			            RegisterActivity.class,
			            HabitListActivity.class,
			 			HabitListActivity.class,
			 			HabitListActivity.class, 
			 			MyHabitsActivity.class, 
			 			VideoActivity.class,
			 			SendStatusActivity.class,
			 			StatusListActivity.class};

	 String testName[]={"注册",
			            "暫無",
			 			"暫無",
			            "習慣列表",
			            "我的習慣",
			            "gift測試",
			            "發送Status",
			            "Status列表"};

	 
	 protected void onCreate(Bundle savedInstanceState) {		    
		    super.onCreate(savedInstanceState);
		    initViews();	                          
		    initActionBar("测试导航页");
		    User.logInInBackground("1", "1",new LogInCallback<AVUser>() {		
			@Override
				public void done(AVUser arg0, AVException arg1) {
					 Utils.toast("登陆成功");
					
				}
			});
	 }
	 
	private void initViews() {	 
		    final LinearLayout layout2=new LinearLayout(this);  
	        layout2.setOrientation(LinearLayout.VERTICAL);  
	        setContentView(layout2); 
       
	        
	        for(int i=0;i<testActvts.length;i++)
	        {  Button btn=new Button(this);          
	           btn.setText(testName[i]);
	           btn.setTag(i);
	           btn.setOnClickListener(this);
	           layout2.addView(btn);     
	        }
	         setContentView(layout2);  
	}
	
	@Override
	public void onClick(View v) {
		
		 int tag = (Integer) v.getTag();
		 Toast.makeText(this, "测试:选了tag="+tag+"按钮", Toast.LENGTH_SHORT).show();
		 Utils.goActivity(this, testActvts[tag]);
		}
};
	 
	 
