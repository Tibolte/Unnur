package is.brana.unnur.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import is.brana.model.entities.Repository;
import is.brana.model.rest.RestRepository;

/**
 * Created by thibaultguegan on 17/05/15.
 */
@Module
public class DomainModule {

    @Provides @Singleton Repository provideDataRepository (RestRepository restRepository) { return new RestRepository(); }

}
