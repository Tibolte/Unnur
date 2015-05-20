package is.brana.unnur.di.components;

import javax.inject.Singleton;

import dagger.Component;
import is.brana.model.rest.RestRepository;
import is.brana.unnur.di.modules.ApplicationModule;
import is.brana.unnur.di.modules.DomainModule;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        DomainModule.class,
})
public interface AppComponent {

    RestRepository restRepository();

}
