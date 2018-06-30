package ru.supplyphotos.presentation.activities.head;

import dagger.Subcomponent;
import ru.supplyphotos.di.ScreenScope;

/**
 * @author Grigoriy Pryamov.
 */
@ScreenScope
@Subcomponent
public interface HeadScreenComponent {

    HeadComponent.Builder headComponentBuilder();
}
