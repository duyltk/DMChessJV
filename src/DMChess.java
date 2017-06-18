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
        for (int j = -1; j <=1; j++){
            for (int k = -1; k <=1; k++){

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
}
