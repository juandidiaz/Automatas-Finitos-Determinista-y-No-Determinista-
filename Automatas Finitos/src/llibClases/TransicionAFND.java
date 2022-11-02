/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package llibClases;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que implementa los metodos para las transiciones con simbolos
 * del Automata Finito No Determinista
 * @author Juan Diego Diaz Dominguez
 */
public class TransicionAFND {
    private String e1;
    private List<String> e2=new ArrayList<>();
    char simbolo;
    /**
     * Constructor con parametros de la clase
     * @param es1
     * @param simbolo
     * @param es2
     */
    public TransicionAFND(String es1,char simbolo,List<String> es2)
    {
        this.e1=es1;
        this.simbolo=simbolo;
        for(int i=0;i<es2.size();i++)
        {
            this.e2.add(es2.get(i));
        }
    }

    /**
     * Funcion que devuelve el estado 1 de la transicion
     * @return e1
     */
    public String getEstado1()
    {
        return e1;
    }
    
    /**
     * Funcion que devuelve el estado 2 de la transicion
     * @return e2
     */
    public List<String> getEstado2()
    {
        return e2;
    }
    
    /**
     * Funcion que devuelve el simbolo de la transicion
     * @return simbolo
     */
    public char getSimbolo()
    {
        return simbolo;
    }
    
}
