package pedidofacil.com.br.pedidofacil;

import android.app.DownloadManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import pedidofacil.com.br.pedidofacil.network.VolleySingleton;

/**
 * Created by diego on 2/28/16.
 */
//class to display each Fragment and push to Adapter above
public class FragmentInfo extends Fragment {
    private TextView textView;
    public static FragmentInfo getInstance(int position){
        FragmentInfo myFragment = new FragmentInfo();
        Bundle args = new Bundle();
        args.putInt("position",position);
        myFragment.setArguments(args);
        return myFragment;
    }

    @Override
    public View onCreateView (LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState){
        View layout = inflater.inflate(R.layout.fragment_list,container,false);
        textView = (TextView) layout.findViewById(R.id.fragment_position);
        Bundle bundle = getArguments();
        if(bundle!=null){
            textView.setText("The page selected is "+bundle.getInt("position"));
        }
        //Request for server info
        RequestQueue requestQueue = VolleySingleton.getmInstance().getmRequestQueue();
        //String Request to be parser
        //Parameters: type of request, url to request, response to do, error response
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://php.net/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getActivity(),"RESPONSE "+ response,Toast.LENGTH_SHORT).show();
            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),"ERROR " + error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);

        return layout;
    }
}
