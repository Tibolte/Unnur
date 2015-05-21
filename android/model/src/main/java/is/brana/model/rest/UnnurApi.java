package is.brana.model.rest;

import java.util.List;

import is.brana.model.entities.Accomodation;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by thibaultguegan on 17/05/15.
 */
public interface UnnurApi {

    public static final String ENDPOINT              = "http://brana.is/unnur/webapi/api/";
    public static final String ENDPOINT_IMAGES       = "http://brana.is/unnur/uploads/fasteignimages/";

    public static final String ORIGINAL_IMAGE        = ENDPOINT_IMAGES + "%s/%s.jpg";
    public static final String CROPPED_IMAGE         = ENDPOINT_IMAGES + "%s/%scrop.jpg";

    public static final String MAP_URL               = "http://maps.googleapis.com/maps/api/staticmap?center=%s,%s&markers=color:0x8fc249|%s,%s&zoom=16&size=450x450&sensor=false";

    @Headers("Content-Type: application/json")
    @GET("/Fasteign/{offset}/{count}")
    Observable<List<Accomodation>> getAccomodations(@Path("offset") int offset, @Path("count") int count);
}
