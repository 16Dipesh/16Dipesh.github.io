package mra.com.tastyfoodcafe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class LoginPage extends AppCompatActivity
{
    EditText Receive_OTP;
    EditText mobilenumber;
    Button RegisterButton;
    TextView txt;
    //ProcessDialog
    TextView f,a;
    ProgressDialog progressDialog;
    //FireBase Objects
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    ProgressBar progressBar,codebar,regbtnprogrss;
    //validaton
    AwesomeValidation awesomeValidation;
    //Otp tools
    Button send_otp1;
    FirebaseUser user;
    int btn=0;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mcallbacks;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    String mVerificationId;
    RelativeLayout r2,r3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        Receive_OTP=(EditText)findViewById(R.id.Rec_otp);
        mobilenumber=(EditText) findViewById(R.id.phonenumber);
        progressDialog=new ProgressDialog(this);
        f=(TextView)findViewById(R.id.feed);
        a=(TextView)findViewById(R.id.about);


        send_otp1=(Button)findViewById(R.id.send_otp1);
        mAuth=FirebaseAuth.getInstance();


        user=FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            Intent i=new Intent(LoginPage.this,MainDrawerWindowActivity.class);
            startActivity(i);
            finish();
        }

        callingfeedandabout();



        send_otp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String PhoneNumber = mobilenumber.getText().toString();

                if(TextUtils.isEmpty(PhoneNumber))
                {
                    Toast.makeText(LoginPage.this,"Enter Mobile Number",Toast.LENGTH_SHORT).show();

                }
                else if(PhoneNumber.length()<=12)
                {
                    Toast.makeText(LoginPage.this,"Invalid Number",Toast.LENGTH_SHORT).show();
                }
                else {
                    send_otp1.setEnabled(true);


                    if(btn==0)
                    {
                        progressDialog.setMessage("Loading...");
                        progressDialog.show();

                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                PhoneNumber,
                                60,
                                TimeUnit.SECONDS,
                                LoginPage.this,
                                mcallbacks

                        );
                        //  send_otp1.setVisibility(View.GONE);


                    }
                    else
                    {

                        //codebar.setVisibility(View.VISIBLE);
                        send_otp1.setVisibility(View.GONE);
                        //rel12.setVisibility(View.GONE);
                        String vlaid=Receive_OTP.getText().toString();
                        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(mVerificationId,vlaid);
                        signInWithPhoneAuthCredential(credential);
                        Toast.makeText(LoginPage.this,"Verifing Entered OTP!",Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();



                    }}

            }
        });


        mcallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                progressDialog.setMessage("Loading...");
                progressDialog.show();
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

                Toast.makeText(LoginPage.this,e.getMessage(),Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {

                Receive_OTP.setVisibility(View.VISIBLE);
                mobilenumber.setVisibility(View.GONE);
                send_otp1.setVisibility(View.VISIBLE);

                mVerificationId = verificationId;
                mResendToken = token;
                btn=1;
                send_otp1.setText("verify Otp");
                progressDialog.dismiss();





            }
        };

        onStart();



    }

    private void callingfeedandabout()
    {
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPage.this, FeedbackActivity.Feedback.class));

            }
        });

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPage.this,AboutUS.class));

            }
        });

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential)
    {

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull final Task<AuthResult> task) {
                        final String phone=mobilenumber.getText().toString();
                        reference = FirebaseDatabase.getInstance().getReference("MobileAuthUser");
                        progressDialog.setMessage("Loading...");
                        progressDialog.show();


                        mAuth=FirebaseAuth.getInstance();
                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                        assert firebaseUser != null;
                        String userid = firebaseUser.getUid();
                        reference = FirebaseDatabase.getInstance().getReference("MobileAuthUser").child("UserInformation").child(userid);
                        HashMap<String,String> hashMap=new HashMap<>();
                        hashMap.put("mobileid",userid);
                        hashMap.put("Number",phone);

                        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(LoginPage.this,"Welcome To Tasty Cafe",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginPage.this,MainDrawerWindowActivity.class));
                                    progressDialog.dismiss();
                                }
                                else
                                {
                                    Toast.makeText(LoginPage.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        });



                    }
                });
    }
}