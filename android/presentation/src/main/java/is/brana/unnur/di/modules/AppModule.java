package is.brana.unnur.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import is.brana.model.entities.Repository;
import is.brana.model.rest.RestRepository;
import is.brana.unnur.UnnurApp;

/**
 * Created by thibaultguegan on 17/05/15.
 */
@Module
public class AppModule {

    private final UnnurApp mUnnurApp;

    public AppModule(UnnurApp unnurApp) {
        this.mUnnurApp = unnurApp;
    }

    @Provides @Singleton UnnurApp provideUnnurAppContext () { return mUnnurApp; }

}
