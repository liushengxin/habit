package cn.ibeilin.habit.entity;

import java.util.HashMap;
import java.util.Map;

import android.R.string;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVCloud;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;

@AVClassName("MyHabit")
public class MyHabit extends AVObject {
	private static final String NAME_KEY = "name";
	private static final String HABIT_ID = "habitId";
	private static final String RECORD_KEY = "record";

	public MyHabit() {

	}

	public void getByHabit(Habit habit) // 通过一个Habit对象 构造MyHabit
	{
		setName(habit.getName());
		
	}

	public void setName(String name) {
		put(NAME_KEY, name);
	}

	public String getName() {
		return this.getString(NAME_KEY);
	}

	public void setRecord(String record) {
		put(RECORD_KEY, record);
	}
	

	public Number getRecord() {
		return this.getNumber(RECORD_KEY);
	}

	public void checkIn() {
	   increment(RECORD_KEY);
	}
	
	
};
