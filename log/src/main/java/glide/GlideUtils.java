package glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

/**
 * 描述：Glide加载图片封装
 * 作者：小辉
 * 时间：2018/05/18
 */

public class GlideUtils implements IGlide {
    private static GlideUtils glideUtils;

    public static GlideUtils getInstance() {
        if (glideUtils == null) {
            synchronized (GlideUtils.class) {
                if (glideUtils == null) {
                    glideUtils = new GlideUtils();
                }
            }
        }
        return glideUtils;
    }

    @Override
    public void loadImg(Context context, Object url, int bitmap, int error, ImageView imageView) {
        if (error > 0 && bitmap > 0) {
            Glide.with(context).load(url).placeholder(bitmap).error(error).into(imageView);
        }
        if (bitmap > 0 && error == 0) {
            Glide.with(context).load(url).placeholder(bitmap).into(imageView);
        }
        if (bitmap == 0 && error == 0) {
            Glide.with(context).load(url).into(imageView);
        }
    }

    @Override
    public void loadImg(Context context, Object url, int bitmap, int error, float thumbnail, ImageView imageView) {
        if (error > 0 && bitmap > 0) {
            Glide.with(context).load(url).placeholder(bitmap).error(error).thumbnail(thumbnail).into(imageView);
        }
        if (bitmap > 0 && error == 0) {
            Glide.with(context).load(url).placeholder(bitmap).thumbnail(thumbnail).into(imageView);
        }
        if (bitmap == 0 && error == 0) {
            Glide.with(context).load(url).thumbnail(thumbnail).into(imageView);
        }
    }

    @Override
    public void loadCircularImg(final Context context, Object url, int bitmap, int error, final ImageView imageView) {
        if (error > 0 && bitmap > 0) {
            Glide.with(context)
                    .load(url)
                    .asBitmap()
                    .placeholder(bitmap)
                    .error(error)
                    .dontAnimate()
                    .centerCrop()
                    .into(new BitmapImageViewTarget(imageView) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            imageView.setImageDrawable(circularBitmapDrawable);
                        }
                    });
        }
        if (bitmap > 0 && error == 0) {
            Glide.with(context)
                    .load(url)
                    .asBitmap()
                    .placeholder(bitmap)
                    .dontAnimate()
                    .centerCrop()
                    .into(new BitmapImageViewTarget(imageView) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            imageView.setImageDrawable(circularBitmapDrawable);
                        }
                    });
        }
        if (bitmap == 0 && error == 0) {
            Glide.with(context)
                    .load(url)
                    .asBitmap()
                    .dontAnimate()
                    .centerCrop()
                    .into(new BitmapImageViewTarget(imageView) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            imageView.setImageDrawable(circularBitmapDrawable);
                        }
                    });
        }
    }
}
