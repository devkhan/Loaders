package teamdapsr.loaders.app.GIF;

import android.content.Context;
import android.webkit.WebView;

/**
 * Created by rajanmaurya on 9/7/15.
 */
public class GifWebView extends WebView {

    public GifWebView(Context context, String path) {
        super(context);

        loadUrl(path);
    }
}