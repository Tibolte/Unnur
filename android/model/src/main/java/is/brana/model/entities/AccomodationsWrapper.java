package is.brana.model.entities;

import java.io.Serializable;
import java.util.List;

/**
 * Created by thibaultguegan on 18/05/15.
 */
public class AccomodationsWrapper implements Serializable {

    private List<Accomodation> results;

    public AccomodationsWrapper(List<Accomodation> results) {
        this.results = results;
    }

    public List<Accomodation> getResults() {
        return results;
    }
}
