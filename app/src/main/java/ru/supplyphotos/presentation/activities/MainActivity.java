package ru.supplyphotos.presentation.activities;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.arellomobile.mvp.presenter.ProvidePresenterTag;
import com.facebook.imagepipeline.nativecode.ImagePipelineNativeLoader;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;

import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.vistrav.ask.Ask;
import com.vistrav.ask.annotations.AskDenied;
import com.vistrav.ask.annotations.AskGranted;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import q.rorbin.badgeview.QBadgeView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.supplyphotos.App;
import ru.supplyphotos.R;
import ru.supplyphotos.design.AppDrawerBuilder;
import ru.supplyphotos.network.ApiService;
import ru.supplyphotos.presentation.fragments.category.CategoryFragment;
import ru.supplyphotos.presentation.fragments.head.HeadFragment;
import ru.supplyphotos.presentation.fragments.manuals.ManualFragment;
import ru.supplyphotos.presentation.fragments.services.ServiceFragment;
import ru.supplyphotos.presentation.presenters.MainPresenter;
import ru.supplyphotos.tools.utils.PathUtils;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.android.SupportAppNavigator;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;
import ru.terrakok.cicerone.commands.Command;

import static ru.supplyphotos.constants.Constants.CATEGORY_SCREEN;
import static ru.supplyphotos.constants.Constants.MANUAL_SCREEN;
import static ru.supplyphotos.constants.Constants.SERVICES_SCREEN;


/**
 * @author libgo (05.01.2018)
 */

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @BindView(R.id.main_toolBar)
    Toolbar toolbar;

    @InjectPresenter
    MainPresenter mainPresenter;

    private Navigator navigator = new SupportFragmentNavigator(getSupportFragmentManager(),
            R.id.frame_for_fragments) {


        @Override
        protected Fragment createFragment(String screenKey, Object data) {
            switch (screenKey) {
                case CATEGORY_SCREEN:
                    return new CategoryFragment();
                case SERVICES_SCREEN:
                    return new ServiceFragment();
                case MANUAL_SCREEN:
                    return new ManualFragment();
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
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            ButterKnife.bind(this);
            setSupportActionBar(toolbar);
            toolbar.setBackgroundColor(Color.parseColor("#ffffff"));
            toolbar.setTitleTextColor(Color.parseColor("#ffbe75"));
            toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_account));
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

    /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        MenuItem menuItem = menu.getItem(0);
        
        Log.d("menuItem", menuItem.toString());
        ImageView view = (ImageView) menuItem.getActionView();
        Log.d("VIEW", view.toString());
       // new QBadgeView(this).bindTarget(view).setBadgeNumber(5);

        
        return true; 
    } */

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


   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer();
                return true;
        }
        return  true;
    } */


}








  //  testStart();
  /*  private void testStart() {

        Ask.on(this)
                .id(1) // in case you are invoking multiple time Ask from same activity or fragment
                .forPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withRationales("In order to save file you will need to grant storage permission") //optional
                .go();

    }
        //optional
        @AskGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        public void fileAccessGranted ( int id){
            Log.i("TAG", "FILE  GRANTED");


            Intent intent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 1);




        }

        //optional
        @AskDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        public void fileAccessDenied ( int id){
            Log.i("TAG", "FILE  DENiED");
        }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        assert uri != null;
        Log.d("ZZzz", uri.toString());


    }        */





// uploadImage(PathUtils.getRealFilePath(uri, getContentResolver()));
   /* private void uploadImage(String realPath){

        File imageFile = new File(realPath);
        Log.d("zzzzz", imageFile.getName());

        ApiService apiService = ApiService.sendPhotoRetrofit.create(ApiService.class);

        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), imageFile);

        MultipartBody.Part body = MultipartBody.Part.createFormData("file",
                imageFile.getName(), requestBody);

        Call<ResponseBody> call = apiService.sendPhoto(body);
        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d("UPLOAD", response.body().toString());
                    try {
                        Log.d("ANSWER", String.valueOf(response.body().string()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.d("ANSWER", response.raw().toString());
                    Log.d("ANSWER", response.raw().body().toString());
                    Log.d("ANSWER", response.raw().message());

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
            }
        });


    }       */
