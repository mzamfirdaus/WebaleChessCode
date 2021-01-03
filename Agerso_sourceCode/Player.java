
/**
 * Player is a class that act as the Model class
 *
 * @author (Zamfirdaus)
 */
public class Player
{
    public String name;
    public int startingPos=0;
    public int numMoves;
    public boolean canMove;
    public int temp = 0;
    
    public Sun sun = new Sun(1); 
    public Chevron chevron1 = new Chevron(1);
    public Chevron chevron2 = new Chevron(2);
    public Triangle triangle1 = new Triangle(1);
    public Triangle triangle2 = new Triangle(2);
    public Plus plus1 = new Plus(1);
    public Plus plus2 = new Plus(2);
    public Arrow arrow1 = new Arrow(1);
    public Arrow arrow2 = new Arrow(2);
    public Arrow arrow3 = new Arrow(3);
    public Arrow arrow4 = new Arrow(4);

    Player(String name){ // constructor 
        numMoves = 0;
        this.name = name;
        sun.setName(name);
        chevron1.setName(name);
        chevron2.setName(name);
        triangle1.setName(name);
        triangle2.setName(name);
        plus1.setName(name);
        plus2.setName(name);
        arrow1.setName(name);
        arrow2.setName(name);
        arrow3.setName(name);
        arrow4.setName(name);
    }
    public boolean validateMove(String a, int fromI, int fromJ, int toI, int toJ, Object[][] board){ // validate move
        canMove=true;
        if(a.contains("21")||a.contains("22")){
            canMove = Chevron.move(fromI,fromJ,toI,toJ);
        }
        if(a.contains("11")){
            canMove = Sun.move(fromI,fromJ,toI,toJ);
        }
        if(a.contains("31")||a.contains("32")){
            canMove = Triangle.move(fromI,fromJ,toI,toJ,board);
        }
        if(a.contains("41")||a.contains("42")){
            canMove = Plus.move(fromI,fromJ,toI,toJ,board);
        }
        if(a.contains("51")){
            temp = arrow1.getStartPos();
            canMove = Arrow.move(fromI,fromJ,toI,toJ,board,name,temp);
        }
        if(a.contains("52")){
            temp = arrow2.getStartPos();
            canMove = Arrow.move(fromI,fromJ,toI,toJ,board,name,temp);
        }
        if(a.contains("53")){
            temp = arrow3.getStartPos();
            canMove = Arrow.move(fromI,fromJ,toI,toJ,board,name,temp);
        }
        if(a.contains("54")){
            temp = arrow4.getStartPos();
            canMove = Arrow.move(fromI,fromJ,toI,toJ,board,name,temp);
        }
        return canMove;
    }
    public void determineStartingPos(Object[][] board){ //obtain the starting position of the player
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                if(board[i][j]!=null){
                    if(board[i][j].toString().contains(name)){
                        if(i<3){
                            startingPos = 1;
                        }
                        if(i>4){
                            startingPos = 2;
                        }
                    }
                }
            }
        }
        arrow1.setStartingPos(startingPos);//set the starting position of the arrow
        arrow2.setStartingPos(startingPos);
        arrow3.setStartingPos(startingPos);
        arrow4.setStartingPos(startingPos);       
    }
    public void updateArrowPos(Object[][] board){ //update direction of arrow during runtime
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                if(board[i][j]!=null){
                    if(board[i][j].toString().contains(name)){
                        if(board[i][j].toString().contains("51")){
                            temp = arrow1.validatePos(i);
                            arrow1.setStartPos(temp);
                        }
                        if(board[i][j].toString().contains("52")){
                            temp= arrow2.validatePos(i);
                            arrow2.setStartPos(temp);
                        }
                        if(board[i][j].toString().contains("53")){
                            temp=arrow3.validatePos(i);
                            arrow3.setStartPos(temp);
                        }
                        if(board[i][j].toString().contains("54")){
                            temp=arrow4.validatePos(i);
                            arrow4.setStartPos(temp);
                        }
                    }
                }
            }
        }
    }
}
