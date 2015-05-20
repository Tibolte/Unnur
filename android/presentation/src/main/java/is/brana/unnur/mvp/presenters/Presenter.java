package is.brana.unnur.mvp.presenters;

import android.content.Intent;

/**
 * Interface that represents a Presenter in the model view presenter Pattern
 * defines methods to manage the Activity / Fragment lifecycle
 */
public interface Presenter {

    /**
     * Called when the presenter is initialized
     */
    void start();

    /**
     * Called when the presenter is stop, i.e when an activity
     * or a fragment finishes
     */
    void stop();

}
