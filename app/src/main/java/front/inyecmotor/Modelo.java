package front.inyecmotor;

public class Modelo {
    private int id; // Variable id
    private String nombre;
    private double motorLitros;
    private String motorTipo;
    private int anio;

    // Constructor vacío (puede ser útil para algunas operaciones)
    public Modelo() {
    }

    // Constructor con parámetro para inicializar el campo nombre


    public Modelo(int id, String nombre, double motorLitros, String motorTipo, int anio) {
        this.id = id;
        this.nombre = nombre;
        this.motorLitros = motorLitros;
        this.motorTipo = motorTipo;
        this.anio = anio;
    }

    public double getMotorLitros() {
        return motorLitros;
    }

    public String getMotorTipo() {
        return motorTipo;
    }

    public int getAnio() {
        return anio;
    }

    public void setMotorLitros(double motorLitros) {
        this.motorLitros = motorLitros;
    }

    public void setMotorTipo(String motorTipo) {
        this.motorTipo = motorTipo;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    // Getter y setter para el campo id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter y setter para el campo nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}

