package ru.supplyphotos.data.repository;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import ru.supplyphotos.data.answers.category.ItemCategory;
import ru.supplyphotos.data.answers.manuals.Manual;
import ru.supplyphotos.data.answers.services.ItemService;
import ru.supplyphotos.data.storage.ItemStorageImage;

/**
 * @author Libgo on 15.03.2018.
 */

public interface BaseAppRepository {


    Observable<Manual> getGuides();

    Observable<List<ItemCategory>> getListCategory();

    Observable<List<ItemService>> getListService(Integer service_id);

    Flowable<List<ItemStorageImage>> getImageFlowable();
}
