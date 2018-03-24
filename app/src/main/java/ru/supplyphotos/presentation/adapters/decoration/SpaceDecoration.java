package ru.supplyphotos.presentation.adapters.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author Libgo on 21.01.2018.
 */

public class SpaceDecoration extends RecyclerView.ItemDecoration {

    private final int verticalSpaceWidth;

    public SpaceDecoration( int verticalSpaceWidth) {
        this.verticalSpaceWidth = verticalSpaceWidth;

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = verticalSpaceWidth;
    }
}
