package is.brana.unnur;

import android.app.Application;

import is.brana.unnur.di.components.AppComponent;
import is.brana.unnur.di.components.DaggerAppComponent;
import is.brana.unnur.di.modules.ApplicationModule;
import is.brana.unnur.di.modules.DomainModule;

/**
 * Created by thibaultguegan on 17/05/15.
 */
public class UnnurApp extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeDependencyInjector();
    }

    private void initializeDependencyInjector() {
        mAppComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .domainModule(new DomainModule())
                .build();
    }

    public AppComponent getAppComponent() {

        return mAppComponent;
    }
}
