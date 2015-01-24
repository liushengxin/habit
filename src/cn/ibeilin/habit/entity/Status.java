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
		// TODO �Զ����ɵķ������
		
		put(INNERSTATUS,avStatus);	
	}

	public void setDetail(AVObject avObject) {
		// TODO �Զ����ɵķ������
		put(DETAIL,avObject);	
		
	}

	public AVStatus getInnerStatus() {
		// TODO �Զ����ɵķ������	
		return (AVStatus) get(INNERSTATUS);
	}

	public AVObject getDetail() {
		
		
		return (AVObject) get(DETAIL);
	}

}
