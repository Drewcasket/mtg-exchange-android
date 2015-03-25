package detroitlabs.mtgexchange;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Drew on 3/24/15.
 */
public class FilterActivity extends Activity implements AdapterView.OnItemSelectedListener {

    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {

        // set "that field" to equal what was chosen
        Toast.makeText(getApplicationContext(), "Spinner Selected",
                Toast.LENGTH_SHORT).show();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);

        Spinner value_dropdown = (Spinner) findViewById(R.id.value_dropdown);
        Spinner value_change_dropdown = (Spinner) findViewById(R.id.value_change_dropdown);
        Spinner color_dropdown = (Spinner) findViewById(R.id.color_dropdown);
        Spinner rarity_dropdown = (Spinner) findViewById(R.id.rarity_dropdown);
        Spinner set_dropdown = (Spinner) findViewById(R.id.set_dropdown);


        ArrayAdapter<CharSequence> colorAdapter = ArrayAdapter.createFromResource(this,
                R.array.colors_array, android.R.layout.simple_spinner_item);
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        color_dropdown.setAdapter(colorAdapter);
        color_dropdown.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> rarityAdapter = ArrayAdapter.createFromResource(this,
                R.array.rarity_array, android.R.layout.simple_spinner_item);
        rarityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rarity_dropdown.setAdapter(rarityAdapter);
        rarity_dropdown.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> setAdapter = ArrayAdapter.createFromResource(this,
                R.array.set_array, android.R.layout.simple_spinner_item);
        setAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        set_dropdown.setAdapter(setAdapter);
        set_dropdown.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> valueAdapter = ArrayAdapter.createFromResource(this,
                R.array.values_array, android.R.layout.simple_spinner_item);
        valueAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        value_dropdown.setAdapter(valueAdapter);
        value_change_dropdown.setAdapter(valueAdapter);
        value_dropdown.setOnItemSelectedListener(this);
        value_change_dropdown.setOnItemSelectedListener(this);
    }
}