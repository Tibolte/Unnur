package is.brana.unnur.di.components;

import android.content.Context;

import dagger.Component;
import is.brana.unnur.di.Activity;
import is.brana.unnur.di.modules.ActivityModule;

/**
 * Created by thibaultguegan on 18/05/15.
 */
@Activity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Context context();
}
