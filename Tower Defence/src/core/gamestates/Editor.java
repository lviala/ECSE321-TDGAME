/**
 * Created by Francis O'Brien - 1/7/2015 - 12:13 AM
 */

package core.gamestates;

import core.Core;
import gui.control.GUIController;
import gui.control.states.Editor_gui;
import gui.control.states.GUIStateIDs;
import map.MapGenerator;
import map.TileMap;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import util.MouseWrapper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Editor extends BasicGameState {

    public static MapGenerator.Size mapsize = MapGenerator.Size.LARGE;

    private int stateID;

    private TileMap map;
    private MouseWrapper mouse;

    private GameContainer gameContainer;
    private StateBasedGame stateBasedGame;
    private GUIController guiController;

    private TextField textBox;

    private boolean paused = false;
    private boolean createMessagePaused = false;
    private String pauseString = "";




    /// Testing ///


    /// End Testing ///

    public Editor(int ID){
        stateID = ID;
    }

    @Override
    public int getID() {
        return stateID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        mouse = new MouseWrapper(gameContainer.getHeight());
        guiController = new GUIController(gameContainer, stateBasedGame, mouse);
        this.gameContainer = gameContainer;
        this.stateBasedGame = stateBasedGame;

        textBox = new TextField(gameContainer, gameContainer.getGraphics().getFont(), 700, 600, 165, 20);
        textBox.setText("MAP NAME");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.setBackground(new Color(0, 135, 255));
        graphics.clear();
        map.render();
        guiController.render(graphics);
        textBox.render(gameContainer, graphics);


        graphics.setColor(Color.black);

        graphics.drawString("START", 207, 580);
        graphics.drawString("END", 367, 580);
        graphics.drawString("PATH", 512, 580);

        graphics.setColor(Color.orange);

        if (createMessagePaused){
            graphics.setColor(Color.black);
            graphics.fillRect(gameContainer.getWidth() / 2 - 300, gameContainer.getHeight() / 2 - 50, 600, 100);
            graphics.setColor(Color.white);
            graphics.drawString(pauseString, gameContainer.getWidth() / 2 - 300, gameContainer.getHeight() / 2 - 30);
            graphics.drawString("Press SPACE to resume", gameContainer.getWidth() / 2 - 100, gameContainer.getHeight() / 2 + 14 );
        }

        if (paused){
            graphics.setColor(Color.black);
            graphics.fillRect(gameContainer.getWidth() / 2 - 150, gameContainer.getHeight() / 2 - 50, 300, 100);
            graphics.setColor(Color.white);
            graphics.drawString("PAUSED", gameContainer.getWidth() / 2 - 43, gameContainer.getHeight() / 2 - 30);
            graphics.drawString("Press ESC to resume", gameContainer.getWidth() / 2 - 100, gameContainer.getHeight() / 2 + 0 );
            graphics.drawString("Press SPACE to restart", gameContainer.getWidth() / 2 - 100, gameContainer.getHeight() / 2 + 14 );
            graphics.drawString("Press ENTER for Main Menu", gameContainer.getWidth() / 2 - 100, gameContainer.getHeight() / 2 + 28 );
        }

        /// Testing ///


        /// End Testing ///

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
        mouse.update();
        guiController.update();

        /// Testing ///


        /// End Testing ///
    }

    @Override
    public void keyPressed(int key, char c) {
        if (!createMessagePaused && !paused) {
            super.keyPressed(key, c);


            String curtext = textBox.getText();

            if (curtext.length() <= 16) {
                if (isLegalCharacter(c)) {
                    curtext = curtext.concat(Character.toString(c));
                    textBox.setText(curtext);
                }
            }


            switch (key) {
                case Input.KEY_ESCAPE:
                    paused = true;
                    break;
                case Input.KEY_BACK:
                    String text = textBox.getText();
                    if (text.length() > 0) {
                        text = text.substring(0, text.length() - 1);
                        textBox.setText(text);
                    }
                    break;
            }


        } else if (createMessagePaused) {
            switch (key) {
                case Input.KEY_SPACE:
                    createMessagePaused = false;
                    pauseString = "";
                    break;
            }
        } else if (paused) {
            switch (key) {
                case Input.KEY_ESCAPE:
                    paused = false;
                    break;
                case Input.KEY_SPACE:
                    paused = false;
                    stateBasedGame.enterState(Core.EDITOR);
                    break;
                case Input.KEY_ENTER:
                    paused = false;
                    stateBasedGame.enterState(Core.MENU);
                    break;
            }

        }
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        map = MapGenerator.generateEmpty(mapsize);
        guiController.addState(new Editor_gui(GUIStateIDs.EDITOR_MAIN.ID, map, this));
        guiController.enterState(GUIStateIDs.EDITOR_MAIN.ID);


        /// Testing ///

        /// End Testing ///

    }

    @Override
    public void mouseClicked(int mouseButton, int x, int y, int clickCount) {
        if (!createMessagePaused && !paused) {
            super.mouseClicked(mouseButton, x, y, clickCount);
            guiController.mouseClicked(mouseButton, clickCount);
        }
    }



    private boolean isLegalCharacter(char c){
       if (c == 32 ) return true;
       if (c >= 48 && c <= 57 ) return true;
       if (c >= 65 && c <= 90 ) return true;
       if (c >= 97 && c <= 122 ) return true;

        return false;
    }

    public void saveMap() {

        if (fileAllreadyExists()){

            pauseString = "File with name allready exists";
            createMessagePaused = true;

        }else if (textBox.getText().equals("MAP NAME")){

            pauseString = "Enter map name";
            createMessagePaused = true;

        }else {

            BufferedImage image = MapGenerator.makePNG(map);
            Image slickImage = MapGenerator.getSlickimage(image);

            String message = MapGenerator.verifyMap(slickImage);

            if (message == null) {


                File output = new File("res/maps/" + textBox.getText() + ".png");
                try {
                    ImageIO.write(image, "png", output);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                pauseString = "Map saved successfully";
                createMessagePaused = true;

            } else {
                pauseString = message;
                createMessagePaused = true;
            }
            try {
                slickImage.destroy();
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean fileAllreadyExists(){

        File dir = new File("res/maps/");
        File[] files = dir.listFiles();

        String name = textBox.getText() + ".png";

        for (File f : files){
            if (f.getName().equals(name)) return true;
        }

        return false;
    }

}
