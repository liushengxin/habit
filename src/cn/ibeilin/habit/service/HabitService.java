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

		AVQuery<Habit> q = AVObject.getQuery(Habit.class); // ��ѯ���е�Habit����
		q.setCachePolicy(AVQuery.CachePolicy.NETWORK_ELSE_CACHE);
		// //LeanCloud �ṩ�˼��ֲ�ͬ�Ļ�����ԣ�
		// IGNORE_CACHE �� Ĭ�ϵĻ�����ԣ���ѯ���߻��棬��ѯ���Ҳ���洢�ڻ��档
		// CACHE_ONLY �� ��ѯֻ�ӻ����ȡ���������硣���������û�н��������һ�� AVException��
		// NETWORK_ONLY �� ��ѯ���߻��棬����·�л�ȡ�����ǲ�ѯ�����д�뻺�档
		// CACHE_ELSE_NETWORK �� ��ѯ���ȳ��Դӻ����л�ȡ�����ʧ�ܣ���������ȡ��������߶�ʧ�ܣ�������һ��
		// AVException��
		// NETWORK_ELSE_CACHE �� ��ѯ���ȳ��Դ������ȡ�����ʧ�ܣ���ӻ����в��ң�������߶�ʧ�ܣ���Ӧ��һ��
		// AVException��
		// CACHE_THEN_NETWORK �� ��ѯ���ȳ��Դӻ����л�ȡ��Ȼ���ٴ������ȡ�����������
		List<Habit> habits = new ArrayList<>();
		habits = q.find();
		App.registerBatchHabitsCache(habits);
		return habits;

	}

	/*
	 * ����ObjectId �õ�һ��ϰ����
	 */
	public static Habit getHabitById(String habitId) throws AVException {

		AVQuery<Habit> q = AVObject.getQuery(Habit.class);
		q.setCachePolicy(AVQuery.CachePolicy.NETWORK_ELSE_CACHE);
		return q.get(habitId);
	}

};
