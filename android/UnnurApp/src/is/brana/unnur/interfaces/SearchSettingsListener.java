package is.brana.unnur.interfaces;

import org.json.JSONObject;

/**
 * Created by thibaultguegan on 03/06/2014.
 */
public interface SearchSettingsListener {

    public void onSearchSettingsSuccess(JSONObject response);

    public void onSearchSettingsFailed();

}
