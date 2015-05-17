package is.brana.model.entities;

import is.brana.model.rest.UnnurApi;

/**
 * Created by thibaultguegan on 17/05/15.
 */
public class Image{

    private Long id;
    private boolean cropped;

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isCropped() {
        return cropped;
    }

    public void setCropped(boolean cropped) {
        this.cropped = cropped;
    }

    public String getImageUrl(Long accomodationId) {
        String url = "";

        if(isCropped()) {
            url = String.format(UnnurApi.CROPPED_IMAGE, accomodationId, this.id);
        } else {
            url = String.format(UnnurApi.ORIGINAL_IMAGE, accomodationId, this.id);
        }

        return url;
    }
}
