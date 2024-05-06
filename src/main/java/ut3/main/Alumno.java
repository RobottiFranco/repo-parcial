package ut3.main;

public class Alumno {
    private final int codigo;
    private final String Nombre;

    public Alumno(int codigo, String Nombre) {
        this.codigo = codigo;
        this.Nombre = Nombre;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @return the Nombre
     */
    public String getNombre() {
        return Nombre;
    }



}
