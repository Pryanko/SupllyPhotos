package ru.supplyphotos.data.resource;

import android.content.res.Resources;

/**
 * Класс для управления ресурсами
 *
 * @author Libgo on 15.03.2018.
 */

public class ResourceManager implements AndroidResourceManager {

    private Resources resources;

    public ResourceManager(Resources resources) {
        this.resources = resources;
    }

    @Override
    public String getString(int resourceId) {
        return resources.getString(resourceId);
    }

    @Override
    public int getInteger(int resourceId) {
        return resources.getInteger(resourceId);
    }
}
