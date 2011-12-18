package pl.project13.hello.test;

import com.google.inject.Injector;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.runners.model.InitializationError;
import pl.project13.hello.guice.CompendiumApplication;
import roboguice.inject.ContextScope;

public class InjectingTestRunner extends RobolectricTestRunner {

    public InjectingTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    @Override
    public void prepareTest(Object test) {
        CompendiumApplication application = (CompendiumApplication) Robolectric.application;

        Injector injector = application.getInjector();

        ContextScope scope = injector.getInstance(ContextScope.class);
        scope.enter(Robolectric.application);

        injector.injectMembers(test);
    }
}
