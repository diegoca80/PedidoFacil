package pedidofacil.com.br.pedidofacil.network;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import pedidofacil.com.br.pedidofacil.MyApplication;

/**
 * Created by diego on 2/28/16.
 */
public class VolleySingleton {
    private static VolleySingleton mInstance = null;
    private RequestQueue mRequestQueue;
    private VolleySingleton(){
        mRequestQueue = Volley.newRequestQueue(MyApplication.getAppContext());
    }

    public static VolleySingleton getmInstance() {
        if (mInstance == null) {
            mInstance = new VolleySingleton();
        }
        return mInstance;
    }

    public RequestQueue getmRequestQueue(){
        return mRequestQueue;
    }
}
