package detroitlabs.mtgexchange;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Drew on 2/4/15.
 */
public class MainActivity extends Activity implements AdapterView.OnItemClickListener{

    ListView list;
    List<ListViewCard> drew = new LinkedList<ListViewCard>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list= (ListView) findViewById(R.id.listView);


        ListViewCard dragonBlood = new ListViewCard();
        dragonBlood.setCardName("DragonBlood");
        dragonBlood.setCardValue(29.32);
        dragonBlood.setValueChange(.45);
        dragonBlood.setCardID(13L);
        dragonBlood.setManaCost("2BB");

        for (int x=1; x <= 10;x++)
        {
        drew.add(dragonBlood);
        }

// on first load, give empty array, and subsequent loads, use cached data
        //back ground image
        ListAdapter adapter=new ListAdapter(this, drew);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent iCardInfo = new Intent(this, CardInfo.class);
        iCardInfo.putExtra("CardObject", drew.get(position));
        // get card id from card object and pass through to next screens webservice call
//        iCompany.putExtra(ComicReleaseListActivity.COMPANY_LOGO, images[position]);
//        iCompany.putExtra(ComicReleaseListActivity.COMPANY_TITLE, publisherTitles[position]);
        startActivity(iCardInfo);
    }
}

class ListAdapter extends ArrayAdapter<ListViewCard>
{
    ListAdapter(Context c, List<ListViewCard> cards) {
        super(c, R.layout.single_row, cards);
    }

    class ViewHolder {
        TextView cName;
        TextView cValue;
        TextView vChange;
        ViewHolder(View v) {
            cName= (TextView) v.findViewById(R.id.card_name);
            cValue= (TextView) v.findViewById(R.id.card_value);
            vChange= (TextView) v.findViewById(R.id.value_change);
            //background image
        }
    }
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


        holder.cName.setText(getItem(position).getCardName());
        holder.cValue.setText(String.valueOf(getItem(position).getCardValue()));
        holder.vChange.setText(String.valueOf(getItem(position).getValueChange()));
//background image
        return row;
    }
}