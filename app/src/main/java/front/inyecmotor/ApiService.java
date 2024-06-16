package front.inyecmotor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @GET("/producto/all") // La ruta de tu endpoint en el servidor Spring Boot
    Call<List<Producto>> getProductos();

    @PATCH("/producto/editar")
    Call<Producto> editarProducto(@Body Producto producto);
    @POST("/producto/crear")  // <-- Añadir esta línea
    Call<ProductoCreate> crearProducto(@Body ProductoCreate nuevoProducto);

    @GET("/tipo/all")
    Call<List<TipoDTO>> getTipos(); // Define el nuevo endpoint para obtener las tipos

    @GET("/proveedor/all")
    Call<List<ProveedorDTO>> getProveedores(); // Define el nuevo endpoint para obtener las tipos
}

