package pl.compendium.hello.guice;

import com.google.inject.Module;
import roboguice.application.RoboApplication;

import java.util.List;

//@ReportsCrashes(formKey = "mymagicgoogleformkey",
//                mode = ReportingInteractionMode.SILENT
//)
public class CompendiumApplication extends RoboApplication {

//    @Override
//    public void onCreate() {
//        ACRA.init(this);
//
//        super.onCreate();
//    }

    @Override
    protected void addApplicationModules(List<Module> modules) {
        modules.add(new TwitterModule());
    }

}
