/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package llibClases;

/**
 *Interfaz que declara la cabecera de los metodos comunes al AFND y AFD
 * @author Juan Diego Diaz Dominguez
 */
public interface Proceso {
    public abstract boolean esFinal(String estado);
    public abstract boolean reconocer(String cadena);
    @Override
    public abstract String toString();
    
}
