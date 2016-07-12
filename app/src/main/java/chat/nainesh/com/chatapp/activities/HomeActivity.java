package chat.nainesh.com.chatapp.activities;

import android.os.Bundle;

public class HomeActivity extends chat.nainesh.com.chatapp.base.BaseActivity {

    private android.support.v4.view.ViewPager mViewPager;
    private  chat.nainesh.com.chatapp.customviews.SlidingTabLayout slidingTabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(chat.nainesh.com.chatapp.R.layout.activity_home);

        init();
    }

    private void init(){
        mViewPager = (android.support.v4.view.ViewPager) findViewById(chat.nainesh.com.chatapp.R.id.viewpager);
        slidingTabLayout = (chat.nainesh.com.chatapp.customviews.SlidingTabLayout) findViewById(chat.nainesh.com.chatapp.R.id.slidingTabs);
        mViewPager.setAdapter(new chat.nainesh.com.chatapp.adapters.HomePagerAdapter(getSupportFragmentManager()));
        slidingTabLayout.setViewPager(mViewPager);
    }
}
