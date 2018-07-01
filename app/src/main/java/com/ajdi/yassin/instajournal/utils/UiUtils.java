package com.ajdi.yassin.instajournal.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.MenuItem;

import androidx.annotation.ColorRes;
import androidx.core.app.ShareCompat;
import androidx.core.graphics.drawable.DrawableCompat;

public class UiUtils {

    // this method is used to tint menu icons for toolbars
    public static void tintMenuIcon(Context context, MenuItem item, @ColorRes int color) {
        Drawable itemIcon = item.getIcon();
        Drawable iconWrapper = DrawableCompat.wrap(itemIcon);
        DrawableCompat.setTint(iconWrapper, context.getResources().getColor(color));

        item.setIcon(iconWrapper);
    }

    // Fire a share intent for sharing articles
    public static void fireShareIntent(Context context, String title, String text) {
        Intent shareIntent = ShareCompat.IntentBuilder.from((Activity) context)
                .setType("text/plain")
                .setSubject(title)
                .setText(text)
                .createChooserIntent();

        int flags = Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
        if (Build.VERSION.SDK_INT >= 21)
            flags |= Intent.FLAG_ACTIVITY_NEW_DOCUMENT;

        shareIntent.addFlags(flags);
        context.startActivity(shareIntent);
    }
}
