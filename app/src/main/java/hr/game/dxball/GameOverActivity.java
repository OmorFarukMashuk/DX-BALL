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
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Mashuk on 9/2/16.
 */
public class GameOverActivity extends Activity{

    //MediaPlayer player;
    Button main;

    TextView txt;

    int score;
    public GameOverActivity(){

    }


    public GameOverActivity(int score){
        this.score=score;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.message);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.txt = (TextView)this.findViewById((R.id.Message));
        //String msg =
        txt.setText(String.valueOf("Game Over."));

        StageActivity.player.stop();


    }


    public void StartStageActivity(View v){
        this.main = (Button)findViewById(R.id.ToStage);
        Intent i = new Intent(GameOverActivity.this,StageActivity.class);
        //GameCanvas.stageNo=2;
        //GameCanvas.num_of_brick=20;
        startActivity(i);

    }

}
