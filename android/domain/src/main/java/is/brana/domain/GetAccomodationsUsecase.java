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
public interface GetAccomodationsUsecase extends Usecase{

    public void requestAccomodations();

}
