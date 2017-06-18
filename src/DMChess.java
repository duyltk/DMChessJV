/**
 * Created by bigzero on 6/18/17.
 */
public class DMChess {
    static String Board[][]={
            {"r","k","b","q","a","b","k","r"},
            {"p","p","p","p","p","p","p","p"},
            {" "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "},
            {" "," "," "," "," "," "," "," "},
            {"P","P","P","P","P","P","P","P"},
            {"R","K","B","Q","A","B","K","R"}
    };

    public static void main(String[] args) {

    }

    public static String moveKnight(int position){
        String List = "", oldPiece;
        int row = position / 8, col = position % 8;
        //Row colume from -2, -1, 1, 2
        for (int tempRow = -2; tempRow <= 2; tempRow++)
        {
            for (int tempCol = -2; tempCol <= 2; tempCol++)
            {
                if (Math.abs(tempRow * tempCol) == 2)
                {
                    try
                    {
                        if(" ".equals(Board[row + tempRow][col + tempCol]) || Character.isLowerCase(Board[row + tempRow][col + tempCol].charAt(0)))
                        {
                            if(safeKing())
                        }

                    }catch (Exception e){}
                }
            }
        }
    }
}
