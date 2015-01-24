package cn.ibeilin.habit.ui.activity;
import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import cn.ibeilin.habit.R;
import cn.ibeilin.habit.adapter.StatusAdapter;
import cn.ibeilin.habit.entity.Status;
import cn.ibeilin.habit.service.StatusService;
import cn.ibeilin.habit.util.SimpleNetTask;
 
 public class StatusListActivity extends BaseActivity implements OnItemClickListener {
	
	private static final String TAG = StatusListActivity.class.getName();
	ListView statusListView;
	List<Status> statuses = new ArrayList<Status>();
	StatusAdapter statusAdapter;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_status_list);
		Log.i("leo", "13");
		initActionBar("status列表");
		initListView();
		refresh(); // 更新ListView	
		
	}
	
	private void initListView() {
	 
   statusAdapter = new StatusAdapter(ctx, statuses);
   statusListView=(ListView)findViewById(R.id.status_list);
   statusListView.setAdapter(statusAdapter);
   statusListView.setOnItemClickListener(this);
}

	private void refresh() {
		new SimpleNetTask(ctx) {
			List<cn.ibeilin.habit.entity.Status> subStatuses;
			@Override
			protected void doInBack() throws Exception {
				subStatuses = StatusService.getStatusDatas(1000, 5);
			}

			@Override
			protected void onSucceed() {
				Log.i("leo", "更新Status成功");
				statuses.clear();
				statuses.addAll(subStatuses);
				// App.registerHabitsCache(statuss);
				statusAdapter.notifyDataSetChanged();
			}
		}.execute();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Status status = (Status) parent.getAdapter().getItem(position);
		Toast.makeText(this, "测试:选了" + status.getObjectId() + "个", Toast.LENGTH_SHORT)
				.show();

		/*Intent intent = new Intent(this, HabitDetailActivity.class);
		intent.putExtra(HabitDetailActivity.HABIT_ID, status.getObjectId());
		intent.putExtra(HabitDetailActivity.HABIT_NAME, status.getName());
		startActivity(intent);*/
	}


}
