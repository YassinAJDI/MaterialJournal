package com.ajdi.yassin.instajournal.utils;

import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;

public class ImageUtils {

    public static String getImagePath() {
        String[] FILE = { MediaStore.Images.Media.DATA };


        Cursor cursor = getContentResolver().query(URI,
                FILE, null, null, null);

        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(FILE[0]);
        ImageDecode = cursor.getString(columnIndex);
        cursor.close();

        imageViewLoad.setImageBitmap(BitmapFactory
                .decodeFile(ImageDecode));


        return "";
    }
}
