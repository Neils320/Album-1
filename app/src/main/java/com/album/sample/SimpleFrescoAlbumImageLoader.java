package com.album.sample;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.album.AlbumConstant;
import com.album.AlbumImageLoader;
import com.album.model.AlbumModel;
import com.album.model.FinderModel;
import com.album.ui.annotation.FrescoType;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/**
 * by y on 20/08/2017.
 */

public class SimpleFrescoAlbumImageLoader implements AlbumImageLoader {

    @Override
    public void displayAlbum(@NonNull ImageView view, int width, int height, @NonNull AlbumModel albumModel) {
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view;
        Uri uri = Uri.parse("file://" + albumModel.getPath());
        ImageRequest request = ImageRequestBuilder
                .newBuilderWithSource(uri)
                .setRotationOptions(RotationOptions.autoRotate())
                .setResizeOptions(new ResizeOptions(width, height))
                .setLowestPermittedRequestLevel(ImageRequest.RequestLevel.FULL_FETCH)
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setTapToRetryEnabled(true)
                .setImageRequest(request)
                .setAutoPlayAnimations(true)
                .setOldController(simpleDraweeView.getController())
                .build();
        GenericDraweeHierarchy hierarchy = simpleDraweeView.getHierarchy();
//        hierarchy.setPlaceholderImage(R.drawable.ic_launcher);
        hierarchy.setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP);
        simpleDraweeView.setController(controller);
    }

    @Override
    public void displayAlbumThumbnails(@NonNull ImageView view, @NonNull FinderModel finderModel) {
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view;
        Uri uri = Uri.parse("file://" + finderModel.getThumbnailsPath());
        ImageRequest request = ImageRequestBuilder
                .newBuilderWithSource(uri)
                .setRotationOptions(RotationOptions.autoRotate())
                .setResizeOptions(new ResizeOptions(50, 50))
                .setLowestPermittedRequestLevel(ImageRequest.RequestLevel.FULL_FETCH)
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setTapToRetryEnabled(true)
                .setImageRequest(request)
                .setOldController(simpleDraweeView.getController())
                .build();
        GenericDraweeHierarchy hierarchy = simpleDraweeView.getHierarchy();
        hierarchy.setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP);
        simpleDraweeView.setController(controller);
    }

    @Override
    public void displayPreview(@NonNull ImageView view, @NonNull AlbumModel albumModel) {
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view;
        Uri uri = Uri.parse("file://" + albumModel.getPath());
        ImageRequest request = ImageRequestBuilder
                .newBuilderWithSource(uri)
                .setRotationOptions(RotationOptions.autoRotate())
                .setResizeOptions(new ResizeOptions(400, 350))
                .setLowestPermittedRequestLevel(ImageRequest.RequestLevel.FULL_FETCH)
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setTapToRetryEnabled(true)
                .setImageRequest(request)
                .setOldController(simpleDraweeView.getController())
                .build();
        GenericDraweeHierarchy hierarchy = simpleDraweeView.getHierarchy();
        hierarchy.setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP);
        simpleDraweeView.setController(controller);
    }

    @Override
    public ImageView frescoView(@NonNull Context context, @FrescoType int type) {
        switch (type) {
            case AlbumConstant.TYPE_FRESCO_ALBUM:
                return new SimpleDraweeView(context);
            case AlbumConstant.TYPE_FRESCO_PREVIEW:
                return new SimpleDraweeView(context);
        }
        return null;
    }
}
