package front.inyecmotor;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {
    private List<Producto> productos;
    private Context context;
    private static final String BASE_URL = "http://192.168.1.3:8080"; // Cambia a la URL de tu servidor
    private static final String TAG = "ProductoAdapter"; // Tag para los logs

    public ProductoAdapter(List<Producto> productos, Context context) {
        this.productos = productos;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        Producto producto = productos.get(position);
        holder.tvProductoNombre.setText(producto.getNombre());
        holder.tvProductoPrecio.setText("Precio: $" + producto.getPrecioCosto());

        holder.btnVerDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogoDetalle(producto);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView tvProductoNombre;
        TextView tvProductoPrecio;
        Button btnVerDetalle;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductoNombre = itemView.findViewById(R.id.tvProductoNombre);
            tvProductoPrecio = itemView.findViewById(R.id.tvProductoPrecio);
            btnVerDetalle = itemView.findViewById(R.id.btnVerDetalle);
        }
    }

    private void mostrarDialogoDetalle(Producto producto) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Detalles del Producto");

        View viewInflated = LayoutInflater.from(context).inflate(R.layout.dialogo_detalle_producto, null);
        builder.setView(viewInflated);

        EditText etNombre = viewInflated.findViewById(R.id.etNombre);
        EditText etPrecioCosto = viewInflated.findViewById(R.id.etPrecioCosto);
        EditText etPrecioVenta = viewInflated.findViewById(R.id.etPrecioVenta);
        EditText etStockActual = viewInflated.findViewById(R.id.etStockActual);

        // Setear valores iniciales del producto
        etNombre.setText(producto.getNombre());
        etPrecioCosto.setText(String.valueOf(producto.getPrecioCosto()));
        etPrecioVenta.setText(String.valueOf(producto.getPrecioVenta()));
        etStockActual.setText(String.valueOf(producto.getStockActual()));

        builder.setPositiveButton("Guardar", (dialog, which) -> {
            // Actualizar el objeto producto con los valores del EditText
            producto.setNombre(etNombre.getText().toString());
            producto.setPrecioCosto(Double.parseDouble(etPrecioCosto.getText().toString()));
            producto.setPrecioVenta(Double.parseDouble(etPrecioVenta.getText().toString()));
            producto.setStockActual(Integer.parseInt(etStockActual.getText().toString()));

            // Enviar los datos editados al servidor
            enviarDatosProducto(producto);

            // Notificar al adaptador que los datos han cambiado
            notifyDataSetChanged();
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void enviarDatosProducto(Producto producto) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<Producto> call = apiService.editarProducto(producto);

        call.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Producto actualizado correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Error al actualizar el producto", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Response Code: " + response.code());
                    Log.d(TAG, "Response Message: " + response.message());
                    if (response.errorBody() != null) {
                        try {
                            Log.d(TAG, "Error Body: " + response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                Toast.makeText(context, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }


}
