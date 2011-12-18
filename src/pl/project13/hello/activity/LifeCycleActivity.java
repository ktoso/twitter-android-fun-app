package pl.project13.hello.activity;

import android.os.Bundle;
import pl.project13.hello.R;
import roboguice.activity.RoboActivity;
import roboguice.util.Ln;

import java.io.Closeable;
import java.io.IOException;

public class LifeCycleActivity extends RoboActivity {

    private Closeable twitterExample;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        Ln.i("onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Ln.i("onStart");
    }



    @Override
    protected void onPause() {
        super.onPause();

        Ln.i("onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        try {
            twitterExample.close();
        } catch (IOException e) {
            Ln.d(e, "Unable to close twitter client");
        }
    }
}
