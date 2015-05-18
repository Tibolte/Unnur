package is.brana.domain;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by thibaultguegan on 18/05/15.
 */
public interface Usecase {

    Subscription execute(Subscriber subscriber);

}
