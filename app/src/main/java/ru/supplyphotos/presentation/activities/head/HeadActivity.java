package ru.supplyphotos.presentation.activities.head;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import javax.inject.Inject;

import ru.sumplyphotos.logger.Logger;
import ru.sumplyphotos.logger.LoggerFactory;
import ru.supplyphotos.R;
import ru.supplyphotos.presentation.activities.base.ActivityModule;
import ru.supplyphotos.presentation.activities.base.MvpActivity;
import ru.supplyphotos.presentation.activities.base.delegates.ToolbarDelegate;
import ru.supplyphotos.presentation.fragments.basket.BasketFragment;
import ru.supplyphotos.presentation.fragments.category.CategoryFragment;
import ru.supplyphotos.presentation.fragments.description.DescriptionFragment;
import ru.supplyphotos.presentation.fragments.head.HeadFragment;
import ru.supplyphotos.presentation.fragments.manuals.ManualFragment;
import ru.supplyphotos.presentation.fragments.register.RegisterFragment;
import ru.supplyphotos.presentation.fragments.services.ServiceFragment;
import ru.supplyphotos.presentation.fragments.upload.UploadFragment;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
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

public class HeadActivity extends MvpActivity implements HeadView {

    private static final Logger logger = LoggerFactory.getLogger(HeadActivity.class);

    public static Intent getCallingIntent(Activity activity) {
        return new Intent(activity, HeadActivity.class);
    }

    //region Di
    HeadScreenComponent screenComponent;
    HeadComponent component;
    @Inject
    NavigatorHolder navigatorHolder;
    @Inject
    ToolbarDelegate toolbarDelegate;


    private HeadPresenter presenter;

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

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        logger.trace("onCreate");
        screenComponent = getScreenComponent();
        component = screenComponent.headComponentBuilder()
                .activityModule(new ActivityModule(this))
                .build();
        component.inject(this);
        super.onCreate(savedInstanceState);
        presenter = getMvpDelegate().getPresenter(component::headPresenter, HeadPresenter.class);
        setContentView(R.layout.activity_main);
        toolbarDelegate.init();
        toolbarDelegate.addOnBasketBtnClickedListener(() -> presenter.onBasketBtnClicked());
        presenter.initialize();
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return screenComponent;
    }

    public HeadScreenComponent getScreenComponent() {
        Object saved = getLastCustomNonConfigurationInstance();
        if (saved == null) {
            return appComponent().headScreenComponent();
        } else {
            return (HeadScreenComponent) saved;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigatorHolder.setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        super.onPause();
        navigatorHolder.removeNavigator();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.shop_button:

        }

        return super.onOptionsItemSelected(item);

    }

//    @Override
//    public void startShow(boolean firstRun) {
//        if (firstRun) {
//            setVisibilityToolbar(firstRun);
//        } else {
//            setVisibilityToolbar(firstRun);
//        }
//    }


//    @Override
//    public void controlToolbar(boolean isControl, boolean isVisBasket) {
//
//        if (isVisBasket) {
//            basketView.setVisibility(View.VISIBLE);
//        } else {
//            basketView.setVisibility(View.GONE);
//        }
//
//
//        if (isControl) {
//            toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_account));
//        } else {
//            toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_rounded_icon_set_arrow_left));
//        }
//    }
}





