package ru.supplyphotos.data.repository;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import ru.supplyphotos.data.answers.category.ItemCategory;
import ru.supplyphotos.data.answers.manuals.Manual;
import ru.supplyphotos.data.answers.services.ItemService;
import ru.supplyphotos.data.storage.ItemStorageImage;
import ru.supplyphotos.data.upload.order_item_id.OrderItemId;

/**
 * @author Libgo on 15.03.2018.
 */

public interface BaseAppRepository {

    interface GetRepository {

        Observable<Manual> getGuides();

        Observable<List<ItemCategory>> getListCategory();

        Observable<List<ItemService>> getListService(Integer service_id);

        Flowable<List<ItemStorageImage>> getImageFlowable();

    }

    interface UploadRepository {


        Flowable<OrderItemId> createOrderItem();

        Flowable<ResponseBody> startingUploadImage();
    }


}
