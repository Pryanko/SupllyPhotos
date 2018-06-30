package ru.supplyphotos.presentation.activities.head;

import dagger.Subcomponent;
import ru.supplyphotos.di.ActivityScope;
import ru.supplyphotos.presentation.activities.base.ActivityModule;

/**
 * @author Grigoriy Pryamov.
 */
@ActivityScope
@Subcomponent(modules = {ActivityModule.class})
public interface HeadComponent {

    void inject(HeadActivity headActivity);

    HeadPresenter headPresenter();

    @Subcomponent.Builder
    interface Builder {

        HeadComponent.Builder activityModule(ActivityModule activityModule);

        HeadComponent build();
    }

}
