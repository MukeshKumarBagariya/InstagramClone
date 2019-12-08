package com.example.insta;

import android.net.Uri;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabAdapter extends FragmentPagerAdapter  {
    public TabAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int tabPosition) {
        switch (tabPosition){
            case 0:
                Profile profile = new Profile();
                return profile;
            case 1:
                User user = new User();
                return user;
            case 2:
                SharePicture sharePicture = new SharePicture();
                return sharePicture;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "profile";
            case 1:
                return "User";
            case 2:
                return "Share Picture";
            default:
                return null;
        }
    }

}
