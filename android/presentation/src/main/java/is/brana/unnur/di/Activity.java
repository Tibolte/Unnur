package is.brana.unnur.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by thibaultguegan on 18/05/15.
 */
@Scope
@Retention(RUNTIME)
public @interface Activity {}
