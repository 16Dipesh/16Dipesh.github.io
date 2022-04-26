package mra.com.tastyfoodcafe;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import mra.com.tastyfoodcafe.Fragments.ProductFragment;
import mra.com.tastyfoodcafe.Fragments.TodaysOffer;

public class MainDrawerWindowActivity extends AppCompatActivity {
    ImageButton cart;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer_window);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        cart=(ImageButton)findViewById(R.id.cart);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        final TabLayout tabLayout=findViewById(R.id.tablayout);
        final ViewPager viewPager=findViewById(R.id.view_pager);

        final ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new ProductFragment(), "Products");
        viewPagerAdapter.addFragment(new TodaysOffer(), "Offers");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainDrawerWindowActivity.this, AddToCartProductActivity.class));
            }
        });


    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.Logout11:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainDrawerWindowActivity.this,LoginPage.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                return true;

        }
        return false;
    }



    class ViewPagerAdapter extends FragmentPagerAdapter
    {

        private ArrayList<Fragment> fragments;
        private ArrayList<String> titles;

        ViewPagerAdapter(FragmentManager fm)
        {
            super(fm);
            this.fragments=new ArrayList<>();
            this.titles=new ArrayList<>();
        }

        @Override
        public Fragment getItem(int position)
        {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
        public void addFragment(Fragment fragment,String title)
        {
            fragments.add(fragment);
            titles.add(title);
        }
        //ctrl+o
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }

}