package is.brana.model.rest;

import java.util.List;

import is.brana.model.entities.Accomodation;
import is.brana.model.entities.Repository;
import retrofit.RestAdapter;
import rx.Observable;

/**
 * Created by thibaultguegan on 17/05/15.
 */
public class RestRepository implements Repository {

    private final UnnurApi mUnnurApi;

    public RestRepository(){

        RestAdapter unnurAPIRest = new RestAdapter.Builder()
                .setEndpoint(UnnurApi.ENDPOINT)
                .setLogLevel(RestAdapter.LogLevel.HEADERS_AND_ARGS)
                .build();

        mUnnurApi = unnurAPIRest.create(UnnurApi.class);
    }

    @Override
    public Observable<List<Accomodation>> getAccomodations(int offset, int count) {
        return mUnnurApi.getAccomodations(offset, count);
    }
}
