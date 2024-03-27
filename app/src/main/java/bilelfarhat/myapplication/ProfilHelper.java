package bilelfarhat.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ProfilHelper extends SQLiteOpenHelper {
    public static final String table_profil="Profils";
    public static final String col_name="Name";
    public static final String col_lastName="LastName";
    public static final String col_number="Number";
    String requete="create table "+table_profil+" ("+col_name+" Text not null," +
            col_lastName+" Text not null," +
            col_number+" Text not null)";
    public ProfilHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //lors de louverture de la base pour la premiere fois
        db.execSQL(requete);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //modification de la version
        db.execSQL(" drop table "+table_profil);
        onCreate(db);

    }
}
