package com.seanm.testgame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.seanm.testgame.MyGdxGame;
import com.seanm.testgame.Scenes.Hud;

/**
 * Created by sean on 9/23/17.
 */

public class PlayScreen implements Screen {
    private MyGdxGame game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    public PlayScreen(MyGdxGame game){
        this.game = game;
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(MyGdxGame.V_WIDTH,MyGdxGame.V_HEIGHT,gameCam);//sets screen to fit within the virtualized width and height
        hud = new Hud(game.batch);//creates the hud passing the sprite batch

        //map loading stuff
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gameCam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);//this puts the camera in the correct position

    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){
        if(Gdx.input.isTouched())//if the screen is touched/ clicked/ whatever
            gameCam.position.x += 100*dt;
    }

    public void update(float dt){
        handleInput(dt);

        gameCam.update();
        renderer.setView(gameCam);
    }

    @Override
    public void render(float delta) {
        update(delta);

        //clear the previous render from screen
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);//sets up what actually gets rendered... ie something that is not on camera does not get rendered
        hud.stage.draw();//add the hud to the screen
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);//redraws the screen when it is resized
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
