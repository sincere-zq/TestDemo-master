package yanyu.com.testdemo;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/2/17 0017.
 * Author：zengqiang
 * Description:功能描述
 */


public class TextViewAdapter extends PagerAdapter {
    private List<TextView> textViews;
    private Context context;

    public TextViewAdapter(Context context) {
        textViews = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            TextView text = new TextView(context);
            if (i == 0)
                text.setText("frag1");
            if (i == 1)
                text.setText("frag2");
            if (i == 2)
                text.setText("frag3");
            text.setBackgroundColor(Color.GREEN);
            ViewGroup.MarginLayoutParams la = new ViewGroup.MarginLayoutParams(100, 50);
            la.setMargins(10, 10, 10, 10);
            text.setLayoutParams(la);
            text.setGravity(Gravity.CENTER);
            textViews.add(text);
        }
    }

    @Override
    public int getCount() {
        return textViews == null ? 0 : Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //对ViewPager页号求模取出View列表中要显示的项
        position %= textViews.size();
        if (position < 0) {
            position = textViews.size() + position;
        }
        TextView view = textViews.get(position);
        //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
        ViewParent vp = view.getParent();
        if (vp != null) {
            ViewGroup parent = (ViewGroup) vp;
            parent.removeView(view);
        }
        container.addView(view);
        //add listeners here if necessary
        return view;
    }
}
