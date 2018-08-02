package com.zt.agw.utils.image;

/**
 * Created by come on 2016/12/5.
 */
/**
 * Copyright © 2015公司名字. All rights reserved.
 *
 * @Title: MyImageLoader.java
 * @Prject: dsf
 * @Package: com.zeta.dsf.util
 * @Description: TODO
 * @author: Administrator
 * @date: 2015年8月13日 下午3:32:08
 * @version: V1.0
 */

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName: MyImageLoader
 * @Description: TODO
 * @author: Administrator
 * @date: 2015年8月13日 下午3:32:08
 */
public class MyImageLoader {

    private static MyImageLoader util = null;
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    public MyImageLoader()
    {

    }

    public static MyImageLoader getInstanImage()
    {
        if (util == null)
            util = new MyImageLoader();
        return util;
    }

    public void ImageLoader(String paramString, final ImageView paramImageView)
    {
        ImageLoader.getInstance().displayImage(paramString, paramImageView, new ImageOptionUtils().getOptionsNoCache(), new SimpleImageLoadingListener()
        {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                // TODO 自动生成的方法存根
                super.onLoadingComplete(imageUri, view, loadedImage);
                paramImageView.setImageBitmap(BitmapHelp.resizeBitmap(loadedImage, 80));
                paramImageView.setVisibility(View.VISIBLE);
            }
        });
    }

    public void ImageLoaderRec(String paramString, ImageView paramImageView)
    {
        ImageLoader.getInstance().displayImage(paramString, paramImageView, new ImageOptionUtils().getOptionsNoCacheRec(), animateFirstListener);
    }

    public void ImageLoaderWithMemoryCacheAndSdcardCache(String paramString, ImageView paramImageView)
    {
        ImageLoader.getInstance().displayImage(paramString, paramImageView, new ImageOptionUtils().getOptionsWithMemoryCacheAndSdcardCache(), animateFirstListener);
    }

    public void loaderTX(String url, ImageView imageView)
    {
        ImageLoader.getInstance().displayImage(url, imageView, new ImageOptionUtils().getOptionsTX(), animateFirstListener);
    }

    public void loaderPhoto(String url, ImageView imageView, int sourceId)
    {
        ImageLoader.getInstance().displayImage(url, imageView, new ImageOptionUtils().getOptionsPhoto(sourceId), animateFirstListener);
    }

    private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener
    {
        static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage)
        {
            if (loadedImage != null)
            {
                ImageView imageView = (ImageView) view;
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay)
                {
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                }
            }
        }
    }
}
