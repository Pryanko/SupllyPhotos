package ru.supplyphotos.presentation.presenters;

/**
 * @author Libgo on 10.04.2018.
 */
public class Representative implements ActivityRepresentative {

    private BasePresenter.ControlForToolbar controlForToolbar;

    @Override
    public void setRepresentative(BasePresenter.ControlForToolbar controlForToolbar) {
           this.controlForToolbar = controlForToolbar;
    }

    @Override
    public void switchTypeScreen(String typeScreen) {
          controlForToolbar.setTypeScreen(typeScreen);
    }
}
