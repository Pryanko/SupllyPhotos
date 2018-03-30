package ru.supplyphotos.presentation.fragments.head.gallery_fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.Provides;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import ru.supplyphotos.R;
import ru.supplyphotos.data.storage.ItemStorageImage;
import ru.supplyphotos.presentation.adapters.GalleryAdapter;
import ru.supplyphotos.presentation.presenters.gallery.GalleryPresenter;

/**
 * @author Libgo on 28.03.2018.
 */
@RuntimePermissions
public class PhoneGalleryFragment extends MvpAppCompatFragment
        implements ContractsGalleryFragmentView.PhoneGalleryView {

    @BindView(R.id.progressBar_gallery_phone)
    ProgressBar progressBar;
    @BindView(R.id.gallery_recycler)
    RecyclerView gaelleryRecycler;

    @InjectPresenter
    GalleryPresenter galleryPresenter;

    private GalleryAdapter galleryAdapter;
    private GridLayoutManager gridLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        galleryAdapter = new GalleryAdapter(getActivity());
        gridLayoutManager = new GridLayoutManager(getActivity(), 3,
                LinearLayoutManager.VERTICAL, false);
        gridLayoutManager.setItemPrefetchEnabled(true);
        



    }



    @SuppressLint("NeedOnRequestPermissionsResult")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PhoneGalleryFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery_phone, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void showStorage(){
        galleryPresenter.loadImage();
    }

    @OnShowRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void showRationaleForCamera(final PermissionRequest request) {
        new AlertDialog.Builder(getParentFragment().getActivity())
                .setMessage("OK OK")
                .setPositiveButton("DA", (dialog, button) -> request.proceed())
                .setNegativeButton("NET", (dialog, button) -> request.cancel())
                .show();
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    void showDeniedForCamera() {
        Toast.makeText(getParentFragment().getActivity(), "НЕТ", Toast.LENGTH_SHORT).show();
    }



    //implements

    @Override
    public void updateAdapterList(List<ItemStorageImage> itemStorageImages) {
        galleryAdapter.updateList(itemStorageImages);
    }

    @Override
    public void checkPermission() {

        PhoneGalleryFragmentPermissionsDispatcher.showStorageWithPermissionCheck(this);
    }


    @Override
    public void showGallery() {
        gaelleryRecycler.setLayoutManager(gridLayoutManager);
        gaelleryRecycler.setAdapter(galleryAdapter);
        galleryAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError() {

    }

    @Override
    public void showLoading(boolean loading) {
        if(loading){
            progressBar.setVisibility(View.VISIBLE);
        }
        else {
            progressBar.setVisibility(View.GONE);
        }

    }
}
