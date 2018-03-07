/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Global variables declared here
     * */
    private int orders = 1;

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        /* Getting necessary information from the customer */
        boolean addWhippedCream = this.hasWhippedCream();
        boolean addChocolate = this.hasChocolate();
        int price = this.calculatePrice(5, addWhippedCream, addChocolate);
        String name = this.getName();

        /* Prompting the customer accordingly */
        if (name.isEmpty()) {
            Toast noName = Toast.makeText(getApplicationContext(), "Please input your name!!!", Toast.LENGTH_SHORT);
            noName.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
            noName.show();
            return;
        }

        /* Everything looks good, proceed with the order */
        else this.createOrderSummary(name, addChocolate, addWhippedCream, price);
    }

    /**
     * This method increase the amount of orders by 1
     * */
    public void increment(View view) {
        if (this.orders < 100) this.orders++;
        else {
            Toast tooMuch = Toast.makeText(getApplicationContext(), "You cannot order more than 100 cups of coffee!!!", Toast.LENGTH_SHORT);
            tooMuch.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
            tooMuch.show();
            return;
        }
        display(this.orders);
    }

    /**
     * This method decrease the amount of orders by 1
     * */
    public void decrement(View view) {
        if (this.orders > 1) this.orders--;
        else {
            Toast negative = Toast.makeText(getApplicationContext(), "You cannot order less than 1 cup of coffee!!!", Toast.LENGTH_SHORT);
            negative.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
            negative.show();
            return;
        }
        display(this.orders);
    }

    /**
     * This method displays the given quantity value on the screen.
     *
     * @param number any number to be displayed at the designated TextView
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * Calculates the price of the order.
     *
     * @param price is the price of 1 cup of coffee
     * @param hasWhippedCream is there whipped cream for the orders, $2 per order
     * @param hasChocolate is there chocolate for the orders, $3 per order
     */
    private int calculatePrice(int price, boolean hasWhippedCream, boolean hasChocolate) {
        int totalPrice = this.orders * price;
        if (hasWhippedCream) totalPrice += 1 * this.orders;
        if (hasChocolate) totalPrice += 2 * this.orders;
        return totalPrice;
    }

    /**
     * Return whether there is whipped cream in the orders
     * */
    private boolean hasWhippedCream() {
        CheckBox whipped_cream = (CheckBox) findViewById(R.id.whipped_cream);
        return whipped_cream.isChecked();
    }

    /**
     * Return whether there is chocolate in the orders
     * */
    private boolean hasChocolate() {
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate);
        return chocolate.isChecked();
    }

    /**
     * Get the customer name from the EditText field
     * */
    private String getName() {
        EditText name = (EditText) findViewById(R.id.customer_name);
        return name.getText().toString();
    }

    /**
     * Return an order summary based on the total cost
     *
     * @param name is the name of the customer
     * @param addChocolate is the choice for the Chocolate topping
     * @param addWhippedCream is the choice for the Whipped Cream topping
     * @param cost is the total cost of the order
     * */
    private void createOrderSummary(String name, boolean addChocolate, boolean addWhippedCream, int cost) {
        /* To be returned at the end */
        String summary = "Name: " + name + "\n";

        /* Response accordingly */
        if (addWhippedCream) {
            summary += "Add whipped cream\n";
        }
        if (addChocolate) {
            summary += "Add chocolate\n";
        }
        if ((!addChocolate) && (!addWhippedCream)) {
            summary += "No additional toppings\n";
        }

        /* Let's be nice and make the order summary lengthier on return */
        String body =  summary + "Quantity: " + this.orders + "\n" +
                "Total: $" + cost + "\n" +
                "Thank you!";
        String subject = "Just Java order for " + name;
        this.sendEmail(subject, body);
    }

    /**
     * Compose the summary email to be sent out
     * */
    private void sendEmail(String subject, String body) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);
        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(emailIntent);
        }
    }
}