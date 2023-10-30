/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carrera;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author oscar
 */
public class Pista {

    private char[][] pista;
    private int numCorredores;
    private int longitudPista;
    private Corredor ganador;
    private List<Corredor> corredores;

    public Pista(int numCorredores, int longitudPista) {
        this.numCorredores = numCorredores;
        this.longitudPista = longitudPista;
        this.ganador = null;
        this.corredores = new ArrayList<>();
        pista = new char[numCorredores][longitudPista];
        for (int i = 0; i < numCorredores; i++) {
            Arrays.fill(pista[i], ' ');
        }
    }

    public int getLongitudPista() {
        return longitudPista;
    }

    public Corredor getGanador() {
        return ganador;
    }

    public void setGanador(Corredor ganador) {
        this.ganador = ganador;
    }
    

    public List<Corredor> getCorredores() {
        return corredores;
    }
    
    
    public void agregarCorredor(Corredor corredor){
        corredores.add(corredor);
    }

    public synchronized void imprimirPista() {
        // Borde superior de la pista
        for (int i = 0; i < longitudPista + 2; i++) {
            System.out.print("-");
        }
        System.out.println();

        // Imprimir las filas de la pista con los corredores
        for (int i = 0; i < numCorredores; i++) {
            System.out.print("|");
            for (int j = 0; j < longitudPista; j++) {
                System.out.print(pista[i][j]); // Imprimir el carácter en esa posición
            }
            System.out.println("|");
        }

        // Borde inferior de la pista
        for (int i = 0; i < longitudPista + 2; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
    
    public synchronized void actualizarPosicion(Corredor corredor, int nuevaPosicion) {
        int fila = corredor.getFila();
        int posicionAnterior = corredor.getPosicion();

        // Borrar la posición anterior del corredor
        pista[fila][posicionAnterior] = ' ';

        // Actualizar la nueva posición del corredor
        pista[fila][nuevaPosicion] = corredor.getSimbolo();
        corredor.setPosicion(nuevaPosicion);
    }
    
    public boolean llegadaMeta(Corredor c){
        return c.getPosicion()>= longitudPista - 1;
    }
    
    public boolean finCarrera(){
        for(Corredor c: corredores){
            if(!llegadaMeta(c)){
                return false;
            }
        }
        return true;
    }

}
