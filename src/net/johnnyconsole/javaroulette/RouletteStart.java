package net.johnnyconsole.javaroulette;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author Johnny Console
 * Project: JavaRoulette
 * Class: RouletteStart.java
 * Purrpose: Main entry point,
 * handles collecting game data
 * Created: 16 May 2019
 */
public class RouletteStart {
    private static final int ERROR = 1;
    private static final int INTERRUPTED = 2;
    static int players, size;
    static String[] names;
    static boolean[] alive;

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Welcome to Java Roulette by Johnny Console!");
            TimeUnit.SECONDS.sleep(2);

            System.out.print("How many players will be playing the game? (enter 0 to exit): ");
            players = scanner.nextInt();
            scanner.nextLine();
            TimeUnit.SECONDS.sleep(2);

            System.out.println("Thanks for that!");
            names = new String[players];
            alive = new boolean[players];

            for (int i = 0; i < players; i++) {
                System.out.print("Enter Player " + (i+1) + "'s name: ");
                names[i] = scanner.nextLine();
                alive[i] = true;
            }

            System.out.print("Thanks! Finally, select your board size: ");
            size = scanner.nextInt();
            scanner.close();

            System.out.println("OK, I've got everything I need. Configuring your game now...");
            TimeUnit.SECONDS.sleep(5);

            System.out.println("Starting your game, please wait...");
            TimeUnit.SECONDS.sleep(5);

            RouletteGame.main(args);

        }catch(InterruptedException ex) {
            System.err.println("Process Interrupted. Exiting...");
            System.exit(INTERRUPTED);
        }
        catch(NumberFormatException ex) {
            System.err.println("Invalid number. Exiting...:");
            ex.printStackTrace();
            System.exit(ERROR);
        }
    }
}
