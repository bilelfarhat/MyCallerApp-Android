package bilelfarhat.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyProfilAdapter extends BaseAdapter {
    //role creer les views
    Context con;
    ArrayList<Profil> data;
    MyProfilAdapter(Context con , ArrayList<Profil> data){
        this.con=con;
        this.data=data;
    }


    @Override
    public int getCount() {
        //retourne le nbre des views a crier
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //creation d'un prototype
        Button b=new Button(con);

        //convertir code xml
        LayoutInflater inf=LayoutInflater.from(con);
        View v=inf.inflate(R.layout.view_profil,null);



        //recuperation des holders/view
        TextView tvname=v.findViewById(R.id.tvfirstname_profil);
        TextView tvlastname=v.findViewById(R.id.tvlastname_profil);
        TextView tvnumber=v.findViewById(R.id.tvnumber_profil);

        ImageView imageDelete=v.findViewById(R.id.imageViewDelete_Profil);
        ImageView imageEdit=v.findViewById(R.id.imageViewEdit_profil);
        ImageView imageColl=v.findViewById(R.id.imageViewColl_profil);

        //recuperation de la donné
        Profil p=data.get(position);

        //afficher le view/HOLDER
        tvname.setText(p.name);
        tvlastname.setText(p.lastName);
        tvnumber.setText(p.number);


        //action sur les holders
        imageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert=new AlertDialog.Builder(con);
                alert.setTitle("suppression");
                alert.setMessage("confirmer la suppression");
                alert.setPositiveButton("Confirmer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //suprission
                        data.remove(position);
                        //refreche
                        notifyDataSetChanged();

                    }
                });
                alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
               // alert.setNeutralButton("Exit",null);
                alert.show();

            }
        });

        imageColl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //numiratation
                Intent i =new Intent();
                i.setAction(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:"+p.number));
                con.startActivity(i);
            }
        });



        return v;
    }
}
