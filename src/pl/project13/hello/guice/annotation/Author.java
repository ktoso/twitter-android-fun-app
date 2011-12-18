package pl.project13.hello.guice.annotation;

import com.google.inject.BindingAnnotation;

import java.lang.annotation.*;

@Documented
@BindingAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface  Author {
}
