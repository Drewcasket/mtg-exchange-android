package detroitlabs.mtgexchange;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.Toast;


/**
 * Created by Drew on 2/4/15.
 */
public class CardInfo extends Activity implements View.OnClickListener {

    @Override
    protected void onStart() {
        super.onStart();
        Card CardInfo = getIntent().getParcelableExtra("CardObject");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.card_info);


        Button add_watchlist = (Button) findViewById(R.id.watch_list_button);
        Button remove_watchlist = (Button) findViewById(R.id.remove_watch_list_button);
        Button add_collection = (Button) findViewById(R.id.add_collection_button);
        Button remove_collection = (Button) findViewById(R.id.remove_collection_button);
        Button buy = (Button) findViewById(R.id.buy_button);

        add_watchlist.setOnClickListener(this);
        remove_watchlist.setOnClickListener(this);
        remove_watchlist.setVisibility(View.INVISIBLE);
        add_collection.setOnClickListener(this);
        remove_collection.setOnClickListener(this);
        remove_collection.setVisibility(View.INVISIBLE);
        buy.setOnClickListener(this);

//        if (partOfCollection()) {
//            add_collection.setVisibility(View.INVISIBLE);
//            remove_collection.setVisibility(View.VISIBLE);
//        }
//        else {
//            add_collection.setVisibility(View.VISIBLE);
//            remove_collection.setVisibility(View.INVISIBLE);
//        }
//
//        if (partOfWatchList()) {
//            add_watchlist.setVisibility(View.INVISIBLE);
//            remove_watchlist.setVisibility(View.VISIBLE);
//        }
//        else {
//            add_watchlist.setVisibility(View.VISIBLE);
//            remove_watchlist.setVisibility(View.INVISIBLE);
//
//        }
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.watch_list_button:
                Toast.makeText(this, "Added to watch list", Toast.LENGTH_SHORT).show();
//                tinydb.putList("monastery_mentor", watchList);
                break;
            case R.id.add_collection_button:
                Toast.makeText(this, "Added to collection", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buy_button:
                Intent iBuy = new Intent(this, MainActivity.class);
                startActivity(iBuy);
                break;
        }

    }
}


