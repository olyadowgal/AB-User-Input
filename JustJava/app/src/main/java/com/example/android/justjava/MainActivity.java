package com.example.android.justjava;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        int price = calculatePrice();

        CheckBox creamCheck = (CheckBox) findViewById(R.id.checkbox_cream);
        boolean cream = creamCheck.isChecked();

        CheckBox chocoCheck = (CheckBox) findViewById(R.id.checkbox_choco);
        boolean choco = chocoCheck.isChecked();

        EditText text = (EditText) findViewById(R.id.name_field);
        String name = text.getText().toString();

        if (cream) {price = price + quantity;}
        if (choco) {price = price + quantity* 2;}


        String priceMessage = createOrderSummary(price, name, cream, choco);

        displayMessage(priceMessage);

    }

    /**
     * This method is called when the order button is clicked.
     */
    public void increment(View view) {
        if ( quantity < 100 ) {
            quantity = quantity + 1;
            displayQuantity(quantity);
        }

    }
    public void decrement(View view) {
        if ( quantity > 1 || quantity == 1 ) {
            quantity = quantity - 1;
            displayQuantity(quantity);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {

        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);

    }

    /**
     * This method displays the given price on the screen.
     */
    private int calculatePrice() {

        return 5 * quantity;
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    private String createOrderSummary(int price, String name, boolean cream, boolean choco) {
        String message = "Name: " + name;
        message = message + "\nAdd whipped cream: " + cream ;
        message = message + "\nAdd chocolate: " + choco ;
        message = message + "\nQuantity: " + quantity ;
        message = message + "\nTotal: " + price;
        message = message + "\nThank you!";
        composeEmail(message);
        return message;

    }

    public void composeEmail(String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}