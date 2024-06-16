package front.inyecmotor;

import java.util.List;

public class Proveedor {

    private int id;
    private String cuit;
    private String tel;
    private String direccion;
    private String nombre;
    private String email;

    public Proveedor() {

    }

    public Proveedor(int id, String cuit, String tel, String direccion, String nombre, String email) {
        this.id = id;
        this.cuit = cuit;
        this.tel = tel;
        this.direccion = direccion;
        this.nombre = nombre;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
