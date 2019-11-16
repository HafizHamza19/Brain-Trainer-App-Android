package com.example.hafizhamza.braintrainerapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int right=0;
    int NoofQuestion=0;
    int CorrectAns;
    CountDownTimer countDownTimer;
    TextView MySum;
    TextView Answer;
    TextView Score;
    TextView Time;
    GridLayout gridLayout;

    ArrayList<Integer> AnswerarrayList=new ArrayList<Integer>();
    Random random=new Random();

    Button Mybutton;
    Button Mybutton1;
    Button Mybutton2;
    Button Mybutton3;
    Button Mybutton4;
    Button PlayAgainButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Mybutton1=(Button)findViewById(R.id.button1);
        Mybutton2=(Button)findViewById(R.id.button2);
        Mybutton3=(Button)findViewById(R.id.button3);
        Mybutton4=(Button)findViewById(R.id.button4);
        Mybutton=(Button)findViewById(R.id.gobutton);
        MySum=(TextView)findViewById(R.id.SumtextView);
        PlayAgainButton=(Button)findViewById(R.id.PlayAgainbutton);
        gridLayout=(GridLayout)findViewById(R.id.MyGridView);
        Answer=(TextView)findViewById(R.id.AnswertextView);
        Score=(TextView)findViewById(R.id.Scoretextview);
        Time=(TextView)findViewById(R.id.TimetextView);


    }

    public void Go(View view) {
        Mybutton.setVisibility(View.INVISIBLE);
        visible();
       Question();
        timer();
    }

    public void ChoseAnswer(View view) {
        Answer.setVisibility(View.VISIBLE);
       if (Integer.toString(CorrectAns).equals(view.getTag().toString()))
        {
            right++;
            Answer.setText("Correct!");

            Question();
        }
        else{
            Answer.setText("Wrong :(");
           Question();
        }
        NoofQuestion++;
        Score.setText(right+"/"+NoofQuestion);
     }
    public void seconds(int l)
    {
        Time.setText(Integer.toString(l)+"s");
    }
    public void timer()
    {
        countDownTimer=new CountDownTimer(15100,1000) {
            @Override
            public void onTick(long l) {
                seconds((int) l/1000);
            }

            @Override
            public void onFinish() {
                Toast toast=Toast.makeText(getApplicationContext(),"Time Is Up",Toast.LENGTH_SHORT);
                toast.show();
                MediaPlayer mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.timeout);
                mediaPlayer.start();
                Answer.setText("Done!");
                PlayAgainButton.setVisibility(View.VISIBLE);
                gridLayout.setVisibility(View.INVISIBLE);
            }
        }.start();
    }
    public void Question()
    {
        int a=random.nextInt(21);
        int b=random.nextInt(21);
        int sum=a+b;
        MySum.setText(String.valueOf(a)+"+"+String.valueOf(b));
        CorrectAns=random.nextInt(4);
        AnswerarrayList.clear();
        for (int i=0;i<4;i++)
        {
            if (CorrectAns==i)
            {
                CorrectAns=i;
                AnswerarrayList.add(sum);
            }
            else
            {
                int wrongAns=random.nextInt(41);
                while (wrongAns==sum)
                {
                    wrongAns=random.nextInt(41);
                }
                AnswerarrayList.add(wrongAns);
            }
        }
        Mybutton1.setText(Integer.toString(AnswerarrayList.get(0)));
        Mybutton2.setText(Integer.toString(AnswerarrayList.get(1)));
        Mybutton3.setText(Integer.toString(AnswerarrayList.get(2)));
        Mybutton4.setText(Integer.toString(AnswerarrayList.get(3)));

    }

    public void PlayAgain(View view) {
        PlayAgainButton.setVisibility(View.INVISIBLE);
        Answer.setVisibility(View.INVISIBLE);
        right=0;
        NoofQuestion=0;
        Question();
        timer();
        gridLayout.setVisibility(View.VISIBLE);
        Score.setText(right+"/"+NoofQuestion);
    }
    public void visible()
    {
        Time.setVisibility(View.VISIBLE);
        MySum.setVisibility(View.VISIBLE);
        Score.setVisibility(View.VISIBLE);
        gridLayout.setVisibility(View.VISIBLE);

    }
}
