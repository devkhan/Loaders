package teamdapsr.loaders.lib;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by deveshkhandelwal on 11/08/15.
 */
public class FillingCircles extends View {
    public FillingCircles(Context context) {
        super(context);
    }

    public FillingCircles(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.HeartDrawView,
                0, 0
        );

        try {

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            styledAttributes.recycle();
        }
    }
}
