package pl.project13.hello.activity;

import android.os.Bundle;
import android.widget.TextView;
import com.google.inject.Inject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import pl.project13.hello.test.InjectingTestRunner;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(InjectingTestRunner.class)
public class MainActivityTest {

    @Inject
    MainActivity mainActivity;

    @Before
    public void setUp() throws Exception {
        mainActivity.onCreate(Bundle.EMPTY);
    }

    @Test
    public void should_haveHelloWorldMessage() throws Exception {
        // given
        String expectedText = "Hello Warszawa!";

        // when
        TextView hello = mainActivity.hello;
        String text = hello.getText().toString();

        // then
        assertThat(text).contains(expectedText);
    }


}
