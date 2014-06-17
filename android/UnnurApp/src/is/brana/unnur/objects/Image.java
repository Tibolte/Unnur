package is.brana.unnur.objects;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import is.brana.unnur.utils.Urls;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by thibaultguegan on 25/05/2014.
 */
public class Image implements Parcelable{

    private Long id;
    private boolean cropped;

    public Image(JSONObject object)
    {
        try {
            this.id = object.getLong("Id");
            this.cropped = object.getBoolean("Cropped");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isCropped() {
        return cropped;
    }

    public void setCropped(boolean cropped) {
        this.cropped = cropped;
    }

    public String getImageUrl(Long accomodationId)
    {
        String url = "";

        if(isCropped())
        {
            url = String.format(Urls.CROPPED_IMAGE, accomodationId, this.id);
        }
        else
        {
            url = String.format(Urls.ORIGINAL_IMAGE, accomodationId, this.id);
        }

        return url;
    }

    public static final Parcelable.Creator<Image> CREATOR
            = new Parcelable.Creator<Image>()
    {
        public Image createFromParcel(Parcel in)
        {
            return new Image(in);
        }

        public Image[] newArray(int size)
        {
            return new Image[size];
        }

    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        Bundle bundle = new Bundle();

        bundle.putLong("id", id);
        bundle.putBoolean("cropped", cropped);

        parcel.writeBundle(bundle);
    }

    private Image(Parcel in)
    {
        Bundle inBundle = in.readBundle();
        id = inBundle.getLong("id");
        cropped = inBundle.getBoolean("cropped");
    }
}
