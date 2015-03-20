package detroitlabs.mtgexchange;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import detroitlabs.mtgexchange.service.CardsParams;
import detroitlabs.mtgexchange.service.CardsResponse;
import detroitlabs.mtgexchange.service.ExchangeServiceClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

@EActivity
public class MainActivity extends Activity implements AdapterView.OnItemClickListener, Callback<CardsResponse>{

    private static final String TAG = MainActivity.class.getName();
    @Bean
    ExchangeServiceClient serviceClient;

    ListView list;
    boolean isLoading = false;
    private final List<Card> card = new LinkedList<Card>();
    private ListAdapter adapter;
    private CardsParams previousResponse;
    private RelativeLayout footer;


    @Override
    protected void onResume() {
        super.onResume();


//spinner pop while call is being made
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar mActionBar = getActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View customBar = mInflater.inflate(R.layout.action_bar, null);
        TextView title = (TextView) customBar.findViewById(R.id.app_title);
        ImageButton filter = (ImageButton) customBar.findViewById(R.id.app_filter);

        title.setText("MTG Exchange");
        filter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Filter Button Clicked!",
                        Toast.LENGTH_LONG).show();
            }
        });

        mActionBar.setCustomView(customBar);
        mActionBar.setDisplayShowCustomEnabled(true);



        list = (ListView) findViewById(R.id.listView);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        footer = (RelativeLayout) inflater.inflate(R.layout.footer, null);
        list.addFooterView(footer);

        previousResponse = new CardsParams();
        previousResponse.setLimit(10L);


//on first load, give empty array, and subsequent loads, use cached data
//        back ground image
        //add pull to refresh
        this.adapter = new ListAdapter(this, card);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
        list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int i) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.i(TAG, totalItemCount + "");
                int lastIndexInScreen = visibleItemCount + firstVisibleItem;
                if (lastIndexInScreen >= totalItemCount && !isLoading) {
                    loadMore();
                }
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent iCardInfo = new Intent(this, CardInfo.class);
        iCardInfo.putExtra("CardObject", card.get(position));
        startActivity(iCardInfo);
    }





    public void loadMore() {


//        this.adapter = new ListAdapter(this, card);
        Log.i(TAG, "you got hit");
        serviceClient.getCards(previousResponse, this);
        isLoading=true;
        //start spinner

    }

    @Override
    public void success(CardsResponse cardsResponse, Response response) {
        //stop spinner
        this.adapter.addAll(cardsResponse.getCards());
        this.previousResponse=cardsResponse;
        previousResponse.setStart(previousResponse.getStart() + previousResponse.getLimit());
        isLoading=false;

    }

    @Override
    public void failure(RetrofitError error) {
        //dismiss spinner
        //show alert popup with error message
        error.printStackTrace();

    }
}

class ListAdapter extends ArrayAdapter<Card>
  {
    ListAdapter(Context c, List<Card> cards) {
        super(c, R.layout.single_row, cards);
        }

        class ViewHolder {
            TextView cName;
            TextView cValue;
            TextView vChange;
            View vChangeContainer;
            TriangleView vArrowUp;
            TriangleView vArrowDown;
            View cColor;
            RelativeLayout cBackground;


            ViewHolder(View v) {
                cName = (TextView) v.findViewById(R.id.card_name);
                cValue = (TextView) v.findViewById(R.id.card_value);
                vChange = (TextView) v.findViewById(R.id.value_change);
                vArrowUp = (TriangleView) v.findViewById(R.id.value_arrow_up);
                vArrowDown = (TriangleView) v.findViewById(R.id.value_arrow_down);
                cColor = (View) v.findViewById(R.id.card_color);
                cBackground = (RelativeLayout) v.findViewById(R.id.card_background);
                vChangeContainer = v.findViewById(R.id.valueChangeContainer);

            }
        }
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            ViewHolder holder = null;

            if (row == null) {
                LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.single_row, parent, false);
                holder = new ViewHolder(row);
                row.setTag(holder);
            } else {
                holder = (ViewHolder) row.getTag();
            }

            List<String> cardColor = getItem(position).getColors();
            String sideColor = getSideColor(cardColor);
            int cardBackground = getCardBackground(cardColor);
            double valueChange = getItem(position).getChangeInPrice();
            holder.cName.setText(getItem(position).getCardName());
            holder.cValue.setText("$" + (String.valueOf(getItem(position).getCurrentPrice())));
            holder.vChange.setText(String.valueOf(getItem(position).getChangeInPrice()));
            holder.vChange.setTextColor(getValueChangeTextColor(valueChange));
            holder.vChangeContainer.setBackgroundResource(getValueChangeColor(valueChange));
            holder.cColor.setBackgroundColor(Color.parseColor(sideColor));
            holder.cBackground.setBackgroundResource(cardBackground);
            if (valueChange < 0) {
                holder.vArrowDown.setVisibility(View.VISIBLE);
                holder.vArrowUp.setVisibility(View.GONE);
                holder.vArrowDown.setArrowGreen(false);
            } else {
                holder.vArrowDown.setVisibility(View.GONE);
                holder.vArrowUp.setVisibility(View.VISIBLE);
                holder.vArrowDown.setArrowGreen(true);
            }

            return row;
        }


        public int getValueChangeColor(double valueChange) {
            return valueChange < 0 ? R.drawable.drawable_value_change_down_background : R.drawable.drawable_value_change_up_background;
        }

        public int getValueChangeTextColor(double valueChange) {
            return getContext().getResources().getColor(valueChange < 0 ? R.color.value_change_text_red : R.color.value_change_text_green);
        }

        public String getSideColor(List<String> cardColor) {

            String sideColor = "#FFFFFF";

            if (cardColor.size() > 1) {
                sideColor = "#C7B164";
            } else if (cardColor.contains("White")) {
                sideColor = "#E6E1CE";
            } else if (cardColor.contains("Black")) {
                sideColor = "#666563";
            } else if (cardColor.contains("Green")) {
                sideColor = "#5C8462";
            } else if (cardColor.contains("Blue")) {
                sideColor = "#4EAED8";
            } else if (cardColor.contains("Red")) {
                sideColor = "#FD5139";
            } else if (cardColor.contains("Artifact")) {
                sideColor = "#8B97A3";
            }

            return sideColor;
        }

        public int getCardBackground(List<String> cardColor) {

            int cardBackground = R.drawable.bg_white;

            if (cardColor.size() > 1) {
                cardBackground = R.drawable.bg_gold;

            } else if (cardColor.contains("White")) {
                cardBackground = R.drawable.bg_white;
            } else if (cardColor.contains("Black")) {
                cardBackground = R.drawable.bg_black;
            } else if (cardColor.contains("Green")) {
                cardBackground = R.drawable.bg_green;
            } else if (cardColor.contains("Blue")) {
                cardBackground = R.drawable.bg_blue;
            } else if (cardColor.contains("Red")) {
                cardBackground = R.drawable.bg_red;
            } else if (cardColor.contains("Artifact")) {
                cardBackground = R.drawable.bg_artifact;
            }
            return cardBackground;
        }

}


