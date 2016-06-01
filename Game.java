import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Alexander on 6/1/2016.
 */
public class Game {

    private SimpleWindowMod window;
    private SquareMod boll;

    /**
        direction är för att hålla koll på vart spelaren rör sig, om dir är 1 så rör sig spelaren åt vänster, om dir
        är 2 så rör sig spelaren åt höger, annars så står spelaren still. bx och by är för att hålla koll på vart bollen
        ska åka
    */
    private int bx = -15, by = -15, direction = 0;

    private ArrayList<SquareMod> player, targets;

    public Game(SimpleWindowMod window, SquareManager sqm){
        player = sqm.playerSquares;
        targets = sqm.squarelist;
        this.window = window;
        boll = new SquareMod(window.getWidth()/2+15/2, (window.getHeight() - 30 - 20), 15);
    }

    public void play(int points, int lives, boolean launch){

        //skriv ut poäng och liv
        while(true) {
            window.moveTo(window.getWidth() / 2 - 40, window.getHeight() / 2 - 100);
            window.writeText("Poäng: " + points);
            window.moveTo(window.getWidth() / 2 - 40, window.getHeight() / 2 - 70);
            window.writeText("Liv: " + lives);

            if (boll.getY() + boll.getSide() > window.getHeight()) {
                launch = false;
                lives--;

                boll = new SquareMod(player.get(1).getX() + 10, player.get(1).getY() - 25, 15);

                bx = -bx;
                by = -15;
            }
            //om bollen inte sitter fast vid spelaren
            if (launch) {
                //kontrollera så att bollen inte åker utanför skärmen
                if (boll.getX() < 0 || boll.getX() + boll.getSide() > window.getWidth())
                    bx *= -1;
                if (boll.getY() < 0)
                    by *= -1;

                boll.move(bx, by);
            }

            /** Beskriver vad som händer då bollen nuddar fyrkanten i mitten*/
            if (boll.getX() < player.get(1).getX() + player.get(1).getSide()
                    && boll.getX() > player.get(1).getX() &&
                    boll.getY() + boll.getSide() > player.get(0).getY()) {
                System.out.println("mitten");
                bx = 0;
                by = -by;
            }

            /** Beskriver vad som händer då bollen nuddar på den vänstra fyrkanten */
            if (boll.getX() < player.get(0).getX() + player.get(0).getSide()
                    && boll.getX() >= player.get(0).getX() &&
                    boll.getY() + boll.getSide() > player.get(0).getY()) {

                bx = -15;
                by = -by;
                System.out.println("vänstra");
            }

            /** Beskriver vad som händer då bollen nuddar fyrkanten till höger*/
            if (boll.getX() < player.get(2).getX() + player.get(2).getSide()
                    && boll.getX() > player.get(2).getX() &&
                    boll.getY() + boll.getSide() > player.get(0).getY()) {
                by = -by;
                bx = 15;
                System.out.println("högra");
            }

            for (int i = 0; i < targets.size(); i++) {
                if (boll.getX() < targets.get(i).getX() + targets.get(0).getSide()
                        && boll.getX() > targets.get(i).getX() &&
                        boll.getY() < targets.get(i).getY() + targets.get(0).getSide()) {
                    by *= -1;
                    targets.get(i).move(1000, 0);
                    points++;
                }
            }

            window.waitForEvent();

            switch (window.getKey()) {
                case 'a':
                    direction = 1;
                    break;
                case 'd':
                    direction = 2;
                    break;
                case 's':
                    direction = 0;
                    break;
                /** Avslutar Fas 0 o påbörjar fas 1 */
                case 'w':
                    if (!launch) {
                        if (by == -15 || by == 0) {
                            by = -by;

                            if (bx == 15 || bx == -15)
                                bx = 0;
                        }
                        launch = true;
                    }
                    break;
            }
            //rör på spelaren
            switch (direction) {
                case 1:
                    if (player.get(0).getX() > 0) {

                        for (int i = 0; i < player.size(); i++)
                            player.get(i).move(-20, 0);
                        //rör på bollen om den sitter kvar på spelaren
                        if (!launch)
                            boll.move(-20, 0);
                    }
                    break;
                case 2:
                    if (player.get(2).getX() + player.get(2).getSide() < window.getWidth()) {
                        for (int i = 0; i < player.size(); i++)
                            player.get(i).move(20, 0);
                        if (!launch)
                            boll.move(20, 0);
                    }
                    break;
            }

            window.clear();
            window.setLineColor(Color.red);
            /** Ritar ut spelaren */
            for (int i = 0; i < player.size(); i++)
                player.get(i).draw(window);

            /** Ritar ut bollen */
            boll.draw(window);
            window.setLineColor(Color.blue);

            /** Första raden "bricks" */
            for (int i = 0; i < targets.size(); i++)
                targets.get(i).draw(window);

            /** Beskriver vad som händer när spelaren förorar eller vunnit*/
            if (lives == 0){
                new ResultScreen("Lost", window).showResult();
            }
            if(points == 34){
                new ResultScreen("Victory", window).showResult();
            }
        }
    }
}
