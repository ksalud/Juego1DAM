package com.gag.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

public class Enemigo extends Sprite {
   //private static final double DEFECTO=10;
   public static final int VIDA=100;
   public static final int ATAQUE=100;
   public static final int VEL_ATAQUE=100;
   public static final int ALTO=156;
   public static final int ANCHO=128;
   private int vida;
   private int danio;
   private int velocidadAtaque;
   protected Body cuerpo;
   protected TextureRegion aspecto;
   protected FixtureDef componente;
   protected Animation<TextureRegion> animacion;
   public static final int FILA=1;
   public static final int COLUMNA=8;
   private float lapso;
   private Vector2 velocidad;
   Texture caminar;


    //
   //
   public Enemigo(World mundo,TextureAtlas atlas, int x, int y) {
       super(atlas.findRegion("Fantasma"));
       caminar = new Texture(Gdx.files.internal("FantasmaRun.png"));
       lapso=0;
        vida=VIDA;
        danio=ATAQUE;
        velocidadAtaque=VEL_ATAQUE;
       //Cuerpo físico

       /*ToDo mal
         TextureRegion[][] tmp = TextureRegion.split(caminar,
               caminar.getWidth() / COLUMNA,
               caminar.getHeight() / FILA);
       TextureRegion[] walkFrames = new TextureRegion[COLUMNA * FILA];
       int index = 0;
       for (int i = 0; i < FILA; i++) {
           for (int j = 0; j < COLUMNA; j++) {
               walkFrames[index++] = tmp[i][j];
           }*/

       aspecto=new TextureRegion(getTexture(),
               getRegionX(),getRegionY(),
               FILA,COLUMNA);
       Array<TextureRegion> fotogramas=new Array<>();
       for(int i=0;i<=8;i++){
           fotogramas.add(new TextureRegion(getTexture(),getRegionWidth()*i,getRegionHeight()));
       }
       animacion=new Animation<TextureRegion>(0.1f,fotogramas);
       BodyDef defCuerpo= new BodyDef();
       defCuerpo.type=BodyDef.BodyType.KinematicBody;
       defCuerpo.position.x=x+0.5f;
       defCuerpo.position.y=y+0.5f;
       cuerpo=mundo.createBody(defCuerpo);
       //Componentes dentro del cuerpo
       FixtureDef defComponente= new FixtureDef();
       CircleShape forma=new CircleShape();
       forma.setRadius(0.5f);
       defComponente.shape=forma;
       defComponente.friction=0;
       defComponente.density=0;
       defComponente.restitution=0;
       cuerpo.createFixture(defComponente);

   }
    public Body getCuerpo() {
        return cuerpo;
    }


    public void actualizar(float delta){
        setPosition(cuerpo.getPosition().x-.5f,cuerpo.getPosition().y-.5f);
        setRegion(decidirAspecto(delta));
       cuerpo.setLinearVelocity(-1.0f*delta,0);
    }
    public TextureRegion decidirAspecto(float delta){
        velocidad=cuerpo.getLinearVelocity();
        aspecto=animacion.getKeyFrame(lapso,true);
        lapso+=delta;
        if( (velocidad.x<0 && !aspecto.isFlipX()) || (velocidad.x>0 && aspecto.isFlipX()) )
            aspecto.flip(true,false);
        return aspecto;
    }

    // //Ataque de enemigo, el enemigo ataca coge la vida del muro y se resta con el daño, depende de la velocidad de ataque pues
   // //ataca mas rapido o mas lento, la animacion la hace mas rapido o mas lenta
   // public void atacarMuro(float danio){
   // muro.setVida(muro.getVida()-danio);
   // }
}
