package com.example.pokedex_tp.Adaptateur;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.pokedex_tp.Fragment.Gen1;
import com.example.pokedex_tp.Fragment.Gen2;
import com.example.pokedex_tp.Fragment.Gen3;
import com.example.pokedex_tp.Fragment.Poke_De;
import com.example.pokedex_tp.Fragment.Poke_Stat;

public class InfoAdaptateur extends FragmentPagerAdapter {

    private Context myContext;
    FragmentManager fm;
    Bundle data;
    int totalTabs;

    public InfoAdaptateur(Context context, FragmentManager fm, int totalTabs, Bundle data) {
        super(fm);
        myContext = context;
        this.data = data;
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Poke_De detail = new Poke_De();
                detail.setArguments(data);
                return detail;
            case 1:
                Gen2 gen2 = new Gen2();
                return gen2;
            case 2:
                Poke_Stat stats = new Poke_Stat();
                stats.setArguments(data);
                return stats;

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

