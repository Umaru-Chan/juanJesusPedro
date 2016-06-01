import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author antonio
 */
public class SquareManager {
    
    public ArrayList<SquareMod> squarelist = new ArrayList<>();
    public ArrayList<SquareMod> playerSquares = new ArrayList<>();

    private static SimpleWindowMod w;

    private int playerWidth = 30, playerXlocation = 235;

    public SquareManager( SimpleWindowMod w)
    {
        this.w = w;
    }

    public void manageSquares()
    {
        for(int i = 0; i < 3; i++)
        {
            SquareMod sq = new SquareMod (playerXlocation, w.getHeight() - playerWidth, playerWidth);
            playerSquares.add(sq);
            playerSquares.get(i).draw(w);
            playerXlocation += playerWidth;

        }

        int yLocation = 20;
        int xLocation = 10;

        /*två dimensionell for-loop*/
        for (int row = 0; row < 2; row++)
        {
            for (int col = 0; col < 17; col++)
            {
                SquareMod fkant = new SquareMod(xLocation, yLocation, 30);
                squarelist.add(fkant);
                squarelist.get(col).draw(w);
                xLocation += 30;


            }
            yLocation += 30;
            xLocation = 10;
        }
    }
}
