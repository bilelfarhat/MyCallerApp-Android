package bilelfarhat.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Constants for SharedPreferences
    private static final String SHARED_PREF_NAME = "my_shared_pref";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_REMEMBER_ME = "rememberMe";

    // Declaration des composantes
    EditText edEmail, edPassword;
    private Button btnLogin, btnExit, btnRemember, btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Mettre un fichier xml sur l'ecran
        setContentView(R.layout.activity_main);

        // Recuperation des composantes
        edEmail = findViewById(R.id.edEmail_auth);
        edPassword = findViewById(R.id.edPassword_auth);
        btnLogin = findViewById(R.id.btnLogin_auth);
        btnExit = findViewById(R.id.btnExit_auth);
        btnRemember = findViewById(R.id.btnRemenber_auth);
        btnCreate = findViewById(R.id.btnCreate_auth);

        // Initialize SharedPreferences
        final SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        // Check if the user is already logged in and Remember option is active
        boolean isLoggedIn = sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
        boolean rememberMe = sharedPreferences.getBoolean(KEY_REMEMBER_ME, false);
        if (isLoggedIn && rememberMe) {
            // If already logged in and Remember option is active, start the Home activity and finish the MainActivity
            startActivity(new Intent(MainActivity.this, Home.class));
            finish();
        }

        // Evennements
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Recuperer les chaines de saisies
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                if (!email.isEmpty() && !password.isEmpty()) {
                    if (email.equals("azer") && password.equals("111")) {
                        // Enregistrer l'email dans SharedPreferences
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(KEY_EMAIL, email);

                        // Check if Remember option is active
                        if (btnRemember.isSelected()) {
                            editor.putBoolean(KEY_REMEMBER_ME, true);
                        }

                        editor.putBoolean(KEY_IS_LOGGED_IN, true);
                        editor.apply();

                        // Passage vers une autre page
                        Intent i = new Intent(MainActivity.this, Home.class);
                        startActivity(i);
                        finish();
                    } else {
                        // Message d'erreur
                        Toast.makeText(MainActivity.this, "Erreur saisie", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnRemember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle the selected state of the Remember button
                btnRemember.setSelected(!btnRemember.isSelected());
            }
        });
    }
}
