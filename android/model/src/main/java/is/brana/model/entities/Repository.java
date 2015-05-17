package is.brana.model.entities;

import rx.Observable;

/**
 * Created by thibaultguegan on 17/05/15.
 */
public interface Repository {

    Observable<Accomodation> getAccomodations(final int offset, final int count);

}
