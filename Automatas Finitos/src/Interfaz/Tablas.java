/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import llibClases.TransicionAFD;
import llibClases.TransicionAFND;
import llibClases.Transicionlambda;

/**
 *
 * @author Juan Diego Diaz Dominguez
 * Clase encargada de dibujar las distintas tablas en la interfaz
 */
public class Tablas {

    
    public DefaultTableModel modeloTablaAFD = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public DefaultTableModel modeloTablaAFND = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
/**
 * Vacia la tabla del Automata Finito Determinista
 */
    public void vaciarTablaAFD() {
        while (modeloTablaAFD.getRowCount() > 0) {
            modeloTablaAFD.removeRow(0);
        }
    }
/**
 * Rellena la tabla del Automata Finito determinista pasandole como
 * parametro una lista de las distintas transiciones
 * @param filas 
 */
    public void rellenarTablaAFD(List<TransicionAFD> filas) {
        int numFilas = filas.size();
        Object[] fila = new Object[3];
        for (int i = 0; i < numFilas; i++) {
            fila[0] = filas.get(i).getEstado1();
            fila[1] = filas.get(i).getSimbolo();
            fila[2] = filas.get(i).getEstado2();
            modeloTablaAFD.addRow(fila);
        }

    }

    /**
     * Dibuja la tabla del Automata Finito Determinista con las distintas columnas
     * @param A 
     */
    
    public void dibujarTablaAFD(AFDTeclado A) {
        A.jTable1.setModel(modeloTablaAFD);

        String[] columnasTabla = {"Estado Origen", "Simbolo", "Estado Destino"};
        modeloTablaAFD.setColumnIdentifiers(columnasTabla);

        A.jTable1.getTableHeader().setResizingAllowed(false);
        A.jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        A.jTable1.getColumnModel().getColumn(0).setPreferredWidth(40);
        A.jTable1.getColumnModel().getColumn(1).setPreferredWidth(240);
        A.jTable1.getColumnModel().getColumn(2).setPreferredWidth(70);

    }
/**
 * Dibuja la tabla del Automata Finito No Determinista con las distintas columnas
 * @param A 
 */
    public void dibujarTablaAFND(AFNDTeclado A) {
        A.jTable1.setModel(modeloTablaAFND);

        String[] columnasTabla = {"Estado Origen", "Simbolo", "Estado Destino","λ"};
        modeloTablaAFND.setColumnIdentifiers(columnasTabla);

        A.jTable1.getTableHeader().setResizingAllowed(false);
        A.jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        A.jTable1.getColumnModel().getColumn(0).setPreferredWidth(40);
        A.jTable1.getColumnModel().getColumn(1).setPreferredWidth(240);
        A.jTable1.getColumnModel().getColumn(2).setPreferredWidth(70);
        A.jTable1.getColumnModel().getColumn(3).setPreferredWidth(80);
    }
/**
 * Rellena la tabla del Automata Finito No Determinista pasandole como parametros
 * una lista de las transiciones y otra lista de las transicionesλ
 * @param filas
 * @param filaslambda 
 */
    public void rellenarTablaAFND(List<TransicionAFND> filas, List<Transicionlambda> filaslambda) {
        int numFilas = filas.size();
        Object[] fila = new Object[4];
        for (int i = 0; i < numFilas; i++) {
            fila[0] = filas.get(i).getEstado1();
            fila[1] = filas.get(i).getSimbolo();
            fila[2] = filas.get(i).getEstado2();
            fila[3] = filaslambda.get(i).getE2();
            modeloTablaAFND.addRow(fila);
        }

    }

/**
 * Vacia la tabla del Automata Finito No Determinista
 */    
    public void vaciarTablaAFND() {
        while (modeloTablaAFND.getRowCount() > 0) {
            modeloTablaAFND.removeRow(0);
        }
    }
}
