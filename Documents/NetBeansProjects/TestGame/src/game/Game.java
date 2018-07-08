/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import Personajes.Jugador;
import Controlador.ControladorBala;
import Controlador.ControladorEnemigo;
import Controlador.ControladorMeteoro;
import Controlador.GestorColision;
import java.util.Random;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Frank
 */
public class Game extends BasicGameState {
    
    public static final int ID = 2;
    public static float xMap=0, yMap=0;
    private ControladorBala balas;
    private Jugador jugador;
    private Input entrada;
    Image b, r, pause, heart, coin;
    private boolean quit = false;
    private ControladorEnemigo enemigos;
    private Random numeros;
    private int relojEnemigo = 0;
    private GestorColision gestor;
    private int relojMeteoro = 0;
    private Random xMeteoro;
    private ControladorMeteoro meteoros;
    
    @Override
    public int getID() {
        return Game.ID;
    }
    public Jugador getJugador(){
        return jugador;
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        //imagenes
        pause = new Image("data/pause.png");
        b = new Image("data/b2.png");
        heart = new Image("data/heart.png");
        coin = new Image("data/coin.png");
        //inicializadores
        jugador = new Jugador();
        jugador.init();
        entrada = gc.getInput();
        balas = new ControladorBala();
        enemigos = new ControladorEnemigo();
        numeros = new Random();
        gestor = new GestorColision();
        gestor.registarCuerpo(jugador);
        xMeteoro = new Random();
        meteoros = new ControladorMeteoro();
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        //mapa
        b.draw((int) xMap-950, (int) yMap);
        b.draw((int) xMap, (int) yMap);
        b.draw((int) xMap+950, (int) yMap);
        b.draw((int) xMap+1900, (int) yMap);
        b.draw((int) xMap+2850, (int) yMap);
        b.draw((int) xMap+3800, (int) yMap);
        b.draw((int) xMap+4750, (int) yMap);
        
        //jugador, balas, enemigo
        g.setColor(Color.black);
        jugador.render(g);
        
        balas.draw(g);
        enemigos.draw(g);
        meteoros.draw(g);
        //vida
        heart.draw(10,20);
        heart.draw(50,20);
        heart.draw(90,20);
        
        //currency
        g.drawRect(10, 60, 100, 30);
        coin.draw(10,60);
        g.drawString("        "+jugador.getScore()/100, 80, 25);
        g.drawString("     "+((jugador.getScore()/100)/5), 30, 63);
        //menu in-game cuando presiona esc
        if(quit==true){
            pause.draw();
            gc.pause();
            g.drawString("Reanudar Juego (R)", 400, 150);
            g.drawString("Menu Principal (M)", 400, 200);
            g.drawString("Tienda (T)", 400, 250);
            g.drawString("Salir (S)", 400, 300);
            if(quit==false){
                g.clear();
            }
        }
    }
    
    @Override
    public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
        entrada = container.getInput();
        jugador.update(container, delta,gestor);
        balas.update(delta);
        enemigos.update(delta);
        meteoros.update(delta);
        enemigos.delete();
        meteoros.delete();
        balas.delete();
        relojEnemigo += delta;
        relojMeteoro += delta;
        gestor.comprobarColisiones();
        jugador.setScore((jugador.getScore()+1));
        
        if(relojEnemigo > 3500 + numeros.nextInt(2000)){
            lanzarEnemigo();
            relojEnemigo=0;
        }
        
        if(relojMeteoro > 2000 + xMeteoro.nextInt(2000)){
            lanzarMeteoro(xMeteoro);
            relojMeteoro=0;
        }
        
        //teclado menu in-game
        if(entrada.isKeyPressed(Input.KEY_ESCAPE)){
            quit = true;
        }else if(entrada.isKeyPressed(Input.KEY_R)){
            quit = false;
            container.resume();
        }else if(entrada.isKeyPressed(Input.KEY_M)){
            sbg.enterState(1);
        }else if(entrada.isKeyPressed(Input.KEY_T)){
            sbg.enterState(3);    
        }else if(entrada.isKeyPressed(Input.KEY_S)){
            System.exit(0);
            //pantalla de game over, se ha puesto aca para mientras por prueba
        }else if(entrada.isKeyPressed(Input.KEY_Z)){
            sbg.enterState(4);
        }
    }

   public void lanzarEnemigo() throws SlickException{
        enemigos.add(1000, 402,gestor);
   }
   
   public void lanzarMeteoro(Random xMeteoro) throws SlickException{
       meteoros.add(xMeteoro.nextInt(700), -10, gestor);
   }
}
