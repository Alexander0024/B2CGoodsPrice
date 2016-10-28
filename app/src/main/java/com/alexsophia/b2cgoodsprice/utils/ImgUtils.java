package com.alexsophia.b2cgoodsprice.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * Image loader
 * <p/>
 * Created by liuweiping on 2016-2-26.
 */
public class ImgUtils {
    private static final String TAG = "ImgUtils";

    /**
     * 使用ImageLoader显示图片
     *
     * @param url               　图片地址
     *                          "http://site.com/image.png" // from Web
     *                          "file:///mnt/sdcard/image.png" // from SD card
     *                          "file:///mnt/sdcard/video.mp4" // from SD card (video thumbnail)
     *                          "content://media/external/images/media/13" // from content provider
     *                          "content://media/external/video/media/13" // from content
     *                          provider (video thumbnail)
     *                          "assets://image.png" // from assets
     *                          "drawable://" + R.drawable.img // from drawables (non-9patch images)
     * @param iv                需要显示到的ImageView
     * @param defaultImageResId 默认图片，加载过程中显示的图片
     * @param errorImageResId   加载失败时显示的图片
     */
    public static void displayImage(String url, ImageView iv, int defaultImageResId, int
            errorImageResId) {
        if (StringUtil.isEmpty(url)) {
            iv.setImageResource(defaultImageResId);
        } else {
            ImageLoader.getInstance().displayImage(formatURL(url), iv, new DisplayImageOptions
                    .Builder().showImageOnLoading(defaultImageResId).showImageOnFail
                    (errorImageResId).cacheInMemory(true).cacheOnDisk(true).build());
        }
    }

    /**
     * 初始化ImageLoader
     *
     * @param context Context
     */
    public static void initImageLoader(Context context) {
//        File cacheDir = StorageUtils.getOwnCacheDirectory(context, CommonDirectory.imgCache());
        // 获取到缓存的目录地址
//        LogWrapper.e(TAG, "Image Loader Cache Directory: " + cacheDir.getPath());
        // 创建配置ImageLoader(所有的选项都是可选的,只使用那些你真的想定制)，这个可以设定在APPLICATION里面，设置为全局的配置参数
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                // max width, max height，即保存的每个缓存文件的最大长宽
                .memoryCacheExtraOptions(480, 800)
                // 线程池内加载的数量
                .threadPoolSize(3)
                // 线程优先级
                .threadPriority(Thread.NORM_PRIORITY - 2)
            /*
             * When you display an image in a small ImageView
             *  and later you try to display this image (from identical URI) in a larger ImageView
             *  so decoded image of bigger size will be cached in memory as a previous decoded
             *  image of smaller size.
             *  So the default behavior is to allow to cache multiple sizes of one image in memory.
             *  You can deny it by calling this method:
             *  so when some image will be cached in memory then previous cached size of this
             *  image (if it exists)
             *   will be removed from memory cache before.
             */
                .denyCacheImageMultipleSizesInMemory()

                /**
                 * 设置内存缓存
                 */
//                 .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024)) //可以通过自己的内存缓存实现
//                .memoryCacheSize(2 * 1024 * 1024)  // 内存缓存的最大值
//                .memoryCacheSizePercentage(13) // default

                /**
                 * 设置SD卡缓存
                 */
//                .diskCache(new UnlimitedDiskCache(cacheDir)) // default 可以自定义缓存路径
//                .diskCacheSize(50 * 1024 * 1024) // 50 Mb sd卡(本地)缓存的最大值
//                .diskCacheFileCount(100)  // 可以缓存的文件数量
                // default为使用HASHCODE对UIL进行加密命名， 还可以用MD5(new Md5FileNameGenerator())加密
//                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())

                //将保存的时候的URI名称用HASHCODE加密
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                // .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                // .imageDownloader(new BaseImageDownloader(context, 5 * 1000,
                // 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间

                .imageDownloader(new BaseImageDownloader(context)) // default
                .imageDecoder(new BaseImageDecoder(true)) // default
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default

                .writeDebugLogs() // Remove for release app
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);// 全局初始化此配置
    }

    /**
     * 初始化加载image的option
     */
    private static void initOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.drawable.ic_stub) // 设置图片下载期间显示的图片
