package is.brana.unnur.di.modules;

import dagger.Module;
import dagger.Provides;
import is.brana.domain.GetAccomodationsUsecase;
import is.brana.domain.GetAccomodationsUsecaseController;
import is.brana.model.rest.RestRepository;

@Module
public class AccomodationsUsecasesModule {

    @Provides GetAccomodationsUsecase provideAccomodationsUsecase(RestRepository restRepository) {
        return new GetAccomodationsUsecaseController(restRepository);
    }

}
