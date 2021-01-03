/**
 * GameBoard is a class that act as the Controller class
 *
 * @author (Zamfirdaus, Nurwahafizah, Nurfarah Faiqah)
 */
import java.util.Scanner;
import java.awt.*;
import javax.swing.*;
import java.io.*;

public class GameBoard{ // main class of GameBoard
    private static Object[][] board = new Object[8][7];  
    private static Player player1 = new Player("A");
    private static Player player2 = new Player("B");
    private static Player[] playerRoll = new Player[]{player1,player2}; 
    private static int counter = 0;
   
    public GameBoard(){ //constructor
    } 
    public void saveBoard(){ // save the current position of piece in board to txt file- Nurfarah
        int saveI, saveJ;
        String pieceType;
        JFileChooser fc = new JFileChooser(); //to choose folder in computer to save to
        fc.setDialogType(JFileChooser.SAVE_DIALOG);
        int fcState = fc.showSaveDialog(null);
        
        switch (fcState){
            case JFileChooser.APPROVE_OPTION: //user has chosen a folder
                try{
                    File fileToSave = fc.getSelectedFile();
                    FileWriter fw = new FileWriter(fileToSave+".txt",true); //write name of txt file in selected folder.
                    for(int i=0;i<8;i++){
                        for(int j=0;j<7;j++){
                            //saving blue pieces into text file
                            //save sun piece
                            if(board[i][j]!=null && board[i][j] == player1.sun){
                                saveI = i;
                                saveJ = j;
                                pieceType = "A11";    
                                fw.write("Player 1," + saveI + "," + saveJ + "," + pieceType + System.getProperty("line.separator"));
                            }
                            
                            //save chevron pieces
                            else if(board[i][j]!=null && (board[i][j] == player1.chevron1 || board[i][j] == player1.chevron2)){
                                saveI = i;
                                saveJ = j;
                                pieceType = "A21";    
                                fw.write("Player 1," + saveI + "," + saveJ + "," + pieceType + System.getProperty("line.separator"));
                            }
                            
                            //save arrow pieces
                            else if(board[i][j]!=null && (board[i][j] == player1.arrow1 || board[i][j] == player1.arrow2 || board[i][j] == player1.arrow3 || board[i][j] == player1.arrow4)){   
                                saveI = i;
                                saveJ = j;
                                pieceType = "A5";    
                                fw.write("Player 1," + saveI + "," + saveJ + "," + pieceType + System.getProperty("line.separator"));
                            }
                            
                            //save plus pieces
                            else if(board[i][j]!=null && (board[i][j].toString().contains("A41") || board[i][j].toString().contains("A42"))){
                                saveI = i;
                                saveJ = j;
                                pieceType = "A41";    
                                fw.write("Player 1," + saveI + "," + saveJ + "," + pieceType + System.getProperty("line.separator"));
                            }
                            
                            //save triangle pieces
                            else if(board[i][j]!=null && (board[i][j].toString().contains("A31") || board[i][j].toString().contains("A32"))){
                                saveI = i;
                                saveJ = j;
                                pieceType = "A31";    
                                fw.write("Player 1," + saveI + "," + saveJ + "," + pieceType + System.getProperty("line.separator"));
                            }
                            
                            //saving red pieces into text file
                            //save sun piece
                            else if(board[i][j]!=null && (board[i][j] == player2.sun)){ 
                                saveI = i;
                                saveJ = j;
                                pieceType = "B11";    
                                fw.write("Player 2," + saveI + "," + saveJ + "," + pieceType + System.getProperty("line.separator"));
                            }
                            
                            //save chevron pieces
                            else if(board[i][j]!=null && (board[i][j] == player2.chevron1 || board[i][j] == player2.chevron2)){
                                saveI = i;
                                saveJ = j;
                                pieceType = "B21";    
                                fw.write("Player 1," + saveI + "," + saveJ + "," + pieceType + System.getProperty("line.separator"));
                            }
                            
                            //save arrow pieces
                            else if(board[i][j]!=null && (board[i][j] == player2.arrow1 || board[i][j] == player2.arrow2 || board[i][j] == player2.arrow3 || board[i][j] == player2.arrow4)){   
                                saveI = i;
                                saveJ = j;
                                pieceType = "B5";    
                                fw.write("Player 1," + saveI + "," + saveJ + "," + pieceType + System.getProperty("line.separator"));
                            }
                            
                            //save plus pieces
                            else if(board[i][j]!=null && (board[i][j].toString().contains("B41") || board[i][j].toString().contains("B42"))){
                                saveI = i;
                                saveJ = j;
                                pieceType = "B41";    
                                fw.write("Player 1," + saveI + "," + saveJ + "," + pieceType + System.getProperty("line.separator"));
                            }
                            
                            //save triangle pieces
                            else if(board[i][j]!=null && (board[i][j].toString().contains("B31") || board[i][j].toString().contains("B32"))){
                                saveI = i;
                                saveJ = j;
                                pieceType = "B31";    
                                fw.write("Player 1," + saveI + "," + saveJ + "," + pieceType + System.getProperty("line.separator"));
                            }
                        }
                    }
                    fw.close();
                }
                catch  (Exception e){
                    e.printStackTrace();
                }
                JOptionPane msj = new JOptionPane();
                JOptionPane.showMessageDialog(msj, "Game successfully saved!");
                break;
                
            case JFileChooser.CANCEL_OPTION:
                break;   
        }
    }
    public void loadSavedBoard(){  // load the position of piece in board from txt file - Nurfarah
        JFileChooser fc = new JFileChooser();
        int fcState = fc.showOpenDialog(null);
        switch(fcState){
            case JFileChooser.APPROVE_OPTION:
                try{
                    File selectedFile = fc.getSelectedFile();
                    FileReader fw = new FileReader(selectedFile);
                    BufferedReader br = new BufferedReader(fw); //read selected file
                    
                    //reset board or remove all pieces
                    for(int i=0;i<8;i++){
                        for(int j=0;j<7;j++){
                            if(board[i][j] != null){
                                board[i][j] = null; //remove Piece
                                GameBoardGUI.btn[i][j].setIcon(null);
                            }
                        }
                    }
                    String lineInFile;
                    
                    //reading/loading pieces from text file
                    while((lineInFile = br.readLine())!=null){
                        String[]spliter = lineInFile.split("\\,");
                        //reading blue pieces
                        //sun piece
                        if(spliter[3].equals("A11")){
                            board[Integer.parseInt(spliter[1])][Integer.parseInt(spliter[2])] = "A11";
                        }
                        
                        //chevron pieces
                        else if(spliter[3].equals("A21")){
                            board[Integer.parseInt(spliter[1])][Integer.parseInt(spliter[2])] = "A21";
                        }
                        
                        //arrow pieces
                        else if(spliter[3].equals("A5")){
                            board[Integer.parseInt(spliter[1])][Integer.parseInt(spliter[2])] = "A5";
                        }
                        
                        //plus pieces
                        else if(spliter[3].equals("A41")){
                            board[Integer.parseInt(spliter[1])][Integer.parseInt(spliter[2])] = "A41";
                        }
                        
                        //triangle pieces
                        else if(spliter[3].equals("A31")){
                            board[Integer.parseInt(spliter[1])][Integer.parseInt(spliter[2])] = "A31";
                        }
                      
                        
                        //reading red pieces
                        //sun piece
                        else if(spliter[3].equals("B11")){
                            board[Integer.parseInt(spliter[1])][Integer.parseInt(spliter[2])] = "B11";
                        }
                        
                        //chevron pieces
                        else if(spliter[3].equals("B21")){
                            board[Integer.parseInt(spliter[1])][Integer.parseInt(spliter[2])] = "B21";
                        }
                        
                        //arrow pieces
                        else if(spliter[3].equals("B5")){
                            board[Integer.parseInt(spliter[1])][Integer.parseInt(spliter[2])] = "B5";
                        }
                        
                        //plus pieces
                        else if(spliter[3].equals("B41")){
                            board[Integer.parseInt(spliter[1])][Integer.parseInt(spliter[2])] = "B41";
                        }
                        
                        //triangle pieces
                        else if(spliter[3].equals("B31")){
                            board[Integer.parseInt(spliter[1])][Integer.parseInt(spliter[2])] = "B31";
                        }
                    }
                }
                catch  (Exception e){
                    e.printStackTrace();
                }
                JOptionPane msj = new JOptionPane();
                JOptionPane.showMessageDialog(msj, "Game successfully loaded!");
                break;
                
            case JFileChooser.CANCEL_OPTION:
                break;
        }
    }
    public void printBoard(){ // print the board in the command prompt - Zam
        System.out.println();
        for(int i=0;i<8;i++){
            System.out.print("|  ");
            for(int j=0;j<7;j++){
                System.out.print(String.format("%-4s %-1s",board[i][j], "| "));
            }
            System.out.println();
            System.out.println();
        }
    }
    public void startGame(){ // set the starting position of pieces and players to start the game - Zam
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                board[i][j] = null;
            }
        }
        board[0][0] = player1.plus1;
        board[0][6] = player1.plus2;
        board[0][3] = player1.sun;
        board[0][1] = player1.triangle1;
        board[0][5] = player1.triangle2;
        board[0][2] = player1.chevron1;
        board[0][4] = player1.chevron2;
        board[1][0] = player1.arrow1; 
        board[1][2] = player1.arrow2;
        board[1][4] = player1.arrow3;
        board[1][6] = player1.arrow4;
        
        
        board[7][0] = player2.plus1;
        board[7][6] = player2.plus2;
        board[7][3] = player2.sun;
        board[7][1] = player2.triangle1;
        board[7][5] = player2.triangle2;
        board[7][2] = player2.chevron1;
        board[7][4] = player2.chevron2;
        board[6][0] = player2.arrow1;
        board[6][2] = player2.arrow2;
        board[6][4] = player2.arrow3;
        board[6][6] = player2.arrow4;
        
        player1.determineStartingPos(board);
        player2.determineStartingPos(board);
    }
    public Object[][] getBoard(){ // get the board to start the gae - Zam
        startGame();
        return board;
    }
    public void movePiece(int fromI, int fromJ, int toI, int toJ){ // move the piece - Zam
        Object temp = null;
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                if(board[i][j]!=null && board[fromI][fromJ]!=null){
                    if(board[i][j].toString().equals(board[fromI][fromJ].toString())){
                        temp = board[i][j];
                        board[i][j] = null;
                        board[toI][toJ] = temp;
                    }
                }
            }
        }
        
    }
    public int checkMate(Object[][] board){ // check the existance of the Sun piece - Nurwahafizah
        int existAB = 0;
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                if(board[i][j]!=null){
                    if(board[i][j].toString().contains("A11")){
                        existAB +=10;
                        
                    }
                    if(board[i][j].toString().contains("B11")){
                        existAB +=100;
                        
                    }
                }
            }
        }
        return existAB;
        
    }
    public void pickWinner(Integer a){ // pick the winner of the game - Nurwahafizah
        if(a==110){
            System.out.println("continue");
        }
        if(a==10){ // B win
            System.out.println("Red win!");
        }
        if(a==100){ // A win
            System.out.println("Blue win!");
        }
    }
    public void turnPlusTriangle(Object[][] board, Player player){ // swap the plus to triangle - Zam
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                if(board[i][j]!=null){
                    if(board[i][j].toString().contains("41") && board[i][j].toString().contains(player.name)){
                        board[i][j] = player.triangle1;
                        
                    }
                    else if(board[i][j].toString().contains("42") && board[i][j].toString().contains(player.name)){
                        board[i][j] = player.triangle2;
                    }
                    
                    else if(board[i][j].toString().contains("31") && board[i][j].toString().contains(player.name)){
                        board[i][j] = player.plus1;
                    }
                    else if(board[i][j].toString().contains("32") && board[i][j].toString().contains(player.name)){
                        board[i][j] = player.plus2;
                    }
                }
            }
        }
        
    }        
} 

