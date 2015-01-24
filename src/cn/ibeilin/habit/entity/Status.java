package cn.ibeilin.habit.entity;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVStatus;

 
@AVClassName("_Status")
public class Status extends AVObject {
	
	 public static final String DETAIL="detail";
     public static final String INNERSTATUS="innerStatus";
    
     public Status (){
    	 
     }

	public void setInnerStatus(AVStatus avStatus) {
		// TODO 自动生成的方法存根
		
		put(INNERSTATUS,avStatus);	
	}

	public void setDetail(AVObject avObject) {
		// TODO 自动生成的方法存根
		put(DETAIL,avObject);	
		
	}

	public AVStatus getInnerStatus() {
		// TODO 自动生成的方法存根	
		return (AVStatus) get(INNERSTATUS);
	}

	public AVObject getDetail() {
		
		
		return (AVObject) get(DETAIL);
	}

}
