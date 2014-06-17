package is.brana.unnur.interfaces;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by thibaultguegan on 21/05/2014.
 */
public interface AccomodationsListener {

    public void onAccomodationsSuccess(JSONArray response);

    public void onAccomodationsFailed();
}
