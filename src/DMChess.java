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


    static int kingPositionU = 0;
    static int kingPositionL = 0;
    static Boolean castlingUShort = true;
    static Boolean castlingULong = true;
    static Boolean castlingLShort = true;
    static Boolean castlingLLong = true;



    public static void main(String[] args) {
//        drawBoard();
//        String move = "";
//        for(int i = 0; i < 64; i++) {
//            if ("A".equals(Board[i/8][i%8])){
//                kingPositionU = i;
//            }
//            if ("a".equals(Board[i/8][i%8])){
//                kingPositionL = i;
//            }
//        }
//        for(int i = 0; i < 64; i++){
//            if ("A".equals(Board[i/8][i%8])){
//                move = move + moveKing(i);
//            }
//        }
//
//        System.out.print(move);


    }
    public static void drawBoard(){
        for(int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j ++)
                System.out.print(Board[i][j] + ";");
            System.out.println();
        }
    }
    public static String movePieces(){
        String List = "";
        for (int position = 0; position < 64; position++)
        {
            if (!Character.isUpperCase(Board[position / 8][position % 8].charAt(0)))
                continue;
            switch (Board[position / 8][position % 8])
            {
                case "P":
                    List = List + movePawn(position);
                    break;
                case "R":
                    List = List + moveRook(position);
                    break;
                case "K":
                    List = List + moveKnight(position);
                    break;
                case "B":
                    List = List + moveBishop(position);
                    break;
                case "Q":
                    List = List + moveQueen(position);
                    break;
                case "A":
                    List = List + moveKing(position);
                    break;
            }
        }
        return List;
    }
    public static String moveRook(int position){
        // Return: list has a structure:
        //      normal: [row][col][next row][next col][piece at next row, next col]
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
        // Return: list has a structure:
        //      normal: [row][col][next row][next col][piece at next row, next col]
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
    public static String movePawn(int position){
        // Return: list has a structure:
        //      normal: [row][col][next row][next col][piece at next row, next col]
        //      if pawn promotion : [col] [nex col] [captured piece] [promoted Piece] [P(pawn)]

        String list = "", getMove, oldPiece;
        int row = position / 8, col = position % 8;
        for(int tempCol = -1; tempCol <=1; tempCol+=2){
            // capture opponent's piece
            try{
                if (Character.isLowerCase(Board[row - 1][col + tempCol].charAt(0)) && row != 1){
                    getMove = Set_GetMove(row, col, row -1, col+tempCol);
                    if(getMove.length() != 0){
                        list = list + getMove;
                    }
                }
            }catch(Exception e){}
            // capture opponent's piece and promote
            try{
                if (Character.isLowerCase(Board[row - 1][col + tempCol].charAt(0)) && row == 1){
                    String temp[] = {"Q", "R", "K", "B"};
                    for (int i = 0; i < 4; i++) {
                        oldPiece = Board[row - 1][col + tempCol];
                        Board[row][col] = " ";
                        Board[row - 1][col + tempCol] = temp[i];
                        if (safeKing()) {
                            list = list + col + (col + tempCol) + oldPiece + temp[i] + "P";
                        }
                        Board[row][col] = "P";
                        Board[row - 1][col + tempCol] = oldPiece;
                    }
                }
            }catch(Exception e){}
        }
        //go straight 1 distance
        try{
            if (" ".equals(Board[row - 1][col]) && row != 1){
                getMove = Set_GetMove(row, col, row -1, col);
                if (getMove.length() != 0){
                    list = list + getMove;
                }
            }
        }catch(Exception e){}
        //go straight 1 distance and promote
        try{
            if (" ".equals(Board[row - 1][col]) && row == 1){
                String temp[] = {"Q", "R", "K", "B"};
                for (int i = 0; i < 4; i++) {
                    oldPiece = " ";
                    Board[row][col] = " ";
                    Board[row - 1][col] = temp[i];
                    if (safeKing()) {
                        list = list + col + (col) + oldPiece + temp[i] + "P";
                    }
                    Board[row][col] = "P";
                    Board[row - 1][col] = " ";
                }
            }
        }catch(Exception e){}
        //go straight 2 distance
        try{
            if (" ".equals(Board[row - 1][col]) && " ".equals(Board[row - 2][col]) && row == 6){
                getMove = Set_GetMove(row, col, row -2, col);
                if (getMove.length() != 0){
                    list = list + getMove;
                }
            }
        }catch(Exception e){}
        return list;
    }
    public static Boolean safeKing(){

        int distance = 1;
        //Bishop & Queen diagonal
        int rowKing = kingPositionU / 8;
        int colKing = kingPositionU % 8;
        for (int i = -1; i <=1; i+=2){ // let the King move like Bishop and Queen
            for (int j = -1; j <= 1; j += 2){
                try
                {
                    while (" ".equals(Board[rowKing + distance * i][ colKing + distance * j]))
                    {
                        distance++;
                    }
                    if ("b".equals(Board[rowKing+ distance * i][ colKing + distance * j]) || "q".equals(Board[rowKing + distance * i][ colKing + distance * j]))
                    {
                        return false;
                    }
                }
                catch (Exception e) { }
                distance = 1;
            }
        }
        //Rook & Queen go straight
        for (int i = -1; i <= 1; i++) // let the King move like Rook and Queen
            for(int j = -1; j<=1; j++){
                if (i * j == 0 && i != j ){
                    try{
                        while(" ".equals(Board[rowKing + distance * i][ colKing + distance * j])){
                            distance++;
                        }
                        if("r".equals(Board[rowKing + distance * i][colKing + distance * j]) || "q".equals(Board[rowKing + distance * i][colKing + distance * j])){
                            return false;
                        }
                    }catch (Exception e){}
                    distance = 1;
                }
            }
        //Knight
        for (int i = -2; i<=2; i++){ // let the King move like Knight
                for (int j =-2; j<=2; j++){
                    if (Math.abs(i*j) == 2){
                        try{
                            if ("k".equals(Board[rowKing + i][colKing + j])){
                                return false;
                            }
                        }catch (Exception e){}
                    }
                }
        }
        //Pawn
        if (rowKing > 1){
            for (int i =-1; i <=1; i+=2){
                try{
                    if ("p".equals(Board[rowKing -1][colKing + i])){
                        return false;
                    }
                }catch (Exception e){}
            }
        }
        //King
        for(int i =-1; i<= 1; i++){
            for (int j = -1; j <= 1; j++){
                try{
                    if(i != 0 || j!= 0) {
                        if ("a".equals(Board[rowKing + i][colKing + j])) {
                            return false;
                        }
                    }
                }catch(Exception e){}
            }
        }
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
        String List = "", getMove;
        int row = position / 8,
                col = position % 8,
                distance = 1;
        for (int tempRow = -1; tempRow <= 1; tempRow += 2)
        {
            for (int tempCol = -1; tempCol <= 1; tempCol += 2)
            {
                try
                {
                    while (" ".equals(Board[row + tempRow * distance][col + tempCol * distance]))
                    {
                        getMove = Set_GetMove(row, col, row + tempRow * distance, col + tempCol * distance);
                        if (getMove.length() != 0){
                            List = List + getMove;
                        }
                        distance++;
                    }
                    if (Character.isLowerCase(Board[row + tempRow * distance][col + tempCol * distance].charAt(0)))
                    {
                        getMove = Set_GetMove(row, col, row + tempRow * distance, col + tempCol * distance);
                        if (getMove.length() != 0){
                            List = List + getMove;
                        }
                    }
                }catch (Exception e){}
                distance = 1;
            }
        }
        return List;
    }
    public static String moveKing(int position){
        String List = "", getMove;
        int row = position / 8,
                col = position % 8;
        for (int tempRow = -1; tempRow <= 1; tempRow++)
        {
            for (int tempCol = -1; tempCol <= 1; tempCol++)
            {
                try
                {
                    if ( !(tempRow == 0 && tempCol == 0) && (" ".equals(Board[row + tempRow][col + tempCol]) || Character.isLowerCase(Board[row + tempRow][col + tempCol].charAt(0))))
                    {
                        getMove = Set_GetMove(row, col, row + tempRow,  col + tempCol);
                        if (getMove.length() != 0)
                        {
                            List = List + getMove;
                        }
                    }
                }catch (Exception e){}
            }
        }
        //Castling upper long
        if (castlingULong && safeKing() && "A".equals(Board[7][4]) && "R".equals(Board[7][0]) && " ".equals(Board[7][1]) && " ".equals(Board[7][2]) && " ".equals(Board[7][3]))
        {
            Boolean checkCastling = true;
            //Colume where King move
            for (int tempCol = 1; tempCol <= 3; tempCol++)
            {
                getMove = Set_GetMove(7,4,7,tempCol);
                if (getMove.length() == 0)
                {
                    checkCastling = false;
                    break;
                }
            }
            if (checkCastling)
            {
                //Colume Previous King, Rook => Next King, Rook
                List = List + "4023C";
            }
        }
        //Castling upper short
        if (castlingUShort && safeKing() && "A".equals(Board[7][4]) && "R".equals(Board[7][7]) && " ".equals(Board[7][5]) && " ".equals(Board[7][6]))
        {
            Boolean checkCastling = true;
            //Colume where King move
            for (int tempCol = 5; tempCol <= 6; tempCol++)
            {
                getMove = Set_GetMove(7,4,7,tempCol);
                if (getMove.length() == 0)
                {
                    checkCastling = false;
                    break;
                }
            }
            if (checkCastling)
            {
                //Colume Previous King, Rook => Next King, Rook
                List = List + "4765C";
            }
        }
        //Castling lower long when convert from MIN=>Max
        if (castlingLLong && safeKing() && "A".equals(Board[7][3]) && "R".equals(Board[7][7]) && " ".equals(Board[7][4]) && " ".equals(Board[7][5]) && " ".equals(Board[7][6]))
        {
            Boolean checkCastling = true;
            //Colume where King move
            for (int tempCol = 4; tempCol <= 6; tempCol++)
            {
                getMove = Set_GetMove(7,4,7,tempCol);
                if (getMove.length() == 0)
                {
                    checkCastling = false;
                    break;
                }
            }
            if (checkCastling)
            {
                //Colume Previous King, Rook => Next King, Rook
                List = List + "3754C";
            }
        }
        //Castling lower long when convert from MIN=>Max
        if (castlingLShort && safeKing() && "A".equals(Board[7][3]) && "R".equals(Board[7][0]) && " ".equals(Board[7][1]) && " ".equals(Board[7][2]))
        {
            Boolean checkCastling = true;
            //Colume where King move
            for (int tempCol = 1; tempCol <= 2; tempCol++)
            {
                getMove = Set_GetMove(7,4,7,tempCol);
                if (getMove.length() == 0)
                {
                    checkCastling = false;
                    break;
                }
            }
            if (checkCastling)
            {
                //Colume Previous King, Rook => Next King, Rook
                List = List + "3012C";
            }
        }
        return List;
    }
    public static String Set_GetMove(int preRow, int preCol, int nextRow, int nextCol){
        String List= "", oldPiece;
        oldPiece = Board[nextRow][nextCol];
        Board[nextRow][nextCol] = Board[preRow][preCol];
        Board[preRow][preCol] = " ";
        if(Board[nextRow][nextCol].equals("A")){
            kingPositionU = nextRow * 8 + nextCol;
        }
        if (safeKing()){
            List = List + preRow + preCol + nextRow + nextCol + oldPiece;
        }
        Board[preRow][preCol] = Board[nextRow][nextCol];
        Board[nextRow][nextCol] = oldPiece;
        return List;
    }
}
