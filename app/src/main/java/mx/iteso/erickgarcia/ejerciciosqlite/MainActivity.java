package mx.iteso.erickgarcia.ejerciciosqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button agregar, eliminar, modificar, consultar;
    private TextView agregar_materia, clave_materia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        agregar_materia = (TextView) findViewById(R.id.txt_nombre_materia);
        clave_materia = (TextView) findViewById(R.id.txt_clave);

        agregar = (Button) findViewById(R.id.btn_agregar);
        eliminar = (Button) findViewById(R.id.btn_eliminar);
        modificar = (Button) findViewById(R.id.btn_modificar);
        consultar = (Button) findViewById(R.id.btn_consultar);

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!clave_materia.equals("") && !agregar_materia.equals("")) {
                    AdminMateriaBD adminMateriaBD = new AdminMateriaBD(MainActivity.this, "materias.db", null, 1);
                    SQLiteDatabase db = adminMateriaBD.getWritableDatabase();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("ClaveMateria", clave_materia.getText().toString());
                    contentValues.put("NombreMateria", agregar_materia.getText().toString());
                    if (db.insert("MtoCat_Materias", null, contentValues) > 0) {
                        Toast.makeText(MainActivity.this, "registro creadotl", Toast.LENGTH_SHORT).show();
                        agregar_materia.setText("");
                        clave_materia.setText("");
                        clave_materia.requestFocus();
                    } else
                        Toast.makeText(MainActivity.this, "error al agregar la wea", Toast.LENGTH_SHORT).show();
                    db.close();
                } else
                    Toast.makeText(MainActivity.this, "favor de agregar los datos", Toast.LENGTH_SHORT).show();
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!clave_materia.getText().equals("")) {
                    AdminMateriaBD adminMateriaBD = new AdminMateriaBD(MainActivity.this, "materias.db", null, 1);
                    SQLiteDatabase db = adminMateriaBD.getWritableDatabase();
                    // para eliminar tocho: db.execSQL("delete from MtoCat_Materias");
                    if (db.delete("MtoCat_Materias", "ClaveMateria="+clave_materia.getText().toString(), null) > 0) {
                        Toast.makeText(MainActivity.this, "registro eliminado bien", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(MainActivity.this, "error al eliminar la wea", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(MainActivity.this, "ingresa la wea bien", Toast.LENGTH_SHORT).show();
            }
        });

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminMateriaBD adminMateriaBD = new AdminMateriaBD(MainActivity.this, "materias.db", null, 1);
                SQLiteDatabase db = adminMateriaBD.getWritableDatabase();
                if (!clave_materia.equals("") && !agregar_materia.equals("")) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("ClaveMateria", clave_materia.getText().toString());
                    contentValues.put("NombreMateria", agregar_materia.getText().toString());
                    db.update("MtoCat_Materias", contentValues, "ClaveMateria="+clave_materia.getText().toString(), null);
                    db.close();
                    Toast.makeText(MainActivity.this, "registro modificado con exito", Toast.LENGTH_SHORT).show();
                    agregar_materia.setText("");
                    clave_materia.setText("");
                    clave_materia.requestFocus();
                }
            }
        });

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminMateriaBD adminMateriaBD = new AdminMateriaBD(MainActivity.this, "materias.db", null, 1);
                SQLiteDatabase db = adminMateriaBD.getReadableDatabase();
                if (!clave_materia.getText().equals("")) {
                    Cursor fila = db.rawQuery("select NombreMateria from MtoCat_Materias where ClaveMateria="
                            + clave_materia.getText().toString(), null);
                    if (fila.moveToFirst()){
                        agregar_materia.setText(fila.getString(0));
                    } else
                        Toast.makeText(MainActivity.this, "no hay registro con esta clave", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(MainActivity.this, "ingresa la wea bien", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
