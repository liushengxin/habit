package cn.ibeilin.habit.service;

 
import cn.ibeilin.habit.base.App;
import cn.ibeilin.habit.entity.Habit;

import com.avos.avoscloud.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzw on 14-10-8.
 */
public class HabitService {

	public static List<Habit> findHabits() throws AVException {

		AVQuery<Habit> q = AVObject.getQuery(Habit.class); // 查询所有的Habit数据
		q.setCachePolicy(AVQuery.CachePolicy.NETWORK_ELSE_CACHE);
		// //LeanCloud 提供了几种不同的缓存策略：
		// IGNORE_CACHE ： 默认的缓存策略，查询不走缓存，查询结果也不存储在缓存。
		// CACHE_ONLY ： 查询只从缓存获取，不走网络。如果缓存中没有结果，引发一个 AVException。
		// NETWORK_ONLY ： 查询不走缓存，从网路中获取，但是查询结果会写入缓存。
		// CACHE_ELSE_NETWORK ： 查询首先尝试从缓存中获取，如果失败，则从网络获取，如果两者都失败，则引发一个
		// AVException。
		// NETWORK_ELSE_CACHE ： 查询首先尝试从网络获取，如果失败，则从缓存中查找；如果两者都失败，则应发一个
		// AVException。
		// CACHE_THEN_NETWORK ： 查询首先尝试从缓存中获取，然后再从网络获取。在这种情况
		List<Habit> habits = new ArrayList<>();
		habits = q.find();
		App.registerBatchHabitsCache(habits);
		return habits;

	}

	/*
	 * 根据ObjectId 得到一个习惯类
	 */
	public static Habit getHabitById(String habitId) throws AVException {

		AVQuery<Habit> q = AVObject.getQuery(Habit.class);
		q.setCachePolicy(AVQuery.CachePolicy.NETWORK_ELSE_CACHE);
		return q.get(habitId);
	}

};
