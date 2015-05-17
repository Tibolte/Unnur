package is.brana.common.utils;

/**
 * Created by thibaultguegan on 19/04/15.
 */
public class Constants {

    public static final String BASE_URL             = "http://brana.is/unnur/";
    public static final String API_SUFFIX           = "webapi/api/";
    public static final String IMAGE_SUFFIX         = "uploads/fasteignimages/";

    public static final String BASE_API             = BASE_URL + API_SUFFIX;
    public static final String BASE_IMAGES          = BASE_URL + IMAGE_SUFFIX;

    public static final String ACCOMODATIONS         = BASE_API + "Fasteign/%s/%s";
    public static final String ACCOMODATIONS_DETAIL  = BASE_API + "Fasteign/%s";
    public static final String SEARCH                = BASE_API + "Fasteign/%s/%s/%s/%s/%s/%s/%s/%s/%s/%s";
    public static final String SEARCH_SETTINGS       = BASE_API + "searchsettings";
    public static final String ORIGINAL_IMAGE        = BASE_IMAGES + "%s/%s.jpg";
    public static final String CROPPED_IMAGE         = BASE_IMAGES + "%s/%scrop.jpg";

    public static final String MAP_URL           = "http://maps.googleapis.com/maps/api/staticmap?center=%s,%s&markers=color:0x8fc249|%s,%s&zoom=16&size=450x450&sensor=false";

}
