package cn.ibeilin.habit.ui.activity.test;

import java.io.File;
import java.io.InputStream;
import java.util.Timer;

import cn.ibeilin.habit.R;
import cn.ibeilin.habit.R.drawable;
import cn.ibeilin.habit.R.id;
import cn.ibeilin.habit.R.layout;

import com.ant.liao.GifView;
import com.ant.liao.GifView.GifImageType;
 














import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class GiftActivity extends Activity {
       
	  private VideoView video1;  
	          ImageView imageView;
	  MediaController  mediaco;  
	   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		WindowManager winManager=(WindowManager)getSystemService(Context.WINDOW_SERVICE); 
	 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gift);
		 
	        video1=(VideoView)findViewById(R.id.videoView1);  
	        //动画的初始化
	        video1.setAlpha(1f);
	        video1.setVisibility(View.VISIBLE);
	      
	        mediaco=new MediaController(this);  
	
	        	Uri uri = Uri.parse("android.resource://cn.ibeilin.habit/raw/on_board_video");
	            video1.setVideoURI(uri);
	            int width=winManager.getDefaultDisplay().getWidth();
	            int height=winManager.getDefaultDisplay().getHeight();
	           video1.getHolder().setFixedSize(width, height);
	            //video1.setMediaController(mediaco);  
	           video1.start();
	          //计时任务
	           final Handler handler=new Handler();
	           Runnable runnable=new Runnable() {
	               @Override
	               public void run() {
	                   // TODO Auto-generated method stub
	                   //要做的事情
	                   handler.postDelayed(this, 2000);
	               }
	           };
	           
	           
	           handler.postDelayed(runnable, 2000);//每两秒执行一次runnable.        
	           handler.removeCallbacks(runnable);   
	           
	           
	           
	           video1.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
	           {
	            @Override
	            public void onCompletion(MediaPlayer mp)
	            {
	                  //播放结束后的动作
	            	video1.animate()
	   	            .alpha(0f)
	   	            .setDuration(10)
	   	            .setListener(null);
	   	    
	            }
	           });
	           
	           
	            //mediaco.setMediaPlayer(video1);  
	            
	      
	          
 
	}
	
	
}
	

