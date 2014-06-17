package is.brana.unnur.search.listeners;

import java.util.List;
import java.util.Map;

/**
 * Created by thibaultguegan on 09/06/2014.
 */
public interface EditAreaChangeListener {

    public void onEditAreaChanged(Map<Integer, List<Integer>> mCheckedStates);

}
