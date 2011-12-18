package pl.compendium.hello.test;

import com.google.inject.Module;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface GuiceModules {
    Class<? extends Module>[] value();
}