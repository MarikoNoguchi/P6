package edu.mnstate.cw3967me.p6;

/*
    curry rice order application
    This provides an order form in which the user can make an order.
    If the user presses the submit button, move on to Results class.

    Mariko Noguchi
    10/10/2016
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.ToggleButton;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    //declare attributes for each of the resources
    private RadioButton riceSRadioButton, riceMRadioButton, riceLRadioButton,
            lowRadioButton, midRadioButton, highRadioButton;
    private CheckBox checkboxNatto, checkboxSpinach, checkboxTuna, checkboxCheese;
    private EditText etFirstName, etLastName;
    private Spinner spinnerQuantity;
    private ArrayAdapter<CharSequence> adapter;
    private ArrayList<String> toppings = new ArrayList<>();
    private ToggleButton spoonsToggleButton;
    private AutoCompleteTextView actvStores;
    private DatePicker dpDate;
    private EditText etRequest;

    private String spoons = "";//quantity of spoons
    private String quantity = "1";//quantity of meals

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //get references for each resource on the interface
        riceSRadioButton = (RadioButton) findViewById(R.id.riceSRadioButton);           //amount of rice
        riceMRadioButton = (RadioButton) findViewById(R.id.riceMRadioButton);
        riceLRadioButton = (RadioButton) findViewById(R.id.riceLRadioButton);
        lowRadioButton = (RadioButton) findViewById(R.id.lowRadioButton);               //spicy level
        midRadioButton = (RadioButton) findViewById(R.id.midRadioButton);
        highRadioButton = (RadioButton) findViewById(R.id.highRadioButton);
        checkboxNatto = (CheckBox) findViewById(R.id.checkboxNatto);                    //toppings
        checkboxSpinach = (CheckBox) findViewById(R.id.checkboxSpinach);
        checkboxTuna = (CheckBox) findViewById(R.id.checkboxTuna);
        checkboxCheese = (CheckBox) findViewById(R.id.checkboxCheese);
        spoonsToggleButton = (ToggleButton) findViewById(R.id.spoonsToggleButton);      //spoons
        spinnerQuantity = (Spinner) findViewById(R.id.spinnerQuantity);                 //quantity
        etFirstName = (EditText) findViewById(R.id.etFirstName);                        //first name
        etLastName = (EditText) findViewById(R.id.etLastName);                          //last name
        actvStores = (AutoCompleteTextView) findViewById(R.id.actvStores);              //store location
        dpDate = (DatePicker) findViewById(R.id.dpDate);                                //order timing
        etRequest = (EditText) findViewById(R.id.etRequest);                            //request

        //create and set the adapter for the spinner
        adapter = ArrayAdapter.createFromResource(this, R.array.quantity, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerQuantity.setAdapter(adapter);
        spinnerQuantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int intQuantity = (int) parent.getItemIdAtPosition(position) + 1;
                quantity = Integer.toString(intQuantity);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //create and set the adapter for the AutoCompleteTextView
        adapter = ArrayAdapter.createFromResource(this, R.array.store_names, android.R.layout.simple_dropdown_item_1line);
        actvStores.setAdapter(adapter);

        //set the minimal date of the order timing
        dpDate.setMinDate(System.currentTimeMillis() - 1000);
    }


    public void onRiceRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.riceSRadioButton://200g
                if (checked)
                    riceSRadioButton.setChecked(true);
                break;
            case R.id.riceMRadioButton://300g
                if (checked)
                    riceMRadioButton.setChecked(true);
                break;
            case R.id.riceLRadioButton://400g
                if (checked)
                    riceLRadioButton.setChecked(true);
                break;
        }
    }

    public void onSpicyLevelRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.lowRadioButton://low
                if (checked)
                    lowRadioButton.setChecked(true);
                break;
            case R.id.midRadioButton://mid
                if (checked)
                    midRadioButton.setChecked(true);
                break;
            case R.id.highRadioButton://high
                if (checked)
                    highRadioButton.setChecked(true);
                break;
        }
    }

    public void submit_onClick(View v) {
        //create the intent
        Intent intent = new Intent(this, Results.class);
        //put data in the intent
        if (riceSRadioButton.isChecked()) intent.putExtra("rice", "200g");              //amount of rice
        else if (riceMRadioButton.isChecked()) intent.putExtra("rice", "300g");
        else if (riceLRadioButton.isChecked()) intent.putExtra("rice", "400g");
        else return;

        if (lowRadioButton.isChecked()) intent.putExtra("spicyLevel", "low");           //spicy level
        else if (midRadioButton.isChecked()) intent.putExtra("spicyLevel", "mid");
        else if (highRadioButton.isChecked()) intent.putExtra("spicyLevel", "high");
        else return;

        if (checkboxNatto.isChecked()) toppings.add("natto");                           //topping
        if (checkboxSpinach.isChecked()) toppings.add("spinach");
        if (checkboxTuna.isChecked()) toppings.add("tuna");
        if (checkboxCheese.isChecked()) toppings.add("cheese");
        String sToppings = "";
        for (int i = 0; i < toppings.size(); i++) {
            if (i == toppings.size() - 1) sToppings += toppings.get(i);
            else sToppings += toppings.get(i) + ", ";
        }
        intent.putExtra("toppings", sToppings);

        if (spoonsToggleButton.isChecked()) {                                           //spoons
            spoons = "Yes";
        } else {
            spoons = "No";
        }
        intent.putExtra("spoons", spoons);

        intent.putExtra("quantity", quantity);                                          //quantity

        intent.putExtra("firstName", etFirstName.getText().toString());                 //first name
        intent.putExtra("lastName", etLastName.getText().toString());                   //last name

        intent.putExtra("stores", actvStores.getText().toString());                     //store location

        int month = dpDate.getMonth() + 1;                                              //order timing
        int dayOfMonth = dpDate.getDayOfMonth();
        int year = dpDate.getYear();
        String date = Integer.toString(month) + "/" + Integer.toString(dayOfMonth) + "/" + Integer.toString(year);
        intent.putExtra("date", date);

        intent.putExtra("request", etRequest.getText().toString());                     //request

        //start the intent
        this.startActivity(intent);
    }

    public void clear_onClick(View v) {
        //clear the amount of rice
        riceSRadioButton.setChecked(false);
        riceMRadioButton.setChecked(false);
        riceLRadioButton.setChecked(false);

        //clear the spicy level
        lowRadioButton.setChecked(false);
        midRadioButton.setChecked(false);
        highRadioButton.setChecked(false);

        //clear toppings
        checkboxNatto.setChecked(false);
        checkboxSpinach.setChecked(false);
        checkboxTuna.setChecked(false);
        checkboxCheese.setChecked(false);
        toppings.clear();

        //clear spoon
        spoonsToggleButton.setChecked(false);

        //clear quantity
        spinnerQuantity.setSelection(0);
        quantity = "1";

        //clear EditTexts
        etFirstName.setText("");
        etLastName.setText("");
        actvStores.setText("");
        etRequest.setText("");

        //clear DatePicker
        Date today = Calendar.getInstance().getTime();//get today's year, month and day of month
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String sYear = sdf.format(new Date());
        int year = Integer.parseInt(sYear);
        int month = today.getMonth();
        sdf = new SimpleDateFormat("dd");
        String sDayOfMonth = sdf.format(new Date());
        int dayOfMonth = Integer.parseInt(sDayOfMonth);
        dpDate.updateDate(year, month, dayOfMonth);//set today as the current date
    }
}
