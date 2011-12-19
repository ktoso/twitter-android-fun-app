package pl.compendium.hello.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;
import com.google.inject.Inject;
import pl.compendium.hello.R;
import pl.compendium.hello.guice.annotation.Author;
import pl.compendium.hello.guice.annotation.Slow;
import pl.compendium.hello.twitter.Twitter;
import pl.compendium.hello.twitter.model.Tweet;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import roboguice.util.Ln;

import java.util.List;

public class MainActivity extends RoboActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        
    }

    @Override
    public void onClick(View view) {
        
    }

}
