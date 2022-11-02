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
 * Clase que implementa los métodos del Automata Finito Determinista
 * @author Juan Diego Diaz Dominguez
 * 
 * 
 */
public class AFD implements Cloneable, Proceso {

    private String estadoInicial;
    private List<String> estados;
    private List<String> estadosFinales;
    private List<TransicionAFD> transiciones;
/**
 *Constructor del Automata Finito Determinista sin parametros 
 */
    public AFD() {
        estados = new ArrayList<>();
        estadoInicial = new String();
        estadosFinales = new ArrayList<>();
        transiciones = new ArrayList<TransicionAFD>();
    }

    /**
     * Metodo que añade un estado a la Lista estados del Automata
     * @param estado 
     */
    public void agregarEstado(String estado) {
        estados.add(estado);
    }

    /**
     * Metodo que agrega una Transicion a la Lista transiciones del Automata
     * @param e1
     * @param simbolo
     * @param e2 
     */
    public void agregarTransicion(String e1, char simbolo, String e2) {
        int i = 0;
        boolean encontrada = false;
        while (i < transiciones.size() && !encontrada) {
            if ((transiciones.get(i).getEstado1().equals(e1) && transiciones.get(i).getEstado2().equals(e2))|| (transiciones
                    .get(i).getEstado1().equals(e1) && transiciones.get(i).getSimbolo()==simbolo)) {
                encontrada = true;
            }
            i++;
        }
        if (!encontrada) {
            TransicionAFD aux = new TransicionAFD(e1, simbolo, e2);
            transiciones.add(aux);
        } else {
            JOptionPane.showMessageDialog(null, "Esa transicion ya existe", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Metodo que establece cual es el estado inicial
     * @param e 
     */
    public void setEstadoInicial(String e) {
        int i = 0;
        boolean encontrado = false;
        while (i < estados.size() && !encontrado) {
            if (estados.get(i).equals(e)) {
                encontrado = true;
            }
            i++;
        }

        if (encontrado) {
            estadoInicial = e;
            System.out.println("Se ha establecido " + e + " como estado inicial");
        } else {
            System.out.println("Ese estado no existe");
            JOptionPane.showMessageDialog(null, "No existe este estado", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Metodo que añade un estado al conjunto de estados finales
     * @param e 
     */
    public void setEstadoFinal(String e) {
        int i = 0;
        boolean encontrado = false;

        while (i < estados.size() && !encontrado) {
            if(estados.get(i).equals(e))
            {
                encontrado=true;
            }
            i++;
        }
        if (encontrado) {
            estadosFinales.add(e);
            System.out.println("Se ha añadido " + e + " al conjunto de estados finales");
        } else {
            System.out.println("Ese estado no existe");
            JOptionPane.showMessageDialog(null, "No existe este estado", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Metodo que devuelve a que estado ha avanzado el Automata cuando se le pasa un
     * estado y un simbolo
     * @param estado
     * @param simbolo
     * @return resultado
     */
    public String transicion(String estado, char simbolo) {

        String resultado = "-1";

        for (int i = 0; i < transiciones.size(); i++) {
            if (transiciones.get(i).getEstado1().equals(estado)) {
                if (transiciones.get(i).getSimbolo() == simbolo) {
                    resultado = transiciones.get(i).getEstado2();
                }
            }
        }
        return resultado;
    }

    /**
     * Metodo que comprueba si el estado al que ha llegado el Automata es final
     * @param estado
     * @return esF
     */
    @Override
    public boolean esFinal(String estado) {
        boolean esF = false;
        int i = 0;

        while (i < estadosFinales.size() && !esF) {
            if (estadosFinales.get(i).equals(estado)) {
                esF = true;
            }
            i++;
        }

        return esF;
    }

    /**
     * Metodo que reconoce una cadena como Aceptada o Rechazada. Una cadena sera
     * aceptada cuando el estado en el que finaliza el automata es final.
     * @param cadena
     * @return esFinal(estado)
     */
    @Override
    public boolean reconocer(String cadena) {
        char[] simbolo = cadena.toCharArray();
        String estado = this.estadoInicial;
        for (int i = 0; i < simbolo.length; i++) {
            estado = transicion(estado, simbolo[i]);
        }
        return esFinal(estado);
    }

//    public  AFD pedir() {
//        
//        
//        Scanner sc=new Scanner(System.in);
//        int opcion=0;
//        System.out.println("¿Desea introducir por teclado(1) o leer un fichero(2)");
//        
//        do{
//            opcion=sc.nextInt();
//            switch(opcion)
//            {
//                case 1:
//                    System.out.println("¿Cuantas transiciones quiere agregar?");
//                    int numtrans=sc.nextInt();
//                    sc.nextLine();
//                    for(int i=0; i<numtrans;i++)
//                    {
//                        System.out.println("Indica estado origen");
//                        String e1=sc.nextLine();
//                        
//                        System.out.println("Indica simbolo de la transicion");
//                        char s=sc.next().charAt(0);
//                        
//                        System.out.println("Indica estado destino");
//                        sc.nextLine();
//                        String e2=sc.nextLine();
//                        
//                        this.agregarTransicion(e1, s, e2);
//                    }
//                    
//                    System.out.println("¿Cuantos estados finales quiere? ");
//                    int numEstados=sc.nextInt();
//                    this.estadosFinales=new String[numEstados;
//                    sc.nextLine();
//                    for(int i=0;i<numEstados;i++)
//                    {
//                        System.out.println("Introduzca el estado final "+i);
//                        String estado=sc.nextLine();
//                        this.estadosFinales[i]=estado;
//                    }
//                    
//                    System.out.println("Los estados finales son: ");
//                    for (int i = 0; i < this.estadosFinales.length; i++) {
//                    System.out.println(this.estadosFinales[i]);
//                        
//                    }
//                    break;
//            }
//            
//        }while(opcion!=1 && opcion!=2);
//        
//        return this;
//    }
//    
    /**
     * Metodo para clonar un Automata Finito Determinista
     * @return obj
     * @throws CloneNotSupportedException 
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        AFD obj = null;

        try {
            obj = (AFD) super.clone();
        } catch (CloneNotSupportedException ex) {
            System.out.println("No se puede clonar");
        }
        obj.estados=this.estados;
        obj.estadosFinales = this.estadosFinales;
        obj.transiciones = this.transiciones;

        return obj;
    }

    /**
     * Metodo que devuelve el conjunto de transiciones del automata
     * @return transiciones 
     */
    public List<TransicionAFD> getTrans() {
        return transiciones;
    }
    
    /**
     * Metodo que devuelve una cadena para mostrar los distintos datos del Automata
     * @return s
     */
    @Override
    public String toString()
    {
        String s="";
        s+="Estados Finales: ";
        for(int i=0;i<estadosFinales.size();i++)
        {
            s+=""+estadosFinales.get(i)+" ";
        }
        s+="\n";
        s+="Transiciones: ";
        s+="\n";
        for(int i=0;i<transiciones.size();i++)
        {
            s+=""+transiciones.get(i).getEstado1()+" "+transiciones.get(i).getSimbolo()+" "+transiciones.get(i).getEstado2()+"\n";
        }
        return s;
    }
}
