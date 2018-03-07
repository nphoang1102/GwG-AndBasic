/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

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
    private int orders = 0;

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = this.calculatePrice(5);
        String priceMessage = "Please make your order.";
        if (price > 0) priceMessage = this.createOrderSummary(price);
        displayMessage(priceMessage);
//        displayPrice(this.orders*this.price);
    }

    /**
     * This method increase the amount of orders by 1
     * */
    public void increment(View view) {
        this.orders++;
        display(this.orders);
    }

    /**
     * This method decrease the amount of orders by 1
     * */
    public void decrement(View view) {
        if (this.orders > 0) this.orders--;
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
     * This method displays the given text on the screen.
     *
     * @param message is the message going to be displayed at the designated TextView
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * Calculates the price of the order.
     *
     * @param price is the price of 1 cup of coffee
     */
    private int calculatePrice(int price) {
        return this.orders * price;
    }

    /**
     * Return an order summary based on the total cost
     *
     * @param cost is the total cost of the order
     * */
    private String createOrderSummary(int cost) {
        return "Name: The Hippie \n" +
                "Quantity: " + this.orders + "\n" +
                "Total: $" + cost + "\n" +
                "Thank you!";

    }
}