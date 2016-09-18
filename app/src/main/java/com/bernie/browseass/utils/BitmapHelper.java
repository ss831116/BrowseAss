package com.bernie.browseass.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * Created by bernie.shi on 2016/9/18.
 */

public class BitmapHelper {
    public static byte[] bitmapToByte(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
    public static Bitmap byteToBitmap(byte[] bytes) {
        if(bytes.length!=0){
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        }
        else {
            return null;
        }
    }
}
