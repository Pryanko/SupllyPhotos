package ru.supplyphotos.presentation.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import org.parceler.Parcels;
import java.util.ArrayList;
import java.util.List;
import ru.supplyphotos.data.answers.manuals.Guide;
import ru.supplyphotos.presentation.fragments.manuals.PageManual;

import static ru.supplyphotos.constants.Constants.GUIDE;

/**
 * @author libgo on 12.01.2018.
 */
public class ViewPagerAdapterManual extends FragmentPagerAdapter {

    private Integer countFragments = 0;
    private List<Guide> guideList = new ArrayList<>();

    public ViewPagerAdapterManual(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        PageManual pageManual = new PageManual();
        Bundle args = new Bundle();
        args.putParcelable(GUIDE, Parcels.wrap(guideList.get(position)));
        pageManual.setArguments(args);
        Log.d("PAGE", String.valueOf(position));
        return pageManual;
    }


    @Override
    public int getCount() {
        return countFragments;
    }

    public void addContentFragments(Integer countFragments, List<Guide> list) {
        this.countFragments = countFragments;
        this.guideList = list;
    }


}
