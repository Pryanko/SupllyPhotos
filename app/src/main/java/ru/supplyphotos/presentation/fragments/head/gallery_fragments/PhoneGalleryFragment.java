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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import ru.supplyphotos.R;
import ru.supplyphotos.data.storage.ItemStorageImage;
import ru.supplyphotos.presentation.adapters.ContractsAdapters;
import ru.supplyphotos.presentation.adapters.GalleryAdapter;
import ru.supplyphotos.presentation.adapters.decoration.GridSpaceDecoration;
import ru.supplyphotos.presentation.adapters.decoration.SpaceDecoration;
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
    RecyclerView galleryRecycler;

    @InjectPresenter
    GalleryPresenter galleryPresenter;

    private GalleryAdapter galleryAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        galleryAdapter = new GalleryAdapter(getActivity());



    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery_phone, container, false);
        ButterKnife.bind(this, view);
        galleryRecycler.addItemDecoration(new GridSpaceDecoration(3,5, true));
        galleryRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 3,
                LinearLayoutManager.VERTICAL, false));
        return view;
    }


    @SuppressLint("NeedOnRequestPermissionsResult")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PhoneGalleryFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode,grantResults);
    }

    @NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
    void run(){
        galleryPresenter.loadImage();
    }


    @OnShowRationale(Manifest.permission.READ_EXTERNAL_STORAGE)
    void runRationaleForStorage(final PermissionRequest request){
        /*new AlertDialog.Builder(Objects.requireNonNull(getActivity()))
                .setMessage("OK")
                .setPositiveButton("DA", (dialog, button) -> request.proceed())
                .setNegativeButton("NET", (dialog, button) -> request.cancel())
                .show();  */
    }

    @OnPermissionDenied(Manifest.permission.READ_EXTERNAL_STORAGE)
    void runDeniedForStorage(){
        Toast.makeText(getActivity(),"LOLOLOLOLOLOLOLOLOLOLO", Toast.LENGTH_SHORT).show();
    }

    
    @OnNeverAskAgain(Manifest.permission.READ_EXTERNAL_STORAGE)
    void runNeverAskForStorage(){
        Toast.makeText(getActivity(),"LOLOLOLOLOLOLOLOLOLOLO", Toast.LENGTH_SHORT).show();
    }



    //implements

    @Override
    public void updateAdapterList(List<ItemStorageImage> itemStorageImages) {
        galleryAdapter.updateList(itemStorageImages);
    }

    @Override
    public void checkPermission() {
        PhoneGalleryFragmentPermissionsDispatcher.runWithPermissionCheck(this);
       
    }

    @Override
    public void setTouchManager(ContractsAdapters.GalleryTouchManager galleryTouchManager){
        galleryAdapter.setGalleryTouchManager(galleryTouchManager);
    }


    @Override
    public void showGallery() {
        galleryRecycler.setAdapter(galleryAdapter);
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
