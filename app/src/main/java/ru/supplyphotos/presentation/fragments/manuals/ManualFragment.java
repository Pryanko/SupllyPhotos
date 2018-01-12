package ru.supplyphotos.presentation.fragments.manuals;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.arellomobile.mvp.MvpAppCompatFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import ru.supplyphotos.App;
import ru.supplyphotos.R;
import ru.supplyphotos.presentation.adapters.ViewPagerAdapterManual;
import ru.supplyphotos.presentation.presenters.ManualPresenter;

/**
 * @author libgo on 12.01.2018.
 */

public class ManualFragment extends MvpAppCompatFragment {

    //BindViews
    @BindView(R.id.page_viewer_for_child_manuals)
    ViewPager viewPager;

    private ViewPagerAdapterManual viewPagerAdapterManual;

    private ManualPresenter manualPresenter = App.getAppComponent().getManualPresenter();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewPagerAdapterManual  = new ViewPagerAdapterManual(getChildFragmentManager());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_parent_manual, container, false);
        ButterKnife.bind(this, view);
        viewPager.setAdapter(viewPagerAdapterManual);
        manualPresenter.setView(this);
        manualPresenter.createView();
        return view;
    }

    public void startShow(){
        Log.d("zzzzzz", manualPresenter.getSizeListManual().toString());
        
        viewPagerAdapterManual.setCountFragments(manualPresenter.getSizeListManual());
        viewPagerAdapterManual.notifyDataSetChanged();
        viewPager.setCurrentItem(0);
        
    }
}
