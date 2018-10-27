package com.edgar.filemanager.utils;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;

import com.edgar.filemanager.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.text.BidiFormatter;
import androidx.core.text.TextUtilsCompat;

/**
 * Created by Edgar on 2018/10/26.
 */
public class FormatUtils {

    public static final int FLAG_DEFAULT = 0;
    public static final int FLAG_SHORTER = 1 << 0;
    public static final int FLAG_CALCULATE_ROUNDED = 1 << 1;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static String formatTime(long date) {
        return DATE_FORMAT.format(date);
    }

    //copy android.text.format.Formatter
    public static String formatFileSize(@Nullable Context context, long sizeBytes) {
        if (context == null) {
            return "";
        }
        final BytesResult res = formatBytes(context.getResources(), sizeBytes, FLAG_DEFAULT);
        return bidiWrap(context, context.getString(R.string.fileSizeSuffix,
                res.value, res.units));
    }

    private static String bidiWrap(@NonNull Context context, String source) {
        final Locale locale = context.getResources().getConfiguration().locale;
        if (TextUtilsCompat.getLayoutDirectionFromLocale(locale) == View.LAYOUT_DIRECTION_RTL) {
            return BidiFormatter.getInstance(true /* RTL*/).unicodeWrap(source);
        } else {
            return source;
        }
    }

    public static BytesResult formatBytes(Resources res, long sizeBytes, int flags) {
        final boolean isNegative = (sizeBytes < 0);
        float result = isNegative ? -sizeBytes : sizeBytes;
        int suffix = R.string.byteShort;
        long mult = 1;
        if (result > 900) {
            suffix = R.string.kilobyteShort;
            mult = 1000;
            result = result / 1000;
        }
        if (result > 900) {
            suffix = R.string.megabyteShort;
            mult *= 1000;
            result = result / 1000;
        }
        if (result > 900) {
            suffix = R.string.gigabyteShort;
            mult *= 1000;
            result = result / 1000;
        }
        if (result > 900) {
            suffix = R.string.terabyteShort;
            mult *= 1000;
            result = result / 1000;
        }
        if (result > 900) {
            suffix = R.string.petabyteShort;
            mult *= 1000;
            result = result / 1000;
        }
        // Note we calculate the rounded long by ourselves, but still let String.format()
        // compute the rounded value. String.format("%f", 0.1) might not return "0.1" due to
        // floating point errors.
        final int roundFactor;
        final String roundFormat;
        if (mult == 1 || result >= 100) {
            roundFactor = 1;
            roundFormat = "%.0f";
        } else if (result < 1) {
            roundFactor = 100;
            roundFormat = "%.1f";
        } else if (result < 10) {
            if ((flags & FLAG_SHORTER) != 0) {
                roundFactor = 10;
                roundFormat = "%.1f";
            } else {
                roundFactor = 100;
                roundFormat = "%.1f";
            }
        } else { // 10 <= result < 100
            if ((flags & FLAG_SHORTER) != 0) {
                roundFactor = 1;
                roundFormat = "%.0f";
            } else {
                roundFactor = 100;
                roundFormat = "%.1f";
            }
        }

        if (isNegative) {
            result = -result;
        }
        final String roundedString = String.format(roundFormat, result);

        // Note this might overflow if abs(result) >= Long.MAX_VALUE / 100, but that's like 80PB so
        // it's okay (for now)...
        final long roundedBytes =
                (flags & FLAG_CALCULATE_ROUNDED) == 0 ? 0
                        : (((long) Math.round(result * roundFactor)) * mult / roundFactor);

        final String units = res.getString(suffix);

        return new BytesResult(roundedString, units, roundedBytes);
    }

    public static class BytesResult {
        public final String value;
        public final String units;
        public final long roundedBytes;

        public BytesResult(String value, String units, long roundedBytes) {
            this.value = value;
            this.units = units;
            this.roundedBytes = roundedBytes;
        }
    }

    public static class DistanceResult {
        public final String value;
        public final String units;

        public DistanceResult(String value, String units) {
            this.value = value;
            this.units = units;
        }
    }
}
