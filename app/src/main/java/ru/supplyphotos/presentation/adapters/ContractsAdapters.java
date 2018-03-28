package ru.supplyphotos.presentation.adapters;

import ru.supplyphotos.data.answers.category.ItemCategory;

/**
 * @author Libgo on 24.03.2018.
 */

public interface ContractsAdapters {


    interface ItemCategoryTouch {

        void touchItemCategory(ItemCategory itemCategory);

    }

    interface ItemServiceTouch {

        void touchItemService(Integer service_id);

    }

}
