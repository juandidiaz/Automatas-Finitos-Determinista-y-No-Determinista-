/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import llibClases.AFD;
import llibClases.AFND;

/**
 * 
 * @author Juan Diego Diaz Dominguez
 * Clase encargada de Controlar las distintas acciones del usuario en la interfaz grafica
 * 
 */
public class Controlador implements ActionListener {

    private VentanaPrincipal vPrincipal = null;
    private Tablas utilTablas = null;
    private AFDFichero aF = null;
    private AFDTeclado aT = null;
    private AFNDFichero afF = null;
    private AFNDTeclado afT = null;
    private AFD Automata = null;
    private AFND AutomataN = null;
    private ArrayList<Object> filas = null;

    /**
     * Constructor del Controlador
     */
    public Controlador() {

        vPrincipal = new VentanaPrincipal();
        utilTablas = new Tablas();

        aF = new AFDFichero();
        aT = new AFDTeclado();
        afF = new AFNDFichero();
        afT = new AFNDTeclado();
        filas = new ArrayList<Object>();

        Automata = new AFD();
        AutomataN = new AFND();

        addListeners();

        vPrincipal.setLocationRelativeTo(null);
        vPrincipal.setVisible(true);

    }

    /**
     *Funcion que añade los distintos Listeners para que los botones 
     * devuelvan su ActionCommand
     */
    public void addListeners() {
        //Ventana Principal
        vPrincipal.afdf.addActionListener(this);
        vPrincipal.afdt.addActionListener(this);
        vPrincipal.afndf.addActionListener(this);
        vPrincipal.afndt.addActionListener(this);

        //AFD desde Teclado
        aT.BotonInsertar.addActionListener(this);
        aT.CerrarPestana.addActionListener(this);
        aT.AceptarF.addActionListener(this);
        aT.AceptarIn.addActionListener(this);
        aT.BotonReconocer.addActionListener(this);

        aF.NombreFichero.addActionListener(this);
        aF.CerrarPF.addActionListener(this);
        afF.AceptarFAFND.addActionListener(this);
        afF.CerrarPFAFND.addActionListener(this);

        afT.InsertarAFND.addActionListener(this);
        afT.CerrarPFNDT.addActionListener(this);
        afT.ReconocerCad.addActionListener(this);
        afT.AnadirFinal.addActionListener(this);
        afT.EstablecerIni.addActionListener(this);

    }

    /**
     * Funcion que realiza las distintas acciones según el evento que ocurra en
     * la interfaz gráfica
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case "GAFDT": {
                if (aT == null) {
                    aT = new AFDTeclado();
                }
                utilTablas.dibujarTablaAFD(aT);
                afF.setVisible(false);
                aF.setVisible(false);
                afT.setVisible(false);
                aT.setVisible(true);

                break;
            }

            case "GAFDF":
                if (aF == null) {
                    aF = new AFDFichero();
                }
                utilTablas.dibujarTablaAFND(afT);
                aT.setVisible(false);
                afF.setVisible(false);
                afT.setVisible(false);
                aF.setVisible(true);
                break;

            case "GAFNDF":
                if (afF == null) {
                    afF = new AFNDFichero();
                }

                aT.setVisible(false);
                aF.setVisible(false);
                afT.setVisible(false);
                afF.setVisible(true);
                break;

            case "GAFNDT":
                if (afT == null) {
                    afT = new AFNDTeclado();
                }
                utilTablas.dibujarTablaAFND(afT);
                aT.setVisible(false);
                aF.setVisible(false);
                afF.setVisible(false);
                afT.setVisible(true);

            case "CerrarP":
                aT.dispose();
                break;

            case "CerrarPF":
                aF.dispose();
                break;

            case "CerrarPFAFND":
                afF.dispose();
                break;

            case "CerrarPFNT":
                afT.dispose();
                break;

            case "Insertar":

                String e1 = aT.EstadoO.getText();
                char s = aT.Simbolo.getText().charAt(0);
                String e2 = aT.EstadoD.getText();
                Automata.agregarEstado(e1);
                Automata.agregarEstado(e2);
                Automata.agregarTransicion(e1, s, e2);
                utilTablas.vaciarTablaAFD();
                utilTablas.rellenarTablaAFD(Automata.getTrans());

                break;

            case "InsertarAFND":
                String es1 = afT.EstadoOAFND.getText();
                char sim = afT.SimboloAFND.getText().charAt(0);
                String[] es2 = afT.EstadoDestinoAFND.getText().split(",");
                List<String> estados2 = new ArrayList<>();
                
                AutomataN.guardarEstado(es1);
                for (int i = 0; i < es2.length; i++) {
                    estados2.add(es2[i]);
                    AutomataN.guardarEstado(es2[i]);
                }

                if (afT.transLambda.getText().length() != 0) {
                    String[] esLambda = afT.transLambda.getText().split(",");
                    List<String> estadosLambda = new ArrayList<>();
                    for (int i = 0; i < esLambda.length; i++) {
                        estadosLambda.add(esLambda[i]);
                        AutomataN.guardarEstado(esLambda[i]);
                    }
                    AutomataN.agregarTransicion(es1, sim, estados2);
                    AutomataN.agregarTransicionλ(es1, estadosLambda);

                } else {
                    List<String>vacio=new ArrayList<>();
                    vacio.add("vacio");
                    AutomataN.agregarTransicion(es1, sim, estados2);
                    AutomataN.agregarTransicionλ(es1, vacio);
                }
                utilTablas.vaciarTablaAFND();
                utilTablas.rellenarTablaAFND(AutomataN.getTransiciones(), AutomataN.getTransicionesλ());

                break;

            case "AceptarInicial":
                String eInicial = aT.EInicial.getText();
                Automata.setEstadoInicial(eInicial);

                break;

            case "AceptarFinal":
                String eFinales = aT.EFinales.getText();
                Automata.setEstadoFinal(eFinales);
                break;

            case "Reconocer":
                String cad = aT.Cadena.getText();
                boolean reconocida = Automata.reconocer(cad);
                String AutomataString=Automata.toString();
                System.out.println(AutomataString);
                if (reconocida) {
                    System.out.println("La cadena es aceptada");
                    JOptionPane.showMessageDialog(null, "Aceptada", "AFD", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    System.out.println("La cadena es rechazada");
                    JOptionPane.showMessageDialog(null, "Rechazada", "AFD", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case "EstablecerIni":
                String eIni=afT.EInicialAFND.getText();
               AutomataN.setEstadoInicial(eIni);
                break;

            case "AnadirFinal":
                String eFinal=afT.EFinalesAFND.getText();
                AutomataN.setEstadoFinal(eFinal);
                break;

            case "ReconocerCad":
                String cadF=afT.CadenaAF.getText();
                boolean reconoce=AutomataN.reconocer(cadF);
                String StringAFND=AutomataN.toString();
                System.out.println(StringAFND);
                if(reconoce)
                {
                    System.out.println("La cadena es aceptada");
                    JOptionPane.showMessageDialog(null, "Aceptada", "AFND", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    System.out.println("La cadena es rechazada");
                    JOptionPane.showMessageDialog(null, "Rechazada", "AFND", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }

}
