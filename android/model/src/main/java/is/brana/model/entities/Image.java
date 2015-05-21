package is.brana.model.entities;

import is.brana.model.rest.UnnurApi;

/**
 * Created by thibaultguegan on 17/05/15.
 */
public class Image{

    private Long Id;
    private boolean Cropped;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public boolean isCropped() {
        return Cropped;
    }

    public void setCropped(boolean cropped) {
        Cropped = cropped;
    }

    public String getImageUrl(Long accomodationId) {
        String url = "";

        if(isCropped()) {
            url = String.format(UnnurApi.CROPPED_IMAGE, accomodationId, this.Id);
        } else {
            url = String.format(UnnurApi.ORIGINAL_IMAGE, accomodationId, this.Id);
        }

        return url;
    }
}
