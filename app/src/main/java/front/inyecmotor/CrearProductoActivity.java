package front.inyecmotor;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CrearProductoActivity extends AppCompatActivity {
    private EditText etCodigo, etNombre, etPrecioCosto, etPrecioVenta, etStockActual, etStockMax, etStockMin;
    private Button btnCrearProducto;

    private static final String BASE_URL = "http://192.168.0.103:8080"; // Cambia a la URL de tu servidor
    private ApiService apiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_producto);

        etCodigo = findViewById(R.id.etCodigo);
        etNombre = findViewById(R.id.etNombre);
        etPrecioCosto = findViewById(R.id.etPrecioCosto);
        etPrecioVenta = findViewById(R.id.etPrecioVenta);
        etStockActual = findViewById(R.id.etStockActual);
        etStockMax = findViewById(R.id.etStockMax);
        etStockMin = findViewById(R.id.etStockMin);
        btnCrearProducto = findViewById(R.id.btnCrearProducto);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);

        btnCrearProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearProducto();
            }
        });
    }

    private void crearProducto() {
        String codigo = etCodigo.getText().toString();
        String nombre = etNombre.getText().toString();
        double precioCosto = Double.parseDouble(etPrecioCosto.getText().toString());
        double precioVenta = Double.parseDouble(etPrecioVenta.getText().toString());
        int stockActual = Integer.parseInt(etStockActual.getText().toString());
        int stockMax = Integer.parseInt(etStockMax.getText().toString());
        int stockMin = Integer.parseInt(etStockMin.getText().toString());
        ArrayList <Integer> proveedores = new ArrayList<>();
        proveedores.add(2);
        ArrayList <Integer> modelos = new ArrayList<>();
        modelos.add(2);
        ArrayList <Integer> marcas = new ArrayList<>();
        marcas.add(2);
        ArrayList <Integer> tipos = new ArrayList<>();
        tipos.add(2);
        Long id = new Long(99999);
        ProductoCreate nuevoProducto = new ProductoCreate(id, codigo, nombre,stockMin, stockMax,stockActual , precioVenta, precioCosto,proveedores,tipos,marcas,modelos);


        Call<ProductoCreate> call = apiService.crearProducto(nuevoProducto);
        call.enqueue(new Callback<ProductoCreate>() {
            @Override
            public void onResponse(Call<ProductoCreate> call, Response<ProductoCreate> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(CrearProductoActivity.this, "Producto creado exitosamente", Toast.LENGTH_SHORT).show();
                    Log.e("Error", "Error: " + response.toString());
                    finish();

                } else {
                    Toast.makeText(CrearProductoActivity.this, "Error al crear el producto", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProductoCreate> call, Throwable t) {
                Toast.makeText(CrearProductoActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
