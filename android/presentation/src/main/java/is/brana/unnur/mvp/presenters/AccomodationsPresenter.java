package is.brana.unnur.mvp.presenters;

import android.content.Intent;

import java.util.List;

import javax.inject.Inject;

import is.brana.domain.GetAccomodationsUsecase;
import is.brana.model.entities.Accomodation;
import is.brana.unnur.mvp.views.AccomodationsView;
import rx.Subscriber;

/**
 * Created by thibaultguegan on 18/05/15.
 */
public class AccomodationsPresenter extends Subscriber<List<Accomodation>> implements Presenter {

    private AccomodationsView mAccomodationsView;
    private GetAccomodationsUsecase mGetAccomodationsUsecase;

    private boolean isLoading = false;

    @Inject
    public AccomodationsPresenter(GetAccomodationsUsecase getAccomodationsUsecase) {
        mGetAccomodationsUsecase = getAccomodationsUsecase;
    }

    public void attachView(AccomodationsView v) {
        mAccomodationsView = (AccomodationsView)v;
    }

    public boolean isLoading() {

        return isLoading;
    }

    public void setLoading(boolean isLoading) {

        this.isLoading = isLoading;
    }

    /**
     * MARK: Presenter overrides
     */
    @Override
    public void start() {
        if(mAccomodationsView.isTheListEmpty()) {
            mAccomodationsView.showLoading();
            mGetAccomodationsUsecase.execute();
            setLoading(true);
        }
    }

    @Override
    public void stop() {

    }

    /**
     * MARK: Subscriber<Accomodation> overrides
     */
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        System.out.println("[DEBUG]" + " AccomodationsPresenter onError - " + e.getMessage());
    }

    @Override
    public void onNext(List<Accomodation> accomodationList) {
        if(mAccomodationsView.isTheListEmpty()) {
            mAccomodationsView.hideLoading();
            //TODO: make the view show the list
        } else {
            mAccomodationsView.hideActionLabel();
            //TODO: make the view append accomodations to the list
        }

        setLoading(false);
    }
}
