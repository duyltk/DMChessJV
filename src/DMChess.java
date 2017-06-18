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
    int kingPositionU = 0;
    public static void main(String[] args) {


    }
    public static void drawBoard(String Board[][]){
        for(int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j ++)
                System.out.print(Board[i][j] + ";");
            System.out.println();
        }
    }
    public static String moveRook(int position){
        String list = "", oldPiece;
        int row = i / 8;
        int col = i % 8;
        int distance = 1;
        for (int j = -1; j <=1; j++) {
            for (int k = -1; k <= 1; k++) {
                if (k * j == 0 && k != j) {
                    try {
                        while (" ".equals(Board[row + distance * j][col + distance * k])) {
                            oldPiece = " ";
                            Board[row][col] = " ";
                            Board[row + distance * j][col + distance * k] = "R";
                            if (safeKing()) {
                                list += row + col + (row + distance * j) + (col + distance * k) + " ";
                            }
                            Board[row][col] = "R";
                            Board[row + distance * j][col + distance * k] = oldPiece;
                            distance++;
                        }
                        if (Character.isLowerCase((Board[row + distance * j][col + distance * k]).charAt(0))) {
                            oldPiece = Board[row + distance * j][col + distance * k];
                            Board[row][col] = " ";
                            Board[row + distance * j][col + distance * k] = "R";
                            if (safeKing()) {
                                list += row + col + (row + distance * j) + (col + distance * k) + oldPiece;
                            }
                            Board[row][col] = "R";
                            Board[row + distance * j][col + distance * k] = oldPiece;
                        }
                    } catch (Exception e) { }
                    distance = 1;
                }
            }
        }
        return list;
    }
    public static Boolean safeKing(){
//        int distance = 1;
//        //Bishop & Queen diagonal
//        for (int i = -1; i <=1; i+=2){
//            for (int j = -1; j <= 1; j += 2){
//                try
//                {
//                    while (" ".Equals(chessBoard[kingPositionU / 8 + distance * i][ kingPositionU % 8 + distance * j]))
//                    {
//                        distance++;
//                    }
//                    if ("b".Equals(chessBoard[kingPositionU / 8 + distance * i][ kingPositionU % 8 + distance * j]) || "q".Equals(chessBoard[kingPositionU / 8 + distance * i][ kingPositionU % 8 + distance * j]))
//                    {
//                        return false;
//                    }
//                }
//                catch (Exception e) { }
//                distance = 1;
//            }
//        }
        return true;
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
                            oldPiece = Board[row + tempRow][col + tempCol];
                            Board[row + tempRow][col + tempCol] = "K";
                            Board[row][col] = " ";
                            if (safeKing()) {
                                List += row + col + (row + tempRow) + (col + tempCol) + oldPiece;
                            }
                            Board[row + tempRow][col + tempCol] = oldPiece;
                            Board[row][col] = "K";
                        }

                    }catch (Exception e){}
                }
            }
        }
        return List;
    }
}
