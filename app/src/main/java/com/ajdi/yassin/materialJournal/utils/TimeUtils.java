package com.ajdi.yassin.materialJournal.utils;

import android.content.Context;
import android.text.format.DateUtils;

public class TimeUtils {

    public static CharSequence getTimeAgo(long timestamp, Context context) {
        // TODO: 06/02/2018 localize time

        return DateUtils.formatDateTime(context, timestamp, DateUtils.FORMAT_ABBREV_MONTH);
    }
}
