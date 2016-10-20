package devkhan.loaders.lib;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import devkhan.loaders.lib.utils.MeasureUtils;
import devkhan.loaders.lib.utils.Utils;

/**
 * Animating concentric circles. Extends {@link View}. A Custom View.
 * <p/>
 * Created on 08-July-2015.
 * Modified on 13-July-2015.
 *
 * @author Devesh Khandelwal
 */
public class ConcentricCircleView extends View {

    /**
     * Minimum radius of the circles.
     */
    protected int mMinRadius;
    /**
     * Maximum radius of the circles.
     */
    protected int mMaxRadius;
    /**
     * Number of circles.
     */
    protected int mCirclesCount;
    /**
     * Animate color or not.
     */
    protected boolean mAnimateColor;
    /**
     * Starting color, if animating.
     */
    protected int mStartColor;
    /**
     * Ending color, if animating.
     */
    protected int mEndColor;
    /**
     * Animation duration.
     */
    protected int mDuration;
    /**
     * Array of {@link ValueAnimator} objects. One for each circle.
     */
    protected ValueAnimator mAnim[];
    /**
     * Array of {@link Paint} objects. One for each circle, if not animating color.
     */
    protected Paint mPaint[];
    /**
     * Paint object used for drawing, if animating color.
     */
    protected Paint mAnimatePaint;
    /**
     * Aniamtor for color, if animating.
     */
    protected ValueAnimator mColorAnim;
    /**
     * Class name for logging.
     */
    private String LOG_TAG = getClass().getSimpleName();


    /**
     * Instantiates an object with just a {@link Context} and the default attributes. Used when
     * creating a view programmatically.
     *
     * @param context variable required for instantiating a view.
     */
    public ConcentricCircleView(Context context) {
        super(context);

        /**
         * Setting default values.
         */
        mMaxRadius = 0;
        mMaxRadius = 100;
        mCirclesCount = 3;
        mAnimateColor = false;
        mDuration = 3000;

        init();
    }

    /**
     * Instantiates an object with just a {@link Context} and attributes provided in the
     * attribute set by the XML resource file. Called when defining view via XML resource.
     *
     * @param context variable required for instantiating a view.
     * @param attrs   Attribute Set containing attributes defined in the layout resource file.
     */
    public ConcentricCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // TODO: Get parent view's measurements so that max radius can be defaulted to max
        // height/width.

