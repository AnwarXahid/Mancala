/**
 * Created by Xahid's PC on 12/16/2017.
 */
public class Mancala {
    public static int[] board = {4,4,4,4,4,4,0,4,4,4,4,4,4,0};
    private static final int  MAX= 1000;
    private static final int MIN = -1000;



    public static void main(String[] args) {
        printMancalaBoard();
        playMove(board,8,2);
        printMancalaBoard();
    }


    //printing mancala board
    public static void printMancalaBoard() {
     System.out.println("       |  12  |  11  |  10  |  9  |  8  |  7  |      ");
     System.out.println("‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
     System.out.print("          "+board[12]+"  |  "+board[11]+"  |  "+board[10]+"  |  "+board[9]+"  |  "+board[8]+"  |  "+board[7]+"\n");
     System.out.println("");
     System.out.println("|      |‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾|      |");
     System.out.println("Player 2: "+board[13]+"                                      Player 1: "+board[6]);
     System.out.println("|      |_________________________________________|      |");
     System.out.println("");
     System.out.print("          "+board[0]+"  |  "+board[1]+"  |  "+board[2]+"  |  "+board[3]+"  |  "+board[4]+"  |  "+board[5]+"\n");
     System.out.println("_____________________________________________________");
     System.out.println("       |  0  |  1  |  2  |  3  |  4  |  5  |      ");
     System.out.println("\n\n\n\n");
    }



    //making mancala move,simple move,last pit that takes marble is empty,return true if gets extra move
    public static boolean playMove(int[] board,int index,int player){
        int i;
        if(player==1)
        {
            if(index<0 | index>5)
            {
                System.out.println("Invalid pit has been selected for player 1.");
            }
            int marbles=board[index];
            if(marbles==0)
                return false;
            board[index]=0;
            for(i = index+1; marbles>0; i++)
            {
                i%=14;
                if(i==13 | board[i]==9)
                    continue;
                marbles--;
                board[i]+=1;
            }
            i--;
            if(i==6)
                return true;
            if(i!=6 & i!=13 & i>=0 & i<=5 & board[i]==1)
            {
                board[6]+=board[i];
                board[i]=0;
                board[6]+=board[12-i];
                board[12-i]=0;
            }
            return false;
        }
        else
        {
            if(index<7 | index>12)
            {
                System.out.println("Invalid pit has been selected for player 2.");
            }
            int marbles=board[index];
            if(marbles==0)
                return false;
            board[index] = 0;
            for(i = index+1; marbles>0; i++)
            {
                i%=14;
                if(i==6 | board[i]==9)
                    continue;
                marbles--;
                board[i]+=1;
            }
            i--;
            if(i==13)
                return true;
            if(i!=6 & i!=13 & i>=7 & i<=12 & board[i]==1)
            {
                board[13]+=board[i];
                board[i]=0;
                board[13]+=board[12-i];
                board[12-i]=0;
            }
            return false;
        }
    }



    //checking whether the game is ended
    public static boolean terminateGame(int[] board,int player){
        boolean status = true;
        if(player==1)
        {
            for(int i = 0 ; i<6 ; i++)
            {
                if(board[i]>0)
                {
                    status=false;
                    break;
                }
            }
        }
        else
        {
            for(int i = 7 ; i<13 ; i++)
            {
                if(board[i]>0)
                {
                    status=false;
                    break;
                }
            }
        }
        return status;
    }




    //minimax with alpha-beta pruning
    public static int minimax(int[] board, int player, boolean maximizingPlayer, int depth, int alpha, int beta)
    {
        int[] tempBoard = new int[14];
        for(int i = 0; i<14 ; i++)
            tempBoard[i]=board[i];
        // terminating condition
        if (depth == 3 | terminateGame(tempBoard,player))
            return heuristic_1(tempBoard,player);

        if (maximizingPlayer)
        {
            int best = MIN;

            // Recur for left and right children
            for (int i=0; i<2; i++)
            {
                int val = minimax(depth+1, nodeIndex*2+i,
                        false, values, alpha, beta);
                best = max(best, val);
                alpha = max(alpha, best);

                // Alpha Beta Pruning
                if (beta <= alpha)
                    break;
            }
            return best;
        }
        else
        {
            int best = MAX;

            // Recur for left and right children
            for (int i=0; i<2; i++)
            {
                int val = minimax(depth+1, nodeIndex*2+i,
                        true, values, alpha, beta);
                best = min(best, val);
                beta = min(beta, best);

                // Alpha Beta Pruning
                if (beta <= alpha)
                    break;
            }
            return best;
        }
    }




    //heuristic 1
    public static int heuristic_1(int[] board, int player)
    {
        int stones_in_my_storage, stones_in_opponents_storage;
        if(player==1)
        {
            stones_in_my_storage = board[6];
            stones_in_opponents_storage=board[13];
            return (stones_in_my_storage-stones_in_opponents_storage);
        }
        else
        {
            stones_in_my_storage = board[13];
            stones_in_opponents_storage=board[6];
            return (stones_in_my_storage-stones_in_opponents_storage);
        }
    }

}
