package pl.project13.hello.test;

import com.google.inject.Stage;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface GuiceStage {
    Stage value();
}