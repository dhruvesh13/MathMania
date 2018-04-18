package the.pixel.factory.hmi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinalScore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score);
        TextView score= (TextView) findViewById(R.id.score);
        Intent i= getIntent();
        score.setText(i.getStringExtra("finalScore"));
        TextView b= (TextView) findViewById(R.id.playAgain);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FinalScore.this, Modes.class));
                finish();
            }
        });
    }
}
