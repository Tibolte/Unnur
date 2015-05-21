package is.brana.model.entities;

import java.util.List;

import rx.Observable;

/**
 * Created by thibaultguegan on 17/05/15.
 */
public interface Repository {

    Observable<List<Accomodation>> getAccomodations(final int offset, final int count);

}
