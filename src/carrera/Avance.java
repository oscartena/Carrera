/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carrera;

import java.util.concurrent.CountDownLatch;

/**
 *
 * @author oscar
 */
public class Avance extends Thread {
    private Corredor corredor;
    private Pista pista;

    public Avance(Corredor corredor, Pista pista) {
        this.corredor = corredor;
        this.pista = pista;
        
    }
    
    @Override
    public void run(){
        while (!pista.llegadaMeta(corredor)) {
        int velocidad = corredor.getVelocidad();

            // Aplicar turbo si es posible
            if (Math.random() * 10 < corredor.getTurbo()) {
                velocidad *= 2; // Duplicar la velocidad
            }

            // Calcular el nuevo avance del corredor
            int nuevaPosicion = corredor.getPosicion() + velocidad;
            
            if(nuevaPosicion>=pista.getLongitudPista()){
                nuevaPosicion=pista.getLongitudPista()-1;
            }

            // Actualizar la posición del corredor en la pista
            pista.actualizarPosicion(corredor, nuevaPosicion);
            pista.imprimirPista();

            // Esperar un segundo antes del próximo movimiento
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(pista.getGanador()==null){
            pista.setGanador(corredor);
        }
        this.interrupt();
    }
}
