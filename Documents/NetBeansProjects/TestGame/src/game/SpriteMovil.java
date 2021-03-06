/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;


import org.newdawn.slick.*;

/**
 *
 * @author HP PC
 */
public class SpriteMovil extends Sprite {
    
    private Vector velocidad;
    
    public SpriteMovil(String ruta, Punto posicion, Vector velocidad) throws SlickException {
        super(ruta, posicion);
        this.velocidad=velocidad;
    }

    public SpriteMovil(String ruta, Punto posicion, Punto velocidad) throws SlickException{
        this(ruta,posicion,new Vector(velocidad));
    }
    public Vector getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(Vector velocidad) {
        this.velocidad = velocidad;
    }
    public void update(int delta){
        float x=posicion.getX()+velocidad.getX()*((float)delta/1000);
        float y=posicion.getY()+velocidad.getY()*((float)delta/1000);
        this.setPosicion(x,y);
    }
    
    public void update2(int delta){
        float x= posicion.getX()-delta*0.05f;
        float y=posicion.getY()-velocidad.getY()*((float)delta/3000);
        this.setPosicion(x,y);
    }
}
