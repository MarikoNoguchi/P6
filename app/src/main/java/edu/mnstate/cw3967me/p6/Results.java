package edu.mnstate.cw3967me.p6;
/*
    curry rice order application
    This show a summary of the order.

    Mariko Noguchi
    10/10/2016
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Results extends AppCompatActivity {
    //declare attributes for each of the resources
    TextView tvRice, tvSpicyLevel, tvToppings, tvSpoons, tvQuantity, tvFirstName,tvLastName, tvStores, tvDate, tvRequest,
        tvTax, tvTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        //get references for each resource on the interface
        tvRice = (TextView) findViewById(R.id.tvRice);
        tvSpicyLevel = (TextView) findViewById(R.id.tvSipicyLevel);
        tvToppings = (TextView) findViewById(R.id.tvToppings);
        tvSpoons = (TextView) findViewById(R.id.tvSpoons);
        tvQuantity = (TextView) findViewById(R.id.tvQuantity);
        tvFirstName = (TextView) findViewById(R.id.tvFirstName);
        tvLastName = (TextView) findViewById(R.id.tvLastName);
        tvStores = (TextView) findViewById(R.id.tvStores);
        tvDate = (TextView) findViewById(R.id.tvDate);
        tvRequest = (TextView) findViewById(R.id.tvRequest);
        tvTax = (TextView) findViewById(R.id.tvTax);
        tvTotal = (TextView) findViewById(R.id.tvTotal);

        //get the intent
        Intent intent = getIntent();

        //get data from the intent
        String rice = intent.getStringExtra("rice");
        String spicyLevel = intent.getStringExtra("spicyLevel");
        String toppings = intent.getStringExtra("toppings");
        String spoons = intent.getStringExtra("spoons");
        String quantity = intent.getStringExtra("quantity");
        double nQuantity = Double.parseDouble(quantity);
        String firstName = intent.getStringExtra("firstName");
        String lastName = intent.getStringExtra("lastName");
        String stores = intent.getStringExtra("stores");
        String date = intent.getStringExtra("date");
        String request = intent.getStringExtra("request");
        //calculate tax
        Double tax = nQuantity * 8.0 * 0.07;//taxRate 7%
        int intTax = (int)(tax * 100);
        tax = intTax / 100.0;
        //calculate total
        Double total = nQuantity *8.0 + tax;

        //set texts on text views
        tvRice.setText(rice);
        tvSpicyLevel.setText(spicyLevel);
        tvToppings.setText(toppings);
        tvSpoons.setText(spoons);
        tvQuantity.setText(quantity);
        tvFirstName.setText(firstName);
        tvLastName.setText(lastName);
        tvStores.setText(stores);
        tvDate.setText(date);
        tvRequest.setText(request);
        tvTax.setText("$" + Double.toString(tax));
        tvTotal.setText("$ "+Double.toString(total));
    }
}
