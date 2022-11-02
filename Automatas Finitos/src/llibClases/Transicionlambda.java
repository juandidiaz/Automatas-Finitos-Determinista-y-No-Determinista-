/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package llibClases;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que implementa los metodos de las transiciones sin simbolo del Automata
 * Finito No Determinista
 * @author Juan Diego Diaz Dominguez
 */
public class Transicionlambda {
    private String e1;
    private List<String> e2=new ArrayList<>();
    
    /**
     * Constructor con parametros de la clase
     * @param es1
     * @param es2 
     */
    public Transicionlambda(String es1,List<String> es2)
    {
        this.e1=es1;
        for(int i=0;i<es2.size();i++)
        {
            this.e2.add(es2.get(i));
        }
    }
    
    /**
     * Funcion que devuelve el estado 1 de la transicion
     * @return e1
     */
    public String getE1()
    {
        return e1;
    }
    
    /**
     *Funcion que devuelve el estado 2 de la transicion
     */
    public List<String> getE2()
    {
        return e2;
    }
    
            
}
