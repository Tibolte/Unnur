package is.brana.unnur.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import is.brana.unnur.UnnurApp;

@Module
public class ApplicationModule {

    private final UnnurApp application;

    public ApplicationModule(UnnurApp application) {
        this.application = application;
    }

    @Provides @Singleton Context provideApplicationContext () { return application; }
}
