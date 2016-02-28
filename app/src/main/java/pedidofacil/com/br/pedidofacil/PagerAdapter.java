package pedidofacil.com.br.pedidofacil;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

import java.util.ResourceBundle;

/**
 * Created by diego on 2/28/16.
 */
//Adapter created to show content on each ViewPager of SlidingTab
class PagerAdapter extends FragmentPagerAdapter {
    private Context context;
    //String [] tabText = context.getResources().getStringArray(R.array.tabs);
    String [] tabText;
    int icons[] = {R.drawable.rest_icon,R.drawable.money_icon};
    public PagerAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.context = context;
        tabText=context.getResources().getStringArray(R.array.tabs);
    }

    @Override
    public Fragment getItem(int position) {
        FragmentInfo fragmentInfo = FragmentInfo.getInstance(position);
        return fragmentInfo;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Drawable drawable = context.getResources().getDrawable(icons[position]);
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


}