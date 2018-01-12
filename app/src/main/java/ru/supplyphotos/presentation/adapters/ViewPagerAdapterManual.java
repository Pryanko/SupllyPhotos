package ru.supplyphotos.presentation.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import ru.supplyphotos.presentation.fragments.manuals.PageManual;
/**
 * @author libgo on 12.01.2018.
 */
public class ViewPagerAdapterManual extends FragmentPagerAdapter {



    private Integer countFragments = 0;

    public ViewPagerAdapterManual(FragmentManager fm) {
        super(fm);
        //this.countFragments = size;

    }

    @Override
    public Fragment getItem(int position) {
        PageManual pageManual = new PageManual();
        Bundle args = new Bundle();
        args.putInt("num", position);
        pageManual.setArguments(args);
        Log.d("PAGE", String.valueOf(position));
        return pageManual;
    }

    @Override
    public int getCount() {
        return countFragments;
    }

    public void setCountFragments(Integer countFragments) {
        this.countFragments = countFragments;
    }


}
