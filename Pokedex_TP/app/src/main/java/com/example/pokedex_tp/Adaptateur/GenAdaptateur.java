package com.example.pokedex_tp.Adaptateur;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.pokedex_tp.Fragment.Gen1;
import com.example.pokedex_tp.Fragment.Gen2;
import com.example.pokedex_tp.Fragment.Gen3;
import com.example.pokedex_tp.Fragment.Gen4;
import com.example.pokedex_tp.Fragment.Gen5;
import com.example.pokedex_tp.Fragment.Gen6;
import com.example.pokedex_tp.Fragment.Gen7;
import com.example.pokedex_tp.Fragment.Gen8;

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
            case 3:
                Gen4 gen4 = new Gen4();
                return gen4;
            case 4:
                Gen5 gen5 = new Gen5();
                return gen5;
            case 5:
                Gen6 gen6 = new Gen6();
                return gen6;
            case 6:
                Gen7 gen7 = new Gen7();
                return gen7;

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

