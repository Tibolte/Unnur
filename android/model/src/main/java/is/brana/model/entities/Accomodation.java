package is.brana.model.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thibaultguegan on 17/05/15.
 */
public class Accomodation {

    private Long FasteignId;
    private int StaerdFm;
    private int HerbergiFjoldi;
    private int Verd;
    private String Gata;
    private int Postnumer;
    private String PostnumerHeiti;
    private String SvaediHeiti;
    private String SveitarfelagHeiti;
    private List<Image> Images;

    public Long getFasteignId() {
        return FasteignId;
    }

    public void setFasteignId(Long fasteignId) {
        FasteignId = fasteignId;
    }

    public int getStaerdFm() {
        return StaerdFm;
    }

    public void setStaerdFm(int staerdFm) {
        StaerdFm = staerdFm;
    }

    public int getHerbergiFjoldi() {
        return HerbergiFjoldi;
    }

    public void setHerbergiFjoldi(int herbergiFjoldi) {
        HerbergiFjoldi = herbergiFjoldi;
    }

    public int getVerd() {
        return Verd;
    }

    public void setVerd(int verd) {
        Verd = verd;
    }

    public String getGata() {
        return Gata;
    }

    public void setGata(String gata) {
        Gata = gata;
    }

    public int getPostnumer() {
        return Postnumer;
    }

    public void setPostnumer(int postnumer) {
        Postnumer = postnumer;
    }

    public String getPostnumerHeiti() {
        return PostnumerHeiti;
    }

    public void setPostnumerHeiti(String postnumerHeiti) {
        PostnumerHeiti = postnumerHeiti;
    }

    public String getSvaediHeiti() {
        return SvaediHeiti;
    }

    public void setSvaediHeiti(String svaediHeiti) {
        SvaediHeiti = svaediHeiti;
    }

    public String getSveitarfelagHeiti() {
        return SveitarfelagHeiti;
    }

    public void setSveitarfelagHeiti(String sveitarfelagHeiti) {
        SveitarfelagHeiti = sveitarfelagHeiti;
    }

    public List<Image> getImages() {
        return Images;
    }

    public void setImages(List<Image> images) {
        Images = images;
    }
}
