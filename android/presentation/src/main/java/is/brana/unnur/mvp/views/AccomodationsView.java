package is.brana.unnur.mvp.views;

/**
 * Created by thibaultguegan on 17/05/15.
 */
public interface AccomodationsView extends MVPView {

    //TODO: add actions relation to showing/appending accomodations

    void showLoading ();

    void hideLoading ();

    void showLoadingLabel();

    void hideActionLabel ();

    boolean isTheListEmpty ();

}
