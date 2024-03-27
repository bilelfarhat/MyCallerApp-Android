package bilelfarhat.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ProfilManager {
    SQLiteDatabase db=null;
    Context con;
    ProfilManager(Context con)
    {
        this.con=con;
    }
    public void ouvrir()
    {
        ProfilHelper helper=new ProfilHelper(con,"mabase.db",null,2);
        db=helper.getWritableDatabase();
    }
    public long ajout(String name,String lastname,String number)
    {
        long a=0;
        ContentValues values=new ContentValues();
        values.put(ProfilHelper.col_name,name);
        values.put(ProfilHelper.col_lastName,lastname);
        values.put(ProfilHelper.col_number,number);
        a=db.insert(ProfilHelper.table_profil,null,values);
        return a;
    }
    public ArrayList<Profil> getAllProfil()
    {
        ArrayList<Profil> l=new ArrayList<Profil>();
        Cursor cr= db.query(ProfilHelper.table_profil,
                new String[]{ProfilHelper.col_name,
                        ProfilHelper.col_lastName,
                        ProfilHelper.col_number},null,null,null,null,null);
        cr.moveToFirst();
        while (!cr.isAfterLast()) {
            String i1 = cr.getString(0);
            String i2 = cr.getString(1);
            String i3 = cr.getString(2);
            l.add(new Profil(i1, i2, i3));
            cr.moveToNext();
        }
        return l;

    }
    public void supprimer()
    {
    }

    public int update(String number, String name, String lastname, String newNumber) {
        ContentValues values = new ContentValues();
        values.put(ProfilHelper.col_name, name);
        values.put(ProfilHelper.col_lastName, lastname);

        // La clause WHERE pour identifier la ligne à mettre à jour
        String selection = ProfilHelper.col_number + " = ?";
        String[] selectionArgs = { number };

        // Effectuer la mise à jour et retourner le nombre de lignes mises à jour
        int rowsUpdated = db.update(ProfilHelper.table_profil, values, selection, selectionArgs);

        return rowsUpdated;
    }

    public int delete(String number) {
        // La clause WHERE pour identifier le profil à supprimer
        String selection = ProfilHelper.col_number + " = ?";
        String[] selectionArgs = {number};

        // Supprimer le profil de la base de données et retourner le nombre de lignes supprimées
        int rowsDeleted = db.delete(ProfilHelper.table_profil, selection, selectionArgs);

        return rowsDeleted;
    }


    public void fermer()
    {
    }
}
