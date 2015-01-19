package cn.ibeilin.habit.service;

import java.util.ArrayList;
import java.util.List;
import cn.ibeilin.habit.entity.Habit;
import cn.ibeilin.habit.entity.User;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVRelation;
public class UserService {
	//传入Habits的Id 加入该习惯
	public static void joinHabit(Habit habit) throws AVException{ 
	    User user =User.curUser();
		// habit=HabitService.getHabitById(habitId);
	    AVRelation<AVObject> relation =user.getRelation("join");
	    relation.add(habit);
		user.save();
	}
	
     public static List<AVObject> getMyHabits() throws AVException 
	  {   User user =User.curUser();
		  return  user.getRelation("join").getQuery().find();
	  }
	  
	
};
