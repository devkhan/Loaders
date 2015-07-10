package teamdapsr.loaders.lib;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;

import teamdapsr.loaders.lib.utils.MeasureUtils;

/**
 * Created by Devesh on 08-Jul-15.
 */
public class CubicBezierRotate extends View{

	private int radius = 0;
	private ShapeDrawable circleOne, circleTwo, circleThree;

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
			radius = a.getInt(R.styleable.CubicBezierRotate_radius, 10);
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

		animator.start();

		while(radius<100) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			updateBounds();
		}
	}

	protected void updateBounds()
	{
		circleOne.setBounds(radius, radius, radius, radius);
		circleTwo.setBounds(radius, radius, radius, radius);
		circleThree.setBounds(radius, radius, radius, radius);

		invalidate();
		requestLayout();
	}
	@Override
	public void onDraw(Canvas canvas)
	{
		circleOne.draw(canvas);
		circleTwo.draw(canvas);
		circleThree.draw(canvas);
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

		//MUST call this to store the measurements
		setMeasuredDimension(widthSize + 10, heightSize + 10);
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
