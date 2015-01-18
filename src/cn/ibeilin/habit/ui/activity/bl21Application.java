package cn.ibeilin.habit.ui.activity;


import android.app.Application;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;


public class bl21Application extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    // 蹇呴渶锛氬垵濮嬪寲浣犵殑appid鍜宎ppkey锛屼繚瀛榠nstallationid
    AVOSCloud.initialize(this, "6qru9in7y91jlun3a7hkm761wihgycjh3l6lm9hv9xy3x0rz", "p4s3in2tdhks9giwldiu1zd399eqraa7koll384lxzko9mww");
    //以下未测试
    AVObject gameScore = new AVObject("GameScore");
    gameScore.put("score", 1200);
    gameScore.put("playerName", "steve");
    gameScore.put("level", 10);
    try {
        gameScore.save();
    } catch (AVException e) {
        // e.getMessage() 捕获的异常信息
    }
  }
}
