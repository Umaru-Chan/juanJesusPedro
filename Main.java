
/**
 * Spel: Pinball
 * @author Hampus Eurén
 * @Version 4 2015/11/16
 * Spelet är ett Bouncy Ball spel med vit bakgrund och färgade fyrkanter. Liven och
 * poäng visas i mitten. Går man in i sidan då bollen är på platformen så ska bollen
 * röra sig med platformens rörelse och stanna vid kanten med riktningen.
 * Spelaren har 4a liv och måste få 34 poäng för att vinna.
 * Bollen har en delay på 15 ms.
 * ------------------
 * RESULTAT:
 *-------------------
 *Resultatet blev lite som väntat förutom att bollen inte avfyras rakt upp efter att
 * spelaren förlorat 1 liv då bollen rörde sig snett. Annan grej är att bollen kan åka
 * in i platformen från sidan ,vänster o höger, och att fyrkanterna i simplewindow
 * blinkar, detta kan bero på att mac datorn måste anstränga sig då Bouncy Ball exekveras.
 * Ett annat bugg som jag är osäker på om hur man fixar är att vissa variablar är överflödiga.
 * När man vunnit eller förlorat spelet så för att avsluta det måste man trycka på x högst upp till
 * vänster. Detta borde inte vara för svårt att ta reda på hur man gör.
 * Till helhet så funkar spel ganska bra.
 * Created by Dunder on 2015-10-12.
 */
public class  Main {
    /************
     * VARIABLER
     ************/

    private static SimpleWindowMod w = new SimpleWindowMod(530, 550, "Bouncy Ball");
    private static SquareManager sqm = new SquareManager(w);
    private static Game game = new Game(w, sqm);

    public static void main(String[] args) {
        w.setLineWidth(5);

        w.clear();
        w.moveTo(w.getWidth()/2-40, w.getHeight() / 2 - 100);
        w.writeText("Instruktioner");
        w.moveTo(w.getWidth()/2-120, w.getHeight() / 2 - 70);
        w.writeText("Reglerna är som klassisk bouncing ball");
        w.moveTo(w.getWidth()/2-120, w.getHeight() / 2 -40);
        w.writeText("Vänster = A");
        w.moveTo(w.getWidth()/2-120, w.getHeight()/2-10);
        w.writeText("Höger = D");
        w.moveTo(w.getWidth()/2-120, w.getHeight() / 2 + 20);
        w.writeText("Stanna = S");
        w.moveTo(w.getWidth()/2-120, w.getHeight() / 2 + 50);
        w.writeText("Avfyra boll = W");
        w.moveTo(w.getWidth()/2-120, w.getHeight() / 2 + 80);
        w.writeText ("klicka med musen för att starta");
        w.waitForMouseClick();
        w.clear();

        sqm.manageSquares();
        game.play(0, 1, false);
    }
}

