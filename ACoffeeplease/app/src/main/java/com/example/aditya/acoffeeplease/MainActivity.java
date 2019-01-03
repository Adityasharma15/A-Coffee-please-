package com.example.aditya.acoffeeplease;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Message when chocolate topping is added
    public void chocolaty(View view)
    {
        Toast.makeText(this, "Chocolate topping costs $1 extra per coffe", Toast.LENGTH_SHORT).show();
    }

    //Message when Whippy cream topping is added
    public void creamy(View view)
    {
        Toast.makeText(this, "Whipped Cream topping costs $2 extra per coffee", Toast.LENGTH_SHORT).show();
    }


    //When Place Order is clicked
    public void SubmitOrder(View view) {

        int pricePerCoffee, billAmount;
        String choc, whipp;

        //Getting name input. Connvert Editable input into string and store.
        EditText edittext1 = (EditText) findViewById(R.id.editText);
        String name = edittext1.getText().toString();

        //Getting quantity input. Convert Editable into integer.
        EditText edittext2 = (EditText) findViewById(R.id.editText2);
        int quantity = Integer.parseInt(String.valueOf(edittext2.getText()));


        //Getting toppings info from user.
        CheckBox chocolate = (CheckBox)findViewById(R.id.checkBox);
        CheckBox whippedCram = (CheckBox)findViewById(R.id.checkBox2);


        // Adjusting price of the order as per toppings
        //Price of basic coffe is $5 and then price increases as per toppings
        pricePerCoffee = 5;

        //increasing price of coffee as per user desire.
        if(chocolate.isChecked()) {pricePerCoffee+=1; choc = "Yes";}
        else choc = "No";


        if(whippedCram.isChecked()) {pricePerCoffee+=2; whipp = "Yes";}
        else whipp = "No";

        billAmount = quantity*pricePerCoffee;

        String OrderSummary = " A bill of cost $" + billAmount;
        OrderSummary = OrderSummary + "\nAdd Chocolate : " + choc ;
        OrderSummary = OrderSummary + "\nAdd Whipped Cream : " + whipp;
        OrderSummary = OrderSummary + "\nThank you! Do visit again.";
        composeEmail(name, OrderSummary);
    }


    //preparing mail after the order button is clicked on
    public void composeEmail(String name, String OrderSummary) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:Starbucks@chalbeychal.com")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT,"Order for " + name  );

        //We need to add this field to enter what the mail composes.
        intent.putExtra(Intent.EXTRA_TEXT, OrderSummary);

        //If any relative app for the intent is not found, then the system must not hang in dilemna what to do.
        if (intent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(intent);
        }
    }
}

