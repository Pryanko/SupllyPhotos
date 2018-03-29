package ru.supplyphotos.presentation.fragments.head;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.arellomobile.mvp.MvpAppCompatFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import ru.supplyphotos.R;
import ru.supplyphotos.presentation.fragments.ContractsFragmentView;

import ru.supplyphotos.presentation.fragments.category.CategoryFragment;
import ru.supplyphotos.presentation.fragments.head.gallery_fragments.PhoneGalleryFragment;
import ru.supplyphotos.presentation.fragments.services.ServiceFragment;


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
        setHasOptionsMenu(true);

        getChildFragmentManager().beginTransaction().replace(R.id.frame_for_child_fragments, new PhoneGalleryFragment()).commit();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();
      

    }

    private void initListener() {
        bottomBar.setOnNavigationItemReselectedListener(item -> {switch (item.getItemId()) {

            case R.id.action_phone:
                getChildFragmentManager().beginTransaction().replace(R.id.frame_for_child_fragments, new PhoneGalleryFragment()).commit();
                bottomBar.getMenu().getItem(0).setChecked(true);
                Log.d("!@#$", "!$!@$@!");
                break;

            case R.id.action_vk:
                getChildFragmentManager().beginTransaction().replace(R.id.frame_for_child_fragments, new CategoryFragment()).commit();
                Log.d("!@#$", "!$!@$@!");
                bottomBar.getMenu().getItem(1).setChecked(true);
                break;

            case R.id.action_instagram:
                getChildFragmentManager().beginTransaction().replace(R.id.frame_for_child_fragments, new ServiceFragment()).commit();
                Log.d("!@#$", "!$!@$@!");
                bottomBar.getMenu().getItem(2).setChecked(true);
                break;

        }

        });






       bottomBar.setOnNavigationItemSelectedListener((MenuItem item) -> {
            
            switch (item.getItemId()) {
                case R.id.action_phone:

                    getChildFragmentManager().beginTransaction().replace(R.id.frame_for_child_fragments, new PhoneGalleryFragment()).commit();
                    Log.d("!@#$", "!$!@$@!");
                    break;

                case R.id.action_vk:
                    getChildFragmentManager().beginTransaction().replace(R.id.frame_for_child_fragments, new CategoryFragment()).commit();
                    Log.d("!@#$", "!$!@$@!");
                     item.isChecked();
                    break;

                case R.id.action_instagram:
                    
                    getChildFragmentManager().beginTransaction().replace(R.id.frame_for_child_fragments, new ServiceFragment()).commit();
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
