package ru.supplyphotos.presentation.fragments.head;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.arellomobile.mvp.MvpAppCompatFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import ru.supplyphotos.R;
import ru.supplyphotos.presentation.fragments.ContractsFragmentView;

import ru.supplyphotos.presentation.fragments.head.gallery_fragments.PhoneGalleryFragment;

/**
 * @author Libgo on 24.01.2018.
 */

public class HeadFragment extends MvpAppCompatFragment
        implements ContractsFragmentView.HeadView{

    @BindView(R.id.button_next)
    Button nextButton;
    @BindView(R.id.bottom_view_bar)
    BottomNavigationView bottomBar;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_head, container, false);
        ButterKnife.bind(this,view);
        bottomBar.setItemIconTintList(R.color.bottom_navigation_item_background_colors);
        getChildFragmentManager().beginTransaction().replace(R.id.frame_for_child_fragments, new PhoneGalleryFragment()).commit();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();

    }

    private void initListener() {
        bottomBar.setOnNavigationItemSelectedListener((MenuItem item) -> {

            switch (item.getItemId()) {
                case R.id.action_phone:
                    getChildFragmentManager().beginTransaction().replace(R.id.frame_for_child_fragments, new PhoneGalleryFragment()).commit();
                    Log.d("!@#$", "!$!@$@!");
                    break;

                case R.id.action_vk:
                    getChildFragmentManager().beginTransaction().replace(R.id.frame_for_child_fragments, new PhoneGalleryFragment()).commit();
                    Log.d("!@#$", "!$!@$@!");
                    break;

                case R.id.action_instagram:
                    getChildFragmentManager().beginTransaction().replace(R.id.frame_for_child_fragments, new PhoneGalleryFragment()).commit();
                    Log.d("!@#$", "!$!@$@!");
                    break;

            }
            return false;
        });
    }


    @Override
    public void onError() {

    }

    @Override
    public void showLoading(boolean loading) {

    }
}
