/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carrera;

/**
 *
 * @author oscar
 */
public class Chocar extends Thread {

    private Corredor corredor;
    private Pista pista;

    public Chocar(Corredor corredor, Pista pista) {
        this.corredor = corredor;
        this.pista = pista;
    }

    @Override
    public void run() {
        while (!pista.llegadaMeta(corredor)) {
            if (Math.random() * 10 < corredor.getEvasion()) {
                // Corredor choca, mantiene la posicion
                pista.actualizarPosicion(corredor, corredor.getPosicion());
            }
            // Esperar un segundo antes de la siguiente comprobación de choque
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
