
package tictactoe;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        String[] sarr=new String[9];
        for(int i=0;i<9;i++){
            sarr[i]=" ";
        }
        inputCommand(sarr);

    }
    public static void inputCommand(String[] sarr){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input command: ");
        String inputs=scanner.nextLine();
        if(InputCommandValidation(inputs).equals("valid")){

            printGrid(sarr);
            RunTictactoe(inputs,sarr);
        }else if(InputCommandValidation(inputs).equals("invalid")){
            inputCommand(sarr);
        }
    }
    public static void RunTictactoe(String inputs,String[] sarr){
        Scanner scanner=new Scanner(System.in);
        String[] inArr=inputs.split(" ");
        String player=inArr[1];
        String currentPlayer="X";
        while(!xWins(sarr)&&!oWins(sarr)&&gameNotFinished(sarr)){
            if(player.equals("easy")){
                easyMove(sarr,currentPlayer);
                if(currentPlayer.equals("X")){
                    currentPlayer="O";
                }else{
                    currentPlayer="X";
                }
                if(player.equals(inArr[1])){
                    player=inArr[2];
                }else{
                    player=inArr[1];
                }

            }else if(player.equals("medium")) {
                MediumMove(sarr,currentPlayer);
                if(currentPlayer.equals("X")){
                    currentPlayer="O";
                }else{
                    currentPlayer="X";
                }
                if(player.equals(inArr[1])){
                    player=inArr[2];
                }else{
                    player=inArr[1];
                }
            }else if(player.equals("hard")){
                HardMove(sarr,currentPlayer);
                if(currentPlayer.equals("X")){
                    currentPlayer="O";
                }else{
                    currentPlayer="X";
                }
                if(player.equals(inArr[1])){
                    player=inArr[2];
                }else{
                    player=inArr[1];
                }
            }
                else{
                while(true){

                    System.out.print("Enter the coordinates: ");
                    String y=scanner.nextLine();
                    if(isInputValid(y,sarr,currentPlayer)){
                        if(xWins(sarr)){
                            System.out.println("X wins");

                            break;
                        }else if(oWins(sarr)){
                            System.out.println("O wins");
                            break;
                        }else if(!gameNotFinished(sarr)){
                            System.out.println("Draw");
                            break;
                        }else{
                            if(currentPlayer.equals("X")){
                                currentPlayer="O";
                            }else{
                                currentPlayer="X";
                            }
                            if(player.equals(inArr[1])){
                                player=inArr[2];
                            }else{
                                player=inArr[1];
                            }
                            break;



                        }
                    }
                }

            }
        }
        for(int i=0;i<9;i++){
            sarr[i]=" ";
        }
        inputCommand(sarr);


    }
    public static void easyMove(String[] sarr,String currentPlayer){
        System.out.println("Making move level \"easy\"");
        Random random=new Random();
        int x=random.nextInt(3)+1;
        int y=random.nextInt(3)+1;
        while(!sarr[indexMod(x,y)].equals(" ")){
            x=random.nextInt(3)+1;
            y=random.nextInt(3)+1;
        }
        sarr[indexMod(x,y)]=currentPlayer;
        printGrid(sarr);
        if(xWins(sarr)){
            System.out.println("X wins");
        }else if(oWins(sarr)){
            System.out.println("O wins");
        }else if(!gameNotFinished(sarr)){
            System.out.println("Draw");
        }
    }
    public static void MediumMove(String[] sarr,String currentPlayer){
        System.out.println("Making move level \"medium\"");
        boolean cond=false;
        for(int i=1;i<4;i++){
            for(int j=1;j<4;j++){
                if(sarr[indexMod(i,j)].equals(" ")){
                    if(currentPlayer.equals("X")){
                        sarr[indexMod(i,j)]="X";
                        if(xWins(sarr)){
                            printGrid(sarr);
                            cond=true;
                            break;
                        }else{
                            sarr[indexMod(i,j)]=" ";
                        }
                    }else{
                        sarr[indexMod(i,j)]="O";
                        if(oWins(sarr)){
                            printGrid(sarr);
                            cond=true;
                            break;
                        }else{
                            sarr[indexMod(i,j)]=" ";
                        }
                    }
                }
            }
        }
        if(cond==false){
            for(int i=1;i<4;i++){
                for(int j=1;j<4;j++){

                    if(sarr[indexMod(i,j)].equals(" ")){
                        if(currentPlayer.equals("X")){
                            sarr[indexMod(i,j)]="O";
                            if(oWins(sarr)){
                                sarr[indexMod(i,j)]="X";
                                printGrid(sarr);
                                cond=true;
                                break;
                            }else{
                                sarr[indexMod(i,j)]=" ";
                            }
                        }else{
                            sarr[indexMod(i,j)]="X";
                            if(xWins(sarr)){
                                sarr[indexMod(i,j)]="O";
                                printGrid(sarr);
                                cond=true;
                                break;
                            }else{
                                sarr[indexMod(i,j)]=" ";
                            }
                        }
                    }
                }
            }
        }
        if(cond==false){
            Random random=new Random();
            int x=random.nextInt(3)+1;
            int y=random.nextInt(3)+1;
            while(!sarr[indexMod(x,y)].equals(" ")){
                x=random.nextInt(3)+1;
                y=random.nextInt(3)+1;
            }
            sarr[indexMod(x,y)]=currentPlayer;
            printGrid(sarr);
            if(xWins(sarr)){
                System.out.println("X wins");
            }else if(oWins(sarr)){
                System.out.println("O wins");
            }else if(!gameNotFinished(sarr)){
                System.out.println("Draw");
            }
        }
    }
    public static void HardMove(String[] sarr,String currentPlayer){
        System.out.println("Making move level \"hard\"");
        int finalScore=Integer.MIN_VALUE;
        int sco=Integer.MIN_VALUE;
        int pos=1;
        for(int i=1;i<4;i++){
            for(int j=1;j<4;j++){
                if(sarr[indexMod(i,j)]==" "){
                    sarr[indexMod(i,j)]=currentPlayer;
                    sco=MinMax(sarr,false,currentPlayer);
                    sarr[indexMod(i,j)]=" ";
                    if(sco>finalScore){
                        finalScore=sco;
                        pos=indexMod(i,j);
                    }
                }
            }

        }
        sarr[pos]=currentPlayer;
        printGrid(sarr);
        if(xWins(sarr)){
            System.out.println("X wins");
        }else if(oWins(sarr)){
            System.out.println("O wins");
        }else if(!gameNotFinished(sarr)){
            System.out.println("Draw");
        }
    }
    public static int MinMax(String[] sarr,boolean isMaximizing,String currentPlayer){
        if(xWins(sarr) && currentPlayer.equals("X")){
            return 1;
        }else if(oWins(sarr)&& currentPlayer.equals("O")){
            return 1;
        }if(xWins(sarr) && currentPlayer.equals("O")){
            return -1;
        }else if(oWins(sarr)&& currentPlayer.equals("X")){
            return -1;
        }else if(!gameNotFinished(sarr)){
            return 0;
        }else{
            if(isMaximizing){
                int score=Integer.MIN_VALUE;
                int bestScore=-10;
                for(int i=1;i<4;i++){
                    for(int j=1;j<4;j++){

                            if(sarr[indexMod(i,j)]==" "){
                                sarr[indexMod(i,j)]=currentPlayer;
                                if(currentPlayer.equals("X")){
                                    score=MinMax(sarr,false,"O");
                                }else{
                                    score=MinMax(sarr,false,"X");
                                }
                                sarr[indexMod(i,j)]=" ";
                                if(score>bestScore){
                                    bestScore=score;
                                }
                            }

                    }

                }
                return bestScore;
            }else{
                int score=Integer.MAX_VALUE;
                int bestScore=10;
                for(int i=1;i<4;i++){
                    for(int j=1;j<4;j++){

                            if(sarr[indexMod(i,j)]==" "){
                                sarr[indexMod(i,j)]=currentPlayer;
                                if(currentPlayer.equals("X")){
                                    score=MinMax(sarr,true,"O");
                                }else{
                                    score=MinMax(sarr,true,"X");
                                }
                                sarr[indexMod(i,j)]=" ";
                                if(score<bestScore){
                                    bestScore=score;
                                }
                            }

                    }

                }
                return bestScore;
            }
        }
    }

    public static String  InputCommandValidation(String input){
        try{
            String[] inArr=input.split(" ");
            if(inArr.length==1&&inArr[0].equals("exit")){
                return "exit";
            }else if(inArr.length!=3){
                System.out.println("Bad parameters!");
                return "invalid";
            }else{
                if(inArr[0].equals("start")){
                    if(inArr[1].equals("easy")||inArr[1].equals("user")||inArr[1].equals("medium")||inArr[1].equals("hard")){
                        if(inArr[2].equals("easy")||inArr[2].equals("user")||inArr[2].equals("medium")||inArr[2].equals("hard")){
                            return "valid";
                        }
                    }
                }
                return "invalid";
            }
        }catch (Exception e){
            System.out.println("Bad parameters!");
            return "invalid";
        }


    }
    public static boolean xWins(String[] sarr){
        if((sarr[0].equals("X")&&sarr[1].equals("X")&&sarr[2].equals("X"))||
                (sarr[3].equals("X")&&sarr[4].equals("X")&&sarr[5].equals("X"))||
                (sarr[6].equals("X")&&sarr[7].equals("X")&&sarr[8].equals("X"))||
                (sarr[0].equals("X")&&sarr[4].equals("X")&&sarr[8].equals("X"))||
                (sarr[2].equals("X")&&sarr[4].equals("X")&&sarr[6].equals("X"))||
                (sarr[0].equals("X")&&sarr[3].equals("X")&&sarr[6].equals("X"))||
                (sarr[1].equals("X")&&sarr[4].equals("X")&&sarr[7].equals("X"))||
                (sarr[2].equals("X")&&sarr[5].equals("X")&&sarr[8].equals("X"))
        ){
            return true;

        }
        return false;
    }
    public static boolean oWins(String[] sarr){
        if((sarr[0].equals("O")&&sarr[1].equals("O")&&sarr[2].equals("O"))||
                (sarr[3].equals("O")&&sarr[4].equals("O")&&sarr[5].equals("O"))||
                (sarr[6].equals("O")&&sarr[7].equals("O")&&sarr[8].equals("O"))||
                (sarr[0].equals("O")&&sarr[4].equals("O")&&sarr[8].equals("O"))||
                (sarr[2].equals("O")&&sarr[4].equals("O")&&sarr[6].equals("O"))||
                (sarr[0].equals("O")&&sarr[3].equals("O")&&sarr[6].equals("O"))||
                (sarr[1].equals("O")&&sarr[4].equals("O")&&sarr[7].equals("O"))||
                (sarr[2].equals("O")&&sarr[5].equals("O")&&sarr[8].equals("O"))
        ){
            return true;

        }
        return false;
    }
    public static boolean gameNotFinished(String[] sarr){
        for(int i =0;i<9;i++){
            if(sarr[i].equals(" ")){
                return true;
            }
        }
        return false;
    }
    public static boolean isInputValid(String s,String[] sarr,String currentPlayer){

        try {
            String x=s.split(" ")[0];
            String y=s.split(" ")[1];
            int Xcoor=Integer.parseInt(x);
            int Ycoor=Integer.parseInt(y);
            if(Xcoor>3||Xcoor<1||Ycoor>3||Ycoor<1){
                System.out.println("Coordinates should be from 1 to 3!");
                return false;
            }
            else if(!sarr[indexMod(Xcoor,Ycoor)].equals(" ")){
                System.out.println("This cell is occupied! Choose another one!");
                return false;
            }else{
                sarr[indexMod(Xcoor,Ycoor)]=currentPlayer;
                printGrid(sarr);
            }

        }catch(Exception e){
            System.out.println("You should enter numbers!");
            return false;
        }

        return true;
    }

    public static int indexMod(int x,int y){
        if(x==1&&y==1){
            return 6;
        }else if(x==1&&y==2){
            return 3;
        }else if(x==1&&y==3){
            return 0;
        }else if(x==2&&y==1){
            return 7;
        }else if(x==2&&y==2){
            return 4;
        }else if(x==2&&y==3){
            return 1;
        }else if(x==3&&y==1){
            return 8;
        }else if(x==3&&y==2){
            return 5;
        }else{
            return 2;
        }
    }

    public static void printGrid(String[] sarr){

        System.out.println("---------");
        for(int i=0;i<9;i++){
            if(i%3==0){
                System.out.print("| ");
            }
            System.out.print(sarr[i]+" ");
            if((i+1)%3==0){
                System.out.print("|");
                System.out.println();
            }
        }
        System.out.println("---------");
    }


    public static boolean ImpossibleCond(String[] sarr){
        int xcount=0;
        int ocount=0;
        for(int i=0;i<9;i++){
            if(sarr[i].equals("X")){
                xcount++;
            }else if(sarr[i].equals("O")){
                ocount++;
            }
        }
        if(xcount>ocount&&xcount-ocount>1){
            return true;
        }else if(xcount<ocount&&ocount-xcount>1){
            return true;
        }
        return false;
    }
}
