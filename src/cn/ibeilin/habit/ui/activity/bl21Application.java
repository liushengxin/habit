package cn.ibeilin.habit.ui.activity;


import android.app.Application;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;


public class bl21Application extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    // 必需：初始化你的appid和appkey，保存installationid
    AVOSCloud.initialize(this, "6qru9in7y91jlun3a7hkm761wihgycjh3l6lm9hv9xy3x0rz", "p4s3in2tdhks9giwldiu1zd399eqraa7koll384lxzko9mww");
    //����δ����
    AVObject gameScore = new AVObject("GameScore");
    gameScore.put("score", 1200);
    gameScore.put("playerName", "steve");
    gameScore.put("level", 10);
    try {
        gameScore.save();
    } catch (AVException e) {
        // e.getMessage() ������쳣��Ϣ
    }
  }
}
