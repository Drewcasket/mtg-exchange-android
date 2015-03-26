package detroitlabs.mtgexchange;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;

import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.List;

import detroitlabs.mtgexchange.service.ExchangeServiceClient;
import detroitlabs.mtgexchange.service.FiltersResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

@EActivity
public class FilterActivity extends Activity implements Callback<FiltersResponse>{

    MultiSelectionSpinner spinner;

    @Bean
    ExchangeServiceClient serviceClient;

    @Override
    protected void onResume() {
        super.onResume();



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);
        setContentView(R.layout.activity_filters);
        serviceClient.getFilters(this);

    }

    public void onClick(View v){
        String s = spinner.getSelectedItemsAsString();
        Toast.makeText(getApplicationContext(), s , Toast.LENGTH_LONG).show();
    }

    @Override
    public void success(FiltersResponse filtersResponse, Response response) {
        List<String> colors = filtersResponse.getColors();
        spinner = (MultiSelectionSpinner) findViewById(R.id.mySpinner1);
        spinner.setItems(colors);
    }

    @Override
    public void failure(RetrofitError error) {

    }
}

