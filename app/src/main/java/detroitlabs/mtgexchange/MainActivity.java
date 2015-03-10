package detroitlabs.mtgexchange;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Drew on 2/4/15.
 */
public class MainActivity extends Activity implements AdapterView.OnItemClickListener{

    ListView list;
    public List<Card> card = new LinkedList<Card>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list= (ListView) findViewById(R.id.listView);


        Card dragonBlood = new Card();
        dragonBlood.setCardName("DragonBlood");
        dragonBlood.setCardValue(2329.32);
        dragonBlood.setValueChange(-.45);
        dragonBlood.setCardID(13L);
        dragonBlood.setManaCost("2BB");
        dragonBlood.setCardColor(new String[] {"Black"});

        Card AleshaWhoSmilesAtDeath = new Card();
        AleshaWhoSmilesAtDeath.setCardName("Alesh, Who Smiles at Death");
        AleshaWhoSmilesAtDeath.setCardValue(8.32);
        AleshaWhoSmilesAtDeath.setValueChange(1.45);
        AleshaWhoSmilesAtDeath.setCardID(49L);
        AleshaWhoSmilesAtDeath.setManaCost("3RR");
        AleshaWhoSmilesAtDeath.setCardColor(new String[] {"Red"});

        Card BrainnRefractor = new Card();
        BrainnRefractor.setCardName("Brain Refractor");
        BrainnRefractor.setCardValue(8.32);
        BrainnRefractor.setValueChange(1.45);
        BrainnRefractor.setCardID(49L);
        BrainnRefractor.setManaCost("3RR");
        BrainnRefractor.setCardColor(new String[] {"Red", "Green"});

        for (int x=1; x <= 10;x++)
        {
        card.add(dragonBlood);
        card.add(AleshaWhoSmilesAtDeath);
        card.add(BrainnRefractor);
        }

// on first load, give empty array, and subsequent loads, use cached data
        //back ground image
        ListAdapter adapter=new ListAdapter(this, card);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent iCardInfo = new Intent(this, CardInfo.class);
        iCardInfo.putExtra("CardObject", card.get(position));
        // get card id from card object and pass through to next screens webservice call
//        iCompany.putExtra(ComicReleaseListActivity.COMPANY_LOGO, images[position]);
//        iCompany.putExtra(ComicReleaseListActivity.COMPANY_TITLE, publisherTitles[position]);
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
        View vArrow;
        RelativeLayout cBackground;

        ViewHolder(View v) {
            cName= (TextView) v.findViewById(R.id.card_name);
            cValue= (TextView) v.findViewById(R.id.card_value);
            vChange= (TextView) v.findViewById(R.id.value_change);
            vArrowUp= (TriangleView) v.findViewById(R.id.value_arrow_up);
            vArrowDown= (TriangleView) v.findViewById(R.id.value_arrow_down);
            cColor= (View) v.findViewById(R.id.card_color);
            cBackground= (RelativeLayout) v.findViewById(R.id.card_background);
            vChangeContainer = v.findViewById(R.id.valueChangeContainer);
        }
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        ViewHolder holder=null;
        if (row==null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_row, parent, false);
            holder=new ViewHolder(row);
            row.setTag(holder);
        }
        else{
            holder= (ViewHolder) row.getTag();
        }

        String [] cardColor = getItem(position).getCardColor();
        String sideColor = getSideColor(cardColor);
        int cardBackground = getCardBackground(cardColor);
        double valueChange = getItem(position).getValueChange();
        holder.cName.setText(getItem(position).getCardName());
        holder.cValue.setText(String.valueOf(getItem(position).getCardValue()));
        holder.vChange.setText(String.valueOf(getItem(position).getValueChange()));
        holder.vChange.setTextColor(getValueChangeTextColor(valueChange));
        holder.vChangeContainer.setBackgroundResource(getValueChangeColor(valueChange));
        holder.cColor.setBackgroundColor(Color.parseColor(sideColor));
        holder.cBackground.setBackgroundResource(cardBackground);
        if (valueChange<0) {
            holder.vArrowDown.setVisibility(View.VISIBLE);
            holder.vArrowUp.setVisibility(View.GONE);
            holder.vArrowDown.setArrowGreen(false);
        }
        else {
            holder.vArrowDown.setVisibility(View.GONE);
            holder.vArrowUp.setVisibility(View.VISIBLE);
            holder.vArrowDown.setArrowGreen(true);
        }

        return row;
    }
    public int getValueChangeColor(double valueChange) {
        return valueChange<0?R.drawable.drawable_value_change_down_background:R.drawable.drawable_value_change_up_background;
    }
    public int getValueChangeTextColor(double valueChange) {
        return getContext().getResources().getColor(valueChange<0? R.color.value_change_text_red:R.color.value_change_text_green);
    }

    public String getSideColor(String[] cardColor) {

        String sideColor = "#FFFFFF";

        if (cardColor.length > 1) {
            sideColor = "#C7B164";
        }
        else if (cardColor.equals(new String[] {"White"})) {
            sideColor = "#E6E1CE";
        }
        else if (cardColor.equals(new String[] {"Black"})) {
            sideColor = "#666563";
        }
        else if (cardColor.equals(new String[] {"Green"})) {
            sideColor = "#5C8462";
        }
        else if (cardColor.equals(new String[] {"Blue"})) {
            sideColor = "#4EAED8";
        }
        else if (cardColor.equals(new String[] {"Red"})) {
            sideColor = "#FD5139";
        }
        else if (cardColor.equals(new String[] {"Artifact"})) {
            sideColor = "#8B97A3";
        }

        return sideColor;
    }

    public int getCardBackground(String[] cardColor) {

        int cardBackground = R.drawable.bg_black;

        if (cardColor.length > 1) {
            cardBackground = R.drawable.bg_gold;

        }

        else if (cardColor.equals(new String[] {"White"})) {
            cardBackground = R.drawable.bg_white;
        }
        else if (cardColor.equals(new String[] {"Black"})) {
            cardBackground = R.drawable.bg_black;
        }
        else if (cardColor.equals(new String[] {"Green"})) {
            cardBackground = R.drawable.bg_green;
        }
        else if (cardColor.equals(new String[] {"Blue"})) {
            cardBackground = R.drawable.bg_blue;
        }
        else if (cardColor.equals(new String[] {"Red"})) {
            cardBackground = R.drawable.bg_red;
        }
        else if (cardColor.equals(new String[] {"Artifact"})) {
            cardBackground = R.drawable.bg_artifact;
        }
        return cardBackground;
    }

}