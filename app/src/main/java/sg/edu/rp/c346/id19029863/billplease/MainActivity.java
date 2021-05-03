package sg.edu.rp.c346.id19029863.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText amount;
    EditText pax;
    ToggleButton svs;
    ToggleButton gst;
    EditText discount;
    RadioGroup rgPay;
    ToggleButton split;
    ToggleButton reset;
    TextView totalBill;
    TextView eachpay;
    Button Cash;
    Button Paynow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amount = findViewById(R.id.inputAmount);
        pax = findViewById(R.id.inputPax);
        discount = findViewById(R.id.inputDiscount);
        svs = findViewById(R.id.svs);
        gst = findViewById(R.id.gst);
        split = findViewById(R.id.split);
        reset = findViewById(R.id.reset);
        rgPay = findViewById(R.id.rgPay);
        totalBill = findViewById(R.id.totalBill);
        eachpay = findViewById(R.id.eachpay);
        Cash = findViewById(R.id.cash);
        Paynow = findViewById(R.id.paynow);

        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Code for the action
                if (amount.getText().toString().trim().length()!= 0 && pax.getText().toString().trim().length()!= 0 ) {
                    double newAmount = 0.0;
                    if (svs.isChecked() && gst.isChecked()) {
                        newAmount = Double.parseDouble(amount.getText().toString()) * 1.17;
                    } else if (!svs.isChecked() && gst.isChecked()) {
                        newAmount = Double.parseDouble(amount.getText().toString()) * 1.07;
                    } else if (svs.isChecked() && !gst.isChecked()) {
                        newAmount = Double.parseDouble(amount.getText().toString()) * 1.1;
                    } else if (!svs.isChecked() && !gst.isChecked()){
                        newAmount = Double.parseDouble(amount.getText().toString());
                    }else{
                        System.out.println("Invalid input ");
                    }

                    if (discount.getText().toString().trim().length()!= 0){
                        newAmount *= 1 - (Double.parseDouble(discount.getText().toString()) / 100);
                    }else{
                        System.out.println("Invalid input");
                    }

                    totalBill.setText("Total Bill : $" + String.format("%.2f", newAmount));
                    int newpax = Integer.parseInt(pax.getText().toString());
                    if (newpax != 0) {
                        int checkedRadioId = rgPay.getCheckedRadioButtonId();
                        if (checkedRadioId == R.id.cash){
                            eachpay.setText("Each Pays : $" + String.format("%.2f", newAmount / newpax) + " in cash");
                        }else {
                            eachpay.setText("Each Pays : $" + String.format("%.2f", newAmount / newpax) + " via paynow to 123456789");
                        }
                    }else{
                        eachpay.setText("Each Pays : $" + String.format("%.2f", newAmount ));

                    }
                }

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Code for the action
                amount.setText("");
                pax.setText("");
                discount.setText("");
                svs.setChecked(false);
                gst.setChecked(false);
            }

        });

    }
}