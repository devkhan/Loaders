package teamdapsr.loaders.lib;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.util.Log;
import android.view.animation.LinearInterpolator;

import teamdapsr.loaders.lib.utils.MeasureUtils;

/**
 * Created by Devesh on 08-Jul-15.
 */
public class CubicBezierRotate extends View{

	private int radius = 0, centerx, centery;
	private ShapeDrawable circleOne, circleTwo, circleThree;
	protected Paint paint[];
	protected ValueAnimator anim1, anim2, anim3;
	public int getRadius(){ return radius; }
	public void setRadius(int radius){ this.radius = radius; }

	public CubicBezierRotate(Context context, AttributeSet attrs)
	{
		super(context, attrs);

		TypedArray a = context.getTheme().obtainStyledAttributes(
				attrs,
				R.styleable.CubicBezierRotate,
				0, 0
		);

		try
		{
			radius = a.getInt(R.styleable.CubicBezierRotate_radius, 0);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			a.recycle();
		}
		init();
	}

	protected void init()
	{
		circleOne = new ShapeDrawable(new OvalShape());
		circleTwo = new ShapeDrawable(new OvalShape());
		circleThree = new ShapeDrawable(new OvalShape());

		circleOne.getPaint().setColor(0x99ff0000);
		circleTwo.getPaint().setColor(0x9900ff00);
		circleThree.getPaint().setColor(0x990000ff);

		ObjectAnimator animator = ObjectAnimator.ofInt(this, "radius", 0, 100);
		animator.setDuration(5000);
		animator.setInterpolator(new AnticipateOvershootInterpolator());

/*		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					while(true) {
						sleep(50);
						updateBounds();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};*/


		paint = new Paint[3];

		paint[0] = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint[1] = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint[2] = new Paint(Paint.ANTI_ALIAS_FLAG);

		paint[0].setStyle(Paint.Style.FILL);
		paint[1].setStyle(Paint.Style.FILL);
		paint[2].setStyle(Paint.Style.FILL);

		paint[0].setColor(0x10ff0000);
		paint[1].setColor(0x1000ff00);
		paint[2].setColor(0x100000ff);



	}

	protected void setupAnimations()
	{

		anim1 = ValueAnimator.ofInt(0, (int)(0.25*getMeasuredHeight()));
		anim2 = ValueAnimator.ofInt(0, (int)(0.35*getMeasuredHeight()));
		anim3 = ValueAnimator.ofInt(0, (int)(0.45*getMeasuredHeight()));

		anim1.setDuration(1500);
		anim2.setDuration(2000);
		anim3.setDuration(2500);

		anim1.setInterpolator(new AccelerateDecelerateInterpolator());
		anim2.setInterpolator(new LinearInterpolator());
		anim3.setInterpolator(new AnticipateOvershootInterpolator());

		anim1.setRepeatMode(ValueAnimator.REVERSE);
		anim2.setRepeatMode(ValueAnimator.REVERSE);
		anim3.setRepeatMode(ValueAnimator.REVERSE);

		anim1.setRepeatCount(ValueAnimator.INFINITE);
		anim2.setRepeatCount(ValueAnimator.INFINITE);
		anim3.setRepeatCount(ValueAnimator.INFINITE);

		anim1.start();
		anim2.start();
		anim3.start();
	}
	protected void updateBounds(int radius)
	{
/*
		circleOne.setBounds(radius, radius, radius, radius);
		circleTwo.setBounds(radius, radius, radius, radius);
		circleThree.setBounds(radius, radius, radius, radius);
*/

		invalidate();
		//requestLayout();
	}

	@Override
	public void onDraw(Canvas canvas)
	{

			Log.i("radius", "Radius1 = " + (int) (anim1.getAnimatedValue()));
			Log.i("radius", "Radius2 = " + (int) (anim2.getAnimatedValue()));
			Log.i("radius", "Radius3 = " + (int) (anim3.getAnimatedValue()));
			canvas.drawCircle(getMeasuredWidth()/2, getMeasuredHeight()/2, (int) (anim1
							.getAnimatedValue()),
					paint[0]);
			canvas.drawCircle(getMeasuredWidth()/2, getMeasuredHeight()/2, (int)(anim2.getAnimatedValue()), paint[1]);
			canvas.drawCircle(getMeasuredWidth()/2, getMeasuredHeight()/2, (int)(anim3.getAnimatedValue()), paint[2]);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		updateBounds(0);

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh)
	{
		super.onSizeChanged(w, h, oldw, oldh);
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		// Get the width measurement
		int widthSize = MeasureUtils.getMeasurement(widthMeasureSpec, getDesiredWidth());

		// Get the height measurement
		int heightSize = MeasureUtils.getMeasurement(heightMeasureSpec, getDesiredHeight());

		centerx = (int)(0.5*MeasureSpec.getSize(widthMeasureSpec));
		centery = (int)(0.5*MeasureSpec.getSize(heightMeasureSpec));

		//MUST call this to store the measurements
		setMeasuredDimension(widthSize + 10, heightSize + 10);

		setupAnimations();
	}

	private int getDesiredWidth()
	{
		// TO-DO Calculate width from child components.

		return 2*radius+200;
	}

	private int getDesiredHeight()
	{
		// TO-DO Calculate height from chile components.
		return 2*radius+200;
	}

}
