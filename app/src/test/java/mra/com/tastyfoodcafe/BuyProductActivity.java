package mra.com.tastyfoodcafe;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BuyProductActivity extends AppCompatActivity {

    EditText name,mobile,add1,add2,qunty;
    Button CashOnDerivilery,cardpay;
    RelativeLayout cash,card;
    Button registerdata;
    DatabaseReference reference;
    Intent intent;
    String uid;
    public String CHANNEL_ID="001";
    TextView text,pname;
    int bigtotal=0;

    String n,m,a1,a2,q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_product);


        if(ContextCompat.checkSelfPermission(BuyProductActivity.this,
                Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(BuyProductActivity.this,
                    Manifest.permission.SEND_SMS))
            {
                ActivityCompat.requestPermissions(BuyProductActivity.this,
                        new String[]{Manifest.permission.SEND_SMS},1);
            }
            else
            {
                ActivityCompat.requestPermissions(BuyProductActivity.this,
                        new String[]{Manifest.permission.SEND_SMS},1);
            }
        }
        else
        {
            //do nothing
        }

        name=(EditText)findViewById(R.id.name);
        mobile=(EditText)findViewById(R.id.mobilenumber);
        add1=(EditText)findViewById(R.id.address);
        add2=(EditText)findViewById(R.id.address1);
        qunty=(EditText)findViewById(R.id.quantity);
        card=(RelativeLayout)findViewById(R.id.rel_card);
        registerdata=(Button)findViewById(R.id.button2);
        //  pname=(TextView)findViewById(R.id.productname);



        CashOnDerivilery=(Button)findViewById(R.id.cod);
        cardpay=(Button)findViewById(R.id.dumycard);

        cash=(RelativeLayout)findViewById(R.id.CODform);
        card=(RelativeLayout)findViewById(R.id.rel_card);
        text=(TextView)findViewById(R.id.payment_amount);
        Button btnPay=(Button)findViewById(R.id.btn_pay);

        intent=getIntent();
        uid=intent.getStringExtra("id");


        CashOnDerivilery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                card.setVisibility(View.GONE);
                cash.setVisibility(View.VISIBLE);

            }
        });


        cardpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                card.setVisibility(View.VISIBLE);
                cash.setVisibility(View.GONE);



            }
        });

        Bakeryitemss bakeryitemss=new Bakeryitemss();

        text.setText("");

        btnPay.setText(String.format("Payer %s",text.getText()));







        registerdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                COD();

            }
        });



    }




    private void COD()
    {
        cash.setVisibility(View.VISIBLE);
        card.setVisibility(View.GONE);

        n=name.getText().toString();
        m=mobile.getText().toString();
        a1=add1.getText().toString();
        a2=add2.getText().toString();
        q=qunty.getText().toString();
        String content="Tasty Food Cafe\n"+n + "\n Your Order Has been Sucessfully Placed..."+"\nOn Address:-"+a1+"\n"+a2 +"\n Thankyou!";

        if (TextUtils.isEmpty(n)||TextUtils.isEmpty(m)||TextUtils.isEmpty(a1)||TextUtils.isEmpty(a2)||TextUtils.isEmpty(q)||
                m.length()<=9)
        {
            Toast.makeText(BuyProductActivity.this,"Fill All Information",Toast.LENGTH_LONG).show();
        }

        else
        {
            intent = getIntent();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = intent.getStringExtra("id");


            reference = FirebaseDatabase.getInstance().getReference("Bill").child("bill").child(user.getUid());
            String id = reference.push().getKey();

            PaymentMode paymentMode = new PaymentMode(id, n, m, a1, a2, q);

            reference.child(id).setValue(paymentMode);
            finish();

            try {

                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(m, null, content, null, null);
                Toast.makeText(BuyProductActivity.this,"sent",Toast.LENGTH_SHORT).show();



            } catch (Exception e)
            {
                Toast.makeText(BuyProductActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }


        }




    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(BuyProductActivity.this,
                            Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(BuyProductActivity.this, "Permission grant", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(BuyProductActivity.this, "No Permission grant", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }

    }



}