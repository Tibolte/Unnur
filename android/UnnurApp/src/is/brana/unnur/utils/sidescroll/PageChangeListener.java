package is.brana.unnur.utils.sidescroll;

import android.widget.ImageView;
import is.brana.unnur.R;

/**
 * Created by thibaultguegan on 22/05/2014.
 */
public class PageChangeListener implements SwipeView.OnPageChangedListener{

    private ImageView[] spotList;

    public PageChangeListener(ImageView[] spotList)
    {
        this.spotList = spotList;
    }

    @Override
    public void onPageChanged(int oldPage, int newPage) {
        for (int i = 0; i < spotList.length; i++)
        {
            ImageView spot = spotList[i];
            if (i == newPage)
                spot.setImageResource(R.drawable.plot_filled);
            else
                spot.setImageResource(R.drawable.plot_empty);
        }
    }
}
