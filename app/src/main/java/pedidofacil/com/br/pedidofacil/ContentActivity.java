package pedidofacil.com.br.pedidofacil;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import pedidofacil.com.br.pedidofacil.tabs.SlidingTabLayout;

public class ContentActivity extends AppCompatActivity {
    private Button exit_button;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ViewPager mPager;
    private SlidingTabLayout mTabs;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_content);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        //Related to Facebook Log Out
        /*
        exit_button = (Button) findViewById(R.id.exit_button);
        final Intent intent = new Intent(this, SplashActivity.class);
        exit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                startActivity(intent);
            }
        });
        */
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        //Related to SlidingTabs
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(),this);
        mPager = (ViewPager) findViewById(R.id.viewPager);
        mPager.setAdapter(new PagerAdapter(getSupportFragmentManager(),this));
        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
        //setting custom tab view for image icons.Note that is necessary textView
        mTabs.setDistributeEvenly(true);
        mTabs.setCustomTabView(R.layout.custom_tab_view, R.id.tabText);
        mTabs.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
        mTabs.setSelectedIndicatorColors(ContextCompat.getColor(getApplicationContext(), R.color.black_overlay));
        mTabs.setViewPager(mPager);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        NavigationDrawerFragment navigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        navigationDrawerFragment.setUp(R.id.fragment_navigation_drawer, drawerLayout, toolbar);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_LONG).show();
            return true;
        }

        if (id == R.id.navigate) {
            startActivity(new Intent(this, SubActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Content Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://pedidofacil.com.br.pedidofacil/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Content Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://pedidofacil.com.br.pedidofacil/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    /*//Adapter created to show content on each ViewPager of SlidingTab
    class MyPagerAdapter extends FragmentPagerAdapter{
        String [] tabText = getResources().getStringArray(R.array.tabs);
        int icons[] = {R.drawable.rest_icon,R.drawable.money_icon};
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            tabText=getResources().getStringArray(R.array.tabs);
        }

        @Override
        public Fragment getItem(int position) {
            FragmentInfo fragmentInfo = FragmentInfo.getInstance(position);
            return fragmentInfo;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Drawable drawable = getResources().getDrawable(icons[position]);
            if(position==0) {
                drawable.setBounds(0, 0, 100, 100);
            }
            else{
                drawable.setBounds(0, 0, 75, 75);
            }//As just works with TextViews, the ImageView has to be set inside a textView using ImageSpan class
            ImageSpan imageSpan = new ImageSpan(drawable);
            //SpannableString does not work with empty space. Important
            SpannableString spannableString = new SpannableString(" ");
            spannableString.setSpan(imageSpan,0,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return spannableString;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }*/
    /*//class to display each Fragment and push to Adapter above
    public static class MyFragment extends Fragment{
        private TextView textView;
        public static MyFragment getInstance(int position){
            MyFragment myFragment = new MyFragment();
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
            return layout;
        }
    }*/
}
