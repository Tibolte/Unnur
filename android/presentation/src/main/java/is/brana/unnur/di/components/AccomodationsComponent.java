package is.brana.unnur.di.components;

import android.content.Context;

import java.util.List;

import dagger.Component;
import is.brana.model.entities.Accomodation;
import is.brana.unnur.MainActivity;
import is.brana.unnur.di.Activity;
import is.brana.unnur.di.modules.AccomodationsModule;
import is.brana.unnur.di.modules.ActivityModule;

/**
 * Created by thibaultguegan on 18/05/15.
 */
@Activity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class})
public interface AccomodationsComponent {

    void inject(MainActivity activity);

    Context activityContext();

    //List<Accomodation> accomodations();
}
