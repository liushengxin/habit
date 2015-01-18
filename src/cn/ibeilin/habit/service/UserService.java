package cn.ibeilin.habit.service;

import java.util.List;

import cn.ibeilin.habit.entity.Habit;
import cn.ibeilin.habit.entity.User;
import cn.ibeilin.habit.service.HabitService;



import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVRelation;
import com.avos.avoscloud.AVUser;
public class UserService {
	
	//����Habits��Id �����ϰ��
	public void developHabits(String habitsNames) throws AVException{ 
	    User user =User.curUser();
		AVRelation<AVObject> relation = user.getRelation("develop"); //��ȡ �����ɣ�ϰ�ߣ�����ϵ��
		Habit habit=HabitService.getHabitById(habitsNames);
		relation.add(habit);
		user.saveInBackground();
		
	}

	
	
	
	
	
	
	
};
