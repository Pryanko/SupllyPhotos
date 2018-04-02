package ru.supplyphotos.tools.mappers;


import java.util.List;

import ru.supplyphotos.data.answers.category.Category;
import ru.supplyphotos.data.answers.category.ItemCategory;
import ru.supplyphotos.data.answers.services.ItemService;
import ru.supplyphotos.data.answers.services.Services;
import ru.supplyphotos.data.answers.start_login.DeviceToken;
import ru.supplyphotos.data.answers.start_login.StartToken;
import ru.supplyphotos.data.storage.ItemStorageImage;

/**
 * @author libgo on 14.01.2018.
 */

public class Mappers {

     public static DeviceToken mapDeviceToken(StartToken startToken){
         return startToken.getDeviceToken();
     }

     public static List<ItemCategory> mapListCategory(Category category){
        return category.getListCategory();
    }

    public static List<ItemService> mapListCategory(Services itemService){
        return itemService.getData();
    }

    public static boolean filterNotNull(List<ItemStorageImage> list){
        return list.size() != 0;
    }

}
