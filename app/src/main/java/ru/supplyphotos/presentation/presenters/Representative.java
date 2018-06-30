package ru.supplyphotos.presentation.presenters;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Libgo on 10.04.2018.
 */
@Singleton
public class Representative implements ActivityRepresentative {

    private BasePresenter.ControlForToolbar controlForToolbar;

    @Inject
    public Representative() {
    }

    @Override
    public void setRepresentative(BasePresenter.ControlForToolbar controlForToolbar) {
           this.controlForToolbar = controlForToolbar;
    }

    @Override
    public void switchTypeScreen(String typeScreen) {
          controlForToolbar.setTypeScreen(typeScreen);
    }
}
