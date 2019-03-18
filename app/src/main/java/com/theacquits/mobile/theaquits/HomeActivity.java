package com.theacquits.mobile.theaquits;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.icu.text.UnicodeSetSpanner;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;


import com.theacquits.mobile.theaquits.Utils.CountUpTimerNow;
import com.theacquits.mobile.theaquits.fragments.BlankFragment;
import com.theacquits.mobile.theaquits.fragments.CalendarFragment;
import com.theacquits.mobile.theaquits.fragments.ConversFragment;
import com.theacquits.mobile.theaquits.fragments.DashBoardFragment;
import com.theacquits.mobile.theaquits.fragments.LessonsFragment;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    Handler handler;

    Runnable runnable;

    private static final int MAX_STEP = 4;


    private TextView mTextMessage;

    @BindView(R.id.counter)
    TextView textView;

    @BindView(R.id.expanded_menu)
    ImageView imageView;

    @BindView(R.id.pager_container)
    ViewPager viewPager;

    @BindView(R.id.main_view_pager)
    ViewPager viewPager2;

    @BindView(R.id.app_bar)
    AppBarLayout appBarLayout;

    PagerAdapter pagerAdapter;

    PagerAdapter2 pagerAdapter2;

    ImageView[] dots;

    ImageButton button1,button2,button3,button4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        //      this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //drawerLayout.addDrawerListener(toggle);
        //toggle.syncState();

        button1 = (ImageButton) findViewById(R.id.dashboard);
        button2 = (ImageButton) findViewById(R.id.calendar);
        button3 = (ImageButton) findViewById(R.id.conversation);
        button4 = (ImageButton) findViewById(R.id.lessons);

        pagerAdapter2 = new PagerAdapter2(getSupportFragmentManager());
        viewPager2.setAdapter(pagerAdapter2);
        viewPager2.addOnPageChangeListener(onPageChangeListener2);
        viewPager2.setCurrentItem(0);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(onPageChangeListener);
        viewPager.setCurrentItem(0);



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        countDownStart();

        LinearLayout dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        dots = new ImageView[MAX_STEP];
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(this);
            int width_height = 15;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(width_height, width_height));
            params.setMargins(10, 10, 10, 10);
            dots[i].setLayoutParams(params);
            dots[i].setImageResource(R.drawable.shape_circle);
            dots[i].setColorFilter(getResources().getColor(R.color.grey_20), PorterDuff.Mode.SRC_IN);
            dotsLayout.addView(dots[i]);
        }

        bottomProgressDots(0);


    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent = null;


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    public void clickAction(View view) {
        switch (view.getId()) {
            case R.id.dashboard:
                viewPager2.setCurrentItem(0);
                Toast.makeText(this, "Dashboard", Toast.LENGTH_SHORT).show();
                break;
            case R.id.calendar:
                viewPager2.setCurrentItem(1);
                Toast.makeText(this, "Calendar", Toast.LENGTH_SHORT).show();
                break;
            case R.id.conversation:
                viewPager2.setCurrentItem(2);
                Toast.makeText(this, "Conversation", Toast.LENGTH_SHORT).show();
                break;
            case R.id.lessons:
                viewPager2.setCurrentItem(3);
                Toast.makeText(this, "Lessons", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fb:
                Intent intent = new Intent(HomeActivity.this, RateDayActivity.class);
                startActivity(intent);
                break;
        }
    }


    public void countDownStart() {
        handler = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                try {
                    final CountUpTimerNow timer = new CountUpTimerNow(00, 00, 9, 3, 1, 2019);
                    /*new CountDownTimer(timer.getIntervalMillis(), 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            int days = (int) ((millisUntilFinished / 1000) / 86400);
                            int hours = (int) (((millisUntilFinished / 1000) - (days * 86400)) / 3600);
                            int minutes = (int) (((millisUntilFinished / 1000) - (days * 86400) - (hours * 3600)) / 60);
                            int seconds = (int) ((millisUntilFinished / 1000) % 60);
                            String countdown = String.format("%02dd %02dh %02dm %02ds", days, hours, minutes, seconds);
                           /* txtTimerDay.setText("" + String.format("%02d", days));
                            txtTimerHour.setText("" + String.format("%02d", hours));
                            txtTimerMinute.setText(""
                                    + String.format("%02d", minutes));
                            txtTimerSecond.setText(""
                                    + String.format("%02d", seconds));*/
                          /* textView.setText(countdown);

                        }*/

                       /* @Override
                        public void onFinish() {

                        }
                    }.start();*/

                    int days = (int) ((timer.getIntervalMillis() / 1000) / 86400);
                    int hours = (int) (((timer.getIntervalMillis() / 1000) - (days * 86400)) / 3600);
                    int minutes = (int) (((timer.getIntervalMillis() / 1000) - (days * 86400) - (hours * 3600)) / 60);
                    int seconds = (int) ((timer.getIntervalMillis() / 1000) % 60);
                    String countdown = String.format("%03d:%02d:%02d:%02d", days, hours, minutes, seconds);
                    textView.setText(countdown);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 1000);
    }


    @Override
    protected void onDestroy() {
        handler.removeCallbacks(runnable);
        super.onDestroy();
    }

    private class PagerAdapter extends FragmentPagerAdapter {
        SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();

        //private String[] tabs = getResources().getStringArray(R.array.tabs);

        private PagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            /*SecondFragment fragment = new SecondFragment();
            Bundle bundle = new Bundle();
            if (position == 2) {
                bundle.putInt("position", Constants.POSITION_FEED_TAB_MY_CITY);
                bundle.putString("place", session.getLoginLocation());
            } else if (position == 1)
                bundle.putInt("position", Constants.POSITION_FEED_TAB_MAIN_FEED);
            else bundle.putInt("position", position);


*/
            return new BlankFragment();

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Fragment fragment = (Fragment) super.instantiateItem(container, position);
            registeredFragments.put(position, fragment);
            return fragment;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            registeredFragments.remove(position);
            super.destroyItem(container, position, object);
        }

        public Fragment getRegisteredFragment(int position) {
            return registeredFragments.get(position);
        }


        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }

    ViewPager.OnPageChangeListener onPageChangeListener2 = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            switch (position){
                case 0:
                    appBarLayout.setExpanded(true);
                    button1.setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN);
                    button2.setColorFilter(getResources().getColor(R.color.grey_80), PorterDuff.Mode.SRC_IN);
                    button3.setColorFilter(getResources().getColor(R.color.grey_80), PorterDuff.Mode.SRC_IN);
                    button4.setColorFilter(getResources().getColor(R.color.grey_80), PorterDuff.Mode.SRC_IN);

                    break;
                case 1:
                    appBarLayout.setExpanded(false);
                    button1.setColorFilter(getResources().getColor(R.color.grey_80), PorterDuff.Mode.SRC_IN);
                    button2.setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN);
                    button3.setColorFilter(getResources().getColor(R.color.grey_80), PorterDuff.Mode.SRC_IN);
                    button4.setColorFilter(getResources().getColor(R.color.grey_80), PorterDuff.Mode.SRC_IN);
                    break;
                case 2:
                    appBarLayout.setExpanded(false);
                    button1.setColorFilter(getResources().getColor(R.color.grey_80), PorterDuff.Mode.SRC_IN);
                    button2.setColorFilter(getResources().getColor(R.color.grey_80), PorterDuff.Mode.SRC_IN);
                    button3.setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN);
                    button4.setColorFilter(getResources().getColor(R.color.grey_80), PorterDuff.Mode.SRC_IN);
                    break;
                case 3:
                    appBarLayout.setExpanded(false);
                    button1.setColorFilter(getResources().getColor(R.color.grey_80), PorterDuff.Mode.SRC_IN);
                    button2.setColorFilter(getResources().getColor(R.color.grey_80), PorterDuff.Mode.SRC_IN);
                    button3.setColorFilter(getResources().getColor(R.color.grey_80), PorterDuff.Mode.SRC_IN);
                    button4.setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN);
                    break;

            }
        }


        @Override
        public void onPageSelected(int position) {


        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void bottomProgressDots(int current_index) {

        for (int i = 0; i < MAX_STEP; i++){
            if (i == current_index){
                dots[current_index].setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
            }else{
                dots[i].setColorFilter(getResources().getColor(R.color.grey_20), PorterDuff.Mode.SRC_IN);

            }
        }
    }


    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            bottomProgressDots(position);
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };





    private class PagerAdapter2 extends FragmentPagerAdapter {
        SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();

        //private String[] tabs = getResources().getStringArray(R.array.tabs);

        private PagerAdapter2(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            /*SecondFragment fragment = new SecondFragment();
            Bundle bundle = new Bundle();
            if (position == 2) {
                bundle.putInt("position", Constants.POSITION_FEED_TAB_MY_CITY);
                bundle.putString("place", session.getLoginLocation());
            } else if (position == 1)
                bundle.putInt("position", Constants.POSITION_FEED_TAB_MAIN_FEED);
            else bundle.putInt("position", position);

           */

            switch (position){
                case 0:
                    return new DashBoardFragment();
                case 1:
                    return new CalendarFragment();
                case 2:
                    return new ConversFragment();
                case 3:
                    return new LessonsFragment();
                default:
                    return null;
            }

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Fragment fragment = (Fragment) super.instantiateItem(container, position);
            registeredFragments.put(position, fragment);
            return fragment;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            registeredFragments.remove(position);
            super.destroyItem(container, position, object);
        }

        public Fragment getRegisteredFragment(int position) {
            return registeredFragments.get(position);
        }


        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }



}
