package is.brana.unnur.objects;

import is.brana.unnur.utils.Urls;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by thibaultguegan on 22/05/2014.
 */
public class AccomodationDetail {

    private Long id;
    private String user;
    private String phone;
    private String mail;

    private int squareSize;
    private int roomCount;
    private int value;

    private Date startDate;
    private Date endDate;

    private String description;
    private String street;
    private int zipCode;
    private String zipName;
    private String region;
    private String municipality;
    private Long areaId;
    private String category;
    private boolean longTerm;
    private boolean heat;
    private boolean electricity;
    private int houseFund;
    private int prePaid;
    private int guarantee;
    private String insurance;
    private float lat;
    private float lon;
    private ArrayList<Image> images;

    public AccomodationDetail(JSONObject object)
    {
        try {
            this.id = object.getLong("FasteignId");
            this.street = object.getString("Gata");
            this.zipCode = object.getInt("Postnumer");
            this.zipName = object.getString("PostnumerHeiti");
            this.region = object.getString("SvaediHeiti");
            this.municipality = object.getString("SveitarfelagHeiti");
            this.description = object.getString("FasteignLysing");
            this.lat = (float) object.getDouble("Lat");
            this.lon = (float) object.getDouble("Lon");
            this.heat = object.getBoolean("HitiInnifalinn");
            this.electricity = object.getBoolean("RafmagnInnifalid");
            this.insurance = object.getString("FasteignTryggingLysing");

            ///Fields that can be null

            if(!object.isNull("TengilidurNafn"))
            {
                this.user = object.getString("TengilidurNafn");
            }
            else
            {
                this.user = "N/A";
            }

            if(!object.isNull("TengilidurSimi"))
            {
                this.phone = object.getString("TengilidurSimi");
            }
            else
            {
                this.phone = "N/A";
            }

            if(!object.isNull("TengilidurPostfang"))
            {
                this.mail = object.getString("TengilidurPostfang");
            }
            else
            {
                this.mail = "N/A";
            }

            if(!object.isNull("FasteignTegundLysing"))
            {
                this.category = object.getString("FasteignTegundLysing");
            }
            else
            {
                this.category = "N/A";
            }

            if(!object.isNull("StaerdFm"))
            {
                this.squareSize = object.getInt("StaerdFm");
            }
            else
            {
                this.squareSize = 0;
            }

            if(!object.isNull("HerbergiFjoldi"))
            {
                this.roomCount = object.getInt("HerbergiFjoldi");
            }
            else
            {
                this.roomCount = 0;
            }

            if(!object.isNull("Verd"))
            {
                this.value = object.getInt("Verd");
            }
            else
            {
                this.value = 0;
            }


            if(!object.isNull("SvaediId"))
            {
                this.areaId = object.getLong("SvaediId");
            }
            else
            {
                this.areaId = Long.valueOf(0);
            }

            if(!object.isNull("Langtimaleiga"))
            {
                this.longTerm = object.getBoolean("Langtimaleiga");
            }
            else
            {
                this.longTerm = true;
            }

            if(!object.isNull("HussjodurKr"))
            {
                this.houseFund = object.getInt("HussjodurKr");
            }
            else
            {
                this.houseFund = 0;
            }

            if(!object.isNull("Fyrirframgreidsla"))
            {
                this.prePaid = object.getInt("Fyrirframgreidsla");
            }
            else
            {
                this.prePaid = 0;
            }

            if(!object.isNull("TryggingUpphaed"))
            {
                this.guarantee = object.getInt("TryggingUpphaed");
            }
            else
            {
                this.guarantee = 0;
            }

            this.images = new ArrayList<Image>();

            JSONArray imageArray = object.getJSONArray("Images");

            for(int i=0; i<imageArray.length(); i++)
            {
                Image image = new Image(imageArray.getJSONObject(i));
                this.images.add(image);
            }

            //dates
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            if(!object.isNull("AfhendingDags"))
            {
                String startDate = object.getString("AfhendingDags");

                try {
                    this.startDate = formatter.parse(startDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if(!object.isNull("LeigdTilDags"))
            {
                String endDate = object.getString("LeigdTilDags");

                try {
                    this.endDate = formatter.parse(endDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
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

    public String getSquareSizeString()
    {
        if(squareSize > 0)
            return String.valueOf(squareSize) + " fm";
        else
            return "N/A";
    }

    public void setSquareSize(int squareSize) {
        this.squareSize = squareSize;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public String getRoomCountString()
    {
        if(roomCount > 0)
            return String.valueOf(roomCount) + ". Hæð";
        else
            return "N/A";
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
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

    public String getZipName() {
        return zipName;
    }

    public void setZipName(String zipName) {
        this.zipName = zipName;
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

    public void setLongTerm(boolean longTerm) {
        this.longTerm = longTerm;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }

    public String getStartDate()
    {
        if(this.startDate != null)
        {
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yy");

            return formatter.format(this.startDate);
        }
        else
        {
            return "N/A";
        }
    }

    public String getEndDate()
    {
        if(this.endDate != null)
        {
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yy");

            return formatter.format(this.endDate);
        }
        else
        {
            return "N/A";
        }
    }

    public boolean isHeat() {
        return heat;
    }

    public void setHeat(boolean heat) {
        this.heat = heat;
    }

    public String getHeat()
    {
        if(isHeat())
        {
            return "Innfalinn";
        }
        else
        {
            return "Ekkert";
        }
    }

    public boolean isElectricity() {
        return electricity;
    }

    public void setElectricity(boolean electricity) {
        this.electricity = electricity;
    }

    public String getElectricity()
    {
        if(isElectricity())
        {
            return "Innfalinn";
        }
        else
        {
            return "Ekkert";
        }
    }

    public int getHouseFund() {
        return houseFund;
    }

    public void setHouseFund(int houseFund) {
        this.houseFund = houseFund;
    }

    public String getHouseFundString()
    {
        if(houseFund > 0)
            return String.valueOf(houseFund)+ " .kr";
        else
            return "N/A";
    }

    public int getPrePaid() {
        return prePaid;
    }

    public void setPrePaid(int prePaid) {
        this.prePaid = prePaid;
    }

    public String getPrePaidString()
    {
        if(prePaid > 0)
            return String.valueOf(prePaid)+ " .kr";
        else
            return "N/A";
    }

    public int getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(int guarantee) {
        this.guarantee = guarantee;
    }

    public String getGuaranteeString()
    {
        if(guarantee > 0)
            return String.valueOf(guarantee)+ " .kr";
        else
            return "N/A";
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getValueString()
    {
        if(value > 0)
            return String.valueOf(value)+ " .kr";
        else
            return "N/A";
    }
}
