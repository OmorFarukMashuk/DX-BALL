package hr.game.dxball;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by Mashuk on 9/2/16.
 */
public class StageActivity extends Activity {

    public static MediaPlayer player;

    Button st1btn;
    Button st2btn;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        player = MediaPlayer.create(this, R.raw.dxmusic);
        setContentView(R.layout.stage);
    }
    @Override
    protected void onResume() {
        super.onResume();

        player.stop();
    }



    @Override
    protected void onStart() {
        super.onStart();


        Button btn = (Button) findViewById(R.id.St2btn);
        try {
            File myFile = new File(getFilesDir(),"stage2.txt");
            if(myFile.exists()) {

                //check whether stage 2 is unlocked or not
                FileInputStream fis = openFileInput("stage2.txt");
                Log.d("state", "file opened");
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader bufferedReader = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }

                Log.d("file", sb.toString());
                if (sb.toString().equals("unlocked")) {
                    btn.setEnabled(true);
                }
            }




        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }



    public void StartStage1(View v){
        this.st1btn = (Button)findViewById(R.id.St1btn);
        Intent i = new Intent(StageActivity.this,DxBallActivity.class);
        GameCanvas.stageNo=1;
        GameCanvas.num_of_brick=30;
        startActivity(i);

    }
    public void StartStage2(View v){
        this.st2btn = (Button)findViewById(R.id.St2btn);
        Intent i = new Intent(StageActivity.this,DxBallActivity.class);
        GameCanvas.stageNo=2;
        GameCanvas.num_of_brick=50;
        startActivity(i);

    }


}
