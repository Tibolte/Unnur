package is.brana.unnur.interfaces;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by thibaultguegan on 14/06/2014.
 */
public interface SearchResultListener {

    public void onSearchResultSuccess(JSONArray array);

    public void onSearchResultFailed();

}
