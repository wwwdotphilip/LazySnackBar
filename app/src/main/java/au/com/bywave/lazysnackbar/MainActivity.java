package au.com.bywave.lazysnackbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import au.com.bywave.snacky.Duration;
import au.com.bywave.snacky.Snacky;
import au.com.bywave.snacky.Type;

public class MainActivity extends AppCompatActivity {
    private Snacky snacky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        snacky = new Snacky(this, (ViewGroup) findViewById(android.R.id.content));
        snacky.addAction("Action", new Snacky.Action() {
            @Override
            public void onClick() {
                Toast.makeText(MainActivity.this, "Button pressed", Toast.LENGTH_LONG).show();
            }
        });
        snacky.setSlideSpeed(200);
        snacky.type(Type.SUCCESS);
        snacky.setDuration(Duration.SHORT);
        snacky.addMessage("The quick brown fox jumps over the lazy dog.");
        snacky.addMessage("This is another text for testing");
        snacky.show();
    }

    public void showHide(View view){
        if (snacky.isShowing()){
            snacky.hide();
        } else {
            snacky.show();
        }
    }
}
