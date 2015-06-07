package es.com.jagarciavi.verde;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Verde";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.verde);
        final Button play_button = (Button)this.findViewById(R.id.verde);

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                Handler mHandler = new Handler();

                mHandler.postDelayed(new Runnable() {
                    public void run() {
                        play_button.setBackgroundResource(android.R.drawable.btn_default);
                        play_button.setText("¡VERDE!");
                        play_button.setEnabled(true);
                    }
                }, 3000);
            }

        });


        play_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                play_button.setEnabled(false);
                play_button.setText("");
                play_button.setBackgroundResource(R.drawable.verdeb);
                Log.v(TAG, "Playing sound...");
                mp.start();
            }
        });
        Log.v(TAG, "Sounds initialized.");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
