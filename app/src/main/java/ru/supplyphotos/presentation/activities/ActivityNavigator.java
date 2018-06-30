package ru.supplyphotos.presentation.activities;

import android.app.Activity;
import android.content.Intent;

import javax.inject.Inject;

import ru.supplyphotos.presentation.activities.head.HeadActivity;

/**
 * @author Grigoriy Pryamov.
 */
public class ActivityNavigator {

    @Inject
    ActivityNavigator() {

    }

    public void navigateToHeadActivity(Activity activity) {
        Intent intent = HeadActivity.getCallingIntent(activity);
        activity.startActivity(intent);
        activity.finish();
    }

    public void navigateBack(Activity activity) {
        activity.finish();
//        animBack(activity);
    }

//    private void animForward(Activity activity) {
//        activity.overridePendingTransition(R.anim.slide_from_right, R.anim.zero_animation);
//    }
//
//    private void animBack(Activity activity) {
//        activity.overridePendingTransition(R.anim.zero_animation, R.anim.slide_to_right);
//    }
}
