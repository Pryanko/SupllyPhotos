package ru.supplyphotos.presentation.fragments.head.gallery_fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;

import ru.supplyphotos.R;

/**
 * @author Libgo on 30.03.2018.
 */
public class InstagramGalleryFragment extends MvpAppCompatFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gallery_instagram, container, false);
    }
}
