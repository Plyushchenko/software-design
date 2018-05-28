import model.Game;
import model.gamemap.GameMapFactory;
import ui.GameFrame;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        Game game = new model.Game(GameMapFactory.createDefaultGameMap());
        GameFrame gameFrame = new GameFrame(game);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setVisible(true);
        while (true) {
            gameFrame.draw();
            game.playTurn();
            Thread.sleep(2000);
        }
    }
}