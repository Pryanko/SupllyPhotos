package ru.supplyphotos.presentation.fragments.manuals;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.hoang8f.widget.FButton;
import ru.supplyphotos.App;
import ru.supplyphotos.R;
import ru.supplyphotos.data.answers.manuals.Guide;
import ru.supplyphotos.presentation.activities.MainActivity;
import ru.supplyphotos.presentation.adapters.ViewPagerAdapterManual;
import ru.supplyphotos.presentation.fragments.ContractsFragmentView;
import ru.supplyphotos.presentation.fragments.head.HeadFragment;
import ru.supplyphotos.presentation.presenters.ManualPresenter;

/**
 * @author libgo on 12.01.2018.
 */

public class ManualFragment extends MvpAppCompatFragment
        implements ContractsFragmentView.ManualView {
 /*   //BindViewsAlert
    @BindView(R.id.dialog_manuals_title)
    TextView textDialogTitle;
    @BindView(R.id.dialog_manuals_message)
    TextView textDialogMessage;
    @BindView(R.id.dialog_positive_button)
    FButton buttonPositive;
    @BindView(R.id.dialog_negative_button)
    FButton buttonNegative;     */
    /**
     * Бинды Алерта временно не трогаем
     */


    //BindViews
    @BindView(R.id.page_viewer_for_child_manuals)
    ViewPager viewPager;
    @BindView(R.id.stepper_view)
    PageIndicatorView stepView;
    @BindView(R.id.bottom_view)
    CardView bottomCard;
    @BindView(R.id.button_miss)
    FButton buttonMiss;
    @BindView(R.id.button_next)
    FButton buttonNext;
    @BindView(R.id.progressBarManual)
    ProgressBar progressBar;


    private ViewPagerAdapterManual viewPagerAdapterManual;

    @InjectPresenter
    ManualPresenter manualPresenter;

    

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        viewPagerAdapterManual = new ViewPagerAdapterManual(getChildFragmentManager());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_parent_manual, container, false);
        ButterKnife.bind(this, view);
        setRetainInstance(true);
        onListener();

        return view;
    }

    @Override
    public void startShow(int size, List<Guide> list){
        //Button init
        buttonMiss.setShadowEnabled(false);
        buttonMiss.setCornerRadius(4);
        buttonMiss.setButtonColor(0xff6792ff);
        buttonNext.setShadowEnabled(false);
        buttonNext.setCornerRadius(4);
        buttonNext.setButtonColor(0xff6792ff);
        //ViewPager fill
        viewPagerAdapterManual.addContentFragments(size, list);
        viewPager.setAdapter(viewPagerAdapterManual);
        viewPager.setCurrentItem(0);
        stepView.setViewPager(viewPager);
        stepView.setCount(size);
        stepView.setAnimationType(AnimationType.DROP);
        bottomCard.animate().alpha(1.0f).setDuration(600).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                bottomCard.setVisibility(View.VISIBLE);
            }
        });
        stepView.animate().alpha(1.0f).setDuration(250).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                stepView.setVisibility(View.VISIBLE);
            }
        });

        
    }

    private void onListener(){

        buttonNext.setOnClickListener(v -> viewPager.setCurrentItem(viewPager.getCurrentItem() + 1));
        buttonMiss.setOnClickListener(v -> manualPresenter.missManual());

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                   stepView.setSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void alertShow(String title, String message, String posButton, String negButton){





        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_show_dialog_manuals, null);
        TextView textDialogTitle = (TextView) dialogView.findViewById(R.id.dialog_manuals_title);
        TextView textDialogMessage = (TextView) dialogView.findViewById(R.id.dialog_manuals_message);
        FButton buttonPositive = (FButton) dialogView.findViewById(R.id.dialog_positive_button);
        FButton buttonNegative = (FButton) dialogView.findViewById(R.id.dialog_negative_button);
        textDialogTitle.setText(title);
        textDialogMessage.setText(message);
        buttonPositive.setText(posButton);
        buttonNegative.setText(negButton);
        buttonPositive.setShadowEnabled(false);
        buttonNegative.setShadowEnabled(false);
        buttonPositive.setCornerRadius(4);
        buttonNegative.setCornerRadius(4);
        buttonPositive.setButtonColor(0xff6792ff);
        buttonNegative.setButtonColor(0xff6792ff);
        alertDialog.setView(dialogView);
        final AlertDialog dialog = alertDialog.create();

        buttonPositive.setOnClickListener(v -> {
            dialog.cancel();
            closeView();
        });
        buttonNegative.setOnClickListener(v -> dialog.cancel());
        dialog.show();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        manualPresenter.destroyView();
        Log.d("CLOSE", "YES");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("CLOSE", "YES");
        if(getActivity() != null){
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.setVisibilityToolbar(true);
        }
    }

    public void closeView(){
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_for_fragments, new HeadFragment()).commit();
    }


    @Override
    public void onError() {

    }

    @Override
    public void showLoading(boolean loading) {
        if (loading){
            progressBar.setVisibility(View.VISIBLE);
        }
        else {
            progressBar.setVisibility(View.GONE);
        }

    }

}
