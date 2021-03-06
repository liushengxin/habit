package cn.ibeilin.habit.base;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.service.textservice.SpellCheckerService.Session;
import cn.ibeilin.habit.entity.Habit;
import cn.ibeilin.habit.entity.MyHabit;
import cn.ibeilin.habit.entity.Status;
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
  public static  Map<String, MyHabit> MyhabitsCache = new HashMap<String,MyHabit>();
 // List<User> friends = new ArrayList<User>();

  @Override
  public void onCreate() {
    super.onCreate();
    ctx = this;                                                                //�õ�context
  //  Utils.fixAsyncTaskBug();
    
    User.registerSubclass(User.class);     
    AVObject.registerSubclass(Habit.class);
    AVObject.registerSubclass(MyHabit.class);
    AVObject.registerSubclass(Status.class);
    
    
    AVOSCloud.initialize(this, "6qru9in7y91jlun3a7hkm761wihgycjh3l6lm9hv9xy3x0rz",
        "p4s3in2tdhks9giwldiu1zd399eqraa7koll384lxzko9mww");
   
        
    AVInstallation.getCurrentInstallation().saveInBackground();
   // PushService.setDefaultPushCallback(ctx, LoginActivity.class);
    AVOSUtils.showInternalDebugLog();
    if (App.debug) {
      Logger.level = Logger.VERBOSE;
    } else {
      Logger.level = Logger.NONE;
    }
     initImageLoader(ctx);//��ʼ��imageloader
    
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
   * 初始化ImageLoader
   */
  
  public static void initImageLoader(Context context) {
    File cacheDir = StorageUtils.getOwnCacheDirectory(context,
        "ibeilin/Cache");
    ImageLoaderConfiguration config = PhotoUtil.getImageLoaderConfig(context, cacheDir);
    // Initialize ImageLoader with configuration.
    ImageLoader.getInstance().init(config);
  }

  public static User lookupUser(String userId) {
    return usersCache.get(userId);
  }

  
  //��¼�û�����
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
  
  
  
  
  
  //��¼Habit����
  public static void registerHabitCache(String habitId, Habit habit) {
	    habitsCache.put(habitId,habit);
	  }
  

  public static void registerBatchHabitsCache(List<Habit> habits) {
	    for (Habit habit: habits) {
	    registerHabitCache(habit.getObjectId(),habit);
	    }
	  }
  //����
  public static Habit lookupHabit(String habitId) {
	    return habitsCache.get(habitId);
	  }
  
 
  
 
  
  //��¼MyHabit����
  public static void registerMyHabitCache(String myHabitId, MyHabit myHabit) {
	    MyhabitsCache.put( myHabitId, myHabit);
	  }

  
  public static void registerBacthMyHabitCache(List<MyHabit> myHabits) {
	    for (MyHabit myHabit: myHabits) {
	    	registerMyHabitCache(myHabit.getObjectId(),myHabit);
	    }
	  }
  
  //����MyHabit����
  public static MyHabit lookupMyHabit(String habitId) {
	    return MyhabitsCache.get(habitId);
	  }

}
