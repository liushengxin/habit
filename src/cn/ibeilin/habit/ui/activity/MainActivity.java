package cn.ibeilin.habit.ui.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
  
import cn.ibeilin.habit.adapter.HabitsAdapter;
import cn.ibeilin.habit.base.App;
import cn.ibeilin.habit.entity.Habit;
import cn.ibeilin.habit.service.HabitService;
import cn.ibeilin.habit.util.Logger;
import cn.ibeilin.habit.util.SimpleNetTask;
import cn.ibeilin.habit.util.Utils;
import cn.ibeilin.habit.R;
import cn.ibeilin.habit.R.id;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.Group;
import com.avos.avoscloud.SaveCallback;

//习惯列表
public class MainActivity extends BaseActivity implements  OnClickListener {
	
     private static final String TAG = MainActivity.class.getName();
     Button btn1;
     Button btn2;
     Button btn3;
     Button btn4;
     Button btn5;
	 
     
	 protected void onCreate(Bundle savedInstanceState) {		    
		    super.onCreate(savedInstanceState);
		    setContentView(R.layout.activity_main);
		    findViews();			 
		    initViews();	                          
		   initActionBar("测试导航页");
	  }
	 


	private void findViews() {
		 btn1=(Button) findViewById(id.button1);
		 btn2=(Button) findViewById(id.button2);
		 btn3=(Button) findViewById(id.button3);
		 btn4=(Button) findViewById(id.button4);
	}
 

	
	private void initViews() {
	  btn1.setOnClickListener(this);
	  btn2.setOnClickListener(this);
	  btn3.setOnClickListener(this);
	  btn4.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		 Toast.makeText(this, "测试:选了第"+v.getId()+"个按钮", Toast.LENGTH_SHORT).show();
		switch (v.getId()) {
	      case R.id.button1:// 注册
	    	 //Utils.goActivity(this, clz);
	    	  break;
	      
	      case R.id.button2:// 登陆
	    	 // startActivity(this,);
	    	  break;
	      case R.id.button3:// 习惯列表
	    	  Utils.goActivity(this, HabitListActivity.class);
	    	  break;
	      case R.id.button4:// 我的习惯
	    	  Utils.goActivity(this, MyHabitsActivity.class);
	    	  break;
	      }
		}
	
};
	 
	 
