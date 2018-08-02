package com.zt.agw.utils.image;

/**
 * Created by come on 2016/12/1.
 */
/**
 * Copyright © 2015公司名字. All rights reserved.
 *
 * @Title: ImageOptionUtils.java
 * @Prject: dsf
 * @Package: com.zeta.dsf.util
 * @Description: TODO
 * @author: Administrator
 * @date: 2015年8月13日 下午3:49:12
 * @version: V1.0
 */

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.zt.agw.R;


/**
 * @ClassName: ImageOptionUtils
 * @Description: TODO
 * @author: Administrator
 * @date: 2015年8月13日 下午3:49:12
 */
public class ImageOptionUtils {

    private DisplayImageOptions options;

    public DisplayImageOptions getOptionsNoCache()
    {
        options = new DisplayImageOptions.Builder().imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2).bitmapConfig(Bitmap.Config.RGB_565).showImageOnFail(R.drawable.load_fail).cacheInMemory(false).cacheOnDisc(false).displayer(new RoundedBitmapDisplayer(20)).build();
        return options;
    }

    public DisplayImageOptions getOptionsNoCacheRec()
    {
        options = new DisplayImageOptions.Builder().resetViewBeforeLoading().imageScaleType(ImageScaleType.EXACTLY).bitmapConfig(Bitmap.Config.RGB_565).showImageOnFail(R.drawable.load_fail).cacheInMemory(false).cacheOnDisc(false).displayer(new FadeInBitmapDisplayer(600)).build();
        return options;
    }

    public DisplayImageOptions getOptionsWithMemoryCacheAndSdcardCache()
    {
        options = new DisplayImageOptions.Builder().cacheOnDisc().cacheInMemory().imageScaleType(ImageScaleType.EXACTLY).bitmapConfig(Bitmap.Config.RGB_565).showStubImage(R.drawable.load_fail).showImageForEmptyUri(R.drawable.load_fail).showImageOnFail(R.drawable.load_fail).displayer(new FadeInBitmapDisplayer(600)).build();
        return options;
    }

    public DisplayImageOptions getOptionsTX()
    {
        options = new DisplayImageOptions.Builder().cacheOnDisc().cacheInMemory().imageScaleType(ImageScaleType.EXACTLY).bitmapConfig(Bitmap.Config.RGB_565).showStubImage(R.drawable.load_fail).showImageForEmptyUri(R.drawable.load_fail).showImageOnFail(R.drawable.load_fail).displayer(new FadeInBitmapDisplayer(10)).build();
        return options;
    }

    public DisplayImageOptions getOptionsPhoto(int sourceId)
    {
        options = new DisplayImageOptions.Builder().cacheOnDisc().cacheInMemory().imageScaleType(ImageScaleType.EXACTLY).bitmapConfig(Bitmap.Config.RGB_565).showStubImage(sourceId).showImageForEmptyUri(sourceId).showImageOnFail(sourceId).displayer(new FadeInBitmapDisplayer(600)).build();
        return options;
    }

    public DisplayImageOptions getOptionsWithSdcardCache()
    {
        options = new DisplayImageOptions.Builder().resetViewBeforeLoading().cacheOnDisc().imageScaleType(ImageScaleType.EXACTLY).bitmapConfig(Bitmap.Config.RGB_565).displayer(new FadeInBitmapDisplayer(600)).build();
        return options;
    }
}

