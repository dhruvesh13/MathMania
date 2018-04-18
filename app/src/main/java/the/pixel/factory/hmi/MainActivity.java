package the.pixel.factory.hmi;

import android.content.Intent;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.igalata.bubblepicker.BubblePickerListener;
import com.igalata.bubblepicker.adapter.BubblePickerAdapter;
import com.igalata.bubblepicker.model.BubbleGradient;
import com.igalata.bubblepicker.model.PickerItem;
import com.igalata.bubblepicker.rendering.BubblePicker;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
BubblePicker picker;
PickerItem newItem;
BubblePickerAdapter adapter;
CountDownTimer timer;
MediaPlayer clickSound_cheer, clickSound_aww;
int score=0;
     TextView equation, displayScore, timerText;
     String mode;
    Random r;
    int sum=0;
    int array[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        mode= i.getStringExtra("mode");
        clickSound_cheer = MediaPlayer.create(this, R.raw.cheering);
        clickSound_aww = MediaPlayer.create(this, R.raw.aww);
        final TypedArray colors = getResources().obtainTypedArray(R.array.colors);
        picker= (BubblePicker) findViewById(R.id.picker);
        equation = (TextView) findViewById(R.id.equation);
        displayScore = (TextView) findViewById(R.id.score);
        timerText = (TextView) findViewById(R.id.timer);

        array = new int[10];
        r= new Random();
        calculate_bubbles();
        newItem = new PickerItem();

        timer = new CountDownTimer(61000, 1000) {

            public void onTick(long millisUntilFinished) {
                timerText.setText("00: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                Intent i = new Intent(MainActivity.this, FinalScore.class);
                i.putExtra("finalScore",displayScore.getText().toString());
                startActivity(i);
            }
        }.start();
        if(mode.equalsIgnoreCase("infinity")) {
            timer.cancel();
        }
        adapter = new BubblePickerAdapter() {
            @Override
            public int getTotalCount() {
                return array.length;
            }

            @NotNull
            @Override
            public PickerItem getItem(int i) {
                PickerItem item = new PickerItem();
                item.setTitle(String.valueOf(array[i%array.length]));
                Log.e("title :", item.getTitle());
                item.setGradient(new BubbleGradient(colors.getColor((i * 2) % 6, 0),
                        colors.getColor((i * 2) % 6 + 1, 0), BubbleGradient.VERTICAL));
                //item.setTypeface(mediumTypeface);
                item.setTextColor(ContextCompat.getColor(MainActivity.this, android.R.color.white));
                item.setTextSize(100);
                return item;
            }
        };

        picker.setAdapter(adapter);
        picker.setBubbleSize(70);
        picker.setMaxSelectedCount(1);


        picker.setListener(new BubblePickerListener() {
            @Override
            public void onBubbleSelected(@NotNull PickerItem item) {
                String check=item.getTitle();
                if(Integer.parseInt(check)==sum){

                    clickSound_cheer.start();
                    Toast.makeText(getApplicationContext(),"Well Done!",Toast.LENGTH_SHORT).show();
                    calculate_bubbles();
                    picker.setVisibility(View.INVISIBLE);
                    score+=10;
                    displayScore.setText(String.valueOf(score));
                    picker.setAdapter(new BubblePickerAdapter() {
                        @Override
                        public int getTotalCount() {
                            return array.length;
                        }

                        @NotNull
                        @Override
                        public PickerItem getItem(int i) {
                            PickerItem item = new PickerItem();
                            item.setTitle(String.valueOf(array[i%array.length]));
                            Log.e("title :", item.getTitle());
                            item.setGradient(new BubbleGradient(colors.getColor((i * 2) % 6, 0),
                                    colors.getColor((i * 2) % 6 + 1, 0), BubbleGradient.VERTICAL));
                            //item.setTypeface(mediumTypeface);
                            item.setTextColor(ContextCompat.getColor(MainActivity.this, android.R.color.white));
                            item.setTextSize(100);
                            return item;
                        }
                    });

                    picker.setVisibility(View.VISIBLE);

                }
                else {
                    clickSound_aww.start();
                    Toast.makeText(getApplicationContext(),"Oh no!",Toast.LENGTH_SHORT).show();
                    calculate_bubbles();
                    if(mode.equalsIgnoreCase("Classic"))
                        score-=5;
                    displayScore.setText(String.valueOf(score));
                    if(score<0)
                        displayScore.setText("0");
                    picker.setVisibility(View.INVISIBLE);
                    picker.setAdapter(new BubblePickerAdapter() {
                        @Override
                        public int getTotalCount() {
                            return array.length;
                        }

                        @NotNull
                        @Override
                        public PickerItem getItem(int i) {
                            PickerItem item = new PickerItem();
                            item.setTitle(String.valueOf(array[i%array.length]));
                            Log.e("title :", item.getTitle());
                            item.setGradient(new BubbleGradient(colors.getColor((i * 2) % 8, 0),
                                    colors.getColor((i * 2) % 8 + 1, 0), BubbleGradient.VERTICAL));
                            //item.setTypeface(mediumTypeface);
                            item.setTextColor(ContextCompat.getColor(MainActivity.this, android.R.color.white));
                            item.setTextSize(100);
                            return item;
                        }
                    });
                    picker.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onBubbleDeselected(@NotNull PickerItem item) {

            }
        });
    }
    void calculate_bubbles()
    {
        int n1= r.nextInt(15);
        int n2= r.nextInt(12);
        equation.setText(String.valueOf(n1)+ " + "+String.valueOf(n2)+ " = ");
         sum=n1+n2;
        Log.e("random numbers: ",String.valueOf(n1)+","+String.valueOf(n2));
        array[0]=sum;
        for(int i=1;i<array.length;i++)
            array[i]=r.nextInt(50);

    }
    @Override
    protected void onResume() {
        super.onResume();
        picker.onResume();
        //equation.setText(String.valueOf(r.nextInt(20))+ " + "+String.valueOf(r.nextInt(20))+ " = ");
        //calculate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        picker.onPause();
       // equation.setText(String.valueOf(r.nextInt(20))+ " + "+String.valueOf(r.nextInt(20))+ " = ");
        //calculate();
    }



}
