package bilelfarhat.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class Edit_Profil extends AppCompatActivity {
    EditText Editname,Editlastname,Editnumber;
    String name, lastName, number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);
        Editname = findViewById(R.id.editText_name);
        Editlastname = findViewById(R.id.editText_lastname);
        Editnumber = findViewById(R.id.editText_number);

// Pré-remplir les champs avec les données du profil
        Editname.setText(name);
        Editlastname.setText(lastName);
        Editnumber.setText(number);

    }
}