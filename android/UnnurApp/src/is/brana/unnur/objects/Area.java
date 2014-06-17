package is.brana.unnur.objects;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by thibaultguegan on 03/06/2014.
 */
public class Area {

    private String name;
    private ArrayList<ZipCode> zipCodes;

    public Area(JSONObject object)
    {
        try {
            this.name = object.getString("Name");

            zipCodes = new ArrayList<ZipCode>();

            JSONArray zipCodesArray = object.getJSONArray("ZipCodes");
            for(int i=0; i<zipCodesArray.length(); i++)
            {
                JSONObject zipCodeObject = zipCodesArray.getJSONObject(i);
                ZipCode zipCode = new ZipCode(zipCodeObject);
                zipCodes.add(zipCode);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ZipCode> getZipCodes() {
        return zipCodes;
    }

    public void setZipCodes(ArrayList<ZipCode> zipCodes) {
        this.zipCodes = zipCodes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "\n" + "name: " + name  + "\n" + "zipCodes: " + zipCodes.toString() + "\n";
    }
}
