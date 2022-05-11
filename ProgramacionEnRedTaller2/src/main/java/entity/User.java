package entity;

public class User {

    private String nombre;

    private int id;

    private String nationalId;

    public User(int id, String nombre, String nationalId) {
        this.nombre = nombre;
        this.id = id;
        this.nationalId = nationalId;
    }

    public User() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }
}
