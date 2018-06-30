package com.ajdi.yassin.instajournal.utils;

import android.content.Context;
import androidx.annotation.NonNull;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

/**
 * Ensures that Glide's generated API is created for this app.
 *
 * @author Yassin AJDI
 */
@GlideModule
public class AppGlideModuleHelper extends AppGlideModule {

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        builder.setDefaultRequestOptions(
                new RequestOptions()
                        .placeholder(android.R.color.darker_gray)
        );
    }
}