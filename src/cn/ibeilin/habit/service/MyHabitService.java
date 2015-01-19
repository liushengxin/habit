package cn.ibeilin.habit.service;

 
import android.util.Log;
import cn.ibeilin.habit.base.App;
import cn.ibeilin.habit.entity.Habit;
import cn.ibeilin.habit.entity.MyHabit;

import com.avos.avoscloud.*;
import com.avos.avoscloud.LogUtil.log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

 
/**
 * Created by lsx on 2015-1-19 .
 */

public class MyHabitService {

	public static List<MyHabit> getAllMyHabits()  {	
		List<MyHabit> habits = new ArrayList<MyHabit>();
		AVQuery<MyHabit> q = AVObject.getQuery(MyHabit.class); 
		q.whereEqualTo("user", "11111");		
		q.setCachePolicy(AVQuery.CachePolicy.NETWORK_ELSE_CACHE);
		try {
			habits = q.find();
			Log.i("leo","���ϰ�߳ɹ�");;
		} catch (AVException e) {
			Log.i("leo","��ѯʧ��");;
		}	
		return habits;	
	}
	
	
	public static MyHabit getMyHabitById(String theObjectId)  {	
	    MyHabit myHabit =  new MyHabit();
		AVQuery<MyHabit> q = AVObject.getQuery(MyHabit.class); 
		q.setCachePolicy(AVQuery.CachePolicy.NETWORK_ELSE_CACHE);
		try {
			myHabit = q.get(theObjectId);
			Log.i("leo","���ϰ�߳ɹ�");;
		} catch (AVException e) {
			Log.i("leo","��ѯʧ��");;
		}	
		return myHabit;	
	}
	
	
	/*=
	 * 
	 * ǩ�� 
	 * ���ҵ�����Id�Ҵ�MyHabit��Ȼ��CheckIn()
	 */
	public static void CheckInById(String habitId) throws AVException {

		AVQuery<MyHabit> q = AVObject.getQuery(MyHabit.class);
		q.setCachePolicy(AVQuery.CachePolicy.CACHE_ELSE_NETWORK); 
		MyHabit myHabit=q.get(habitId);
		myHabit.checkIn();
		myHabit.save();
		Log.i("leo","ǩ������ѱ���");;
		
	}

};