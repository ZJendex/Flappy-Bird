import java.awt.event.KeyEvent;
import java.awt.*;


public class FlappyBird {
    /**
     * Instance variables declaration.
     */
    private Bird bird;
    private Background background;
    private LineSegment[][] map;
    private int count;
    private int start;
    private int highestScore;

    /**
     * Constructor for Flappy Bird.
     */
    public FlappyBird() {
        bird = new Bird(new Vector(2, 5), new Vector(0, 0), 0.4, 0);
        background = new Background(new Vector(5, 0), new Vector(-0.07, 0));
        count = 0;
        start = 0;
    }

    /**
     * The main method.
     */
    public static void main(String[] args) {
        new FlappyBird().run();
    }

    /**
     * Starts the game, and allows the player to choose a difficulty.
     */
    private void run() {
        runGame();
        while (true) {
            double[] click = menuMouseClick();
            if (click[0] < 5.5 && click[0] > 3.5 && click[1] > 1.5 && click[1] < 3.5) {
                bird.setBird(new Vector(2, 5), new Vector(0, 0), 0.4, 0);
                background.setBackground(new Vector(5, 0), new Vector(0, 0));
                start = 0;
                count = 0;
                runGame();
            }
        }
    }

    /**
     * Intializes the graphics, and runs the game.
     */
    private void runGame() {
        int winner = 2;
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(800, 1000);
        StdDraw.setScale(-0.5, 9.5);
        double[] randomArray = new double[1000];
        for (int i = 0; i < 1000; i++) {
            randomArray[i] = StdRandom.uniform(4) + 3;
        }
        draw(winner);
        winner = menu();
        while (winner <= 0) {
            StdDraw.pause(1);
            map = background.createBackground(1000, randomArray);
            if (start > 0) {
                background.drift();
            }
            mouseClick();
            winner = update();
            draw(winner);
        }
    }

    /**
     * Updates the game every frame to check if the bird has intersected with a pipe.
     */
    private int update() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 6; j++) {
                if (bird.intersects(map[i][j])) return 1;
            }
        }
        if (bird.getPosition().getY() > 9.5 || bird.getPosition().getY() < -0.5) return 1;
        return -1;
    }

    /**
     * Detects mouse click or space click.
     */
    private void mouseClick() {
        if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE) || StdDraw.isMousePressed()) {
            bird.accelerate(new Vector(0, 0.14));
            bird.setG(0.012);
            start++;
        }
        bird.drift();
        bird.setAccelerateBack();
        if (bird.getVelocity().getY() > 0.07) {
            bird.setVelocity(new Vector(0, 0.07));
        }
    }

    /**
     * Menu for choosing a difficulty. Changes background speed based off of chosen difficulty.
     */
    private int menu() {
        double[] mouse = menuMouseClick();
        if (mouse[0] < 3.5 && mouse[0] > 1.5 && mouse[1] > 3.5 && mouse[1] < 5.5) {
            background.setVelocity(new Vector(-0.1, 0));
        } else if (mouse[0] < 7.5 && mouse[0] > 5.5 && mouse[1] > 3.5 && mouse[1] < 5.5) {
            background.setVelocity(new Vector(-0.2, 0));
        } else {
            menu();
        }
        return 0;
    }

    /**
     * Gets mouse click on difficulty menu.
     */
    private double[] menuMouseClick() {
        while (!StdDraw.isMousePressed()) {
            // wait for mouse click
        }
        double x = StdDraw.mouseX();
        double y = StdDraw.mouseY();
        while (StdDraw.isMousePressed()) {
        }
        return new double[]{x, y};
    }

    /**
     * Draws the graphics depending on the player's state.
     */
    private void draw(int winner) {
        StdDraw.clear();
        StdDraw.picture(4.5, 4.5, "background.jpg", 10, 10);
        StdDraw.setPenColor(Color.BLACK);
        if (start == 0 && winner == -1) {
            StdDraw.text(2, 5.75, "Press space to jump.");
        }
        if (winner == 2) {
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.filledSquare(2.5, 4.5, 1);
            StdDraw.filledSquare(6.5, 4.5, 1);
            StdDraw.text(4.5, 5.75, "Choose difficulty");
            StdDraw.setPenColor(Color.WHITE);
            StdDraw.text(2.5, 4.5, "Easy");
            StdDraw.text(6.5, 4.5, "Hard");
        } else {

            if (start % 3 == 0) {
                StdDraw.picture(bird.getPosition().getX(), bird.getPosition().getY(), "Bird-1.jpg", 1, 1);
            }
            if (start % 3 == 1) {
                StdDraw.picture(bird.getPosition().getX(), bird.getPosition().getY(), "Bird0.jpg", 1, 1);
            }
            if (start % 3 == 2) {
                StdDraw.picture(bird.getPosition().getX(), bird.getPosition().getY(), "Bird1.jpg", 1, 1);
            }

            for (int i = 0; i < 100; i++) {
                if (bird.intersects(map[i][6])) {
                    count = i + 1;
                    if (count > highestScore) {
                        highestScore = count;
                    }
                }
                StdDraw.picture(map[i][0].getBX() + 1, map[i][0].getBY() * .5, "pipes.png", 7, map[i][0].getBY());
                StdDraw.picture(map[i][4].getAX() + 1, 5 + map[i][4].getAY() * .5, "pipes.png", 7, 10 - map[i][4].getBY(), 180);
            }
            StdDraw.setFont(new Font("Arial", Font.ITALIC, 60));
            StdDraw.text(4.5, 4.5, "" + count);
            if (winner == 1) {
                StdDraw.text(4.5, 8.5, "You Lose!");
                StdDraw.filledSquare(4.5, 2.5, 1);
                StdDraw.setPenColor(Color.WHITE);
                StdDraw.setFont(new Font("Arial", Font.BOLD, 40));
                StdDraw.text(4.5, 2.5, "Restart");

            }
        }
        StdDraw.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));
        StdDraw.text(1, 9, "Highest Score is " + highestScore);
        StdDraw.show();
    }
}