//                .showImageForEmptyUri(R.drawable.ic_empty) // 设置图片Uri为空或是错误的时候显示的图片
//                .showImageOnFail(R.drawable.ic_error) // 设置图片加载或解码过程中发生错误显示的图片
//                .resetViewBeforeLoading(false)  // default 设置图片在加载前是否重置、复位
//                .delayBeforeLoading(1000)  // 下载前的延迟时间
                .cacheInMemory(true) // default  设置下载的图片是否缓存在内存中
                .cacheOnDisk(true) // default  设置下载的图片是否缓存在SD卡中
//                .preProcessor(...)
//                .postProcessor(...)
//                .extraForDownloader(...)
//                .considerExifParams(false) // default
//                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default 设置图片以如何的编码方式显示
//                .bitmapConfig(Bitmap.Config.ARGB_8888) // default 设置图片的解码类型
//                .decodingOptions(...)  // 图片的解码设置
//                .displayer(new SimpleBitmapDisplayer()) // default  还可以设置圆角图片new
// RoundedBitmapDisplayer(20)
//                .handler(new Handler()) // default
                .build();
    }

    public static void loadImage(String url, ImageLoadingListener imageLoadingListener) {
        ImageLoader.getInstance().loadImage(url, imageLoadingListener);
    }

    /**
     * 获取压缩比例
     */
    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int maxWidth, int maxHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (width > maxWidth || height > maxHeight) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) maxHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) maxWidth);
            }
            final float totalPixels = width * height;
            final float maxTotalPixels = maxWidth * maxHeight * 2;
            while (totalPixels / (inSampleSize * inSampleSize) > maxTotalPixels) {
                inSampleSize++;
            }
        }
        return inSampleSize;
    }

    /**
     * 将彩色图片转换成灰色图片
     *
     * @param bitmap     源Bitmap
     * @param saturation a value of 0 maps the color to gray-scale. 1 is identity.
     */
    public static Bitmap toGrayImage(Bitmap bitmap, float saturation) {
        if (bitmap == null)
            return null;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap grayImg = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(grayImg);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(saturation < 0f || saturation > 1f ? 1f
                : saturation);
        ColorMatrixColorFilter colorMatrixFilter = new ColorMatrixColorFilter(
                colorMatrix);
        paint.setColorFilter(colorMatrixFilter);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        bitmap.recycle();
        return grayImg;
    }

    public static Bitmap compressImageFromFile(String srcPath, int width,
                                               int height) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;// 只读边,不读内容
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // float hh = 800f;//
        // float ww = 480f;//
        int be = 1;
        if (w > h && w > width) {
            be = newOpts.outWidth / width;
        } else if (w < h && h > height) {
            be = newOpts.outHeight / height;
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// 设置采样率
        newOpts.inPreferredConfig = Bitmap.Config.ARGB_8888;// 该模式是默认的,可不设
        newOpts.inPurgeable = true;// 同时设置才会有效
        newOpts.inInputShareable = true;// 。当系统内存不够时候图片自动被回收
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return bitmap;
    }

    /**
     * 将头像中的“|”和“%7C”去除掉，只保留域名及最后一个分隔符以后的头像地址
     *
     * @param avatar 头像的URL
     * @return 截取后的第一个URL
     */
    public static String formatURL(String avatar) {
        if (avatar.contains("|")) {
            String domain = avatar.substring(0, avatar.indexOf("/upload"));
            LogWrapper.d(TAG, "Avatar = " + avatar);
            avatar = domain + avatar.substring(avatar.lastIndexOf('|') + 1);
            LogWrapper.d(TAG, "Update avatar location to: " + avatar);
        }
        if (avatar.contains("%7C")) {
            String domain = avatar.substring(0, avatar.indexOf("/upload"));
            LogWrapper.d(TAG, "Avatar = " + avatar);
            avatar = domain + avatar.substring(avatar.lastIndexOf("%7C") + 4);
            LogWrapper.d(TAG, "Update avatar location to: " + avatar);
        }
        return avatar;
    }

}
