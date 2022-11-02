/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package llibClases;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Clase que implementa los metodos del Automata Finito No Determinista
 * @author Juan Diego Diaz Dominguez
 * 
 */
public class AFND implements Cloneable, Proceso {

    private List<String> estados, estadosFinales;
    private List<TransicionAFND> transiciones;
    private List<Transicionlambda> transicionesλ;
    private String estadoInicial;

    /**
    * Constructor del Automata Finito No Determinista sin parametros
    */
    public AFND() {
        estados = new ArrayList<>();
        estadosFinales = new ArrayList<>();
        transiciones = new ArrayList<>();
        transicionesλ = new ArrayList<>();
    }

    /**
     *Metodo que añade un estado al conjunto de estados del Automata 
     */
     
    public void guardarEstado(String estado) {
        estados.add(estado);
    }

    /**
     * Metodo que añade una transicion a la Lista transiciones del Automata
     * @param e1
     * @param simbolo
     * @param e2 
     */
    public void agregarTransicion(String e1, char simbolo, List<String> e2) {

//        int i = 0;
//        boolean encontrado = false;
//
//        while (i < transiciones.size() && !encontrado) {
//            if (transiciones.get(i).getEstado1().equals(e1) && transiciones.get(i).getSimbolo() == simbolo &&
//                    transiciones.get(i).getEstado2().equals(e2)) 
//            {
//                encontrado = true;
//            }
//        }
//        if (!encontrado) {
        TransicionAFND aux = new TransicionAFND(e1, simbolo, e2);
        transiciones.add(aux);
//        }
//        else{
//            JOptionPane.showMessageDialog(null,"Ya existe esa transicion","Error",JOptionPane.ERROR_MESSAGE);
//        }

    }

