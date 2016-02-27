package pedidofacil.com.br.pedidofacil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.facebook.FacebookSdk;
import com.facebook.Profile;

import java.util.logging.LogRecord;

public class SplashActivity extends AppCompatActivity implements Runnable{

    private Profile profile;
    private boolean state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //require for facebook SDK use
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_splash);
        state = false;
        profile = Profile.getCurrentProfile().getCurrentProfile();
        if (profile != null) {
            // user has logged in
            state = true;
        }

        Handler handler = new Handler();
        handler.postDelayed(this,4000);

    }

    @Override
    public void run() {
        if(state==true)
            startActivity(new Intent(this,ContentActivity.class));
        startActivity(new Intent(this,LoginActivity.class));


        finish();
    }
}
