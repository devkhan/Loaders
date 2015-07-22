package teamdapsr.loaders.app;

import android.app.WallpaperManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by rajanmaurya on 9/7/15.
 */
public class Gif extends AppCompatActivity {

    WebView view;
    InputStream stream;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        stream = null;
        try {
            stream = getAssets().open("sperm_loader.gif");
        } catch (IOException e) {
            e.printStackTrace();
        }

        view = (WebView)findViewById(R.id.webView);
        view.loadUrl("file:///android_asset/sperm_loader.gif");
       // view = new WebView(this, "file:///android_asset/sperm_loader.gif");

        Button button  = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WallpaperManager myWallpaperManager = WallpaperManager.getInstance(getApplicationContext());
                try {
                    myWallpaperManager.setStream(stream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}
