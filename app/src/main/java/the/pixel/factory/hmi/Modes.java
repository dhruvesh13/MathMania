package the.pixel.factory.hmi;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Modes extends AppCompatActivity implements View.OnClickListener{
LinearLayout classic, rush, infinity;
MediaPlayer clickSound, clickSound2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modes);
        classic = (LinearLayout) findViewById(R.id.classic);
        rush = (LinearLayout) findViewById(R.id.rush);
        infinity = (LinearLayout) findViewById(R.id.infinity);
        classic.setOnClickListener(this);
        rush.setOnClickListener(this);
        infinity.setOnClickListener(this);
        clickSound2 = MediaPlayer.create(this, R.raw.intro_bgm);
    }

    @Override
    public void onClick(View view) {
            clickSound2.stop();
            clickSound2.release();
            clickSound2=null;

        if (view.getId() == R.id.classic)
        {
            Intent i = new Intent(Modes.this, MainActivity.class);
            i.putExtra("mode", "classic");
//            clickSound = MediaPlayer.create(this, R.raw.classic_bgm);
//            clickSound.start();
            startActivity(i);
        }
        else if (view.getId() == R.id.rush)
        {

            Intent i = new Intent(Modes.this, MainActivity.class);
            i.putExtra("mode", "rush");
//            clickSound = MediaPlayer.create(this, R.raw.rush_bgm);
//            clickSound.start();
            startActivity(i);
        }
        else if (view.getId() == R.id.infinity)
        {

            Intent i = new Intent(Modes.this, MainActivity.class);
            i.putExtra("mode", "infinity");
//            clickSound = MediaPlayer.create(this, R.raw.classic_bgm);
//            clickSound.start();
            startActivity(i);
        }
    }


}
