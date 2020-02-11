package com.iesfranciscodelosrios.informatica.nbapp.views;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.iesfranciscodelosrios.informatica.nbapp.R;
import com.iesfranciscodelosrios.informatica.nbapp.interfaces.FormularioInterface;
import com.iesfranciscodelosrios.informatica.nbapp.models.PersonR;
import com.iesfranciscodelosrios.informatica.nbapp.presenters.FormularioPresenter;

import android.app.DatePickerDialog;;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Spinner;

import static com.iesfranciscodelosrios.informatica.nbapp.R.id.NombreEquipo;
import static com.iesfranciscodelosrios.informatica.nbapp.R.id.buttonAddSpinner;
import static com.iesfranciscodelosrios.informatica.nbapp.R.id.buttonEliminar;
import static com.iesfranciscodelosrios.informatica.nbapp.R.id.image;
import static com.iesfranciscodelosrios.informatica.nbapp.R.id.spinnerAdd;
import static com.iesfranciscodelosrios.informatica.nbapp.R.id.topeSalarial;


public class FormularioActivity extends AppCompatActivity implements FormularioInterface.View,View.OnClickListener {
    private FormularioInterface.Presenter presenter;
    String TAG="NBApp/FormularioActivity";
    private static final String CERO = "0";
    private static final String BARRA = "/";
    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    SQLiteDatabase db;

    private static final int REQUEST_CAPTURE_IMAGE = 200;
    private static final int REQUEST_SELECT_IMAGE = 201;
    private Uri uri;


    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    //Widgets
    EditText Fechadecreación;
    ImageButton imageFecha;
    private Context myContext=this;
    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    ImageButton buttonAdd;
    TextInputLayout TextNombre;
    ImageButton imageButton;
    private Bitmap bmp;
    TextInputEditText nombre;
    final private int CODE_READ_EXTERNAL_STORAGE_PERMISSION=123;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        final AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Widget EditText donde se mostrara la fecha obtenida
        Fechadecreación = (EditText) findViewById(R.id.Fechadecreación);
        //Widget ImageButton del cual usaremos el evento clic para obtener la fecha
        imageFecha = (ImageButton) findViewById(R.id.imageFecha);
        //Evento setOnClickListener - clic
        imageFecha.setOnClickListener(this);

        TextNombre = (TextInputLayout) findViewById(R.id.textInputLayout);

