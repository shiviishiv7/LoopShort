package com.example.loopshort;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.example.loopshort.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        activityMainBinding.userLoginButton.setOnClickListener(this::UserLogin);
        activityMainBinding.userSignupButton.setOnClickListener(this::signUp);
        activityMainBinding.userVerificationButton.setOnClickListener(this::verfication);
    //    startActivity(new Intent(this,VideoWatchActivity.class));
    }

    private void verfication(View view) {
        String email = activityMainBinding.userEmailMainActivity.getText().toString().trim();
        String password = activityMainBinding.userPasswordMainActivity.getText().toString().trim();
        String code= activityMainBinding.userVerificationCode.getText().toString().trim();
        Amplify.Auth.confirmSignUp(
                email,code,
                result -> {
                    Log.i("AuthQuickstart", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete");
                    // Toast.makeText(getApplicationContext(),"verfication successful, press login button to login",Toast.LENGTH_SHORT).show();},
                },error -> {Toast.makeText(view.getContext()," verfication code fail",Toast.LENGTH_SHORT).show();}
        );
    }

    private void signUp(View view) {
        String email = activityMainBinding.userEmailMainActivity.getText().toString().trim();
        String password = activityMainBinding.userPasswordMainActivity.getText().toString().trim();
        Amplify.Auth.signUp(
                email,password,
                AuthSignUpOptions.builder().userAttribute(AuthUserAttributeKey.email(), email).build(),
                result -> {
                    Log.i("AuthQuickStart", "Result: " + result.toString());
                   Toast.makeText(view.getContext(),"signup successful,check email for verfication code",Toast.LENGTH_SHORT).show();
                },
                error ->{Toast.makeText(view.getContext(),"signup fail"+error.getLocalizedMessage(),Toast.LENGTH_SHORT).show();}
        );
    }

    private void UserLogin(View view) {
        String email = activityMainBinding.userEmailMainActivity.getText().toString().trim();
        String password = activityMainBinding.userPasswordMainActivity.getText().toString().trim();
        Amplify.Auth.signIn(
                email,
                password,
                result ->{
//                    Toast.makeText(view.getContext(),"signup successful,check email for verfication code",Toast.LENGTH_SHORT).show();
                    Log.i("AuthQuickstart", result.isSignInComplete() ? "Sign in succeeded" : "Sign in not complete");
                    startActivity(new Intent(this,SecondActivity.class));},
                error ->{Toast.makeText(view.getContext(),""+error.getLocalizedMessage(),Toast.LENGTH_SHORT).show();}
        );
    }
}