package cn.ibeilin.habit.ui.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.view.WindowManager;
import cn.ibeilin.habit.base.App;
import cn.ibeilin.habit.util.Utils;



public class BaseActivity extends FragmentActivity {
  Activity ctx;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    ctx = this;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {     
    switch (item.getItemId()) {
      case android.R.id.home:
        super.onBackPressed();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  void hideSoftInputView() {
    Utils.hideSoftInputView(this);
  }

  void setSoftInputMode() {
    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
  }
  /**
   * 无参数的初始化动作 栏
   */
  void initActionBar(){
    initActionBar(null);
  }
   /**
    * 初始化动作栏，以一字符串作为标题
    */
  void initActionBar(String title) { 
    ActionBar actionBar = getActionBar();
    if(title!=null){
      actionBar.setTitle(title);
    }
    actionBar.setDisplayUseLogoEnabled(false);
    actionBar.setDisplayHomeAsUpEnabled(true);
  }

  void initActionBar(int id){
    initActionBar(App.ctx.getString(id));
  }
}
