package edu.cnm.deepdive.ormdemo;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.j256.ormlite.dao.Dao;
import edu.cnm.deepdive.ormdemo.dummy.DummyContent;
import edu.cnm.deepdive.ormdemo.entities.Student;
import edu.cnm.deepdive.ormdemo.helpers.OrmHelper;
import java.sql.SQLException;

/**
 * A fragment representing a single Student detail screen. This fragment is either contained in a
 * {@link StudentListActivity} in two-pane mode (on tablets) or a {@link StudentDetailActivity} on
 * handsets.
 */
public class StudentDetailFragment extends Fragment {

  /**
   * The fragment argument representing the item ID that this fragment represents.
   */
  public static final String ARG_ITEM_ID = "student_id";

  /**
   * The dummy content this fragment is presenting.
   */
  private Student mItem;

  private OrmHelper helper;
  private View rootView;


  /**
   * Mandatory empty constructor for the fragment manager to instantiate the fragment (e.g. upon
   * screen orientation changes).
   */
  public StudentDetailFragment() {
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (getArguments().containsKey(ARG_ITEM_ID)) {
      // Load the dummy content specified by the fragment
      // arguments. In a real-world scenario, use a Loader
      // to load content from a content provider.
      try {
        mItem = ((StudentDetailFragmentDaoInteraction) getContext())
            .getDao().queryForId(getArguments().getInt(ARG_ITEM_ID));
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }

      Activity activity = this.getActivity();
      CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity
          .findViewById(R.id.toolbar_layout);
      if (appBarLayout != null) {
        appBarLayout.setTitle(mItem.getName());
      }
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.student_detail, container, false);

    // Show the dummy content as text in a TextView.
    if (mItem != null) {
      ((TextView) rootView.findViewById(R.id.student_detail)).setText(mItem.toString());
    }

    return rootView;
  }

  public interface StudentDetailFragmentDaoInteraction {

    Dao<Student, Integer> getDao() throws SQLException;
  }
}
