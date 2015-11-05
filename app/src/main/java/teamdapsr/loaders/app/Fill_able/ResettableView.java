package teamdapsr.loaders.app.Fill_able;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * @author jorge
 * @since 11/08/15
 */
public interface ResettableView {

  void onCreate(@Nullable Bundle savedInstanceState, LayoutInflater inflater);

  void onCreate(@Nullable Bundle savedInstanceState, LayoutInflater inflater, ViewGroup container);

  void reset();
}
