package com.ajdi.yassin.instajournal;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String ANONYMOUS = "anonymous";
    private static final int RC_SIGN_IN = 123;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private String mUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase components
        mFirebaseAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // already signed in
                    onSignedInInitialize(user.getDisplayName());
                } else {
                    // not signed in
                    onSignedOutCleanup();

                    // Choose authentication providers
                    List<AuthUI.IdpConfig> providers = Arrays.asList(
                            new AuthUI.IdpConfig.EmailBuilder().build(),
                            new AuthUI.IdpConfig.PhoneBuilder().build(),
                            new AuthUI.IdpConfig.GoogleBuilder().build());

                    // Create and launch sign-in intent
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(!BuildConfig.DEBUG /* credentials */, true /* hints */)
                                    .setAvailableProviders(providers)
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };
    }

    private void onSignedInInitialize(String username) {
        mUsername = username;
        attachDatabaseReadListener();
    }

    private void onSignedOutCleanup() {
        mUsername = ANONYMOUS;
        //mMessageAdapter.clear();
        detachDatabaseReadListener();
    }

    private void attachDatabaseReadListener() {
        // Attach listener to read from database
    }

    private void detachDatabaseReadListener() {
        // detach listener from database
    }
}
