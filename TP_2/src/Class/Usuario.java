package Class;

public class Usuario {
    private final int ID;
    private String nombre;
    private String mail;

    //CONSTRUCTOR
    public Usuario(int ID, String nombre, String mail) {
        this.ID = ID;
        this.nombre = nombre;
        this.mail = mail;
    }

    //GETTER
    public int getID() {
        return ID;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMail() {
        return mail;
    }

    //SETTER
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "ID: " + ID + "\nNombre: " + nombre + "\nEmail: " + mail;
    }

}
