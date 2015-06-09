package teamdapsr.loaders.app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.widget.LinearLayout;

import teamdapsr.loaders.lib.CrosswordGrid;

/**
 * Created by pa1pal on 9/6/15.
 */
public class Cross extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context context;
        LinearLayout layout = new LinearLayout(this);
        layout.setGravity(Gravity.CENTER);
        layout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        CrosswordGrid crosswordGrid = new CrosswordGrid(getApplicationContext(), 10);
        layout.addView(crosswordGrid, layoutParams);

        setContentView(layout);
    }
}
