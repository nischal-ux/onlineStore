package com.example.shamsonlinemark;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
LinearLayout cart,account;
    ImageView home,message;
ViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

home=findViewById(R.id.home);
       message =findViewById(R.id.message);
        cart=findViewById(R.id.cart);

        account=findViewById(R.id.account);
        pager=findViewById(R.id.container);
        pager.setAdapter(new hamroAdapter(getSupportFragmentManager()));
    }
    public void tabListner(View view){
        if(view.getId()==R.id.home){

            pager.setCurrentItem(0);
        } else if(view.getId()==R.id.message){

            pager.setCurrentItem(1);
        } else if(view.getId()==R.id.cart){

            pager.setCurrentItem(2);
        } else{

            pager.setCurrentItem(3);
        }

    }
    public class hamroAdapter extends FragmentPagerAdapter {
        public hamroAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
    if(position==0){
        return  new Home();
    }else  if(position==1){
        return  new pratice();
    }
    else  if(position==2){
        return  new CartSystem();
    }
            return  new Account();
        }

        @Override
        public int getCount() {
            return 4;
        }
    }


}
