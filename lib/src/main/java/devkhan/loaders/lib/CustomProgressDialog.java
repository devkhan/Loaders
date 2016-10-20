package devkhan.loaders.lib;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by Devesh on 08-Jun-15.
 */
public class CustomProgressDialog extends Dialog {
    private final String LOG_TAG = "CustomProgressDialog";
    private ImageView one, two, three, four;
    private float beginValue, endValue, height, width;
    private RelativeLayout.LayoutParams layoutParams;
    private int duration = 3000;
    private float interpolationTime = 0.9f;

    /**
     * @param context Activity context to create views.
     */
    public CustomProgressDialog(Context context) {
        super(context);
    }

    /**
     * @param savedInstance Saved instance for resuming from onPause.
     */
    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.custom_progress_dialog);

        one = (ImageView) findViewById(R.id.one);
        two = (ImageView) findViewById(R.id.two);
        three = (ImageView) findViewById(R.id.three);
        four = (ImageView) findViewById(R.id.four);

        height = this.getWindow().getDecorView().getHeight();
        width = this.getWindow().getDecorView().getWidth();

        Log.i(LOG_TAG, "Height: " + height + " Width: " + width);

    }

    private void animate() {

        height = width = 200;
        final TranslateAnimation left2right = new TranslateAnimation(0,
                width, 0, 0);
        final TranslateAnimation top2bottom = new TranslateAnimation(0, 0, 0,
                height);
        final TranslateAnimation right2left = new TranslateAnimation(0,
                -width, 0, 0);
        final TranslateAnimation bottom2top = new TranslateAnimation(0, 0, 0,
                -height);

        left2right.setDuration(duration);
        top2bottom.setDuration(duration);
        right2left.setDuration(duration);
        bottom2top.setDuration(duration);

        left2right.setInterpolator(new AccelerateDecelerateInterpolator() {
            @Override
            public float getInterpolation(float v) {
                return v;
            }
        });
        top2bottom.setInterpolator(new AccelerateDecelerateInterpolator() {
            @Override
            public float getInterpolation(float v) {
                return v;
            }
        });
        right2left.setInterpolator(new AccelerateDecelerateInterpolator() {
            @Override
            public float getInterpolation(float v) {
                return v;
            }
        });
        bottom2top.setInterpolator(new AccelerateDecelerateInterpolator() {
            @Override
            public float getInterpolation(float v) {
                return v;
            }
        });

        left2right.setFillAfter(true);
        top2bottom.setFillAfter(true);
        right2left.setFillAfter(true);
        bottom2top.setFillAfter(true);

        left2right.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                one.startAnimation(top2bottom);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        top2bottom.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                one.startAnimation(right2left);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        right2left.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                one.startAnimation(bottom2top);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        bottom2top.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                one.startAnimation(left2right);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        one.startAnimation(left2right);
    }

    @Override
    public void show() {
        super.show();
        animate();
    }

    @Override
    public void dismiss() {
        super.dismiss();

    }
}
