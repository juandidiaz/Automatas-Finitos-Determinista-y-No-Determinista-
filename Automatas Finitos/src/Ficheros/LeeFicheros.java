/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ficheros;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import llibClases.AFD;

/**
 *
 * @author Usuario
 */
public class LeeFicheros {

    public static AFD analizarTextoAFD(String text) throws IOException {
        StringReader sr = new StringReader(text);
        BufferedReader br = new BufferedReader(sr);
        AFD afd = analizarAFD(br);
        br.close();
        sr.close();
        return afd;
    }

    private static AFD analizarAFD(BufferedReader fichero) throws IOException {
        
        AFD afd = new AFD();
        String linea;
        
        boolean ZonaTransiciones = false;
        while ((linea = fichero.readLine()) != null) 
        {
            linea = linea.trim().replaceAll("\t", " ").replaceAll(" +", " ");
            if (linea.equals("FIN")) 
                ZonaTransiciones = false;
            
            else if (ZonaTransiciones) 
            {
                String[] partes = linea.split("'");
                afd.agregarTransicion(partes[0].trim(), partes[1].charAt(0), partes[2].trim());
            } 
            else 
            {
                if (linea.startsWith("ESTADOS:")) 
                    afd.agregarEstado(linea.substring(8).trim());
                
                else if (linea.startsWith("INICIAL:")) 
                    afd.setEstadoInicial(linea.substring(8).trim());
                
                else if (linea.startsWith("FINALES:")) 
                    afd.setEstadoFinal(linea.substring(8).trim());
                
                else if (linea.startsWith("TRANSICIONES:")) 
                    ZonaTransiciones = true;
                
            }
        }

        return afd;
    }

}
