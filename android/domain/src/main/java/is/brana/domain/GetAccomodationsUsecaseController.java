package is.brana.domain;

import is.brana.model.rest.RestRepository;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by thibaultguegan on 20/05/15.
 */
public class GetAccomodationsUsecaseController implements GetAccomodationsUsecase {

    private RestRepository mRestRepository;
    private Subscriber mSubscriber;
    private int mCurrentOffset = 0;

    /**
     * Constructor of the class
     *
     * @param restRepository the repository to retrieve the list of accomodations
     * @param subscriber the subscriver that gonna listen to the emmited accomodations
     */
    public GetAccomodationsUsecaseController(RestRepository restRepository) {
        mRestRepository = restRepository;
        //mSubscriber = subscriber;
    }

    @Override
    public void requestAccomodations() {
        /*mRestRepository.getAccomodations(mCurrentOffset, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mSubscriber);*/
    }

    @Override
    public void execute() {
        requestAccomodations();
        mCurrentOffset += 10;
    }
}
