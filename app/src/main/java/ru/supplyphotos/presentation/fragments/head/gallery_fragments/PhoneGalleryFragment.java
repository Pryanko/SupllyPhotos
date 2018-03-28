package ru.supplyphotos.presentation.fragments.head.gallery_fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import ru.supplyphotos.R;
import ru.supplyphotos.presentation.presenters.gallery.GalleryPresenter;

/**
 * @author Libgo on 28.03.2018.
 */
@RuntimePermissions
public class PhoneGalleryFragment extends MvpAppCompatFragment
        implements ContractsGalleryFragmentView.PhoneGalleryView {



    @InjectPresenter
    GalleryPresenter galleryPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PhoneGalleryFragmentPermissionsDispatcher.showStorageWithPermissionCheck(this);

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
        
        return inflater.inflate(R.layout.fragment_gallery_phone, container, false);
    }


    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void showStorage(){
        Log.d("STORAGE", "OK");
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
    public void showGallery() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void showLoading(boolean loading) {

    }
}
