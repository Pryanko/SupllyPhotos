package ru.supplyphotos.design;

import android.graphics.drawable.Drawable;
import com.mikepenz.materialdrawer.DrawerBuilder;



/**
 * @author Libgo on 08.02.2018.
 */

public class AppDrawerBuilder extends DrawerBuilder {

    private android.support.v7.widget.Toolbar getToolbar(){
        return this.mToolbar;
    }

    public void setIcon(Integer icon){
        getToolbar().setNavigationIcon(icon);
    }

}
