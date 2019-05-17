package net.johnnyconsole.javaroulette;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Johnny Console
 * Project: JavaRoulette
 * Class: RouletteGame.java
 * Purrpose: Draws game board,
 * handles game logic
 * Created: 16 May 2019
 */
public class RouletteGame extends Application {
    private GridPane board;
    private int player = 0, lastPlayer;
    private File matrix = new File("matrix.txt");
    private File game = new File("game-history.txt");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage ps) {
        try {
            board = new GridPane();
            board.setPadding(new Insets(11, 12, 13, 14));
            board.setHgap(10);
            board.setVgap(10);

            PrintWriter mWriter = new PrintWriter(matrix), history = new PrintWriter(game);
            int n = 1;
            for (int i = 0; i < RouletteStart.size; i++) {
                for (int j = 0; j < RouletteStart.size; j++) {
                    int r = (int) (Math.random() * 101);
                    RouletteTile tile = new RouletteTile(n++, r);
                    mWriter.print(tile.n + " (" + r + ")  ");
                    board.add(tile, j, i);
                    tile.setOnAction(e -> {
                        tile.setVisible(false);
                        if (tile.isMine) {
                            if (countAlive() -1> 0) {
                                System.out.println(RouletteStart.names[player] + " died!");
                                history.println("Plsyer " + RouletteStart.names[player] + " clicked on tile #" + tile.n + " and died!");
                                RouletteStart.alive[player] = false;
                                lastPlayer = player;
                                player = next();
                            }
                        } else {
                            if(countTiles() == 0) {
                                System.out.println("Ran out of Tiles, the game is a draw!\nGame Over@");
                                history.println("Out of tiles");
                                endGame(history);
                            }
                            if(countAlive() > 1) {
                                System.out.println(RouletteStart.names[player] + " lives!");
                                history.println("Plsyer " + RouletteStart.names[player] + " clicked on tile #" + tile.n + " and lives!");
                                lastPlayer = player;
                                player = next();
                            }
                            else {
                                System.out.println(RouletteStart.names[player] + " won the game!\nGame Over!");
                                history.println("Plsyer " + RouletteStart.names[player] + " clicked on tile #" + tile.n + " and won the game!");
                                endGame(history);
                            }
                        }
                    });

                }
                mWriter.println();
            }
            mWriter.close();
            ps.setScene(new Scene(board));
            ps.setTitle("Roulette!");
            ps.show();
        } catch(IOException ex) {
            System.err.println("IO Error: ");
            ex.printStackTrace();
        }
    }

    private int countAlive() {
        int count = 0;

        for (int i = 0; i < RouletteStart.alive.length; i++) {
            if(RouletteStart.alive[i]) count++;
        }
        return count;
    }

    private int countTiles() {
        int count = 0;
        for (int i = 0; i <  board. getChildren().size(); i++) {
            if(board.getChildren().get(i).isVisible()) count++;
        }
        return count;
    }

    private void endGame(PrintWriter writer) {
        writer.print("Game Over!");
        writer.close();
        System.out.println("Game created by Johnny Console");
        System.out.println("The game matrix can be found at " + matrix.getAbsolutePath());
        System.out.println("The game summary can be found at " + game.getAbsolutePath());
        System.exit(0);
    }

    private int next() {
        player = (player == RouletteStart.players ? 0 : player + 1);
        int n = -1;
        for (int i = player; i < RouletteStart.players; i++) {
            if(RouletteStart.alive[i]) {
                n = i;
                break;
            }
        }
        if(n == -1) {
            for (int i = 0; i < player; i++) {
                if (RouletteStart.alive[i]){
                    n =i;
                    break;
                }
            }
        }
        return n;
    }

}
