package yanyu.com.testdemo;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.transition.move;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created on 2017/2/17 0017.
 * Author：zengqiang
 * Description:功能描述
 */


public class TextReAdapter extends RecyclerView.Adapter<TextReAdapter.ViewHolder> {
    private Context context;
    private int screenWidth;
    private RecyclerView recyclerView;
    private int tag = 100;
    private boolean move = false;
    private TagChangListener tagChangListener;

    public void setTagChangListener(TagChangListener tagChangListener) {
        this.tagChangListener = tagChangListener;
    }

    public TextReAdapter(Context context, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
        screenWidth = getScreenWidth(context);
    }

    @Override
    public TextReAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(screenWidth / 2, 100);
//        TextView textView = new TextView(context);
//        textView.setLayoutParams(params);
//        ViewHolder viewHolder = new ViewHolder(textView);
//        textView.setText("frag");
//        textView.setGravity(Gravity.CENTER);
//        textView.setBackgroundColor(Color.BLUE);
        View view = View.inflate(context, R.layout.item_text, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TextReAdapter.ViewHolder holder, final int position) {
        if (position == 100) {
            Toast.makeText(context, holder.text.getText().toString(), Toast.LENGTH_LONG);
        }
        if (position % 3 == 0) {
            holder.text.setText("frag1");
            holder.text.setBackgroundColor(Color.BLUE);
        }
        if (position % 3 == 1) {
            holder.text.setText("frag2");
            holder.text.setBackgroundColor(Color.YELLOW);
        }
        if (position % 3 == 2) {
            holder.text.setText("frag3");
            holder.text.setBackgroundColor(Color.RED);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position == tag) {
//                    recyclerView.smoothScrollToPosition(position + 1);
                    tag = position + 1;
                } else if (tag < position) {
//                    recyclerView.smoothScrollToPosition(position - 2);
                    tag = position - 2;
                }
//                else {
//                    recyclerView.smoothScrollToPosition(position + 1);
//                    tag = position;
//                }
                tagChangListener.change(tag);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((screenWidth - 80) / 2, 200);
            params.setMargins(20, 20, 20, 20);
            text.setLayoutParams(params);
        }
    }


    /**
     * 获取屏幕宽度
     */
    @SuppressWarnings("deprecation")
    public static int getScreenWidth(Context context) {
        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        return display.getWidth();
    }

    interface TagChangListener {
        void change(int tag);
    }


}
