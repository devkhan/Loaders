package devkhan.loaders.lib;

import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;

import devkhan.loaders.lib.utils.MeasureUtils;

/**
 * Beating Heart. Extends {@link View}. A Custom View.
 * <p/>
 * Created on 27-July-2015.
 * Modified on 28-July-2015.
 *
 * @author Devesh Khandelwal
 */
public class HeartDrawView extends View {
    protected int mTValue;
    protected Paint mHeartPaint;
    protected int mAngle = 0;
    protected Path mHeartOutline;
    protected int mX, mY, centerX, centerY;
    protected ValueAnimator mSizeAnimator;
    protected Path mEmptyPath = new Path();
    private String LOG_TAG = getClass().getSimpleName();

    /**
     * Simple constructor to use when creating a view from code.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     */
    public HeartDrawView(Context context) {
        super(context);
    }

    /**
     * Constructor that is called when inflating a view from XML. This is called
     * when a view is being constructed from an XML file, supplying attributes
     * that were specified in the XML file. This version uses a default style of
     * 0, so the only attribute values applied are those in the Context's Theme
     * and the given AttributeSet.
     * <p>
     * <p>
     * The method onFinishInflate() will be called after all children have been
     * added.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     * @param attrs   The attributes of the XML tag that is inflating the view.
     */
    public HeartDrawView(Context context, AttributeSet attrs) {
        super(context, attrs);

        /**
         * Gets the defined attributes in the layout resource file as a {@link TypedArray}.
         */
        TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.HeartDrawView,
                0, 0
        );

        /**
         * Trying to extract defined attributes in the layout resource file.
         */
        try {
            mTValue = styledAttributes.getInt(R.styleable.HeartDrawView_tValue, 10);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            styledAttributes.recycle();
        }
        init();
    }

    protected void init() {
        mHeartPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mHeartPaint.setStyle(Paint.Style.STROKE);
        mHeartPaint.setColor(0xffff0000);

        mHeartOutline = new Path();

        /**
         * Animating size of the shape/path to be drawn.
         */
        mSizeAnimator = ValueAnimator.ofFloat((float) (0.5 * mTValue), (float) mTValue);
        mSizeAnimator.setDuration(3000);
        mSizeAnimator.setInterpolator(new AnticipateOvershootInterpolator());
        mSizeAnimator.setRepeatMode(ValueAnimator.REVERSE);
        mSizeAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mSizeAnimator.start();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Reallocating path to remove previous draws.
        mHeartOutline.reset();

        // Resetting angle.
        mAngle = 360;
        Log.i(LOG_TAG, "Angle reset.");

        // Get the size of the shape to be drawn from the animator.
        float size = ((float) (mSizeAnimator.getAnimatedValue()));
        Log.i(LOG_TAG, "TValue animated value: " + mTValue);

        // Creating path for angle 360->0 degrees.
        while (mAngle != 0) {
            mHeartOutline.moveTo(mX, mY);
            mX = centerX + (int) (size * 16 * pow(sin(toRadians(mAngle)), 3));
            mY = centerY + (int) (size * ((13 * cos(toRadians(mAngle))) - 5 * cos
                    (2 * toRadians(mAngle)) - 2 * cos(3 * toRadians(mAngle)) - cos(
                    4 * toRadians(mAngle))));
            mY *= -1;
            mY += getMeasuredHeight();
            mHeartOutline.lineTo(mX, mY);
            mAngle -= 2;
        }

        // Draw the path.
        canvas.drawPath(mHeartOutline, mHeartPaint);
        Log.i(LOG_TAG, "Shape drawn.");
        /**
         * Waiting for sometime, mainly for animation purposes and litte bit performance issues.
         */
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        centerX = (int) event.getX();
        centerY = (int) event.getY();

        postInvalidate();
        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Get the width measurement
        int widthSize = MeasureUtils.getMeasurement(widthMeasureSpec, getDesiredWidth());

        // Get the height measurement
        int heightSize = MeasureUtils.getMeasurement(heightMeasureSpec, getDesiredHeight());

        //MUST call this to store the measurements
        setMeasuredDimension(widthSize + 10, heightSize + 10);

        centerX = getMeasuredWidth() / 2;
        centerY = getMeasuredHeight() / 2;
    }

    /**
     * Calculates width from child components.
     *
     * @return Desired width in view.
     */
    private int getDesiredWidth() {
        // TO-DO Calculate width from child components.

        return 2 * mTValue + 300;
    }

    /**
     * Calculate height from child components.
     *
     * @return Desired height of view.
     */
    private int getDesiredHeight() {
        // TO-DO Calculate height from child components.
        return 2 * mTValue + 300;
    }
}
