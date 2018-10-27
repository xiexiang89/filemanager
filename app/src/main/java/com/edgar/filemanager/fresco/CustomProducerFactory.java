package com.edgar.filemanager.fresco;

import android.content.ContentResolver;
import android.content.Context;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.core.ExecutorSupplier;
import com.facebook.imagepipeline.core.ProducerFactory;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.image.CloseableImage;

/**
 * Created by Edgar on 2018/10/27.
 */
public class CustomProducerFactory extends ProducerFactory {

    private ContentResolver mContentResolver;
    private final ExecutorSupplier mExecutorSupplier;
    private final PooledByteBufferFactory mPooledByteBufferFactory;

    public CustomProducerFactory(Context context, ByteArrayPool byteArrayPool, ImageDecoder imageDecoder, ProgressiveJpegConfig progressiveJpegConfig, boolean downsampleEnabled, boolean resizeAndRotateEnabledForNetwork, boolean decodeCancellationEnabled, ExecutorSupplier executorSupplier, PooledByteBufferFactory pooledByteBufferFactory, MemoryCache<CacheKey, CloseableImage> bitmapMemoryCache, MemoryCache<CacheKey, PooledByteBuffer> encodedMemoryCache, BufferedDiskCache defaultBufferedDiskCache, BufferedDiskCache smallImageBufferedDiskCache, CacheKeyFactory cacheKeyFactory, PlatformBitmapFactory platformBitmapFactory, int bitmapPrepareToDrawMinSizeBytes, int bitmapPrepareToDrawMaxSizeBytes, boolean bitmapPrepareToDrawForPrefetch, int maxBitmapSize) {
        super(context, byteArrayPool, imageDecoder, progressiveJpegConfig, downsampleEnabled, resizeAndRotateEnabledForNetwork, decodeCancellationEnabled, executorSupplier, pooledByteBufferFactory, bitmapMemoryCache, encodedMemoryCache, defaultBufferedDiskCache, smallImageBufferedDiskCache, cacheKeyFactory, platformBitmapFactory, bitmapPrepareToDrawMinSizeBytes, bitmapPrepareToDrawMaxSizeBytes, bitmapPrepareToDrawForPrefetch, maxBitmapSize);
        mContentResolver = context.getApplicationContext().getContentResolver();
        mExecutorSupplier = executorSupplier;
        mPooledByteBufferFactory = pooledByteBufferFactory;
    }

    @Override
    public com.facebook.imagepipeline.producers.LocalContentUriFetchProducer newLocalContentUriFetchProducer() {
        return new LocalContentUriFetchProducer(mExecutorSupplier.forLocalStorageRead(), mPooledByteBufferFactory, mContentResolver);
    }
}
