package cn.ibeilin.habit.entity;


import java.util.HashMap;
import java.util.Map;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVCloud;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;

import android.R.string;


@AVClassName("Habit")

public class Habit extends AVObject {

	  static final String HABIT_CLASS = "Habit";  
	  
	  private static final String NAME_KEY = "name"; 
	  private static final String TYPE_KEY = "type";
	  private static final String CONTENT_KEY = "content";

	  
	//¹¹Ôìº¯Êý
	public void saveHabit(string name,string type)throws AVException
	{  
	  Map<String,Object> map=new HashMap<String, Object>();
	    map.put(NAME_KEY, name);
	    map.put(TYPE_KEY,type);
		AVCloud.callFunction("saveHabit",map);
	}
	
 
	public void setType(String content) {
	    this.put(CONTENT_KEY, content);
	  }
	
	
	public String getName() {
	    return this.getString(NAME_KEY);
	  }

	
	 public String getContent() {
		    return this.getString(CONTENT_KEY);
		  }

	 public String getType() {
		    return this.getString(TYPE_KEY);
		  }
	
	   }

 

 