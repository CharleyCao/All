package cn.caoleix.all.utils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import cn.caoleix.all.R;

/**
 * @author lei.cao
 * @date 2019/12/27 13:59
 * @desc 图片加载
 */
public class CImageLoader {

    private static ImageLoader imageLoader = ImageLoader.getInstance();

    /**
     * 在application的onCreate方法里面初始化
     */
    public static void init(Context appContext) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(appContext)
                .threadPriority(Thread.NORM_PRIORITY - 2)
//				.denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Remove for release app
                .build();
        ImageLoader.getInstance().init(config);
    }

    public static CImageLoader newInstance() {
        return new CImageLoader();
    }

    private int loadingImage = R.mipmap.ic_launcher;
    private int emptyImage = R.mipmap.ic_launcher;
    private int errorImage = R.mipmap.ic_launcher;

    private boolean isCacheInMemory = true;
    private boolean isCacheInSDCard = true;
    private ImageScaleType mImageScaleType = ImageScaleType.EXACTLY;
    private boolean isRoundCornered = false;
    private int radius = 0;
    private Config mBitmapConfig = Config.RGB_565;

    private DisplayImageOptions createDisplayImageOptions() {
        DisplayImageOptions.Builder builder = new DisplayImageOptions.Builder()
                .showStubImage(loadingImage)
                .showImageForEmptyUri(emptyImage)
                .showImageOnFail(errorImage)
                .cacheInMemory(isCacheInMemory)
                .cacheOnDisc(isCacheInSDCard)
                .imageScaleType(mImageScaleType)
                .bitmapConfig(mBitmapConfig);
        if (isRoundCornered) {
            builder.displayer(new RoundedBitmapDisplayer(radius));
        }
        return builder.build();
    }

    private static ImageLoadingListener listener = new SimpleImageLoadingListener() {

        final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;

                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                }
            }
        }
    };

    /**
     * 加载网络图片
     */
    public void url(String url, ImageView iv) {
        imageLoader.displayImage(url, iv, createDisplayImageOptions(), listener);
    }

    /**
     * 加载assets下的图片
     */
    public void assets(String name, ImageView iv) {
        imageLoader.displayImage("assets://" + name, iv, createDisplayImageOptions(), listener);
    }

    /**
     * 加载drawable下的图片
     */
    public void resource(int resId, ImageView iv) {
        imageLoader.displayImage("drawable://" + resId, iv, createDisplayImageOptions(), listener);
    }

    /**
     * 加载内存卡上的图片
     */
    public void sdcard(String filePath, ImageView iv) {
        imageLoader.displayImage("file:///" + filePath, iv, createDisplayImageOptions(), listener);
    }

    public void url(String uri, ImageView iv, ImageLoadingListener listener) {
        imageLoader.loadImage(uri, new ImageSize(iv.getMeasuredWidth(), iv.getMeasuredHeight()), /*createDisplayImageOptions(),*/ listener);
    }

    public int getLoadingImage() {
        return loadingImage;
    }

    public CImageLoader setLoadingImage(int loadingImage) {
        this.loadingImage = loadingImage;
        return this;
    }

    public int getEmptyImage() {
        return emptyImage;
    }

    public CImageLoader setEmptyImage(int emptyImage) {
        this.emptyImage = emptyImage;
        return this;
    }

    public int getErrorImage() {
        return errorImage;
    }

    public CImageLoader setErrorImage(int errorImage) {
        this.errorImage = errorImage;
        return this;
    }

    public boolean isCacheInMemory() {
        return isCacheInMemory;
    }

    public CImageLoader setCacheInMemory(boolean isCacheInMemory) {
        this.isCacheInMemory = isCacheInMemory;
        return this;
    }

    public boolean isCacheInSDCard() {
        return isCacheInSDCard;
    }

    public CImageLoader setCacheInSDCard(boolean isCacheInSDCard) {
        this.isCacheInSDCard = isCacheInSDCard;
        return this;
    }

    public ImageScaleType getmImageScaleType() {
        return mImageScaleType;
    }

    public CImageLoader setmImageScaleType(ImageScaleType mImageScaleType) {
        this.mImageScaleType = mImageScaleType;
        return this;
    }

    public boolean isRoundCornered() {
        return isRoundCornered;
    }

    public CImageLoader setRoundCornered(boolean isRoundCornered) {
        this.isRoundCornered = isRoundCornered;
        return this;
    }

    public int getRadius() {
        return radius;
    }

    public CImageLoader setRadius(int radius) {
        this.radius = radius;
        return this;
    }

    public Config getBitmapConfig() {
        return mBitmapConfig;
    }

    public CImageLoader setBitmapConfig(Config mBitmapConfig) {
        this.mBitmapConfig = mBitmapConfig;
        return this;
    }

}
