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

/**
 * Created by Drew on 2/4/15.
 */
public class MainActivity extends Activity implements AdapterView.OnItemClickListener{

    ListView list;
    String[] cardNames;
    String[] cardValues;
    String[] valueChanges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list= (ListView) findViewById(R.id.listView);

        Resources res=getResources();
        cardNames=res.getStringArray(R.array.cardNames);
        cardValues=res.getStringArray(R.array.cardValues);
        valueChanges=res.getStringArray(R.array.valueChanges);


        ListAdapter adapter=new ListAdapter(this, cardNames, cardValues, valueChanges);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent iCardInfo = new Intent(this, CardInfo.class);
//        iCompany.putExtra(ComicReleaseListActivity.COMPANY_LOGO, images[position]);
//        iCompany.putExtra(ComicReleaseListActivity.COMPANY_TITLE, publisherTitles[position]);
        startActivity(iCardInfo);
    }
}

class ListAdapter extends ArrayAdapter<String>
{
    Context context;
    String[] cardNameArray;
    String[] cardValueArray;
    String[] valueChangeArray;
    ListAdapter(Context c, String[] cardNames, String[] cardValues, String[] valueChanges) {
        super(c, R.layout.single_row,R.id.card_name,cardNames);
        this.context=c;
        this.cardNameArray=cardNames;
        this.cardValueArray=cardValues;
        this.valueChangeArray=valueChanges;
    }

    class ViewHolder {
        TextView cName;
        TextView cValue;
        TextView vChange;
        ViewHolder(View v) {
            cName= (TextView) v.findViewById(R.id.card_name);
            cValue= (TextView) v.findViewById(R.id.card_value);
            vChange= (TextView) v.findViewById(R.id.value_change);
        }
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        ViewHolder holder=null;
        if (row==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_row, parent, false);
            holder=new ViewHolder(row);
            row.setTag(holder);
        }
        else{
            holder= (ViewHolder) row.getTag();
        }


        holder.cName.setText(cardNameArray[position]);
        holder.cValue.setText(cardValueArray[position]);
        holder.vChange.setText(valueChangeArray[position]);

        return row;
    }
}