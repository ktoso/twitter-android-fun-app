package pl.compendium.hello.guice.annotation;

import com.google.inject.BindingAnnotation;

import java.lang.annotation.*;

@Documented
@BindingAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface TAG {
}
