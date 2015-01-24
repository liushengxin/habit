package cn.ibeilin.habit.entity;
import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;

/**
 * Created on 14-10-16.
 */
@AVClassName("Avatar")
public class Avatar extends AVObject {
  public static final String FILE = "file";

  public Avatar() {
  }

  //AVFile file;

  public AVFile getFile() {
    return getAVFile(FILE);
  }

  public void setFile(AVFile file) {
    put(FILE, file);
  }
}