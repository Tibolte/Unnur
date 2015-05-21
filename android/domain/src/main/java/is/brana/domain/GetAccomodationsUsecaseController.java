package is.brana.domain;

import java.util.List;

import is.brana.model.entities.Accomodation;
import is.brana.model.rest.RestRepository;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by thibaultguegan on 20/05/15.
 */
public class GetAccomodationsUsecaseController extends Subscriber<List<Accomodation>> implements GetAccomodationsUsecase {

    private static final String LOG_TAG = GetAccomodationsUsecaseController.class.getSimpleName();

    private RestRepository mRestRepository;
    private int mCurrentOffset = 0;

    /**
     * Constructor of the class
     *
     * @param restRepository the repository to retrieve the list of accomodations
     */
    public GetAccomodationsUsecaseController(RestRepository restRepository) {
        mRestRepository = restRepository;
    }

    @Override
    public void requestAccomodations() {
        System.out.println("[DEBUG]" + " GetAccomodationsUsecaseController - requestAccomodations() called");
        mRestRepository.getAccomodations(mCurrentOffset, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    public void execute() {
        requestAccomodations();
        mCurrentOffset += 10;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(List<Accomodation> accomodationList) {
        System.out.println("[DEBUG]" + " GetAccomodationsUsecaseController - onNext() called");
    }
}
