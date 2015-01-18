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
	
	//传入Habits的Id 加入该习惯
	public void developHabits(String habitsNames) throws AVException{ 
	    User user =User.curUser();
		AVRelation<AVObject> relation = user.getRelation("develop"); //获取 “养成（习惯）”关系表
		Habit habit=HabitService.getHabitById(habitsNames);
		relation.add(habit);
		user.saveInBackground();
		
	}

	
	
	
	
	
	
	
};
