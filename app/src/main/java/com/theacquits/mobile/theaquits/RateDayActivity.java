package com.theacquits.mobile.theaquits;

import android.graphics.Color;
import android.os.Build;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.bvapp.arcmenulibrary.ArcMenu;
import com.bvapp.arcmenulibrary.widget.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RateDayActivity extends AppCompatActivity {

    Animation rotatefw, rotatebw;
    ArcMenu arcMenu;

    Boolean isOpen = false;

    private static final int[] ITEM_DRAWABLES = { R.drawable.ic_dashboard_black_24dp,
            R.drawable.ic_home_black_24dp, R.drawable.ic_chat_black_24dp, R.drawable.ic_dashboard_black_24dp,
            R.drawable.ic_dashboard_black_24dp};

    private static final String[] STR = {"Great","Good","Meh","Difficult","Awful"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeStatusBarColor();
        setContentView(R.layout.activity_rate_day);
        ButterKnife.bind(this);

        rotatefw = AnimationUtils.loadAnimation(this,R.anim.rotate_foward);
        rotatebw = AnimationUtils.loadAnimation(this,R.anim.rotate_backward);

        arcMenu = (ArcMenu) findViewById(R.id.arcMenu);


        arcMenu.setToolTipTextSize(14);
        arcMenu.setMinRadius(145);
        arcMenu.setArc(0,-225);
        arcMenu.setToolTipSide(ArcMenu.TOOLTIP_DOWN);
        arcMenu.setToolTipTextColor(Color.WHITE);
        arcMenu.setToolTipBackColor(Color.TRANSPARENT);
        arcMenu.setToolTipCorner(40);  //set tooltip corner
        arcMenu.setToolTipPadding(0);  //set tooltip padding
        arcMenu.setColorNormal(getResources().getColor(R.color.white));
        arcMenu.showTooltip(true);
        arcMenu.setDuration(600);
        arcMenu.setEnabled(true);



        initArcMenu(arcMenu, STR, ITEM_DRAWABLES);
//
//



    }

    public void Close(View view){
        finish();
    }

    private void initArcMenu(final ArcMenu menu, final String[] str, int[] itemDrawables) {
        for (int i = 0; i < itemDrawables.length ; i++) {
            FloatingActionButton item = new FloatingActionButton(this);  //Use internal fab as a child
            item.setSize(FloatingActionButton.SIZE_NORMAL);  //set minimum size for fab 42dp
            item.setShadow(true); //enable to draw shadow
            item.setIcon(itemDrawables[i]); //add icon for fab
            item.setBackgroundColor(getResources().getColor(R.color.white));  //set menu button normal color programmatically
            menu.setChildSize(item.getIntrinsicHeight()); // fit menu child size exactly same as fab

            final int position = i;
            menu.addItem(item, str[i], new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Toast.makeText(RateDayActivity.this, str[position],
                            Toast.LENGTH_SHORT).show();
                }
            });
        }


    }




    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }
    }
}
