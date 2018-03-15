package mx.iteso.erickgarcia.ejerciciosqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by erickgarcia on 14/03/18
 */

public class AdminMateriaBD extends SQLiteOpenHelper {
    private String MtoCat_materias = "create table MtoCat_Materias (id integer primary key autoincrement, ClaveMateria text not null, NombreMateria text not null)";

    public AdminMateriaBD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MtoCat_materias);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
