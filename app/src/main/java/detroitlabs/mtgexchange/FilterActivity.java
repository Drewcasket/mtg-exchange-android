package detroitlabs.mtgexchange;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import detroitlabs.mtgexchange.service.CardsParams;
import detroitlabs.mtgexchange.service.ExchangeServiceClient;
import detroitlabs.mtgexchange.service.FiltersResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

@EActivity
public class FilterActivity extends Activity implements Callback<FiltersResponse>{

    MultiSelectionSpinner color_spinner;
    MultiSelectionSpinner set_spinner;
    MultiSelectionSpinner rarity_spinner;
    MultiSelectionSpinner price_spinner;
    MultiSelectionSpinner price_change_spinner;
    Button applyFilters;
    Button clearFilters;

    private final List<Card> card = new LinkedList<Card>();

    @Bean
    ExchangeServiceClient serviceClient;
    private CardsParams cardsParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);

        ActionBar mActionBar = getActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View customBar = mInflater.inflate(R.layout.action_bar, null);
        TextView title = (TextView) customBar.findViewById(R.id.app_title);
        ImageButton filter = (ImageButton) customBar.findViewById(R.id.app_filter);
        title.setText("MTG Exchange");
        filter.setVisibility(View.INVISIBLE);
        mActionBar.setCustomView(customBar);
        mActionBar.setDisplayShowCustomEnabled(true);

        this.cardsParams = this.getIntent().getParcelableExtra("cardsParams");
        applyFilters = (Button) findViewById(R.id.apply_filters) ;
        clearFilters = (Button) findViewById(R.id.clear_filters);
        serviceClient.getFilters(this);
    }

    public void onClick(View v){
        List<String> colorSelected = color_spinner.getSelectedStrings();
        List<String> setSelected = set_spinner.getSelectedStrings();
        List<String> raritySelected = rarity_spinner.getSelectedStrings();


        switch(v.getId()){

            case R.id.apply_filters:
                cardsParams.setColors(colorSelected);
//                cardsParams.setSet(setSelected);
                cardsParams.setStart(0L);
                Intent iResult = new Intent();
                iResult.putExtra("cardsParams", cardsParams);
                this.setResult(RESULT_OK, iResult);
                this.finish();
                break;

            case R.id.clear_filters:
                clearFilters();
                //set the parameters back to null

                break;
        }


    }

    @Override
    public void success(FiltersResponse filtersResponse, Response response) {

        List<String> colors = filtersResponse.getColors();
        List<String> sets = filtersResponse.getSets();
        List<String> rarities = filtersResponse.getRarities();
        color_spinner = (MultiSelectionSpinner) findViewById(R.id.color_spinner);
        color_spinner.setItems(colors);
        set_spinner = (MultiSelectionSpinner) findViewById(R.id.set_spinner);
        set_spinner.setItems(sets);
        rarity_spinner = (MultiSelectionSpinner) findViewById(R.id.rarity_spinner);
        rarity_spinner.setItems(rarities);
        String[] price = { ">", "<" };
        price_spinner = (MultiSelectionSpinner) findViewById(R.id.price_choice_spinner);
        price_spinner.setItems(price);
        String[] price_change = { ">", "<" };
        price_change_spinner = (MultiSelectionSpinner) findViewById(R.id.price_change_choice_spinner);
        price_change_spinner.setItems(price_change);
//      should we also add setting the textviews with the filterresponse? Then in the future
//        if another element was added to a field, like another color, it would line up correctly on the screen.
    }

    @Override
    public void failure(RetrofitError error) {

    }

    public void clearFilters() {
        serviceClient.getFilters(this);
    }
}

