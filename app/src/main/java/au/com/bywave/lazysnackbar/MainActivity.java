package au.com.bywave.lazysnackbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
        snacky = (Snacky) findViewById(R.id.snacky);
        snacky.addAction("Action", new Snacky.Action() {
            @Override
            public void onClick() {
                Toast.makeText(MainActivity.this, "Button pressed", Toast.LENGTH_LONG).show();
            }
        });
        snacky.type(Type.UPDATE);
        snacky.setDuration(Duration.SHORT);
        snacky.setMessage("The quick brown fox jumps over the lazy dog.");
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
