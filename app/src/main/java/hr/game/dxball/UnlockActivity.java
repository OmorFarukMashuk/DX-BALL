package hr.game.dxball;

import android.app.Activity;
import android.content.Intent;
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
public class UnlockActivity extends Activity{


    TextView txt;
    Button main;
    //public static String msg;
    //public String stg2status = "";

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
        txt.setText(String.valueOf("Congratulation!You have unlocked Stage 2. Please Go to MAIN"));


        //txt = (TextView)findViewById(R.id.Message);

        //Log.d("msg",msg.toString());


        File myFile = new File(getFilesDir(),"stage2.txt");
        FileOutputStream fileOutputStream = null;

        try
        {


            fileOutputStream = new FileOutputStream(myFile,false);

            fileOutputStream.write("unlocked".getBytes());
            //stg2status="unlocked";
            //Log.d("unlocked","stage2");



            //fileOutputStream.write("\n".getBytes());
            //Toast.makeText(getApplicationContext(),"saved dir: " + myFile.getAbsolutePath(),Toast.LENGTH_LONG).show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        StageActivity.player.stop();

    }


    public void StartStageActivity(View v){
        this.main = (Button)findViewById(R.id.ToStage);
        Intent i = new Intent(UnlockActivity.this,StageActivity.class);
        //GameCanvas.stageNo=2;
        //GameCanvas.num_of_brick=20;
        startActivity(i);

    }

}
