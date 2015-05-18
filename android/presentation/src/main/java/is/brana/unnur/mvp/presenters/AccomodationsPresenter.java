package is.brana.unnur.mvp.presenters;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import is.brana.model.entities.Accomodation;
import is.brana.unnur.mvp.views.AccomodationsView;

/**
 * Created by thibaultguegan on 18/05/15.
 */
public class AccomodationsPresenter implements Presenter {

    private final List<Accomodation> mAccomodationsList;
    private final Context mContext;
    private AccomodationsView mAccomodationsView;
    private Intent mIntent;

    @Inject public AccomodationsPresenter(List<Accomodation> accomodationList, Context context) {
        mAccomodationsList = accomodationList;
        mContext = context;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void attachView(View v) {
        mAccomodationsView = (AccomodationsView)v;
        mAccomodationsView.showAccomodationList(mAccomodationsList);
    }

    @Override
    public void attachIncomingIntent(Intent intent) {
        mIntent = intent;
    }
}
