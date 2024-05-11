package ut5.main;

import ut5.TDA.TArbolG;

public class Main_ut_pd1 {
    public static void main(String[] args) {
        TArbolG<String> arbol = new TArbolG<String>();
        arbol.insertar("RECTORIA", "");
        arbol.insertar("VICERRECTORIA DEL MEDIO UNIVERSITARIO", "RECTORIA");
        arbol.insertar("VICERRECTORIA ACADEMICA", "RECTORIA");
        arbol.insertar("VICERRECTORIA ADMINISTRATIVA", "RECTORIA");
        arbol.insertar("FACULTAD DE CIENCIAS EMPRESARIALES", "VICERRECTORIA ACADEMICA");
        arbol.insertar("FACULTAD DE CIENCIAS HUMANAS", "VICERRECTORIA ACADEMICA");
        arbol.insertar("FACULTAD DE INGENIERIA Y TECNOLOGIAS", "VICERRECTORIA ACADEMICA");
        arbol.insertar("FACULTAD DE PSICOLOGIA", "VICERRECTORIA ACADEMICA");
        arbol.insertar("DEPARTAMENTO DE INFORMATICA Y CIENCIAS DE LA COMPUTACION",
                "FACULTAD DE INGENIERIA Y TECNOLOGIAS");
        arbol.insertar("DEPARTAMENTO DE INGENIERIA ELECTRICA", "FACULTAD DE INGENIERIA Y TECNOLOGIAS");
        arbol.insertar("DEPARTAMENTO DE MATEMATICAS", "FACULTAD DE INGENIERIA Y TECNOLOGIAS");

        System.out.println(arbol.buscar("INSTITUTO DE MATEMATICA Y ESTADISTICA"));
        System.out.println(arbol.buscar("FACULTAD DE PSICOLOGIA"));
        System.out.println(arbol.buscar("VICERRECTORIA ADMINISTRATIVA"));
        System.out.println(arbol.buscar("ECONOMATO"));

        System.out.println();
        System.out.println(arbol.listarIndentadoPorNiveles());

    }
}
