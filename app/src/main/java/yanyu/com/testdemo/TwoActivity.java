package yanyu.com.testdemo;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;


public class TwoActivity extends AppCompatActivity {
    private int margin;
    private int width;
    private LinearLayout activity_two;
    private int screenWidth;
    private RecyclerView recyclerView;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        activity_two = (LinearLayout) findViewById(R.id.activity_two);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        });
//        recyclerView.setAdapter(new TextReAdapter(this, recyclerView));
        screenWidth = getScreenWidth(this);
        margin = 10;
        recyclerView.scrollToPosition(10);
        ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(screenWidth / 2, 100);
        TextView one = new TextView(this);
        one.setLayoutParams(layoutParams);
        one.setPadding(10, 10, 10, 10);
        one.setText("frag1");
        one.setBackgroundColor(Color.YELLOW);
        one.setGravity(Gravity.CENTER);
        TextView two = new TextView(this);
        two.setLayoutParams(layoutParams);
        two.setText("frag2");
        two.setBackgroundColor(Color.RED);
        two.setGravity(Gravity.CENTER);
        TextView three = new TextView(this);
        three.setLayoutParams(layoutParams);
        three.setText("frag3");
        three.setBackgroundColor(Color.BLUE);
        three.setGravity(Gravity.CENTER);
        activity_two.addView(one);
        activity_two.addView(two);
        activity_two.addView(three);

    }


    /**
     * 获取屏幕宽度
     */
    @SuppressWarnings("deprecation")
    public static int getScreenWidth(Context context) {
        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        return display.getWidth();
    }
}
