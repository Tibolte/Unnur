package is.brana.unnur.objects;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by thibaultguegan on 05/06/2014.
 */
public class AccomodationType {

    private Long id;
    private String name;

    public AccomodationType(JSONObject object)
    {
        try {
            this.id = object.getLong("Id");
            this.name = object.getString("Name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
