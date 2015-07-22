package teamdapsr.loaders.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import teamdapsr.loaders.lib.ProgressWheel;

/**
 * Created by rajanmaurya on 9/7/15.
 */
public class progressdemo extends AppCompatActivity {


    private ProgressWheel progressWheel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progressdemo);
        progressWheel = (ProgressWheel) findViewById(R.id.progress_wheel);

    }
}
