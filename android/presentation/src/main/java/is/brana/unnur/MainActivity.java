package is.brana.unnur;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import is.brana.model.entities.Accomodation;
import is.brana.unnur.di.components.DaggerAccomodationsUsecasesComponent;
import is.brana.unnur.di.modules.AccomodationsUsecasesModule;
import is.brana.unnur.mvp.presenters.AccomodationsPresenter;
import is.brana.unnur.mvp.views.AccomodationsView;


public class MainActivity extends ActionBarActivity implements AccomodationsView {

    @Inject
    AccomodationsPresenter mAccomodationspresenter;

    /**
     * MARK: Lifecycle methods
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        initializeDependencyInjector();

        if (savedInstanceState == null) {
            mAccomodationspresenter.attachView(this);
        } else {
            //TODO: recreate from saved instance
        }
    }

    @Override
    protected void onStart() {

        super.onStart();
        mAccomodationspresenter.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * MARK: Private methods
     */
    private void initializeDependencyInjector() {

        UnnurApp unnurApp = (UnnurApp) getApplication();

        DaggerAccomodationsUsecasesComponent.builder()
                .appComponent(unnurApp.getAppComponent())
                .accomodationsUsecasesModule(new AccomodationsUsecasesModule())
                .build().inject(this);

    }

    /**
     * MARK: AccomodationsView overrides
     */
    @Override
    public void showAccomodationList(List<Accomodation> accomodationList) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showLoadingLabel() {

    }

    @Override
    public void hideActionLabel() {

    }

    @Override
    public boolean isTheListEmpty() {
        //TODO: not for now
        return true;
    }

    @Override
    public void appendAccomodationList(List<Accomodation> accomodationList) {

    }

    @Override
    public Context getContext() {
        return this;
    }
}
