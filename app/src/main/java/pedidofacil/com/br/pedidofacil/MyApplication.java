package pedidofacil.com.br.pedidofacil;

import android.app.Application;
import android.content.Context;

/**
 * Created by diego on 2/28/16.
 */
//class to send data to VolleySingleton as it requires
public class MyApplication extends Application{
    private static MyApplication myApplication;

    @Override
    public void onCreate(){
        super.onCreate();
        myApplication = this;
    }

    public static MyApplication getMyApplication(){
        return myApplication;
    }
    public static Context getAppContext(){
        return myApplication.getApplicationContext();
    }
}