        /**
         * Gets the defined attributes in the layout resource file as a {@link TypedArray}.
         */
        TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ConcentricCircleView,
                0, 0
        );

        /**
         * Trying to extract defined attributes in the layout resource file.
         */
        try {
            mMinRadius = styledAttributes.getInt(R.styleable.ConcentricCircleView_min_radius, 0);
            mMaxRadius = styledAttributes.getInt(R.styleable.ConcentricCircleView_max_radius, 100);
            mCirclesCount = styledAttributes.getInt(R.styleable.ConcentricCircleView_circles_count,
                    3);
            mAnimateColor = styledAttributes.getBoolean(
                    R.styleable.ConcentricCircleView_animate_color, true);
            if (mAnimateColor) {
                mStartColor = styledAttributes.getColor(
                        R.styleable.ConcentricCircleView_start_color, Color
                                .parseColor("#00000000"));
                mEndColor = styledAttributes.getColor(R.styleable.ConcentricCircleView_end_color,
                        Color.parseColor
                                ("#ffffffff"));
            }
            mDuration = styledAttributes.getInt(R.styleable.ConcentricCircleView_duration, 1500);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            styledAttributes.recycle();
        }
        init();
    }

    /**
     * Initializing {@link Paint} objects to draw later on.
     */
    protected void init() {
        /**
         * Setting up paints for drawing circles.
         */
        if (mAnimateColor) {
            mAnimatePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mAnimatePaint.setStyle(Paint.Style.FILL);
            mAnimatePaint.setColor(mStartColor);
        } else {
            mPaint = new Paint[mCirclesCount];

            for (int i = 0; i < mPaint.length; i++) {
                mPaint[i] = new Paint(Paint.ANTI_ALIAS_FLAG);
                mPaint[i].setStyle(Paint.Style.FILL);
                mPaint[i].setColor(Utils.randomColor());
            }
        }

    }

    /**
     * Setting up {@link ValueAnimator} objects and starting them.
     */
    protected void setupAnimations() {
        /**
         * Setting up animations for animating circles and colors.
         */
        if (mAnimateColor) {
            if (mStartColor <= mEndColor) {
                mColorAnim = ValueAnimator.ofInt(mStartColor, mEndColor);
            } else {
                mColorAnim = ValueAnimator.ofInt(mEndColor, mStartColor);
            }

            mColorAnim.setDuration(mDuration * 10);
            mColorAnim.setRepeatMode(ValueAnimator.REVERSE);
            mColorAnim.setRepeatCount(ValueAnimator.INFINITE);
            mColorAnim.start();
        }

        mAnim = new ValueAnimator[mCirclesCount];

        for (int i = 0; i < mAnim.length; i++) {
            mAnim[i] = ValueAnimator.ofInt(mMinRadius, mMaxRadius);
            mAnim[i].setDuration(mDuration);
            mAnim[i].setRepeatMode(ValueAnimator.REVERSE);
            mAnim[i].setRepeatCount(ValueAnimator.INFINITE);
            mAnim[i].setInterpolator(Utils.randomInterpolator());
            mAnim[i].start();
        }
    }

    @Override
    public void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        if (!mAnimateColor) {
            for (int i = 0; i < mCirclesCount; i++) {
                Log.i(LOG_TAG, "Radius " + i + " : " + (int) (mAnim[i]
                        .getAnimatedValue()));
                canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, (int) (mAnim[i]
                        .getAnimatedValue()), mPaint[i]);
            }
        } else {
            mAnimatePaint.setColor((int) (mColorAnim.getAnimatedValue()));
            for (int i = 0; i < mCirclesCount; i++) {
                Log.i(LOG_TAG, "Radius " + i + " : " + (int) (mAnim[i]
                        .getAnimatedValue()));
                canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, (int) (mAnim[i]
                        .getAnimatedValue()), mAnimatePaint);
            }
        }

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

        setupAnimations();
    }

    /**
     * Calculates width from child components.
     *
     * @return Desired width in view.
     */
    private int getDesiredWidth() {
        // TO-DO Calculate width from child components.

        return 2 * mMaxRadius + 100;
    }

    /**
     * Calculate height from child components.
     *
     * @return Desired height of view.
     */
    private int getDesiredHeight() {
        // TO-DO Calculate height from child components.
        return 2 * mMaxRadius + 100;
    }


    /**
     * Getter for attribute 'min_radius'.
     *
     * @return Value for attribute 'min_radius'.
     */
    public int getMinRadius() {
        return mMinRadius;
    }

    /**
     * Setter for attribute 'min_radius'.
     *
     * @param mMinRadius Value to set for attribute 'min_radius'.
     */
    public void setMinRadius(int mMinRadius) {
        this.mMinRadius = mMinRadius;
        setupAnimations();
        invalidate();
    }

    /**
     * Getter for attribute 'max_radius'.
     *
     * @return Value for attribute 'max_radius'.
     */
    public int getMaxRadius() {
        return mMaxRadius;
    }

    /**
     * Setter for attribute 'max_radius'.
     *
     * @param mMaxRadius Value to set for attribute 'max_radius'.
     */
    public void setMaxRadius(int mMaxRadius) {
        this.mMaxRadius = mMaxRadius;
        setupAnimations();
        invalidate();
    }

    /**
     * Getter for attribute 'circles_count'.
     *
     * @return Value for attribute 'circles_count'.
     */
    public int getCirclesCount() {
        return mCirclesCount;
    }

    /**
     * Setter for attribute 'circles_count'.
     *
     * @param mCirclesCount Value to set for attribute 'circles_count'.
     */
    public void setCirclesCount(int mCirclesCount) {
        this.mCirclesCount = mCirclesCount;
        init();
        setupAnimations();
        invalidate();
    }

    /**
     * Getter for property 'duration'.
     *
     * @return Value for property 'duration'.
     */
    public int getDuration() {
        return mDuration;
    }

    /**
     * Setter for property 'duration'.
     *
     * @param mDuration Value to set for property 'duration'.
     */
    public void setDuration(int mDuration) {
        this.mDuration = mDuration;
        setupAnimations();
        invalidate();
    }

}
