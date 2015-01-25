package cn.ibeilin.habit.ui.activity;

import java.io.File;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.SaveCallback;
import com.nostra13.universalimageloader.core.ImageLoader;

import cn.ibeilin.habit.R;
import cn.ibeilin.habit.R.id;
import cn.ibeilin.habit.R.layout;
import cn.ibeilin.habit.service.StatusService;
import cn.ibeilin.habit.util.PhotoUtil;
import cn.ibeilin.habit.util.Utils;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SendStatusActivity extends BaseActivity implements OnClickListener {
	public static ImageLoader imageLoader = ImageLoader.getInstance(); //imageLoader在使用需要生成一個實例
	
	private static final int IMAGE_REQUEST = 0;
	public static final int LOCATION_REQUEST = 1;
	private static final int TAKE_CAMERA_REQUEST = 2;
	private String imagePath="";

	private String localCameraPath = cn.ibeilin.habit.util.PathUtils
			.getTmpPath();

	Button sendStatusBtn;
	Button addImageBtn;
	EditText statusContentET;
	TextView imagePathTV;
	ImageView imageSelectedIV;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_status);

		findView();
		initView();
	}

	

	private void findView() {
		sendStatusBtn = (Button) findViewById(id.send_status_btn);
		addImageBtn = (Button) findViewById(id.add_image_btn);
		statusContentET = (EditText) findViewById(id.status_content_et);
		imagePathTV = (TextView) findViewById(id.image_path_tv);
		imageSelectedIV=(ImageView)findViewById(id.selected_image_IV);
		
	}
	
	
	private void initView() {
	     sendStatusBtn.setOnClickListener(this);
	     addImageBtn.setOnClickListener(this);
		
	}

	void sendStatus() {
		Bitmap bitmap = null;

		if (!imagePath.isEmpty()) // 如果有图片
		{   
		 
			bitmap = PhotoUtil.getImageThumbnail(imagePath, 400, 800);
			Log.i("leo", ""+bitmap.getHeight());
			  
		}

		StatusService.sendStatus(statusContentET.getText().toString(), bitmap,
				new SaveCallback() {

					@Override
					public void done(AVException arg0) {
					     Utils.toast("已發送data");

					}
				});

	}

	public void selectImageFromLocal() {
		Intent intent;
		intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*");
		startActivityForResult(intent, IMAGE_REQUEST);
	}

	public void selectImageFromCamera() {
		Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		Uri imageUri = Uri.fromFile(new File(localCameraPath));
		openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		startActivityForResult(openCameraIntent, TAKE_CAMERA_REQUEST);
	}

	// 显示以选中的图片后的
	public void displayImage() {
		imagePathTV.setText(imagePath);
		PhotoUtil.displayImageByUri(imageSelectedIV, imagePath, "");
		Bitmap a = PhotoUtil.getImageThumbnail(imagePath, 400, 800);
		Log.i("leo", ""+a.getHeight());
		  

	}

	private String parsePathByReturnData(Intent data) {
		if (data == null) {
			return null;
		}
		String localSelectPath = null;
		Uri selectedImage = data.getData();
		if (selectedImage != null) {
			Cursor cursor = getContentResolver().query(selectedImage, null,
					null, null, null);
			cursor.moveToFirst();
			int columnIndex = cursor.getColumnIndex("_data");
			localSelectPath = cursor.getString(columnIndex);
			cursor.close();
		}
		return localSelectPath;
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case IMAGE_REQUEST:
				String localSelectPath = parsePathByReturnData(data);
				imagePath = localSelectPath;
				displayImage();
				break;
			case TAKE_CAMERA_REQUEST:
				imagePath = localCameraPath;
				displayImage();
				break;

			}
		}
		// hideBottomLayout();
		super.onActivityResult(requestCode, resultCode, data);
	}
	
 
	
	
	

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.send_status_btn:
			sendStatus();
			break;
		case R.id.add_image_btn:
			selectImageFromLocal();
			break;
		}
	}

}
