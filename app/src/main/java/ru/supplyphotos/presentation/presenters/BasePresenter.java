package ru.supplyphotos.presentation.presenters;



/**
 * @author or libgo on 12.01.2018.
 */

public interface BasePresenter  {



    interface Description extends Contracts{
        void nextScreen();

    }


    interface Upload extends Contracts{

    }

    interface Gallery extends Contracts{

        void loadImage();

    }


    interface Category extends NavigationScreen{
    }

    interface Manual extends  NavigationScreen{
    }

    interface NavigationScreen extends Contracts{
        void nextScreen();
    }

    interface Contracts {
        void onError(Throwable throwable);

        void destroyView();
    }

}
