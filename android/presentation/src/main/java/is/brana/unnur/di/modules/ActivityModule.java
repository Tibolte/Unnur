package is.brana.unnur.di.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import is.brana.unnur.di.Activity;

/**
 * Created by thibaultguegan on 18/05/15.
 */
@Module
public class ActivityModule {

    private final Context mContext;

    public ActivityModule(Context mContext) {

        this.mContext = mContext;
    }

    @Provides
    @Activity
    Context provideActivityContext() {
        return mContext;
    }

}
