package is.brana.unnur.di.components;

import javax.inject.Singleton;

import dagger.Component;
import is.brana.model.entities.Repository;
import is.brana.unnur.UnnurApp;
import is.brana.unnur.di.modules.AppModule;

/**
 * Created by thibaultguegan on 17/05/15.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    UnnurApp app();

}
