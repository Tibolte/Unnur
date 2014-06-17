package is.brana.unnur.objects;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by thibaultguegan on 03/06/2014.
 */
public class ZipCode {

    private Long zipCodeId;
    private int zipCode;
    private String title;

    public ZipCode(JSONObject object)
    {
        try {
            this.zipCodeId = object.getLong("ZipCodeId");
            this.zipCode = object.getInt("ZipCode");
            this.title = object.getString("Title");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Long getZipCodeId() {
        return zipCodeId;
    }

    public void setZipCodeId(Long zipCodeId) {
        this.zipCodeId = zipCodeId;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString()
    {
        return  "zipCodeId: " + String.valueOf(zipCodeId) + " zipCode: " + String.valueOf(zipCode) + ", title: " + String.valueOf(title);
    }
}
