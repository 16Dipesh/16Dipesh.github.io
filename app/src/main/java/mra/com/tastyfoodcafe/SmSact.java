package mra.com.tastyfoodcafe;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

class SmSact extends AppCompatActivity {

    Button send;
    EditText num,cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsact);

        if(ContextCompat.checkSelfPermission(mra.com.tastyfoodcafe.SmSact.this,
                Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(mra.com.tastyfoodcafe.SmSact.this,
                    Manifest.permission.SEND_SMS))
            {
                ActivityCompat.requestPermissions(mra.com.tastyfoodcafe.SmSact.this,
                        new String[]{Manifest.permission.SEND_SMS},1);
            }
            else
            {
                ActivityCompat.requestPermissions(mra.com.tastyfoodcafe.SmSact.this,
                        new String[]{Manifest.permission.SEND_SMS},1);
            }
        }
        else
        {
            //do nothing
        }

        send=(Button)findViewById(R.id.btm);
        num=(EditText)findViewById(R.id.num);
        cont=(EditText)findViewById(R.id.cont);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = num.getText().toString();
                String content = cont.getText().toString();
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(number, null, content, null, null);
                    Toast.makeText(mra.com.tastyfoodcafe.SmSact.this,"sent",Toast.LENGTH_SHORT).show();

                } catch (Exception e)
                {
                    Toast.makeText(mra.com.tastyfoodcafe.SmSact.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(mra.com.tastyfoodcafe.SmSact.this,
                            Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(mra.com.tastyfoodcafe.SmSact.this, "Permission grant", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(mra.com.tastyfoodcafe.SmSact.this, "No Permission grant", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }

    }
}
