package is.brana.unnur.mvp.presenters;

import android.content.Intent;

import javax.inject.Inject;

import is.brana.domain.GetAccomodationsUsecase;
import is.brana.unnur.mvp.views.AccomodationsView;
import rx.Subscriber;

/**
 * Created by thibaultguegan on 18/05/15.
 */
public class AccomodationsPresenter extends Presenter {

    private Subscriber mSubscriber;
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

    public void start() {
        if(mAccomodationsView.isTheListEmpty()) {
            mAccomodationsView.showLoading();
            //TODO: usecase.execute
        }
    }

    public void stop() {

    }

    public boolean isLoading() {

        return isLoading;
    }

    public void setLoading(boolean isLoading) {

        this.isLoading = isLoading;
    }
}
