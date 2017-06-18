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
        drawBoard();
        String move = "";
        for(int i = 0; i < 64; i++) {
            if ("Q".equals(Board[i/8][i%8])){

                move = move + moveQueen(i);
            }
        }
        System.out.print(move);

    }
    public static void drawBoard(){
        for(int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j ++)
                System.out.print(Board[i][j] + ";");
            System.out.println();
        }
    }
    public static String moveRook(int position){
        String list = "", getMove;
        int row = position / 8;
        int col = position % 8;
        int distance = 1;
        for (int i = -1; i <=1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i * j == 0 && i != j) {
                    try {
                        while (" ".equals(Board[row + distance * i][col + distance * j])) {
                            getMove = Set_GetMove(row, col, row + distance * i , col + distance * j );
                            if(getMove.length() != 0)
                            {
                                list = list + getMove;
                            }
                            distance++;
                        }
                        if (Character.isLowerCase((Board[row + distance * i][col + distance * j]).charAt(0))) {
                            getMove = Set_GetMove(row, col, row + distance * i , col + distance * j );
                            if(getMove.length() != 0)
                            {
                                list = list + getMove;
                            }
                        }
                    } catch (Exception e) { }
                    distance = 1;
                }
            }
        }
        return list;
    }
    public static String moveQueen(int position){
        String list = "", getMove;
        int row = position / 8, col = position % 8;
        int distance = 1;
        for (int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                if (i != 0 || j != 0){
                    try{
                        while(" ".equals(Board[row + distance * i][col + distance * j])){
                            getMove = Set_GetMove(row, col, row + distance * i , col + distance * j );
                            if(getMove.length() != 0)
                            {
                                list = list + getMove;
                            }
                            distance++;
                        }
                        if (Character.isLowerCase(Board[row + distance * i][col + distance * j].charAt(0))){
                            getMove = Set_GetMove(row, col, row + distance * i , col + distance * j );
                            if(getMove.length() != 0)
                            {
                                list = list + getMove;
                            }
                        }
                    }catch(Exception e){ }
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
        String List = "", getMove;
        int row = position / 8,
                col = position % 8;
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
                            getMove = Set_GetMove(row, col, row + tempRow , col + tempCol );
                            if(getMove.length() != 0)
                            {
                                List = List + getMove;
                            }
                        }

                    }catch (Exception e){}
                }
            }
        }
        return List;
    }
    public static String moveBishop(int position){
        String List = "", oldPiece;
        int row = position / 8,
                col = position % 8,
                distance = 1;
        for (int tempRow = -1; tempRow <= 1; tempRow += 2)
        {
            for (int tempCol = -1; tempCol <= 1; tempCol += 2)
            {
                try
                {
                    while (" ".equals(Board[row + tempRow * distance][col + tempCol * distance].charAt(0)))
                    {
                        Board[row + tempRow * distance][col + tempCol * distance] = "B";
                        Board[row][col] = " ";

                        distance++;
                    }

                }catch (Exception e){}
                distance = 1;
            }
        }
        return List;
    }
    public static String Set_GetMove(int preRow, int preCol, int nextRow, int nextCol){
        String List= "", oldPiece;
        oldPiece = Board[nextRow][nextCol];
        Board[nextRow][nextCol] = Board[preRow][preCol];
        Board[preRow][preCol] = " ";
        if (safeKing()){
            List = List + preRow + preCol + nextRow + nextCol + oldPiece;
        }
        Board[preRow][preCol] = Board[nextRow][nextCol];
        Board[nextRow][nextCol] = oldPiece;
        return List;
    }
}
