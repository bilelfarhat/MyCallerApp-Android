package bilelfarhat.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    public static ArrayList<Profil> data = new ArrayList<Profil>();

    private Button btnAdd;
    private Button btnList, btnLogout;

    // Constants for SharedPreferences
    private static final String SHARED_PREF_NAME = "my_shared_pref";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Recuperer les composantes
        btnAdd = findViewById(R.id.btnAdd_home);
        btnList = findViewById(R.id.bntList_home);
        btnLogout = findViewById(R.id.btnLogout_home);

        // Evennement
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, Add_Profil.class);
                startActivity(i);
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, List_Profil.class);
                startActivity(i);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear the SharedPreferences data
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                // Redirect to MainActivity
                Intent intent = new Intent(Home.this, MainActivity.class);
                // Add flag to clear back stack
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // Close the current activity
            }
        });
    }
}
