package cn.ibeilin.habit.ui.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import cn.ibeilin.habit.adapter.MyHabitsAdapter;
import cn.ibeilin.habit.base.App;
import cn.ibeilin.habit.entity.MyHabit;
import cn.ibeilin.habit.service.MyHabitService;
import cn.ibeilin.habit.util.SimpleNetTask;
import cn.ibeilin.habit.R;
import cn.ibeilin.habit.R.id;

//习惯列表
public class MyHabitsActivity extends BaseActivity implements  OnItemClickListener {

     
	
     private static final String TAG = MyHabitsActivity.class.getName();
     ListView myHabitListView;
	 List<MyHabit> myHabits=new ArrayList <MyHabit>();
	 MyHabitsAdapter myHabitAdapter;
	
	 
	 protected void onCreate(Bundle savedInstanceState) {		    
		    super.onCreate(savedInstanceState);
		    setContentView(R.layout.activity_habit_list);
		   initListView();			 
		   refresh();                                //更新ListView	 
		   initActionBar("我的习惯");
	  }
	 
	  
	  private void initListView() {
		 
	    myHabitAdapter = new MyHabitsAdapter(ctx, myHabits);
	    myHabitListView=(ListView)findViewById(id.habitslist);
	    myHabitListView.setAdapter(myHabitAdapter);
		myHabitListView.setOnItemClickListener(this);
		
	}
	  
	  
	private void refresh() {
		    new SimpleNetTask(ctx) {
		      List<MyHabit> subHabits;
		    @Override
			protected void doInBack() throws Exception {
				subHabits = MyHabitService.getAllMyHabits();
			}

			@Override
			protected void onSucceed() {
				myHabits.clear();
				myHabits.addAll(subHabits);
				App.registerBacthMyHabitCache(subHabits);
				myHabitAdapter.notifyDataSetChanged();
			}
		}.execute();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		MyHabit habit = (MyHabit) myHabitAdapter.getItem(position);
		 Toast.makeText(this, "测试:选了"+habit.getName() ,11).show();
		
		Intent intent = new Intent(this, MyHabitDetailActivity.class);
		intent.putExtra(MyHabitDetailActivity.HABIT_ID, habit.getObjectId());
		intent.putExtra(MyHabitDetailActivity.HABIT_NAME, habit.getName());
		startActivity(intent);
	}

}
