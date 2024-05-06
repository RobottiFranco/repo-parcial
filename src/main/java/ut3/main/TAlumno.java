package ut3.main;
import ut3.TDA.Conjunto;
import ut3.TDA.IConjunto;

public class TAlumno {
    Integer cedula;
    String nombre;
    String apellido;

    public TAlumno(int cedula, String nombre, String apellido) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Comparable<Integer> getComparable(){
        return this.cedula;
    }

    public static void main(String[] args) {
        // 1. Crear dos instancias del TDA Conjunto para representar los cursos
        IConjunto<TAlumno> cursoAED1 = new Conjunto<TAlumno>();
        IConjunto<TAlumno> cursoPF = new Conjunto<TAlumno>();

        // 2. Crear varias instancias de TAlumno
        TAlumno alumno1 = new TAlumno(1234, "Juan", "Perez");
        TAlumno alumno2 = new TAlumno(5678, "Maria", "Gomez");
        TAlumno alumno3 = new TAlumno(9012, "Pedro", "Martinez");

        // 3. Asignar alumnos a los cursos
        cursoAED1.insertar(alumno1.getComparable(), alumno1);
        cursoAED1.insertar(alumno2.getComparable(), alumno2);
        cursoPF.insertar(alumno2.getComparable(), alumno2);
        cursoPF.insertar(alumno3.getComparable(), alumno3);

        // 4. Listado de alumnos en cualquiera de los dos cursos
        IConjunto<TAlumno> alumnosEnCualquierCurso = cursoAED1.union(cursoPF);
        System.out.println("Alumnos en cualquier curso:");
        alumnosEnCualquierCurso.imprimir(", ");

        // 5. Listado de alumnos en ambos cursos
        IConjunto<TAlumno> alumnosEnAmbosCursos = cursoAED1.interseccion(cursoPF);
        System.out.println("Alumnos en ambos cursos:");
        alumnosEnAmbosCursos.imprimir(", ");
    }
}
