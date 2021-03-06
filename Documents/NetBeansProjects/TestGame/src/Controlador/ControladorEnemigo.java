/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Interfaces.Controlador;
import Personajes.Enemigo;
import game.Game;
import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author EdwinLovo
 */
public class ControladorEnemigo implements Controlador{
    private Game game;
    private ArrayList<Enemigo> enemigos;
    
    
    public ControladorEnemigo(){
        enemigos = new ArrayList<Enemigo>();
       
    }
    
    @Override
    public void add(float x, float y,GestorColision gestor) throws SlickException {
        Enemigo enemigo = new Enemigo(x, y);
        enemigos.add(enemigo);
        gestor.registarCuerpo(enemigo);
    }

    @Override
    public void draw(Graphics g) {
        for(int i = 0; i< enemigos.size(); i++){
            enemigos.get(i).draw();
            enemigos.get(i).render(g);
        }
    }

    @Override
    public void update(int delta) {
        for(int i = 0; i< enemigos.size(); i++){
            enemigos.get(i).update(delta);
        }
    }

    @Override
    public void delete() {
        for(int i = 0; i< enemigos.size(); i++){
            if(enemigos.get(i).getAreaColision().getX()<-200 && enemigos.get(i).getAreaColision().getX()>-300){
                enemigos.remove(i);
                
            }
        }
    }
    @Override
    public int delete2(){
        int n=0;
        for(int i = 0; i< enemigos.size(); i++){
            if(enemigos.get(i).getAreaColision().getX()<-4000 && enemigos.get(i).getAreaColision().getX()>-6000){
                enemigos.remove(i);
                System.out.println("SEFUE");
                n=1;
            }
        }
        return n;
    }

}
