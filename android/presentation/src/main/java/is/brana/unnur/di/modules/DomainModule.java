package is.brana.unnur.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import is.brana.model.rest.RestRepository;

@Module
public class DomainModule {
    @Provides @Singleton RestRepository provideRestRepository() {
        return new RestRepository();
    }
}
