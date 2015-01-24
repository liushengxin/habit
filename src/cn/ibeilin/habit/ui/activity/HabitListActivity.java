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
import cn.ibeilin.habit.R;
import cn.ibeilin.habit.R.id;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.Group;
import com.avos.avoscloud.SaveCallback;

//ϰ���б�
public class HabitListActivity extends BaseActivity implements  OnItemClickListener {

     
	
     private static final String TAG = HabitListActivity.class.getName();
     ListView habitListView;
	 List <Habit> habits=new ArrayList <Habit>();
	 HabitsAdapter habitAdapter;
	
	 
	 protected void onCreate(Bundle savedInstanceState) {		    
		    super.onCreate(savedInstanceState);
		    setContentView(R.layout.activity_habit_list);
		    Log.i("leo","13");
		   initListView();			 
		   
		   
		   refresh();                                //����ListView	 
		   initActionBar("ϰ���б�");
	  }
	
	  private void initListView() {	 
	    habitAdapter = new HabitsAdapter(ctx, habits);
	    habitListView=(ListView)findViewById(id.habitslist);
	    habitListView.setAdapter(habitAdapter);
		habitListView.setOnItemClickListener(this);
	}
	  
	  private void refresh() {
		    new SimpleNetTask(ctx) {
		      List<Habit> subHabits;
		      
		    @Override
			protected void doInBack() throws Exception {
			    subHabits = HabitService.findHabits();
			}
		     
		    
			@Override
			protected void onSucceed() {
				Log.i("leo", "���³ɹ�");
				habits.clear();
				habits.addAll(subHabits);
				//App.registerHabitsCache(habits);
				habitAdapter.notifyDataSetChanged();
			}
		}.execute();
	} 
	
	
	@Override
        	public void onItemClick(AdapterView<?> parent, View view, int position,
		    	long id) {
	         Habit habit= (Habit) parent.getAdapter().getItem(position);
	         Toast.makeText(this, "����:ѡ�˵�"+position+"��", Toast.LENGTH_SHORT).show();
	         
	         Intent intent = new Intent(this, HabitDetailActivity.class);
	         intent.putExtra(HabitDetailActivity.HABIT_ID, habit.getObjectId());
	         intent.putExtra(HabitDetailActivity.HABIT_NAME, habit.getName());
	         startActivity(intent);
	}
		  }

