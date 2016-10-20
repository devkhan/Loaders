package devkhan.loaders.lib.utils;

import android.graphics.Color;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

import java.util.ArrayList;
import java.util.Random;

/**
 * Methods useful in many other reduntant tasks.
 * <p/>
 * Created on 12-July-15.
 * Modified on 12-July-2015.
 *
 * @author Devesh Khandelwal
 */
public class Utils {

    /**
     * Selects and returns a random {@link Interpolator} from a predefined set of interpolator.
     *
     * @return A random interpolator object.
     */
    public static Interpolator randomInterpolator() {
        Random rnd = new Random();

        ArrayList<Interpolator> interpolatorList = new ArrayList<>();
        interpolatorList.add(new AccelerateDecelerateInterpolator());
        interpolatorList.add(new AccelerateInterpolator());
        interpolatorList.add(new AnticipateInterpolator());
        interpolatorList.add(new AnticipateOvershootInterpolator());
        interpolatorList.add(new BounceInterpolator());
        interpolatorList.add(new DecelerateInterpolator());
        interpolatorList.add(new FastOutLinearInInterpolator());
        interpolatorList.add(new FastOutSlowInInterpolator());
        interpolatorList.add(new LinearInterpolator());
        interpolatorList.add(new LinearOutSlowInInterpolator());
        interpolatorList.add(new OvershootInterpolator());

        return interpolatorList.get(rnd.nextInt(11));
    }

    /**
     * Generates a random ARGB {@link Color}.
     *
     * @return Random {@link Color} as an integer.
     */
    public static int randomColor() {
        Random rnd = new Random();

        return Color.argb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    public static float constrain(float min, float max, float v) {
        return Math.max(min, Math.min(max, v));
    }
}
