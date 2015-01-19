package cn.ibeilin.habit.entity;

import java.util.ArrayList;
import java.util.List;

import cn.ibeilin.habit.base.App;
import cn.ibeilin.habit.R;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVGeoPoint;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVRelation;
import com.avos.avoscloud.AVUser;
 

/**
 * Created by lzw on 14-6-26.
 */
@AVClassName("_User")
public class User extends AVUser {
  public static final String USERNAME = "username";
  public static final String PASSWORD = "password";
  public static final String AVATAR = "avatar";
  public static final String FRIENDS = "friends";
  public static final String LOCATION = "location";
  public static final String SEX = "sex";
  //AVFile avatar;
  //AVGeoPoint location;
  private String sortLetters;
  //private boolean sex;// true is male ; false is female
  public static User curUser;

  public User() {
  }

  public static User curUser() {
    if (curUser == null) {
      curUser = getCurrentUser(User.class);
    }
    return curUser;
    /*AVUser avUser = getCurrentUser(User.class);
    User user = User.cast(avUser, User.class);
    return user;*/
    //User user = getCurrentUser(User.class);
    //return user;
  }

  public static String curUserId() {
    User user = curUser();
    if (user != null) {
      return user.getObjectId();
    } else {
      return null;
    }
  }

  public AVFile getAvatar() {
    return getAVFile(AVATAR);
  }

  public void setAvatar(AVFile avatar) {
    put(AVATAR, avatar);
  }

  public String getAvatarUrl() {
    AVFile avatar = getAvatar();
    if (avatar != null) {
      return avatar.getUrl();
    } else {
      return null;
    }
  }

  public void addFriend(User user) {
    getRelation(FRIENDS).add(user);
  }


  public void removeFriend(User user) {
    getRelation(FRIENDS).remove(user);
  }
  
  
  
  

  public AVGeoPoint getLocation() {
    return getAVGeoPoint(LOCATION);
  }

  public void setLocation(AVGeoPoint location) {
    put(LOCATION, location);
  }

  public boolean getSex() {
    return getBoolean(SEX);
  }

  public void setSex(boolean isMale) {
    put(SEX, isMale);
  }

  public String getSexInfo() {
    return getSex() ? App.ctx.getString(R.string.male) :
     App.ctx.getString(R.string.female);
  }

  public String getSortLetters() {
    return sortLetters;
  }

  public void setSortLetters(String sortLetters) {
    this.sortLetters = sortLetters;
  }
  
  public void joinHabit(Habit habit) { 
	    getRelation("join").add(habit);
		
     }
  public void deleteHabit(Habit habit) { 
	    getRelation("join").remove(habit);
   }

};
