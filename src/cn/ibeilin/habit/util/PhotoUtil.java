package cn.ibeilin.habit.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.*;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import cn.ibeilin.habit.R;
import cn.ibeilin.habit.ui.activity.SendStatusActivity;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PhotoUtil {

	/**
	 * 回收垃圾 recycle
	 * 
	 * @throws
	 */

	public static void displayImageByUri(ImageView imageView, String localPath,
			String url) {
		File file = new File(localPath);
		ImageLoader imageLoader =SendStatusActivity.imageLoader;
	    
		if (file.exists()) {
			imageLoader.displayImage("file://"+localPath, imageView,
					PhotoUtil.normalImageOptions);
			Log.i("leo","file.exists()");
		} else {
			imageLoader.displayImage(url,imageView,
					PhotoUtil.normalImageOptions);
			Log.i("leo","!file.exists()");
		}
	}

	  /**
	   * �������� recycle
	   *
	   * @throws
	   */
	  public static void recycle(Bitmap bitmap) {
	    // ���ж��Ƿ��Ѿ�����
	    if (bitmap != null && !bitmap.isRecycled()) {
	      // ���ղ�����Ϊnull
	      bitmap.recycle();
	      bitmap = null;
	    }
	    System.gc();
	  }

	  /**
	   * ��ȡָ��·���µ�ͼƬ��ָ����С������ͼ getImageThumbnail
	   *
	   * @return Bitmap
	   * @throws
	   */
	  public static Bitmap getImageThumbnail(String imagePath, int width,
	                                         int height) {
	    Bitmap bitmap = null;
	    BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inJustDecodeBounds = true;
	    // ��ȡ���ͼƬ�Ŀ�͸ߣ�ע��˴���bitmapΪnull
	    bitmap = BitmapFactory.decodeFile(imagePath, options);
	    options.inJustDecodeBounds = false; // ��Ϊ false
	    // �������ű�
	    int h = options.outHeight;
	    int w = options.outWidth;
	    int beWidth = w / width;
	    int beHeight = h / height;
	    int be = 1;
	    if (beWidth < beHeight) {
	      be = beWidth;
	    } else {
	      be = beHeight;
	    }
	    if (be <= 0) {
	      be = 1;
	    }
	    options.inSampleSize = be;
	    // ���¶���ͼƬ����ȡ���ź��bitmap��ע�����Ҫ��options.inJustDecodeBounds ��Ϊ false
	    bitmap = BitmapFactory.decodeFile(imagePath, options);
	    // ����ThumbnailUtils����������ͼ������Ҫָ��Ҫ�����ĸ�Bitmap����
	    bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
	        ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
	    return bitmap;
	  }

	  public static void saveBitmap(String dirpath, String filename,
	                                Bitmap bitmap, boolean isDelete) {
	    File dir = new File(dirpath);
	    if (!dir.exists()) {
	      dir.mkdirs();
	    }

	    File file = new File(dirpath, filename);
	    if (isDelete) {
	      if (file.exists()) {
	        file.delete();
	      }
	    }

	    if (!file.exists()) {
	      try {
	        file.createNewFile();
	      } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	      }
	    }
	    FileOutputStream out = null;
	    try {
	      out = new FileOutputStream(file);
	      if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)) {
	        out.flush();
	      }
	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    } finally {
	      if (out != null) {
	        try {
	          out.close();
	        } catch (IOException e) {
	          e.printStackTrace();
	        }
	      }
	    }
	  }

	  public static File getFilePath(String filePath, String fileName) {
	    File file = null;
	    makeRootDirectory(filePath);
	    try {
	      file = new File(filePath + fileName);
	      if (!file.exists()) {
	        file.createNewFile();
	      }

	    } catch (Exception e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	    return file;
	  }

	  public static void makeRootDirectory(String filePath) {
	    File file = null;
	    try {
	      file = new File(filePath);
	      if (!file.exists()) {
	        file.mkdirs();
	      }
	    } catch (Exception e) {

	    }
	  }

	  /**
	   * ��ȡͼƬ���ԣ���ת�ĽǶ�
	   *
	   * @param path ͼƬ����·��
	   * @return degree��ת�ĽǶ�
	   */

	  public static int readPictureDegree(String path) {
	    int degree = 0;
	    try {
	      ExifInterface exifInterface = new ExifInterface(path);
	      int orientation = exifInterface.getAttributeInt(
	          ExifInterface.TAG_ORIENTATION,
	          ExifInterface.ORIENTATION_NORMAL);
	      switch (orientation) {
	        case ExifInterface.ORIENTATION_ROTATE_90:
	          degree = 90;
	          break;
	        case ExifInterface.ORIENTATION_ROTATE_180:
	          degree = 180;
	          break;
	        case ExifInterface.ORIENTATION_ROTATE_270:
	          degree = 270;
	          break;
	      }
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    return degree;

	  }

	  /**
	   * ��תͼƬһ���Ƕ�
	   * rotaingImageView
	   *
	   * @return Bitmap
	   * @throws
	   */
	  public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
	    // ��תͼƬ ����
	    Matrix matrix = new Matrix();
	    matrix.postRotate(angle);
	    // �����µ�ͼƬ
	    Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
	        bitmap.getWidth(), bitmap.getHeight(), matrix, true);
	    return resizedBitmap;
	  }

	  /**
	   * ��ͼƬ��ΪԲ��
	   *
	   * @param bitmap ԭBitmapͼƬ
	   * @param pixels ͼƬԲ�ǵĻ���(��λ:����(px))
	   * @return ����Բ�ǵ�ͼƬ(Bitmap ����)
	   */
	  public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
	    Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
	        bitmap.getHeight(), Config.ARGB_8888);
	    Canvas canvas = new Canvas(output);

	    final int color = 0xff424242;
	    final Paint paint = new Paint();
	    final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
	    final RectF rectF = new RectF(rect);
	    final float roundPx = pixels;

	    paint.setAntiAlias(true);
	    canvas.drawARGB(0, 0, 0, 0);
	    paint.setColor(color);
	    canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

	    paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
	    canvas.drawBitmap(bitmap, rect, rect, paint);

	    return output;
	  }

	  /**
	   * ��ͼƬת��ΪԲ��ͷ��
	   *
	   * @throws
	   * @Title: toRoundBitmap
	   */
	  public static Bitmap toRoundBitmap(Bitmap bitmap) {
	    int width = bitmap.getWidth();
	    int height = bitmap.getHeight();
	    float roundPx;
	    float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
	    if (width <= height) {
	      roundPx = width / 2;

	      left = 0;
	      top = 0;
	      right = width;
	      bottom = width;

	      height = width;

	      dst_left = 0;
	      dst_top = 0;
	      dst_right = width;
	      dst_bottom = width;
	    } else {
	      roundPx = height / 2;

	      float clip = (width - height) / 2;

	      left = clip;
	      right = width - clip;
	      top = 0;
	      bottom = height;
	      width = height;

	      dst_left = 0;
	      dst_top = 0;
	      dst_right = height;
	      dst_bottom = height;
	    }

	    Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
	    Canvas canvas = new Canvas(output);

	    final Paint paint = new Paint();
	    final Rect src = new Rect((int) left, (int) top, (int) right,
	        (int) bottom);
	    final Rect dst = new Rect((int) dst_left, (int) dst_top,
	        (int) dst_right, (int) dst_bottom);
	    final RectF rectF = new RectF(dst);

	    paint.setAntiAlias(true);// ���û����޾��

	    canvas.drawARGB(0, 0, 0, 0); // �������Canvas

	    // ���������ַ�����Բ,drawRounRect��drawCircle
	    canvas.drawRoundRect(rectF, roundPx, roundPx, paint);// ��Բ�Ǿ��Σ���һ������Ϊͼ����ʾ���򣬵ڶ��������͵����������ֱ���ˮƽԲ�ǰ뾶�ʹ�ֱԲ�ǰ뾶��
	    // canvas.drawCircle(roundPx, roundPx, roundPx, paint);

	    paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));// ��������ͼƬ�ཻʱ��ģʽ,�ο�http://trylovecatch.iteye.com/blog/1189452
	    canvas.drawBitmap(bitmap, src, dst, paint); // ��Mode.SRC_INģʽ�ϲ�bitmap���Ѿ�draw�˵�Circle

	    return output;
	  }

	  public static String simpleCompressImage(String path, String newPath) {
	    Bitmap bitmap = BitmapFactory.decodeFile(path);
	    FileOutputStream outputStream = null;
	    try {
	      outputStream = new FileOutputStream(newPath);
	      bitmap.compress(Bitmap.CompressFormat.JPEG, 80, outputStream);
	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    }
	    recycle(bitmap);
	    return newPath;
	  }

	  public static String compressImage(String path, String newPath) {
	    BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inJustDecodeBounds = true;
	    BitmapFactory.decodeFile(path, options);
	    int inSampleSize = 1;
	    int maxSize = 3000;
	    Logger.d("outWidth=" + options.outWidth + " outHeight=" + options.outHeight);
	    if (options.outWidth > maxSize || options.outHeight > maxSize) {
	      int widthScale = (int) Math.ceil(options.outWidth * 1.0 / maxSize);
	      int heightScale = (int) Math.ceil(options.outHeight * 1.0 / maxSize);
	      inSampleSize = Math.max(widthScale, heightScale);
	    }
	    Logger.d("inSampleSize=" + inSampleSize);
	    options.inJustDecodeBounds = false;
	    options.inSampleSize = inSampleSize;
	    Bitmap bitmap = BitmapFactory.decodeFile(path, options);
	    int w = bitmap.getWidth();
	    int h = bitmap.getHeight();
	    int newW = w;
	    int newH = h;
	    if (w > maxSize || h > maxSize) {
	      if (w > h) {
	        newW = maxSize;
	        newH = (int) (newW * h * 1.0 / w);
	      } else {
	        newH = maxSize;
	        newW = (int) (newH * w * 1.0 / h);
	      }
	    }
	    Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap, newW, newH, false);
	    //recycle(bitmap);
	    Logger.d("bitmap width=" + newBitmap.getWidth() + " h=" + newBitmap.getHeight());

	    FileOutputStream outputStream = null;
	    try {
	      outputStream = new FileOutputStream(newPath);
	      newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, outputStream);
	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    } finally {
	      try {
	        if (outputStream != null) {
	          outputStream.close();
	        }
	      } catch (IOException e) {
	        e.printStackTrace();
	      }

	    }
	    recycle(newBitmap);
	    recycle(bitmap);
	    return newPath;
	  }

	  public static DisplayImageOptions avatarImageOptions = new DisplayImageOptions.Builder()
	      .showImageOnLoading(R.drawable.default_user_avatar)
	      .showImageForEmptyUri(R.drawable.default_user_avatar)
	      .showImageOnFail(R.drawable.default_user_avatar)
	      .cacheInMemory(true)
	      .cacheOnDisc(true)
	      .considerExifParams(true)
	      .imageScaleType(ImageScaleType.EXACTLY)
	      .bitmapConfig(Config.RGB_565)
	      .resetViewBeforeLoading(true)// ����ͼƬ������ǰ�Ƿ����ã���λ
	          //.displayer(new RoundedBitmapDisplayer(20))
	          //.displayer(new FadeInBitmapDisplayer(100))// ����
	      .build();

	  public static DisplayImageOptions normalImageOptions = new DisplayImageOptions.Builder()
	     .showImageOnLoading(R.drawable.empty_photo)
	       .showImageForEmptyUri(R.drawable.empty_photo)
	      .showImageOnFail(R.drawable.image_load_fail)
	      .cacheInMemory(true)
	      .cacheOnDisc(true)
	      .considerExifParams(true)
	      .imageScaleType(ImageScaleType.EXACTLY)
	      .bitmapConfig(Config.RGB_565)
	      .resetViewBeforeLoading(true)// ����ͼƬ������ǰ�Ƿ����ã���λ
	          //.displayer(new RoundedBitmapDisplayer(20))
	          //.displayer(new FadeInBitmapDisplayer(100))// ����
	      .build();

	  public static ImageLoaderConfiguration getImageLoaderConfig(Context context, File cacheDir) {
	    return new ImageLoaderConfiguration.Builder(
	        context)
	        .threadPoolSize(3).threadPriority(Thread.NORM_PRIORITY - 2)
	            //.memoryCache(new WeakMemoryCache())
	        .denyCacheImageMultipleSizesInMemory()
	        .discCacheFileNameGenerator(new Md5FileNameGenerator())
	            // �������ʱ���URI������MD5 ����
	        .tasksProcessingOrder(QueueProcessingType.LIFO)
	        .discCache(new UnlimitedDiscCache(cacheDir))// �Զ��建��·��
	            // .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
	        .writeDebugLogs() // Remove for release app
	        .build();
	  }
	}
