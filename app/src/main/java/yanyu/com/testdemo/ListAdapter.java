package yanyu.com.testdemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created on 2017/2/17 0017.
 * Author：zengqiang
 * Description:功能描述
 */


public class ListAdapter extends BaseAdapter {
    private int type;
    private Context context;

    public ListAdapter(int type, Context context) {
        this.type = type;
        this.context = context;
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = View.inflate(context, R.layout.item_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.img_title = (ImageView) view.findViewById(R.id.img_title);
            view.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) view.getTag();
        if (type == 1)
            viewHolder.img_title.setImageResource(R.mipmap.bingtwo);
        if (type == 2)
            viewHolder.img_title.setImageResource(R.mipmap.fanbibi);
        return view;
    }

    public class ViewHolder {
        ImageView img_title;
    }
}
