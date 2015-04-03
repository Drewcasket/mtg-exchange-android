package detroitlabs.mtgexchange;

import android.app.ActionBar;
import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by Drew on 2/4/15.
 */
public class CardInfoActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onStart() {
        super.onStart();
        Card CardInfo = getIntent().getParcelableExtra("CardObject");

        TextView cValue;
        TextView vChange;
        TriangleView vArrowUp;
        TriangleView vArrowDown;
        View vChangeContainer;
        ImageView cImage;
        final ImageView zoomedImage;
        double valueChange = CardInfo.getChangeInPrice();


        cValue = (TextView) findViewById(R.id.card_value);
        vChange= (TextView) findViewById(R.id.value_change);
        vChangeContainer =  findViewById(R.id.valueChangeContainer);
        vArrowUp= (TriangleView) findViewById(R.id.value_arrow_up);
        vArrowDown= (TriangleView) findViewById(R.id.value_arrow_down);
        cImage = (ImageView) findViewById(R.id.card_picture);
        zoomedImage = (ImageView) findViewById(R.id.zoomed_card);
        cImage.setImageResource(R.drawable.cardtest);
        zoomedImage.setImageResource(R.drawable.cardtest);
        zoomedImage.setVisibility(View.GONE);
        cImage.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            System.out.println("Clicked Image");
            zoomedImage.setVisibility(View.VISIBLE);
            zoomedImage.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    zoomedImage.setVisibility(View.GONE);
                }
            });
        }
    });


        cValue.setText("$" + (String.valueOf(CardInfo.getCurrentPrice())));
        vChange.setText(String.valueOf(CardInfo.getChangeInPrice()));
        vChange.setTextColor(getValueChangeTextColor(CardInfo.getChangeInPrice()));
        vChangeContainer.setBackgroundResource(getValueChangeColor(valueChange));
        if (valueChange<0) {
            vArrowDown.setVisibility(View.VISIBLE);
            vArrowUp.setVisibility(View.GONE);
            vArrowDown.setArrowGreen(false);
        }
        else {
            vArrowDown.setVisibility(View.GONE);
            vArrowUp.setVisibility(View.VISIBLE);
            vArrowDown.setArrowGreen(true);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.card_info);



//        new DownloadImageTask((ImageView) findViewById(R.id.card_picture))
//                .execute(CardInfo.getImageURL());


//        ActionBar mActionBar = getActionBar();
//        mActionBar.setDisplayShowHomeEnabled(false);
//        mActionBar.setDisplayShowTitleEnabled(false);
//        LayoutInflater mInflater = LayoutInflater.from(this);

        final CardInfoActivity _this = this;

//        View customBar = mInflater.inflate(R.layout.action_bar, null);
//        TextView title = (TextView) customBar.findViewById(R.id.app_title);
//        ImageButton filter = (ImageButton) customBar.findViewById(R.id.app_filter);
//        filter.setVisibility(View.INVISIBLE);
//        mActionBar.setCustomView(customBar);
//        mActionBar.setDisplayShowCustomEnabled(true);
    }

    public int getValueChangeColor(double valueChange) {
        return valueChange<0?R.drawable.drawable_value_change_down_background:R.drawable.drawable_value_change_up_background;
    }

    public int getValueChangeTextColor(double valueChange) {
        return getResources().getColor(valueChange < 0 ? R.color.value_change_text_red : R.color.value_change_text_green);
    }

    @Override
    public void onClick(View view) {
    }


//    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
//        ImageView bmImage;
//
//        public DownloadImageTask(ImageView bmImage) {
//            this.bmImage = bmImage;
//        }
//
//        protected Bitmap doInBackground(String... urls) {
//            String urldisplay = urls[0];
//            Bitmap mIcon11 = null;
//            try {
//                InputStream in = new java.net.URL(urldisplay).openStream();
//                mIcon11 = BitmapFactory.decodeStream(in);
//            } catch (Exception e) {
//                Log.e("Error", e.getMessage());
//                e.printStackTrace();
//            }
//            return mIcon11;
//        }
//
//        protected void onPostExecute(Bitmap result) {
//            bmImage.setImageBitmap(result);
//        }
//   }


}


