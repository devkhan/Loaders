package teamdapsr.loaders.app.Fill_able;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import teamdapsr.loaders.app.R;

/**
 * @author jorge
 * @since 7/08/15
 */
public class Fill_able_MainActivity extends AppCompatActivity {

  @Bind(R.id.pager) ViewPager pager;
  @Bind(R.id.clippingTransformMode) TextView clippingModeText;


  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.fill_able);
    ButterKnife.bind(this);
    setupPagination();
    setupDisableTraceButton();
  }

  private void setupPagination() {
    pager = (ViewPager) findViewById(R.id.pager);
    final FillablePagesAdapter adapter = new FillablePagesAdapter(getSupportFragmentManager());
    pager.setAdapter(adapter);
    pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
      @Override public void onPageSelected(int position) {
        super.onPageSelected(position);
        ((ResettableView) adapter.getItem(position)).reset();
        clippingModeText.setText(adapter.getPageTitle(position));
      }
    });

    pager.post(new Runnable() {
      @Override
      public void run() {
        ((ResettableView) adapter.getItem(0)).reset();
        clippingModeText.setText(adapter.getPageTitle(0));
      }
    });
  }



  private void setupDisableTraceButton() {

  }

}