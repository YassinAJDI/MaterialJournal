package com.ajdi.yassin.materialjournal.utils;

import android.content.Context;

import com.ajdi.yassin.materialjournal.R;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

import androidx.annotation.NonNull;

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
                        .placeholder(R.color.md_grey_200)
        );
    }
}