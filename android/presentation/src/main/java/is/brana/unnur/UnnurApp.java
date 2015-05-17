package is.brana.unnur;

import android.app.Application;

import is.brana.unnur.di.components.AppComponent;
import is.brana.unnur.di.components.DaggerAppComponent;
import is.brana.unnur.di.modules.AppModule;

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
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {

        return mAppComponent;
    }
}
