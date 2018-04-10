package ru.supplyphotos.presentation.activities;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import ru.supplyphotos.App;
import ru.supplyphotos.R;
import ru.supplyphotos.presentation.fragments.basket.BasketFragment;
import ru.supplyphotos.presentation.fragments.category.CategoryFragment;
import ru.supplyphotos.presentation.fragments.description.DescriptionFragment;
import ru.supplyphotos.presentation.fragments.head.HeadFragment;
import ru.supplyphotos.presentation.fragments.manuals.ManualFragment;
import ru.supplyphotos.presentation.fragments.register.RegisterFragment;
import ru.supplyphotos.presentation.fragments.services.ServiceFragment;
import ru.supplyphotos.presentation.fragments.upload.UploadFragment;
import ru.supplyphotos.presentation.presenters.MainPresenter;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;

import static ru.supplyphotos.constants.Constants.BASKET_SCREEN;
import static ru.supplyphotos.constants.Constants.CATEGORY_SCREEN;
import static ru.supplyphotos.constants.Constants.DESCRIPTION_SCREEN;
import static ru.supplyphotos.constants.Constants.HEAD_SCREEN;
import static ru.supplyphotos.constants.Constants.MANUAL_SCREEN;
import static ru.supplyphotos.constants.Constants.REGISTER_SCREEN;
import static ru.supplyphotos.constants.Constants.SERVICES_SCREEN;
import static ru.supplyphotos.constants.Constants.UPLOAD_SCREEN;


/**
 * @author libgo (05.01.2018)
 */

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @BindView(R.id.main_toolBar)
    Toolbar toolbar;

    private ImageView basketView;

    @InjectPresenter
    MainPresenter mainPresenter;

    private Navigator navigator = new SupportFragmentNavigator(getSupportFragmentManager(),
            R.id.frame_for_fragments) {


        @NonNull
        @Override
        protected Fragment createFragment(String screenKey, Object data) {
            switch (screenKey) {
                case CATEGORY_SCREEN:
                    return new CategoryFragment();
                case SERVICES_SCREEN:
                    return new ServiceFragment();
                case MANUAL_SCREEN:
                    return new ManualFragment();
                case HEAD_SCREEN:
                    return new HeadFragment();
                case UPLOAD_SCREEN:
                    return new UploadFragment();
                case DESCRIPTION_SCREEN:
                    return new DescriptionFragment();
                case REGISTER_SCREEN:
                    return new RegisterFragment();
                case BASKET_SCREEN:
                    return new BasketFragment();
                default:
                    throw new RuntimeException("“Unknown screen key!”");
            }
        }

        @Override
        protected void showSystemMessage(String message) {

        }

        @Override
        protected void exit() {
             App.getAppComponent().getRouter().exit();
            
        }
    };

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            ButterKnife.bind(this);
            setSupportActionBar(toolbar);
            toolbar.setBackgroundColor(Color.parseColor("#ffffff"));
            toolbar.setTitleTextColor(Color.parseColor("#ffbe75"));
          //  toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_account));
            getSupportActionBar().setTitle("I Love Print my Photos");
            

        }

        @Override
        protected void onResume() {
            super.onResume();
            App.getAppComponent().getNavigatorHolder().setNavigator(navigator);
        }

        @Override
        protected void onPause() {
            super.onPause();
            App.getAppComponent().getNavigatorHolder().removeNavigator();
        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        Log.d("LOGS_SCREEN", "MENU");
        basketView = (ImageView) menu.findItem(R.id.shop_button).getActionView();
        return true; 
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.shop_button:
                mainPresenter.openBasket();
        }
        
        return super.onOptionsItemSelected(item);

    }

    @Override
        public void startShow(boolean firstRun) {
            if (firstRun) {
                setVisibilityToolbar(firstRun);
            } else {
                setVisibilityToolbar(firstRun);
            }
        }

        public void setVisibilityToolbar(boolean visibilityToolbar) {
            if (visibilityToolbar) {
                toolbar.setVisibility(View.VISIBLE);
            } else {
                toolbar.setVisibility(View.GONE);
            }
        }


    @Override
        public void controlToolbar(boolean isControl, boolean isVisBasket){

            if (isVisBasket){
                basketView.setVisibility(View.VISIBLE);
            }else {
                basketView.setVisibility(View.GONE);
            }


              if(isControl){
                  toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_account));
              }
              else {
                  toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_rounded_icon_set_arrow_left));
              }
        }

}





