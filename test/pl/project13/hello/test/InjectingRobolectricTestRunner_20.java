//package pl.project13.hello.test;
//
//import com.google.inject.Injector;
//import com.google.inject.Module;
//import com.google.inject.Stage;
//import com.google.inject.util.Modules;
//import com.xtremelabs.robolectric.Robolectric;
//import com.xtremelabs.robolectric.RobolectricTestRunner;
//import org.junit.runners.model.InitializationError;
//import roboguice.RoboGuice;
//import roboguice.inject.ContextScope;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class InjectingRobolectricTestRunner_20 extends RobolectricTestRunner {
//
//    public InjectingRobolectricTestRunner_20(Class<?> testClass) throws InitializationError {
//        super(testClass);
//    }
//
//    @Override
//    public void prepareTest(Object test) {
//        Injector injector = loadModulesFor(test);
////        enterContextScope(injector);
//
////        RoboGuice.injectMembers(Robolectric.application, test);
//        injector.injectMembers(test);
//    }
//
//    private void enterContextScope(Injector injector) {
//        ContextScope contextScope = injector.getInstance(ContextScope.class);
//        contextScope.enter(Robolectric.application);
//    }
//
//    private Injector loadModulesFor(Object test) {
//        Class<? extends Object> testClass = test.getClass();
//
//        boolean hasModules = testClass.isAnnotationPresent(GuiceModules.class);
//        if (hasModules) {
//            GuiceModules modulesAnnotation = testClass.getAnnotation(GuiceModules.class);
//            Module module = createCombinedModule(modulesAnnotation);
//
//            Stage stage= RoboGuice.DEFAULT_STAGE;
//            boolean hasStage = testClass.isAnnotationPresent(GuiceStage.class);
//            if(hasStage) {
//                stage = getRequiredStage(testClass);
//            }
//
//            RoboGuice.setBaseApplicationInjector(Robolectric.application, stage, module);
//        }
//
//        return RoboGuice.getBaseApplicationInjector(Robolectric.application);
//    }
//
//    private Stage getRequiredStage(Class<? extends Object> testClass) {
//        GuiceStage stageAnnotation = testClass.getAnnotation(GuiceStage.class);
//
//        @SuppressWarnings("UnnecessaryLocalVariable")
//        Stage stage = stageAnnotation.value();
//        return stage;
//    }
//
//    private Module createCombinedModule(GuiceModules modulesAnnotation) {
//        Class<? extends Module>[] moduleClasses = modulesAnnotation.value();
//
//        List<Module> modules = new ArrayList<Module>();
//        for (Class<? extends Module> moduleClass : moduleClasses) {
//            try {
//                Module module = moduleClass.newInstance();
//                modules.add(module);
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InstantiationException e) {
//                e.printStackTrace();
//            }
//        }
//
//        @SuppressWarnings("UnnecessaryLocalVariable")
//        Module combinedModule = Modules.override(RoboGuice.newDefaultRoboModule(Robolectric.application)).with(modules);
//        return combinedModule;
//    }
//}
