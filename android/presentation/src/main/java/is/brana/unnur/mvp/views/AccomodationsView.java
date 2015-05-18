package is.brana.unnur.mvp.views;

import java.util.List;

import is.brana.model.entities.Accomodation;

/**
 * Created by thibaultguegan on 17/05/15.
 */
public interface AccomodationsView extends MVPView {

    void showAccomodationList(List<Accomodation> accomodationList);

    void showLoading ();

    void hideLoading ();

    void showLoadingLabel();

    void hideActionLabel ();

    boolean isTheListEmpty ();

    void appendAccomodationList(List<Accomodation> accomodationList);
}
