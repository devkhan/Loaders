package teamdapsr.loaders.app.GIF;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.os.SystemClock;
import android.view.View;

import java.io.InputStream;

/**
 * Created by rajanmaurya on 9/7/15.
 */
public class GifMovieView extends View {

    private Movie mMovie;

    private long mMoviestart;

    public GifMovieView(Context context, InputStream stream) {
        super(context);

        mMovie = Movie.decodeStream(stream);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.TRANSPARENT);
        super.onDraw(canvas);
        final long now = SystemClock.uptimeMillis();

        if (mMoviestart == 0) {
            mMoviestart = now;
        }

        final int relTime = (int)((now - mMoviestart) % mMovie.duration());
        mMovie.setTime(relTime);
        mMovie.draw(canvas, 10, 10);
        this.invalidate();
    }
}