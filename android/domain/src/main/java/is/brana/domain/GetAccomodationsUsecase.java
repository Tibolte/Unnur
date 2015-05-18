package is.brana.domain;

import is.brana.model.entities.AccomodationsWrapper;
import is.brana.model.entities.Repository;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by thibaultguegan on 18/05/15.
 */
public class GetAccomodationsUsecase implements Usecase{

    private int mOffset, mCount;
    private Repository mRepository;

    //TODO: inject this at some point
    public GetAccomodationsUsecase(int offset, int count, Repository repository) {
        mOffset = offset;
        mCount = count;
        mRepository = repository;
    }

    @Override
    public Subscription execute(Subscriber subscriber) {
        return mRepository.getAccomodations(mOffset, mCount)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
