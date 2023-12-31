/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package carrera;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author oscar
 */
public class Carrera {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numCorredores, velocidad, turbo, evasion;
        char simbolo;
        List<Thread> hilos = new ArrayList<>();
        
        System.out.println("Cuantos corredores quiere?");
        numCorredores = sc.nextInt();
        
        Pista pista = new Pista(numCorredores, 100);
        
        for(int i=0; i<numCorredores; i++){
            System.out.println("Introduzca un caracter:");
            simbolo = sc.next().charAt(0);
            System.out.println("Introduzca la velocidad(1-5)");
            velocidad = sc.nextInt();
            System.out.println("Introduzca el turbo(1-5)");
            turbo = sc.nextInt();
            System.out.println("Introduzca la evasion(1-5)");
            evasion = sc.nextInt();
            sc.nextLine();
            Corredor c = new Corredor(simbolo, velocidad, turbo, evasion, i);
            pista.agregarCorredor(c);
        }


        for (Corredor c: pista.getCorredores()){
            Thread avance  = new Avance(c, pista);
            Thread chocar = new Chocar(c, pista);
            hilos.add(avance);
            hilos.add(chocar);
        }
        /*
        Corredor A = new Corredor('A', 3, 2, 5, 0);
        Corredor B = new Corredor('B', 3, 4, 3, 1);

        pista.agregarCorredor(A);
        pista.agregarCorredor(B);

        Thread avanceA = new Avance(A, pista);
        Thread avanceB = new Avance(B, pista);
        Thread tropiezoA = new Chocar(A, pista);
        Thread tropiezoB = new Chocar(B, pista);
        
        avanceA.start();
        avanceB.start();
        tropiezoA.start();
        tropiezoB.start();
        
        hilos.add(avanceB);
        hilos.add(avanceA);
        hilos.add(tropiezoA);
        hilos.add(tropiezoB);
        */
        
         for (Thread hilo : hilos) {
                hilo.start();
        }

        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        
        System.out.println("El ganador ha sido "+ pista.getGanador().getSimbolo());
               

    }

}
