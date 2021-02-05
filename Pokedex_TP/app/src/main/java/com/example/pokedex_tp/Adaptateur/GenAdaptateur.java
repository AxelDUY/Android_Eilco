package com.example.pokedex_tp.Adaptateur;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.pokedex_tp.Fragment.Gen1;
import com.example.pokedex_tp.Fragment.Gen2;
import com.example.pokedex_tp.Fragment.Gen3;

public class GenAdaptateur extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public GenAdaptateur(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Gen1 gen1 = new Gen1();
                return gen1;
            case 1:
                Gen2 gen2 = new Gen2();
                return gen2;
            case 2:
                Gen3 gen3 = new Gen3();
                return gen3;

            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }


}

