package ru.supplyphotos.data.repository;

import java.util.List;

import io.reactivex.Observable;
import ru.supplyphotos.data.answers.category.ItemCategory;
import ru.supplyphotos.data.answers.manuals.Manual;
import ru.supplyphotos.data.answers.services.ItemService;

/**
 * @author Libgo on 15.03.2018.
 */

public interface BaseAppRepository {


    Observable<Manual> getGuides();


    Observable<List<ItemCategory>> getListCategory();

    Observable<List<ItemService>> getListService(Integer service_id);


}