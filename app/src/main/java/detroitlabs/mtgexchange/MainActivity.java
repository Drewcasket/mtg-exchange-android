package detroitlabs.mtgexchange;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener{

    ListView list;
    public List<Card> card = new LinkedList<Card>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar mActionBar = getActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        list = (ListView) findViewById(R.id.listView);

        Card ArchfiendOfDepravity = new Card();
        ArchfiendOfDepravity.setCardName("Archfiend of Depravity");
        ArchfiendOfDepravity.setCurrentPrice(.49);
        ArchfiendOfDepravity.setChangeInPrice(.00);
//        ArchfiendOfDepravity.setCardID(10L);
//        ArchfiendOfDepravity.setManaCost("3BB");
        ArchfiendOfDepravity.setColors(new ArrayList<String>());
        ArchfiendOfDepravity.getColors().add("Black");
        ArchfiendOfDepravity.setImageURL("http://www.cardshark.com/images/Magic/Fate%20Reforged/391795.jpg");

        Card AtarkaWorldRender = new Card();
        AtarkaWorldRender.setCardName("Atarka, World Render");
        AtarkaWorldRender.setCurrentPrice(.55);
        AtarkaWorldRender.setChangeInPrice(.06);
//        AtarkaWorldRender.setCardID(11L);
//        AtarkaWorldRender.setManaCost("3RR");
        AtarkaWorldRender.setColors(new ArrayList<String>());
        AtarkaWorldRender.getColors().add("Red");
        AtarkaWorldRender.getColors().add("Green");
        AtarkaWorldRender.setImageURL("http://www.cardshark.com/images/Magic/Fate%20Reforged/391796.jpg");

        Card AleshaWhoSmilesAtDeath = new Card();
        AleshaWhoSmilesAtDeath.setCardName("Alesh, Who Smiles at Death");
        AleshaWhoSmilesAtDeath.setCurrentPrice(.99);
        AleshaWhoSmilesAtDeath.setChangeInPrice(-.36);
//        AleshaWhoSmilesAtDeath.setCardID(12L);
//        AleshaWhoSmilesAtDeath.setManaCost("3RR");
        AleshaWhoSmilesAtDeath.setColors(new ArrayList<String>());
        AleshaWhoSmilesAtDeath.getColors().add("Red");
        AleshaWhoSmilesAtDeath.setImageURL("http://www.cardshark.com/images/Magic/Fate%20Reforged/391787.jpg");

        Card WhisperwoodElemental = new Card();
        WhisperwoodElemental.setCardName("Whisperwood Elemental");
        WhisperwoodElemental.setCurrentPrice(15.49);
        WhisperwoodElemental.setChangeInPrice(.49);
//        WhisperwoodElemental.setCardID(13L);
//        WhisperwoodElemental.setManaCost("3GG");
        WhisperwoodElemental.setColors(new ArrayList<String>());
        WhisperwoodElemental.getColors().add("Green");
        WhisperwoodElemental.setImageURL("http://www.cardshark.com/images/Magic/Time%20Spiral/116745.jpg");

        Card SoulfireGrandMaster = new Card();
        SoulfireGrandMaster.setCardName("Soulfire Grand Master");
        SoulfireGrandMaster.setCurrentPrice(13.79);
        SoulfireGrandMaster.setChangeInPrice(-.19);
//        SoulfireGrandMaster.setCardID(14L);
//        SoulfireGrandMaster.setManaCost("1W");
        SoulfireGrandMaster.setColors(new ArrayList<String>());
        SoulfireGrandMaster.getColors().add("White");
        SoulfireGrandMaster.setImageURL("http://www.cardshark.com/images/Magic/Fate%20Reforged/391927.jpg");

        Card TorrentElemental = new Card();
        TorrentElemental.setCardName("Torrent Elemental");
        TorrentElemental.setCurrentPrice(2.95);
        TorrentElemental.setChangeInPrice(.65);
//        TorrentElemental.setCardID(15L);
//        TorrentElemental.setManaCost("4U");
        TorrentElemental.setColors(new ArrayList<String>());
        TorrentElemental.getColors().add("Blue");
        TorrentElemental.setImageURL("http://www.cardshark.com/images/Magic/Fate%20Reforged/391945.jpg");

        Card UginTheSpiritDragon = new Card();
        UginTheSpiritDragon.setCardName("Ugin, the Spirit Dragon");
        UginTheSpiritDragon.setCurrentPrice(35.05);
        UginTheSpiritDragon.setChangeInPrice(4.95);
//        UginTheSpiritDragon.setCardID(16L);
//        UginTheSpiritDragon.setManaCost("8");
        UginTheSpiritDragon.setColors(new ArrayList<String>());
        UginTheSpiritDragon.getColors().add("Artifact");
        UginTheSpiritDragon.setImageURL("http://www.cardshark.com/images/Magic/Fate%20Reforged/391948.jpg");


        for (int x = 1; x <= 10; x++) {
            card.add(ArchfiendOfDepravity);
            card.add(AtarkaWorldRender);
            card.add(AleshaWhoSmilesAtDeath);
            card.add(WhisperwoodElemental);
            card.add(SoulfireGrandMaster);
            card.add(TorrentElemental);
            card.add(UginTheSpiritDragon);
        }

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

//on first load, give empty array, and subsequent loads, use cached data
//        back ground image
        ListAdapter adapter = new ListAdapter(this, card);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent iCardInfo = new Intent(this, CardInfo.class);
        iCardInfo.putExtra("CardObject", card.get(position));
        // get card id from card object and pass through to next screens webservice call
        startActivity(iCardInfo);
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

            } else if (cardColor.equals(new String[]{"White"})) {
                cardBackground = R.drawable.bg_white;
            } else if (cardColor.equals(new String[]{"Black"})) {
                cardBackground = R.drawable.bg_black;
            } else if (cardColor.equals(new String[]{"Green"})) {
                cardBackground = R.drawable.bg_green;
            } else if (cardColor.equals(new String[]{"Blue"})) {
                cardBackground = R.drawable.bg_blue;
            } else if (cardColor.equals(new String[]{"Red"})) {
                cardBackground = R.drawable.bg_red;
            } else if (cardColor.equals(new String[]{"Artifact"})) {
                cardBackground = R.drawable.bg_artifact;
            }
            return cardBackground;
        }
}


