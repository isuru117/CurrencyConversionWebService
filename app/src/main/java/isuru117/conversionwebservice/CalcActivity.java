package isuru117.conversionwebservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CalcActivity extends AppCompatActivity {

    double rate;
    String cur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        final TextView btn=(TextView) findViewById(R.id.textView);

        Intent intent = getIntent();

        cur = intent.getStringExtra("CUR");
        rate = Double.parseDouble(intent.getStringExtra("RATE"));

        final TextView txtResult = (TextView) findViewById(R.id.textView);
        txtResult.setText("0.00 EUR to " +cur+" is : 0.00");

    }


    public void onClick(final View v) {

                final EditText txtAmount = (EditText) findViewById(R.id.editText);
                final TextView txtResult = (TextView) findViewById(R.id.textView);

                double amount = Double.parseDouble(txtAmount.getText().toString());

                double result = rate * amount;

                txtResult.setText(amount + " EUR to " +cur+" is : " + result);

            }
    }