    /**
     * Metodo que establece cual es el estado inicial
     * @param estado 
     */
    public void setEstadoInicial(String estado) {
        int i = 0;
        boolean encontrado = false;
        while (i < estados.size() && !encontrado) {
            if (estados.get(i).equals(estado)) {
                encontrado = true;
            }
            i++;
        }
        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "No existe ese estado", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Ese estado no existe");
        } else {
            System.out.println("Se ha establecido " + estado + " como estado Inicial");
            this.estadoInicial = estado;
        }
    }

    /**
     * Metodo que añade un estado al conjunto de estados finales
     * @param estado 
     */
    public void setEstadoFinal(String estado) {
        int i = 0;
        boolean encontrado = false;

        while (i < estados.size() && !encontrado) {
            if (estados.get(i).equals(estado)) {
                encontrado = true;
            }
            i++;
        }
        if (encontrado) {
            estadosFinales.add(estado);
            System.out.println("Se ha añadido " + estado + " al conjunto de estados finales");
        } else {
            System.out.println("Ese estado no existe");
            JOptionPane.showMessageDialog(null, "No existe este estado", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     *Metodo que añade una transicion lambda al conjunto de transicioneslambda 
     */
    public void agregarTransicionλ(String e1, List<String> e2) {

        Transicionlambda aux = new Transicionlambda(e1, e2);
        transicionesλ.add(aux);

    }

    /**
     * Metodo que devuelve el conjunto de transiciones del Automata
     * @return transiciones
     */
    public List<TransicionAFND> getTransiciones() {
        return transiciones;
    }

    /**
     * Metodo que devuelve el conjunto de transiciones lambda del Automata
     * @return transicionesλ
     */
    public List<Transicionlambda> getTransicionesλ() {
        return transicionesλ;
    }

    /**
     * Metodo que al pasarle un macroestado y un simbolo, devuelve a cual macroestado
     * ha avanzado el Automata
     * @param macroestado
     * @param simbolo
     * @return resultado
     */
    public List<String> transicion(List<String> macroestado, char simbolo) {

        List<String> resultado = new ArrayList<>();
        if (!macroestado.contains("vacio")) {
            for (int i = 0; i < transiciones.size(); i++) {
                for (int j = 0; j < macroestado.size(); j++) {
                    if (transiciones.get(i).getEstado1().equals(macroestado.get(j))) {
                        if (transiciones.get(i).getSimbolo() == simbolo) {
                            for (int k = 0; k < transiciones.get(i).getEstado2().size(); k++) {
                                resultado.add(transiciones.get(i).getEstado2().get(k));
                            }
                        }

                    }
                }
            }
        }

        return resultado;
    }

    /**
     * Metodo que dado un macroestado, devuelve a cual macroestado avanza el Automata
     * mediante el simbolo lambda
     * @param estado
     * @return resultado
     */
    public List<String> transicionλ(List<String> estado) {

        List<String> resultado = new ArrayList<>();

        for (int i = 0; i < estado.size(); i++) {
            for (int j = 0; j < transicionesλ.size(); j++) {
                if (transicionesλ.get(j).getE1().equals(estado.get(i))) {
                    for (int k = 0; k < transicionesλ.get(j).getE2().size(); k++) {
                        if (!resultado.contains(transicionesλ.get(j).getE2())) {
                            resultado.add(transicionesλ.get(j).getE2().get(k));
                        }
                    }
                }
            }
        }

        return resultado;

    }

    /**
     * Metodo que devuelve true si algunos de los estados del macroestado que se 
     * le pasa por parametro es final
     * @param macroestado
     * @return esFinal
     */
    public boolean esFinal(List<String> macroestado) {
        boolean esFinal = false;

        int i = 0;
        int j;
        while (!esFinal && i < macroestado.size()) {
            j = 0;
            while (j < estadosFinales.size()) {
                if (estadosFinales.get(j).equals(macroestado.get(i))) {
                    esFinal = true;
                }
                j++;
            }
            i++;
        }

        return esFinal;
    }

    /**
     * Metodo que realiza recursivamente la lambda-clausura del macroestado que se le
     * pasa como parametro.
     * La lambda clausura es el conjunto de estados al que se puede avanzar mediante
     * el simbolo lambda union con el estado en el que se encuentre el Automata
     * @param macroestado
     * @param resultado 
     */
    private void λ_clausura(List<String> macroestado, List<String> resultado) {

        if (!macroestado.isEmpty() && !macroestado.contains("vacio")) {
            for (int i = 0; i < macroestado.size(); i++) {
                resultado.add(macroestado.get(i));
            }
        }

        List<String> aux = new ArrayList<>();
        aux = transicionλ(macroestado);
        if (aux.size() > 0) {
            for (int i = 0; i < aux.size(); i++) {
                if (!resultado.contains(aux.get(i)) && !aux.contains("vacio")) {
                    resultado.add(aux.get(i));
                    λ_clausura(aux, resultado);
                }

            }
        }

    }

    /**
     * Metodo que reconoce una cadena como Aceptada o Rechazada. Para 
     * que una cadena sea aceptada, el Automata debe finalizar en un macroestado
     * donde algunos de los estados sean finales.
     * @param cadena
     * @return esFinal(estado)
     */
    @Override
    public boolean reconocer(String cadena) {
        char[] simbolo = cadena.toCharArray();
        List<String> estado = new ArrayList<String>();
        estado.add(this.estadoInicial);

        List<String> resultado = new ArrayList<>();
        λ_clausura(estado, resultado);
        for (int i = 0; i < simbolo.length; i++) {
            estado = transicion(resultado, simbolo[i]);
            λ_clausura(estado, resultado);
        }
        return esFinal(estado);

    }

    /**
     * Metodo para clonar un Automata Finito No Determinista
     * @return obj
     * @throws CloneNotSupportedException 
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        AFND obj = null;

        try {
            obj = (AFND) super.clone();
        } catch (CloneNotSupportedException ex) {
            System.out.println("No se puede clonar");
        }
        obj.estados=this.estados;
        obj.estadosFinales = this.estadosFinales;
        obj.transiciones = this.transiciones;
        obj.transicionesλ = this.transicionesλ;

        return obj;
    }

    /**
     * Metodo que devuelve una cadena para mostrar los distintos datos del Automata Finito
     * No Determinista
     * @return 
     */
    @Override
    public String toString() {
        String s = "";
        s += "Estados Finales: ";
        for (int i = 0; i < estadosFinales.size(); i++) {
            s += "" + estadosFinales.get(i) + " ";
        }
        s += "\n";
        s += "Transiciones: ";
        s += "\n";
        for (int i = 0; i < transiciones.size(); i++) {
            for (int j = 0; j < transiciones.get(i).getEstado2().size(); j++) {
                s += "" + transiciones.get(i).getEstado1() + " " + transiciones.get(i).getSimbolo() + " " + transiciones.get(i).getEstado2().get(j);
            }
            s += "\n";
        }
        s += "Transiciones λ: ";
        s += "\n";
        for(int i=0;i<transicionesλ.size();i++){
            for(int j=0;j<transicionesλ.get(i).getE2().size();j++){
                s+=""+transicionesλ.get(i).getE1()+" "+transicionesλ.get(i).getE2().get(j)+"\n";
            }
            s+="\n";
        }

    return s ;
}

    @Override
    public boolean esFinal(String estado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
