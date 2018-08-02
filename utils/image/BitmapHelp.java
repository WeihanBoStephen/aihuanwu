package com.zt.agw.utils.image;

/**
 * Created by come on 2016/12/1.
 */
/**
 * Copyright © 2015公司名字. All rights reserved.
 *
 * @Title: BitmapHelp.java
 * @Prject: dsf
 * @Package: com.zeta.dsf.util
 * @Description: TODO
 * @author: Administrator
 * @date: 2015年8月6日 上午9:49:25
 * @version: V1.0
 */

import android.graphics.Bitmap;

/**
 * @ClassName: BitmapHelp
 * @Description: TODO
 * @author: Administrator
 * @date: 2015年8月6日 上午9:49:25
 */
public class BitmapHelp {

    // public static String convertIconToString(Bitmap bitmap) {
    // ByteArrayOutputStream baos = new ByteArrayOutputStream();// outputstream
    // bitmap.compress(CompressFormat.PNG, 100, baos);
    // byte[] appicon = baos.toByteArray();// 转为byte数组
    // return Base64.encodeBase64String(appicon);
    //
    // }
    //
    // /**
    // * string转成bitmap
    // *
    // * @param st
    // */
    // public static Bitmap convertStringToIcon(String st) {
    // // OutputStream out;
    // Bitmap bitmap = null;
    // try {
    // // out = new FileOutputStream("/sdcard/aa.jpg");
    // byte[] bitmapArray;
    // bitmapArray = Base64.decodeBase64(st);
    // bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0,
    // bitmapArray.length);
    // // bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
    // return bitmap;
    // } catch (Exception e) {
    // return null;
    // }
    // }

    // 图片做压缩处理
    public static Bitmap resizeBitmap(Bitmap bitmap, int scaleLength) {
        int originWidth = bitmap.getWidth();
        int originHeight = bitmap.getHeight();

        int width = originWidth;
        int height = originHeight;

        int length = width;
        int startX = 0, startY = 0;
        if (width > height) {
            length = height;
            startX = (width - height) / 2;
            startY = 0;
        }
        if (width < height) {
            length = width;
            startX = 0;
            startY = (height - width) / 2;
        }
        // 将bitmap按短边剪切成正方形
        Bitmap squareBitmap = Bitmap.createBitmap(bitmap, startX, startY, length, length);

        // 按照指定的大小进行压缩
        // float scale = (float) (1.0 * length / scaleLength);
        // Matrix matrix = new Matrix();
        // matrix.postScale(scale, scale);
        // Bitmap resizeBmp =
        // Bitmap.createBitmap(squareBitmap,0,0,squareBitmap.getWidth(),squareBitmap.getHeight(),matrix,true);
        //
        // bitmap.recycle();
        // squareBitmap.recycle();
        return squareBitmap;
    }
}
