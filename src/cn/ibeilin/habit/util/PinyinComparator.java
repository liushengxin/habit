package cn.ibeilin.habit.util;

 

import java.util.Comparator;

import cn.ibeilin.habit.entity.User;

public class PinyinComparator implements Comparator<User> {
  public int compare(User o1, cn.ibeilin.habit.entity.User o2) {
    if (o1.getSortLetters().equals("@")
        || o2.getSortLetters().equals("#")) {
      return -1;
    } else if (o1.getSortLetters().equals("#")
        || o2.getSortLetters().equals("@")) {
      return 1;
    } else {
      return o1.getSortLetters().compareTo(o2.getSortLetters());
    }
  }

}