        imageButton=(ImageButton) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                presenter.onClickImage(myContext);
            }
        });

        presenter = new FormularioPresenter(this);
        presenter.UpButton();

        Button button = findViewById(R.id.botonGuardar);
        nombre=(TextInputEditText)findViewById(R.id.nombre);


        //nombre = (TextInputEditText) findViewById(R.id.nombre);
        //topeSalarial = ()findViewById(topeSalarial);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonR p = new PersonR();
                p.setnEquipo(nombre.getText().toString());
                presenter.botonGuardar(p);
            }
        });


        TextInputEditText nombre = (TextInputEditText) findViewById(R.id.nombre);
        nombre.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    TextInputEditText et = (TextInputEditText) view;
                    Log.d("AppCRUD", et.getText().toString());
                    if (et.getText().toString().startsWith("Incorrecto")) {
                        TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.textInputLayout);
                        textInputLayout.setError("Nombre incorrecto");
                    } else {
                        TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.textInputLayout);
                        textInputLayout.setError("");
                    }
                }



            }
        });

        Button eliminar = findViewById(buttonEliminar);
        eliminar.setEnabled(false);
        eliminar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                dialogo1.setTitle("ATENCION!");
                dialogo1.setMessage("Va a eliminar los datos introducidos esta seguro?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        Aceptar();
                    }

                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int buttonEliminar) {
                        Cancelar();
                    }
                });
                dialogo1.show();
            }

            public void Aceptar() {

                finish();
            }

            public void Cancelar() {


            }
        });

        ArrayList<String> items = new ArrayList<String>();


        // Definición del Adaptador que contiene la lista de opciones
        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        // Definición del Spinner
        spinner = (Spinner) findViewById(spinnerAdd);
        spinner.setAdapter(adapter);

        // Definición de la acción del botón
        buttonAdd = (ImageButton) findViewById(buttonAddSpinner);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View w) {
                // Recuperación de la vista del AlertDialog a partir del layout de la Actividad
                LayoutInflater layoutActivity = LayoutInflater.from(myContext);
                View viewAlertDialog = layoutActivity.inflate(R.layout.alert_dialog, null);

                // Definición del AlertDialog
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(myContext);

                // Asignación del AlertDialog a su vista
                alertDialog.setView(viewAlertDialog);

                // Recuperación del EditText del AlertDialog
                final EditText dialogInput = (EditText) viewAlertDialog.findViewById(R.id.dialogInput);

                // Configuración del AlertDialog
                alertDialog
                        .setCancelable(false)
                        // Botón Añadir
                        .setPositiveButton(getResources().getString(R.string.add_new_option),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        adapter.add(dialogInput.getText().toString());
                                        spinner.setSelection(adapter.getPosition(dialogInput.getText().toString()));
                                    }
                                })
                        // Botón Cancelar
                        .setNegativeButton(getResources().getString(R.string.cancel),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        dialogBox.cancel();
                                    }
                                })
                        .create()
                        .show();
            }
        });

        if(imageButton.getDrawable() == null){
            imageButton.setImageResource(R.drawable.nbapp);
        }
        ImageButton ib= findViewById(R.id.imageButton);
        BitmapDrawable bmDr=(BitmapDrawable) ib.getDrawable();
        if (bmDr != null){
            bmp=bmDr.getBitmap();
        }else{
            bmp=null;
        }


        PersonR p = new PersonR();
        p.setnEquipo(nombre.getText().toString());
        Log.d(TAG,"Pulsando boton flotante...");
        presenter.botonGuardar(p);// esto en el boton guardar en el oncreate

    }
    @Override
    public void botonGuardar() {
        Log.d(TAG, "Lanzando Formulario..");
        Intent intent = new Intent(FormularioActivity.this, ListadoActivity.class);
        startActivity(intent);
    }


    @Override
    protected  void onStart(){
        super.onStart();
        Log.d(TAG,"onStart...");
    }
    @Override
    protected  void onResume(){

        super.onResume();
        Log.d(TAG,"onResume...");
        /*
        String[] campos = new String[] {"id", "nombre","tSalarial"};
        String[] args = new String[] {"equipos"};

        Cursor c = db.query("nbaDB", campos, "null=?", args, null, null, null);

        //Nos aseguramos de que existe al menos un registro
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                String id = c.getString( c.getColumnIndex("id") ); // id is column name in db
                String nombre= c.getString(c.getColumnIndex("nombre"));
                String tSalarial = c.getString(c.getColumnIndex("tSalarial"));
            } while(c.moveToNext());
        */
    }

    @Override
    protected  void onPause(){
        super.onPause();
        Log.d(TAG,"onPause...");
    }
    @Override
    protected  void onStop(){
        super.onStop();
        Log.d(TAG,"onStop...");
    }
    @Override
    protected  void onRestart(){
        super.onRestart();
        Log.d(TAG,"onRestart...");
    }
    @Override
    protected  void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy...");
    }


    @Override
    public void UpButton() {
        Log.d(TAG,"Lanzando Formulario..");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG,"onBackPressed...");
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        Log.d(TAG,"onSupportnavigateUp...");
        return super.onSupportNavigateUp();
    }




        public void onClick(View v) {
            switch (v.getId()){
                case R.id.imageFecha:
                    obtenerFecha();
                    break;
            }
        }

        private void obtenerFecha(){
            DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                    final int mesActual = month + 1;
                    //Formateo el día obtenido: antepone el 0 si son menores de 10
                    String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                    //Formateo el mes obtenido: antepone el 0 si son menores de 10
                    String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                    //Muestro la fecha con el formato deseado
                    Fechadecreación.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);


                }
                //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
                /**
                 *También puede cargar los valores que usted desee
                 */
            },anio, mes, dia);
            //Muestro el widget
            recogerFecha.show();

        }
    public void onClickImage(View v) {
        presenter.onClickImage(myContext);

    }
    public void requestPermission(){
        ActivityCompat.requestPermissions(FormularioActivity.this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, CODE_READ_EXTERNAL_STORAGE_PERMISSION);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case CODE_READ_EXTERNAL_STORAGE_PERMISSION:
                presenter.resultPermission(grantResults[0]);
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void launchGallery(){
        // Se le pide al sistema una imagen del dispositivo
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(intent, getResources().getString(R.string.selecImagenFormu)), REQUEST_SELECT_IMAGE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case (REQUEST_CAPTURE_IMAGE):
                if (resultCode == FormularioActivity.RESULT_OK) {
                    // Se carga la imagen desde un objeto URI al imageView
                    ImageView imageView = findViewById(R.id.imageButton);
                    imageView.setImageURI(uri);

                    // Se le envía un broadcast a la Galería para que se actualice
                    Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    mediaScanIntent.setData(uri);
                    sendBroadcast(mediaScanIntent);
                } else if (resultCode == Activity.RESULT_CANCELED) {
                    // Se borra el archivo temporal
                    File file = new File(uri.getPath());
                    file.delete();
                }
                break;

            case (REQUEST_SELECT_IMAGE):
                if (resultCode == Activity.RESULT_OK) {
                    // Se carga la imagen desde un objeto Bitmap
                    Uri selectedImage = data.getData();
                    String selectedPath = selectedImage.getPath();

                    if (selectedPath != null) {
                        // Se leen los bytes de la imagen
                        InputStream imageStream = null;
                        try {
                            imageStream = getContentResolver().openInputStream(selectedImage);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                        // Se transformam los bytes de la imagen a un Bitmap
                        Bitmap bmp = BitmapFactory.decodeStream(imageStream);

                        // Se carga el Bitmap en el ImageView
                        ImageView imageView = findViewById(R.id.imageButton);
                        imageView.setImageBitmap(bmp);
                    }
                }
                break;
        }


    }

    public static void insertar(Context myContext){
        //Abrimos la base de datos 'DBUsuarios' en modo escritura
        /*
        SQLiteDatabase basedatos = new SQLiteDatabase(myContext);

        SQLiteDatabase db = basedatos.getWritableDatabase();


        //Si hemos abierto correctamente la base de datos
        if (db != null) {

            //Insertamos los datos en la tabla Usuarios

            db.execSQL("INSERT INTO Preguntas (nEquipo,tSalarial, imagen) " +
                    "VALUES ('" + nombre.getNombre() + "', '" + topeSalarial.gettTopeSalarial() + "', '" + image.getImagen() + "')");


            //Cerramos la base de datos
            db.close();
        }

        */
    }

}
