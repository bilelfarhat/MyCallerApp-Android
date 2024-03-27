package bilelfarhat.myapplication;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import android.Manifest;


public class MyRecycleProfilAdapter extends RecyclerView.Adapter<MyRecycleProfilAdapter.MyViewHolder> {
    Context con;
    ArrayList<Profil> data;


    public MyRecycleProfilAdapter(Context con, ArrayList<Profil> data) {
        this.con = con;
        this.data = data;


    }



    @NonNull
    @Override
    public MyRecycleProfilAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //creation de la view
        //convertir code xml
        LayoutInflater inf=LayoutInflater.from(con);
        View v=inf.inflate(R.layout.view_profil,null);

        return new MyViewHolder(v);
    }
    public void setData(ArrayList<Profil> newData) {
        data = newData;
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(@NonNull MyRecycleProfilAdapter.MyViewHolder holder, int position) {
        //affectattion des holders
        //recuperation de la donné
        Profil p=data.get(position);

        //afficher le view/HOLDER
        holder.tvname.setText(p.name);
        holder.tvlastname.setText(p.lastName);
        holder.tvnumber.setText(p.number);

    }


    @Override
    public int getItemCount() {
        // Sinon, retourner la taille de la liste filtrée
        return data.size();

    }




    public class MyViewHolder extends RecyclerView.ViewHolder {
        private static final int CALL_PHONE_REQUEST_CODE = 1001; // Define request code
        TextView tvname,tvlastname,tvnumber;
        ImageView imageDelete,imageEdit,imageColl;
        private static final int CALL_PERMISSION_REQUEST_CODE = 100;


        public MyViewHolder(@NonNull View v) {
            super(v);

            //recuperation des holders/view
            tvname=v.findViewById(R.id.tvfirstname_profil);
            tvlastname=v.findViewById(R.id.tvlastname_profil);
            tvnumber=v.findViewById(R.id.tvnumber_profil);

            imageDelete=v.findViewById(R.id.imageViewDelete_Profil);
            imageEdit=v.findViewById(R.id.imageViewEdit_profil);
            imageColl=v.findViewById(R.id.imageViewColl_profil);

            //evennement

            imageDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(con);
                    alert.setTitle("Suppression");
                    alert.setMessage("Confirmer la suppression");
                    alert.setPositiveButton("Confirmer", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Récupérer la position de l'élément sélectionné
                            int position = getAdapterPosition();
                            ProfilManager manager = new ProfilManager(con);
                            Profil profil = data.get(position);
                            manager.ouvrir();
                            int rowsDeleted = manager.delete(profil.number);
                            manager.fermer();
                            // Actualiser le RecyclerView
                            if (rowsDeleted > 0) {
                                data.remove(position); // Supprimer l'élément de la liste des données
                                notifyItemRemoved(position); // Notifier l'adaptateur de la suppression
                            }
                        }
                    });
                    alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Annuler la suppression
                        }
                    });
                    alert.show();
                }
            });



            imageColl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    // Check if the permission to make a phone call is granted
                    if (ContextCompat.checkSelfPermission(con, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        // If permission is granted, initiate the call
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:" + data.get(position).number));
                        con.startActivity(callIntent);
                    } else {
                        // Request permission to make a phone call
                        ActivityCompat.requestPermissions((Activity) con, new String[]{Manifest.permission.CALL_PHONE}, CALL_PERMISSION_REQUEST_CODE);
                    }
                }
            });





            imageEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    editProfil(position);

                }
            });



        }
        private void editProfil(final int position) {
            LayoutInflater inflater = LayoutInflater.from(con);
            View view = inflater.inflate(R.layout.activity_edit_profil, null);

            // récupérer les champs
            final EditText editName = view.findViewById(R.id.editText_name);
            final EditText editLastname = view.findViewById(R.id.editText_lastname);
            final EditText editNumber = view.findViewById(R.id.editText_number);

            // récupérer le contenu des champs
            Profil profil = data.get(position);
            editName.setText(profil.name);
            editLastname.setText(profil.lastName);
            editNumber.setText(profil.number);

            AlertDialog.Builder builder = new AlertDialog.Builder(con);
            builder.setView(view)
                    .setTitle("Edit Profil")
                    .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Mettez ici le code pour enregistrer les modifications du profil
                            String newName = editName.getText().toString();
                            String newLastname = editLastname.getText().toString();
                            String newNumber = editNumber.getText().toString();

                            ProfilManager manager = new ProfilManager(con);
                            manager.ouvrir();
                            int rowsUpdated = manager.update( profil.number,newName, newLastname, newNumber);
                            manager.fermer();

                            // Vérifiez si la mise à jour a réussi
                            if (rowsUpdated > 0) {
                                // Mettez à jour le profil dans la liste
                                Profil updatedProfil = new Profil(newName, newLastname, newNumber);
                                data.set(position, updatedProfil);
                                notifyDataSetChanged();
                                Toast.makeText(con, "Profile updated successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(con, "Failed to update profile", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }


    }
}