
/**
 * GameBoardGUI is a class that act as the View class 
 *
 * @author (Zamfirdaus, Nurfarah Faiqah, Wan eliasaph)
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.util.*;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GameBoardGUI extends JFrame implements ActionListener { // main class of GUI board
    JMenuBar menuBar;
    JMenu optionMenu, aboutMenu;
    JMenuItem newGameMenu,saveGameMenu, loadGameMenu, aboutSubMenu, gameRulesSubMenu;
    JPanel mainPanel, gameBoard, panelMsg;
    JTextField gameTxtField,errorTxtField;
    JScrollPane scrollBar;
    JTextArea gameTxtArea;
    Color myColor;
    public static JButton[][] btn = new JButton[8][7];
    
    private Object[][] board = new Object[8][7]; 
    private static int fromI;
    private static int fromJ;
    private static int toI;
    private static int toJ;
    private static String tempFrom=null,tempTo=null, temp=null;
    private static Player player1 = new Player("A");
    private static Player player2 = new Player("B");
    private static Player[] playerRoll = new Player[]{player1,player2}; 
    private static int counter = 1;
    private GameBoard game = new GameBoard();       

    public GameBoardGUI() { // class constructor to initiate game - Zam,Wan,Nurfarah
        super("Board Game");
        setSize(800, 800);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

       gameBoard = new JPanel(new GridLayout(8,0)); 
       for (int rows = 0; rows < 8; rows++) {
            for (int columns = 0; columns < 7; columns++) {
            btn[rows][columns] = new JButton();
            btn[rows][columns].setBackground(new Color(255,255,255));
            gameBoard.add(btn[rows][columns]);
            btn[rows][columns].addActionListener(this); 
            btn[rows][columns].setText(Integer.toString(rows)+Integer.toString(columns));
            }
       }
       add(gameBoard,BorderLayout.CENTER);

       panelMsg = new JPanel();
       panelMsg.setLayout(new BorderLayout());
       gameTxtField = new JTextField("");
       gameTxtField.setText("");
       panelMsg.add(gameTxtField, BorderLayout.NORTH);
       errorTxtField = new JTextField("");
       errorTxtField.setText("");
       panelMsg.add(errorTxtField, BorderLayout.SOUTH); 
       add(panelMsg,BorderLayout.SOUTH);
       
        // create menu bar
       menuBar = new JMenuBar();
       this.setJMenuBar(menuBar);

        // add "option" into menu bar
       optionMenu = new JMenu("Options");
       menuBar.add(optionMenu);

        // add "about" into menu bar
       aboutMenu = new JMenu("About");
       menuBar.add(aboutMenu);

       newGameMenu = new JMenuItem("New game", new ImageIcon("new2.ico")); // new ico
       newGameMenu.addActionListener(new newGameButtonActionListener());
       optionMenu.add(newGameMenu);
       
        // add sub "save as" into option bar
       saveGameMenu = new JMenuItem("Save as", new ImageIcon("save2.ico"));
       saveGameMenu.addActionListener(new saveButtonActionListener());
       optionMenu.add(saveGameMenu);
        
        //add sub "load game" into option bar
       loadGameMenu = new JMenuItem("Load game", new ImageIcon("load2.ico"));
       loadGameMenu.addActionListener(new loadButtonActionListener());
       optionMenu.add(loadGameMenu);
        
        //add sub "about" into about bar
       aboutSubMenu = new JMenuItem("About us", new ImageIcon("user2.ico"));
       aboutSubMenu.addActionListener(new aboutMenuButtonActionListener());
       aboutMenu.add(aboutSubMenu);
        
        //add sub "rules" into about bar
       gameRulesSubMenu = new JMenuItem("Game rules", new ImageIcon("quest2.ico"));
       gameRulesSubMenu.addActionListener(new rulesButtonActionListener());
       aboutMenu.add(gameRulesSubMenu);

       JMenuItem exit = new JMenuItem("Exit");
       menuBar.add(exit);
       exit.addActionListener(this);
    
       board = game.getBoard();
       player1.determineStartingPos(board);
       player2.determineStartingPos(board);
       setImage(board);
       gameTxtField.setText("TURN RED");
       setVisible(true);
   }
    private void setImage(Object[][] board){ // set the image of the game button - Zam
       for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                btn[i][j].setBackground(Color.WHITE);
                btn[i][j].setForeground(Color.WHITE);
                btn[i][j].setIcon(null);
        }
       }
       for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                if(board[i][j]!=null){
                    if(board[i][j].toString().contains("A11")){
                        btn[i][j].setIcon(loadImage("sunBlue.png"));
                    }
                    if(board[i][j].toString().contains("A21") || board[i][j].toString().contains("A22")){
                        btn[i][j].setIcon(loadImage("chevronBlue.png"));
                    }
                    if(board[i][j].toString().contains("A31") || board[i][j].toString().contains("A32")){
                        btn[i][j].setIcon(loadImage("triangleBlue.png"));
                    }
                    if(board[i][j].toString().contains("A41") || board[i][j].toString().contains("A42")){
                        btn[i][j].setIcon(loadImage("plusBlue.png"));
                    }
                    if(board[i][j].toString().contains("A5")){
                        btn[i][j].setIcon(loadImage("arrowBlue.png"));
                    }
                    
                    if(board[i][j].toString().contains("B11")){
                        btn[i][j].setIcon(loadImage("sunRed.png"));
                    }
                    if(board[i][j].toString().contains("B21") || board[i][j].toString().contains("B22")){
                        btn[i][j].setIcon(loadImage("chevronRed.png"));
                    }
                    if(board[i][j].toString().contains("B31") || board[i][j].toString().contains("B32")){
                        btn[i][j].setIcon(loadImage("triangleRed.png"));
                    }
                    if(board[i][j].toString().contains("B41") || board[i][j].toString().contains("B42")){
                        btn[i][j].setIcon(loadImage("plusRed.png"));
                    }
                    if(board[i][j].toString().contains("B5")){
                        btn[i][j].setIcon(loadImage("arrowRed.png"));
                    }
                   
            }
        }
       }
       
   }
   public ImageIcon loadImage(String path){ // load the image from the file - Nurfarah
    Image image = new ImageIcon(this.getClass().getResource(path)).getImage();
    Image scaledImage = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
    return new ImageIcon(scaledImage);
   }
   public void actionPerformed(ActionEvent e){ // detect movement and changes of pieces during the gameplay - Zam
       errorTxtField.setText("");
       setImage(board);
       for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                if(e.getSource()==btn[i][j]){
                       temp =btn[i][j].getText();
                }
        }
       }
       if(e.getActionCommand().equals("Exit")){
           System.exit(0);
       }
       if(tempTo!=null){
           tempTo = temp;
       }
       if(tempFrom==null){
           tempFrom = temp;
           tempTo="";
       }
       if(tempFrom!=null && tempTo!=""){
           String turnPlayer;
           Player playerTemp;
           Boolean flagBreak,canMove,checkNotEatMember;
           flagBreak = false;
           canMove = false;
           int checkSun = 0;
           int fromI = Integer.parseInt(String.valueOf(tempFrom.charAt(0)));
           int fromJ = Integer.parseInt(String.valueOf(tempFrom.charAt(1)));
           int toI = Integer.parseInt(String.valueOf(tempTo.charAt(0)));
           int toJ = Integer.parseInt(String.valueOf(tempTo.charAt(1)));
           if(counter%2==0){ 
                turnPlayer = player1.name;
                playerTemp = player1;
            }
            else{
                turnPlayer = player2.name;
                playerTemp = player2;
           }
           if(board[fromI][fromJ]!=null){
                    checkNotEatMember=true;
                    if(board[toI][toJ]!=null){
                        if(board[toI][toJ].toString().contains(turnPlayer)){
                            errorTxtField.setText("WRONG MOVE, CAN'T EAT OWN PIECE");
                            checkNotEatMember=false;
                        }
                    }
                    if(checkNotEatMember==true){
                        if(board[fromI][fromJ].toString().contains(turnPlayer)){
                        canMove = playerTemp.validateMove(board[fromI][fromJ].toString(),
                        fromI,fromJ,toI,toJ,board);
                        if(canMove == true){   
                                game.movePiece(fromI,fromJ,toI,toJ);
                                flagBreak = true;
                                playerTemp.numMoves+=1;  
                        }                        
                        if(canMove == false){
                              errorTxtField.setText("INVALID MOVE");  
                        }
                    }
                    }
                    
           } 
           else{
                    errorTxtField.setText("INVALID MOVE");
           }
           if(flagBreak==true){    
                    if(player1.numMoves%2==0 && player2.numMoves==player1.numMoves){
                        game.turnPlusTriangle(board,player2);
                        game.turnPlusTriangle(board,player1);
                    }
                    checkSun = game.checkMate(board);
                    counter++;
           }
           player1.updateArrowPos(board);
           player2.updateArrowPos(board);
           setImage(board);
           if(counter%2==0){ 
                gameTxtField.setText("TURN BLUE");
            }
            else{
                gameTxtField.setText("TURN RED");
            }
           if(checkSun==10){ // B win
                errorTxtField.setText("BLUE WIN!");
                gameTxtField.setText("BLUE WIN!");
            }
           if(checkSun==100){ // A win
                errorTxtField.setText("RED WIN!");
                gameTxtField.setText("RED WIN!");
            }
           tempTo=null;tempFrom=null;
        }
   }
   public static void main(String[] args) { // run the game - Wan
        GameBoardGUI run = new GameBoardGUI();
    }
   public class newGameButtonActionListener implements ActionListener {// listener class for new game menu item - Wan
        public void actionPerformed(ActionEvent evt) {
            gameTxtField.setText("New game...");
            board = game.getBoard();
            counter = 1;
            player1.numMoves=0;
            player2.numMoves=0;
            setImage(board);
        }
    }
   public class saveButtonActionListener implements ActionListener { // listener class for save as menu item - Wan
        public void actionPerformed(ActionEvent evt) {
            game.saveBoard();
        }
   }
   public class loadButtonActionListener implements ActionListener { // listener class for load game menu item - Wan
        public void actionPerformed(ActionEvent evt) {
            game.loadSavedBoard();
            setImage(board);
            setVisible(true);
        }
   }
   public class aboutMenuButtonActionListener implements ActionListener {//listener class for about us menu item - Wan
        public void actionPerformed(ActionEvent evt) {
            JFrame aboutFrame = new JFrame("About us");
            aboutFrame.setResizable(false);
            aboutFrame.setSize(500, 500);
            aboutFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            aboutFrame.setLocationRelativeTo(null);
            aboutFrame.setVisible(true);
            aboutFrame.setLayout(new BorderLayout());

            JPanel rulePanel = new JPanel();
            aboutFrame.add(rulePanel, BorderLayout.CENTER);
            rulePanel.setLayout(new BorderLayout());
            
            myColor = new Color(237, 237, 237);

            gameTxtArea = new JTextArea();
            gameTxtArea.setEditable(false);
            gameTxtArea.setBackground(myColor);
            gameTxtArea.setFont(new Font("Serif",Font.PLAIN,20));
            
            gameTxtArea.setText("Group Name:\nAgerso\n\nGroup Member:\nMohamad Zamfirdaus Bin Mohd Saberi - 1181101148\nHadya Ayeisha Binti Marzuki - 1181101256\nNur Wahafizah Ahmadi - 1181101272\nEliasaph Wan Uyo - 1181101266\nNurfarah Fa'iqah Binti Daud - 1181101238");
            

            rulePanel.add(gameTxtArea, BorderLayout.CENTER);

            scrollBar = new JScrollPane(gameTxtArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            aboutFrame.add(scrollBar);
        }
   }
   public class rulesButtonActionListener implements ActionListener { // listener class for game rules menu item- wan 
        public void actionPerformed(ActionEvent evt) {
            JFrame aboutFrame = new JFrame("Game rules");
            aboutFrame.setResizable(false);
            aboutFrame.setSize(500, 500);
            aboutFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            aboutFrame.setLocationRelativeTo(null);
            aboutFrame.setVisible(true);
            aboutFrame.setLayout(new BorderLayout());

            myColor = new Color(237, 237, 237);

            JPanel rulePanel = new JPanel();
            aboutFrame.add(rulePanel, BorderLayout.CENTER);
            rulePanel.setLayout(new BorderLayout());

            gameTxtArea = new JTextArea();
            gameTxtArea.setEditable(false);
            gameTxtArea.setBackground(myColor);
            gameTxtArea.setFont(new Font("Serif",Font.PLAIN,20));

            gameTxtArea.setText("Game rules:\n\nSun:\n->Able to move one step in any direction.\n\nChevron:\n->Able to move in an L shape (2 steps in one direction, 1\nstep perpendicular to it.\n-> Able to skip/jump over other pieces.\n\nTriangle:\n-> Able to move any number of steps diagonally\n-> Unable to skip other pieces\n\nPlus:\n-> Able to move any number of steps up and down, or left\nand right\n-> Unable to skip other pieces\n\nArrow:\n-> Able to move 1 or 2 steps forward only\n-> Unable to skip other pieces");

            rulePanel.add(gameTxtArea, BorderLayout.CENTER);

            scrollBar = new JScrollPane(gameTxtArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            aboutFrame.add(scrollBar);
        } 
   }   
}
