package cn.ibeilin.habit.base;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.service.textservice.SpellCheckerService.Session;
import cn.ibeilin.habit.entity.Habit;
import cn.ibeilin.habit.entity.User;
import cn.ibeilin.habit.util.AVOSUtils;
import cn.ibeilin.habit.util.Logger;
import cn.ibeilin.habit.util.PhotoUtil;

import com.avos.avoscloud.*;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.util.*;


public class App extends Application {
 public static final String DB_NAME = "chat.db3";
  public static final int DB_VER = 2;
  public static boolean debug = false;
  public static App ctx;
  public static Session session;
  private static Map<String, User> usersCache = new HashMap<String, User>();
  public static  Map<String, Habit> habitsCache = new HashMap<String,Habit>();
 // List<User> friends = new ArrayList<User>();

  @Override
  public void onCreate() {
    super.onCreate();
    ctx = this;                                                                //得到context
  //  Utils.fixAsyncTaskBug();
    AVOSCloud.initialize(this, "6qru9in7y91jlun3a7hkm761wihgycjh3l6lm9hv9xy3x0rz",
        "p4s3in2tdhks9giwldiu1zd399eqraa7koll384lxzko9mww");
    User.registerSubclass(User.class);     
    AVObject.registerSubclass(Habit.class);
        
    AVInstallation.getCurrentInstallation().saveInBackground();
   // PushService.setDefaultPushCallback(ctx, LoginActivity.class);
    AVOSUtils.showInternalDebugLog();
    if (App.debug) {
      Logger.level = Logger.VERBOSE;
    } else {
      Logger.level = Logger.NONE;
    }
    //initImageLoader(ctx);
    
   // openStrictMode();
  }

  
  public void openStrictMode() {
    if (App.debug) {
      StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
          .detectDiskReads()
          .detectDiskWrites()
          .detectNetwork()   // or .detectAll() for all detectable problems
          .penaltyLog()
          .build());
      
      StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
          .detectLeakedSqlLiteObjects()
          .detectLeakedClosableObjects()
          .penaltyLog()
              //.penaltyDeath()
          .build());
    }
  }

 
  public static App getInstance() {
    return ctx;
  }

  
  /**
   * 鍒濆鍖朓mageLoader
   */
  
  public static void initImageLoader(Context context) {
    File cacheDir = StorageUtils.getOwnCacheDirectory(context,
        "leanchat/Cache");
    ImageLoaderConfiguration config = PhotoUtil.getImageLoaderConfig(context, cacheDir);
    // Initialize ImageLoader with configuration.
    ImageLoader.getInstance().init(config);
  }

  public static User lookupUser(String userId) {
    return usersCache.get(userId);
  }

  
  //记录用户缓存
  public static void registerUserCache(String userId, User user) {
    usersCache.put(userId, user);
  }

  public static void registerUserCache(User user) {
    registerUserCache(user.getObjectId(), user);
  }
  
  
  
 
  
  public static void registerBatchUserCache(List<User> users) {
    for (User user : users) {
      registerUserCache(user);
    }
  }
  
  
  
  //记录Habit缓存
  public static void registerHabitsCache(List<Habit> habits) {
	    for (Habit habit: habits) {
	      habitsCache.put(habit.getObjectId(),habit);
	    }
	  }
  
  
  //查找Habit缓存
  public static Habit lookupHabit(String habitId) {
	    return habitsCache.get(habitId);
	  }



}
