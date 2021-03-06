/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author HP PC
 */
public class Vector {
    private Punto origen, destino;

    public Vector(Punto origen, Punto destino) {
        super();
        this.origen = origen;
        this.destino = destino;
    }
    public Vector(Punto destino){
        this(new Punto(0,0),destino);
    }

    
    public Punto getOrigen() {
        return origen;
    }

    public void setOrigen(Punto origen) {
        this.origen = origen;
    }

    public Punto getDestino() {
        return destino;
    }

    public void setDestino(Punto destino) {
        this.destino = destino;
    }
    
    public float getX(){
        return destino.getX()-origen.getX();
    }
    public float getY(){
        return destino.getY()-origen.getY();
    }
    public float getModulo(){
        double x=(double) this.getX();
        double y=(double) this.getY();
        return (float) Math.sqrt(x*x + y*y);
    }
}
