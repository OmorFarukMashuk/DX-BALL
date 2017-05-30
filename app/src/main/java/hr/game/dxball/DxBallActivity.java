package hr.game.dxball;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class DxBallActivity extends Activity implements Runnable {
    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new GameCanvas(this));

        run();
    }

    public void run() {
        StageActivity.player = MediaPlayer.create(this, R.raw.dxmusic);
        StageActivity.player.setLooping(true); // Set looping
        StageActivity.player.setVolume(1.0f, 1.0f);

        StageActivity.player.start();
    }
}