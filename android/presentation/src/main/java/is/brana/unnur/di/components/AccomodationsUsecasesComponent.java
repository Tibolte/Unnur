package is.brana.unnur.di.components;

import dagger.Component;
import is.brana.unnur.MainActivity;
import is.brana.unnur.di.modules.AccomodationsUsecasesModule;
import is.brana.unnur.di.scopes.PerActivity;

@PerActivity
@Component(dependencies = AppComponent.class, modules = AccomodationsUsecasesModule.class)
public interface AccomodationsUsecasesComponent {
    void inject(MainActivity activity);
}
