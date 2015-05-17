package is.brana.model.entities;

import java.util.ArrayList;

/**
 * Created by thibaultguegan on 17/05/15.
 */
public class Accomodation {

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
        return this.roomCount;
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

    public String getLongTerm() {
        if(isLongTerm()) {
            return "Langtíma";
        } else {
            return "Skammtíma";
        }
    }
}
