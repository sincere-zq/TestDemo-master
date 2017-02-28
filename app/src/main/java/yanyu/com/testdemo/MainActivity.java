package yanyu.com.testdemo;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static android.R.transition.move;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private BlankFragment fragment;
    private BlankTwoFragment twoFragment;
    private BlankThreeFragment threeFragment;
    private TextView tv_one, tv_two, tv_three, tv_to;
    FragmentManager fm;
    private int tag = 1;
    private int count = 0;
    private ImageView img_one, img_two, img_three;
    private ListView listThree, listTwo, listOne;
    //    private ViewPager viewPager;
    private RelativeLayout ll_view;
    private RecyclerView recyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private TextReAdapter adapter;
    private int myTag = 1;
    private int aTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    class RecyclerViewListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                int n = aTag - ((LinearLayoutManager) (recyclerView.getLayoutManager())).findFirstVisibleItemPosition();
                if (0 <= n && n < recyclerView.getChildCount()) {
                    int top = recyclerView.getChildAt(n).getLeft();
                    recyclerView.smoothScrollBy(0, top);
                }
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int n = aTag - ((LinearLayoutManager) (recyclerView.getLayoutManager())).findFirstVisibleItemPosition();
            if (0 <= n && n < recyclerView.getChildCount()) {
                int top = recyclerView.getChildAt(n).getLeft();
                recyclerView.scrollBy(0, top);
            }
        }
    }

    private void smoothMoveToPosition(int n) {
        int firstItem = mLinearLayoutManager.findFirstVisibleItemPosition();
        int lastItem = mLinearLayoutManager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            recyclerView.smoothScrollToPosition(n);
        } else if (n <= lastItem) {
            int top = recyclerView.getChildAt(n - firstItem).getLeft();
            recyclerView.smoothScrollBy(top,0);
        } else {
            recyclerView.smoothScrollToPosition(n);
        }
    }

    private void init() {
        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        adapter = new TextReAdapter(this, recyclerView);
//        recyclerView.addOnScrollListener(new RecyclerViewListener());
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(100);
        adapter.setTagChangListener(new TextReAdapter.TagChangListener() {
            @Override
            public void change(int tag) {
                aTag = tag;
                smoothMoveToPosition(tag);
                int curent = tag % 3;
                if (curent == myTag)
                    return;
                else {
                    switch (curent) {
                        case 0:
                            listThree.bringToFront();
                            oneAnimatorUp(listThree);
                            if (myTag == 2)
                                oneAnimatorDown(listTwo);
                            if (myTag == 1)
                                oneAnimatorDown(listOne);
                            myTag = 3;
                            break;
                        case 1:
                            listOne.bringToFront();
                            oneAnimatorUp(listOne);
                            if (myTag == 2)
                                oneAnimatorDown(listTwo);
                            if (myTag == 3)
                                oneAnimatorDown(listThree);
                            myTag = 1;
                            break;

                        case 2:
                            listTwo.bringToFront();
                            oneAnimatorUp(listTwo);
                            if (myTag == 1)
                                oneAnimatorDown(listOne);
                            if (myTag == 3)
                                oneAnimatorDown(listThree);
                            myTag = 2;
                            break;
                    }
                }

            }
        });
        ll_view = (RelativeLayout) findViewById(R.id.ll_view);
        listThree = (ListView) findViewById(R.id.listThree);
        listTwo = (ListView) findViewById(R.id.listTwo);
        listOne = (ListView) findViewById(R.id.listOne);

//        viewPager = (ViewPager) findViewById(viewPager);
//        viewPager.setAdapter(new TextViewAdapter(this));
//        viewPager.setPageMargin(60);
//        viewPager.setCurrentItem(100);
//        ll_view.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                return viewPager.dispatchTouchEvent(motionEvent);
//            }
//        });
//        img_one = (ImageView) findViewById(R.id.img_one);
//        img_two = (ImageView) findViewById(R.id.img_two);
//        img_three = (ImageView) findViewById(R.id.img_three);
//        tv_one = (TextView) findViewById(R.id.tv_one);
//        tv_two = (TextView) findViewById(R.id.tv_two);
//        tv_three = (TextView) findViewById(R.id.tv_three);
        tv_to = (TextView) findViewById(R.id.tv_to);
//        tv_one.setOnClickListener(this);
//        tv_two.setOnClickListener(this);
//        tv_three.setOnClickListener(this);
        tv_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TwoActivity.class));
            }
        });
        listOne.setAdapter(new ListAdapter(0, this));
        listTwo.setAdapter(new ListAdapter(1, this));
        listThree.setAdapter(new ListAdapter(2, this));

