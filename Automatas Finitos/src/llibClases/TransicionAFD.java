/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package llibClases;

/**
 * Clase que implementa los metodos de una transicion para el Automata Finito
 * Determinista
 *
 * @author Juan Diego Diaz Dominguez
 */
public class TransicionAFD {

    private String estado1;
    private String estado2;
    private char simbolo;

    /**
     * Constructor con parametros de la clase
     *
     * @param e1
     * @param e2
     */
    public TransicionAFD(String e1, char s, String e2) {
        estado1 = e1;
        estado2 = e2;
        simbolo = s;
    }

    /**
     * Funcion que devuelve el estado 1 de la transicion
     *
     * @return estado 1
     */
    public String getEstado1() {
        return estado1;
    }

    /**
     * Funcion que devuelve el estado 2 de la transicion
     *
     * @return estado2
     */
    public String getEstado2() {
        return estado2;
    }

    /**
     * Funcion que devuelve el simbolo de la transicion
     *
     * @return simbolo
     */
    public char getSimbolo() {
        return simbolo;
    }
}
