package bilelfarhat.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Add_Profil extends AppCompatActivity {
    ArrayList<Profil> data=new ArrayList<>();

    private Button btnSave;
    private Button btnCancel;
    private Button btnBack;
    EditText edname,edlastname,ednumber;
    ProfilManager manager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btnSave=findViewById(R.id.btnSave_add);
        btnCancel=findViewById(R.id.btnCancel_add);
        btnBack=findViewById(R.id.btnBack_add);
        edname=findViewById(R.id.edName_add);
        edlastname=findViewById(R.id.edLast_Name_add);
        ednumber=findViewById(R.id.edNumber_add);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=edname.getText().toString();
                String lastName=edlastname.getText().toString();
                String number=ednumber.getText().toString();

               // Profil p =new Profil(name,lastName,number);
               // Home.data.add(p);
                //a faire rejoindre l'aregistrement dans la base SQLITE
                /////

                manager=new ProfilManager(Add_Profil.this);
                manager.ouvrir();

                // Insérer les données dans la base de données
                long result = manager.ajout(name, lastName, number);

                // Vérifier si l'insertion s'est bien passée
                if (result != -1) {
                    // Afficher un message de succès
                    Toast.makeText(Add_Profil.this, "Profil ajouté avec succès", Toast.LENGTH_SHORT).show();
                } else {
                    // Afficher un message d'échec
                    Toast.makeText(Add_Profil.this, "Erreur lors de l'ajout du profil", Toast.LENGTH_SHORT).show();
                }

                // Fermer la connexion avec la base de données
                manager.fermer();
                ///
                Intent i=new Intent(Add_Profil.this, Home.class);
                startActivity(i);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Add_Profil.this, Home.class);
                startActivity(i);
            }
        });



    }
}