//        if (null == fm)
//            fm = getSupportFragmentManager();
//        FragmentTransaction transaction = fm.beginTransaction();
//        fragment = new BlankFragment();
//        transaction.add(R.id.frags, fragment, "one");
//        transaction.commit();
    }

    @Override
    public void onClick(View view) {
//        if (null == fm)
//            fm = getSupportFragmentManager();
//        // 开启Fragment事务
//        FragmentTransaction transaction = fm.beginTransaction();
//        transaction.setCustomAnimations(R.anim.frag_in, R.anim.frag_out);
//        switch (view.getId()) {
//            case R.id.tv_one:
////                replaceOne(transaction);
////                if (tagOne(transaction)) return;
////                hideOne(transaction);
//                if (tag == 1)
//                    return;
////                ImageOne();
//                listOne.bringToFront();
//                oneAnimatorUp(listOne);
//                if (tag == 2)
//                    oneAnimatorDown(listTwo);
//                if (tag == 3)
//                    oneAnimatorDown(listThree);
//                tag = 1;
//                break;
//            case R.id.tv_two:
////                hideTwo(transaction);
////                replaceTwo(transaction);
////                if (tagTwo(transaction)) return;
//                if (tag == 2)
//                    return;
////                imageTwo();
//                listTwo.bringToFront();
//                oneAnimatorUp(listTwo);
//                if (tag == 1)
//                    oneAnimatorDown(listOne);
//                if (tag == 3)
//                    oneAnimatorDown(listThree);
//                tag = 2;
//                break;
//            case R.id.tv_three:
////                hideThree(transaction);
////                replaceThree(transaction);
////                if (tagThree(transaction)) return;
//                if (tag == 3)
//                    return;
//                listThree.bringToFront();
//                oneAnimatorUp(listThree);
//                if (tag == 2)
//                    oneAnimatorDown(listTwo);
//                if (tag == 1)
//                    oneAnimatorDown(listOne);
////                imgThree();
//                tag = 3;
//                break;
//
//        }

    }

    private void imgThree() {
        img_three.bringToFront();
        oneAnimatorUp(img_three);
        if (tag == 2)
            oneAnimatorDown(img_two);
        else if (tag == 1)
            oneAnimatorDown(img_one);
    }

    private void imageTwo() {
        img_two.bringToFront();
        oneAnimatorUp(img_two);
        if (tag == 1)
            oneAnimatorDown(img_one);
        else if (tag == 3)
            oneAnimatorDown(img_three);
    }

    private void ImageOne() {
        img_one.bringToFront();
        oneAnimatorUp(img_one);
        if (tag == 3)
            oneAnimatorDown(img_three);
        else if (tag == 2)
            oneAnimatorDown(img_two);
    }

    private void hideThree(FragmentTransaction transaction) {
        if (null == threeFragment) {
            threeFragment = new BlankThreeFragment();
            transaction.add(R.id.frags, threeFragment);
        }
        hideFragment(transaction);
        transaction.show(threeFragment);
        transaction.commit();
    }

    private void hideTwo(FragmentTransaction transaction) {
        if (null == twoFragment) {
            twoFragment = new BlankTwoFragment();
            transaction.add(R.id.frags, twoFragment);
        }
        hideFragment(transaction);
        transaction.show(twoFragment);
        transaction.commit();
    }

    private void hideOne(FragmentTransaction transaction) {
        if (null == fragment) {
            fragment = new BlankFragment();
            transaction.add(R.id.frags, fragment);
        }
        hideFragment(transaction);
        transaction.show(fragment);
        transaction.commit();
    }

    private void replaceThree(FragmentTransaction transaction) {
        if (threeFragment == null)
            threeFragment = new BlankThreeFragment();
        transaction.replace(R.id.frags, threeFragment);
    }

    private void replaceTwo(FragmentTransaction transaction) {
        if (twoFragment == null)
            twoFragment = new BlankTwoFragment();
        transaction.replace(R.id.frags, twoFragment);
    }

    private void replaceOne(FragmentTransaction transaction) {
        if (fragment == null)
            fragment = new BlankFragment();
        transaction.replace(R.id.frags, fragment);
    }

    private boolean tagOne(FragmentTransaction transaction) {
        if (tag == 1)
            return true;
        if (tag != 1)
            count++;
        if (count > 2) {
            if (tag == 3) {
                twoFragment = (BlankTwoFragment) fm.findFragmentByTag("two");
                if (twoFragment != null)
                    transaction.remove(twoFragment);
            } else if (tag == 2) {
                threeFragment = (BlankThreeFragment) fm.findFragmentByTag("three");
                if (threeFragment != null)
                    transaction.remove(threeFragment);
            }
        }
        fragment = new BlankFragment();
        transaction.add(R.id.frags, fragment, "one");
        tag = 1;
        return false;
    }

    private boolean tagTwo(FragmentTransaction transaction) {
        if (tag == 2)
            return true;
        if (tag != 2)
            count++;
        if (count > 2) {
            if (tag == 3) {
                fragment = (BlankFragment) fm.findFragmentByTag("one");
                if (fragment != null)
                    transaction.remove(fragment);
            } else if (tag == 1) {
                threeFragment = (BlankThreeFragment) fm.findFragmentByTag("three");
                if (threeFragment != null)
                    transaction.remove(threeFragment);
            }
        }
        twoFragment = new BlankTwoFragment();
        transaction.add(R.id.frags, twoFragment, "two");
        tag = 2;
        return false;
    }

    private boolean tagThree(FragmentTransaction transaction) {
        if (tag == 3)
            return true;
        if (tag != 3)
            count++;
        if (count > 2) {
            if (tag == 2) {
                fragment = (BlankFragment) fm.findFragmentByTag("one");
                if (fragment != null)
                    transaction.remove(fragment);
            } else if (tag == 1) {
                twoFragment = (BlankTwoFragment) fm.findFragmentByTag("two");
                if (twoFragment != null)
                    transaction.remove(twoFragment);
            }
        }
        threeFragment = new BlankThreeFragment();
        transaction.add(R.id.frags, threeFragment, "three");
        tag = 3;
        return false;
    }

    //隐藏所有的fragment
    private void hideFragment(FragmentTransaction transaction) {
        if (fragment != null) {
            transaction.hide(fragment);
        }
        if (twoFragment != null) {
            transaction.hide(twoFragment);
        }
        if (threeFragment != null) {
            transaction.hide(threeFragment);
        }
    }

    private void oneAnimatorUp(View imageView) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "translationY", 1000F, -50F, -50F, 0F);
        animator.setDuration(400);
//        DecelerateInterpolator interpolator = new DecelerateInterpolator(0.2f);
//        LinearInterpolator interpolator = new LinearInterpolator();
//        animator.setInterpolator(interpolator);
        animator.start();
    }

    private void oneAnimatorDown(View imageView) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "translationY", 0F, 10000);
        animator.setDuration(200);
        animator.setStartDelay(400);
        animator.start();
    }
}
