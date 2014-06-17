package is.brana.unnur.interfaces;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by thibaultguegan on 25/05/2014.
 */
public interface AccomodationDetailListener {

    public void onAccomodationDetailSuccess(JSONObject response);

    public void onAccomodationDetailFailed();

}
