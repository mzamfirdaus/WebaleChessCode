/**
* Arrow is a class that act as the Model class
*
* @author (Zamfirdaus, Hadya)
*/
public class Arrow{
    private static int val =5;
    private static boolean status = false;
    private String name;
    private int pieceNum;
    private int startPos; 
    
    public Arrow(int pieceNum){ // constructor
        this.pieceNum =pieceNum;
    }
    public void setStartingPos(int startPos){ // set the starting direction of the Arrow
        this.startPos = startPos;
    }
    public static boolean move(int fromI, int fromJ, int toI, int toJ, Object[][] a,String name, int dir){//validate move - zam,hadya
        status = false;        
        if(dir==1){
            if(fromJ!=toJ){
                return false;
            }
            if (toI==(fromI+2) || toI==fromI+1){
                status = true;
                if(toI==(fromI+2)){
                    if (a[fromI+1][toJ]!=null){
                        status = false;
                    }
                }
                
            }    
        }
        if(dir==2){
            if(fromJ!=toJ){
                return false;
            }
            if (toI==fromI-1 || toI==fromI-2){
                status = true;
                if(toI==(fromI+2)){
                    if (a[fromI-1][toJ]!=null){
                        status = false;
                    }
                }
            }    
        }      
        return status;
    }  
    public int validatePos(int posI){ // update the direction of the Arrow
        if(posI==0){
            startPos = 1;
        }
        else if(posI==7){
            startPos = 2;
        }
        else{
            startPos = startPos;
        }
        return startPos;
    }
    public void setStartPos(int startPos){ //set the direction of the Arrow during runtime
        this.startPos = startPos;
    }
    public int getStartPos(){ // get the direction position
        return startPos;
    }
    public String toString(){ // get name of the Arrow
        return name+String.valueOf(val)+String.valueOf(pieceNum);
    }
    public String name(){ // get name of player of the Arrow
        return name;
    }
    public void setName(String name){ // set the name of the player of the Arrow
        this.name = name;
    }
}
