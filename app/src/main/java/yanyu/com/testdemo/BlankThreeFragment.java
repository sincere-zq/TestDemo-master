package yanyu.com.testdemo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankThreeFragment extends Fragment {


    public BlankThreeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        // Inflate the layout for this fragment
        ImageView imageView = (ImageView) view.findViewById(R.id.img);
        imageView.setImageResource(R.mipmap.bingtwo);

        return view;
    }
    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return super.onCreateAnimation(transit, enter, nextAnim);
    }
}
