package the.pixel.factory.hmi;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class Start extends AppCompatActivity {
Button play;
MediaPlayer clickSound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        play= (Button) findViewById(R.id.button_play);
        Animation a = AnimationUtils.loadAnimation(this, R.anim.scale_animation);
        play.startAnimation(a);
//        clickSound = MediaPlayer.create(this, R.raw.cheering);
//        clickSound.start();
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Start.this, Modes.class);
                startActivity(i);
            }
        });
    }

}
