
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dunders
 */
public class ResultScreen {
    
    String outcome;
    static int Launch = 0;
    SimpleWindowMod w;
    static int Lives = 4;
    static int points = 0;
    
    protected ResultScreen( String outcome, SimpleWindowMod w) {
        this.outcome = outcome;
        this.w = w;
    }
    
    public void showResult()
    {
        switch ( outcome )
        {
            case "Lost":
                int option = JOptionPane.showConfirmDialog(null, " Vill du försöka igen?", "GAME OVER",
                JOptionPane.YES_NO_OPTION);
                if(option == JOptionPane.YES_OPTION){
                    w.clear();
                    SquareManager sqm = new SquareManager(w);
                    sqm.manageSquares();
                    new Game(w, sqm).play(0, 4, false);
                }
                
            break;
        
            case "Victory":
                option = JOptionPane.showConfirmDialog(null, " DU VANN! Vill du spela igen?", "MISSION ACCOMPLISHED",
                JOptionPane.YES_NO_OPTION);
                if(option == JOptionPane.YES_OPTION){
                    w.clear();
                    SquareManager sqm = new SquareManager(w);
                    sqm.manageSquares();
                    new Game(w, sqm).play(0, 4, false);
                }
                break;
        }
        Launch -= 1;
        w.clear();
    }
}
