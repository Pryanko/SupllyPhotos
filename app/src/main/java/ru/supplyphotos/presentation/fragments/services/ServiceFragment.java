package ru.supplyphotos.presentation.fragments.services;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.supplyphotos.R;
import ru.supplyphotos.data.answers.services.ItemService;
import ru.supplyphotos.data.settings.SelectedItemCategory;
import ru.supplyphotos.presentation.adapters.ServiceAdapter;
import ru.supplyphotos.presentation.adapters.decoration.SpaceDecoration;
import ru.supplyphotos.presentation.fragments.ContractsFragmentView;
import ru.supplyphotos.presentation.presenters.ServicePresenter;

/**
 * @author Libgo on 24.03.2018.
 */

public class ServiceFragment extends MvpAppCompatFragment
        implements ContractsFragmentView.ServiceView {

    //BindViews
    @BindView(R.id.text_service_head_image)
    TextView textImageHeadService;
    @BindView(R.id.service_recycler)
    RecyclerView recyclerViewService;
    @BindView(R.id.head_service_image)
    SimpleDraweeView imageServiceHead;

    private ServiceAdapter serviceAdapter;

    @InjectPresenter
    ServicePresenter servicePresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        serviceAdapter = new ServiceAdapter();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_servise, container, false);
        ButterKnife.bind(this, view);
        recyclerViewService.addItemDecoration(new SpaceDecoration(4));
        recyclerViewService.setNestedScrollingEnabled(false);
        recyclerViewService.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }



    @Override
    public void onError() {

    }

    @Override
    public void showLoading(boolean loading) {

    }

    @Override
    public void testStart(SelectedItemCategory selectedItemCategory, List<ItemService> list) {
        imageServiceHead.setImageURI(selectedItemCategory.getImageHeadUrls());
        textImageHeadService.setText("ID Category "+ String.valueOf(selectedItemCategory.getId()));
        recyclerViewService.setAdapter(serviceAdapter);
        serviceAdapter.updateList(list);
    }
}
