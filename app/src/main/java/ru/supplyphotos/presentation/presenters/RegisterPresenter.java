package ru.supplyphotos.presentation.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import ru.supplyphotos.App;
import ru.supplyphotos.presentation.fragments.ContractsFragmentView;
import ru.terrakok.cicerone.Router;

import static ru.supplyphotos.constants.Constants.UPLOAD_SCREEN;

/**
 * @author Libgo on 09.04.2018.
 */
@InjectViewState
public class RegisterPresenter extends MvpPresenter<ContractsFragmentView.RegisterView>
        implements BasePresenter.Register{

    private Router router;

    public RegisterPresenter() {
        router = App.getAppComponent().getRouter();
    }

    @Override
    public void nextScreen(){
        router.navigateTo(UPLOAD_SCREEN);
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void destroyView() {

    }
}
