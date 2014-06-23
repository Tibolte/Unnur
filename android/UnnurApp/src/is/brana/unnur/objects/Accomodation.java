package is.brana.unnur.objects;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import is.brana.unnur.utils.Urls;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by thibaultguegan on 16/05/2014.
 */
public class Accomodation implements Parcelable{

    private Long id;
    private int squareSize;
    private int roomCount;
    private int value;
    private String street;
    private int zipCode;
    private String zipName;
    private String region;
    private String municipality;
    private Long areaId;
    private String category;
    private boolean longTerm;
    private ArrayList<Image> images;

    public Accomodation()
    {

    }

    public Accomodation(JSONObject object)
    {

        try {
            this.id = object.getLong("FasteignId");
            this.squareSize = object.getInt("StaerdFm");
            this.roomCount = object.getInt("HerbergiFjoldi");
            this.value = object.getInt("Verd");
            this.street = object.getString("Gata");
            this.zipCode = object.getInt("Postnumer");
            this.zipName = object.getString("PostnumerHeiti");
            this.region = object.getString("SvaediHeiti");
            this.municipality = object.getString("SveitarfelagHeiti");
            if(!object.isNull("SvaediId"))
            {
                this.areaId = object.getLong("SvaediId");
            }
            else
            {
                this.areaId = Long.valueOf(0);
            }

            if(!object.isNull("Tegund"))
            {
                this.category = object.getString("Tegund");
            }
            else
            {
                this.category = "";
            }

            if(!object.isNull("Langtimaleiga"))
            {
                this.longTerm = object.getBoolean("Langtimaleiga");
            }
            else
            {
                this.longTerm = true;
            }

            this.images = new ArrayList<Image>();

            JSONArray imageArray = object.getJSONArray("Images");

            for(int i=0; i<imageArray.length(); i++)
            {
                Image image = new Image(imageArray.getJSONObject(i));
                this.images.add(image);
            }

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

    public int getSquareSize() {
        return squareSize;
    }

    public void setSquareSize(int squareSize) {
        this.squareSize = squareSize;
    }

    public int getRoomSize() {
        return roomCount;
    }

    public void setRoomSize(int roomSize) {
        this.roomCount = roomSize;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return zipName;
    }

    public void setAddress(String address) {
        this.zipName = address;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isLongTerm() {
        return longTerm;
    }

    public void setLongTerm(boolean longTerm) {
        this.longTerm = longTerm;
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }

    public String getLongTerm()
    {
        if(isLongTerm())
        {
            return "Langtíma";
        }
        else
        {
            return "Skammtíma";
        }
    }

    public static final Parcelable.Creator<Accomodation> CREATOR
            = new Parcelable.Creator<Accomodation>()
    {
        public Accomodation createFromParcel(Parcel in)
        {
            return new Accomodation(in);
        }

        public Accomodation[] newArray(int size)
        {
            return new Accomodation[size];
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
        bundle.putInt("squareSize", squareSize);
        bundle.putInt("roomCount", roomCount);
        bundle.putInt("value", value);
        bundle.putString("street", street);
        bundle.putInt("zipCode", zipCode);
        bundle.putString("zipName", zipName);
        bundle.putString("region", region);
        bundle.putString("municipality", municipality);
        bundle.putLong("areaId", areaId);
        bundle.putString("category", category);
        bundle.putBoolean("longTerm", longTerm);
        bundle.putParcelableArrayList("images", images);

        parcel.writeBundle(bundle);
    }

    private Accomodation(Parcel in)
    {
        Bundle inBundle = in.readBundle();

        id = inBundle.getLong("id");
        squareSize = inBundle.getInt("squareSize");
        roomCount = inBundle.getInt("roomcount");
        value = inBundle.getInt("value");
        street = inBundle.getString("street");
        zipCode = inBundle.getInt("zipCode");
        zipName = inBundle.getString("zipName");
        region = inBundle.getString("region");
        municipality = inBundle.getString("municipality");
        areaId = inBundle.getLong("areaId");
        category = inBundle.getString("category");
        longTerm = inBundle.getBoolean("longTerm");
        images = inBundle.getParcelableArrayList("images");
    }
}
