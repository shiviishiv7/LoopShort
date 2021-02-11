package com.example.loopshort;


import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        try {
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSDataStorePlugin());
            // Add this line, to include the Auth plugin.
            Amplify.addPlugin(new AWSCognitoAuthPlugin());

            Amplify.configure(getApplicationContext());
//            Amplify.Auth.fetchAuthSession(
//                    result -> {
//                        Intent intent = new Intent(this, SecondActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(intent);},
//                    error -> Log.e("AmplifyQuickstart", error.toString())
//            );
            Intent intent = new Intent(this, SecondActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            AuthUser currentUser = Amplify.Auth.getCurrentUser();
            if(currentUser!=null)   startActivity(intent);
            Log.i("Tutorial", "Initialized Amplify");
        } catch (AmplifyException failure) {
            Log.e("Tutorial", "Could not initialize Amplify", failure);
        }


    }
}
