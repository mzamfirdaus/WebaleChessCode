/**
 * Triangle is a class that act as the Model class
 * 
 * @author (NurWahafizah)
 */
public class Chevron{
    private final int val = 2;
    private String name;
    private int pieceNum;
    
    public Chevron(int pieceNum){ // constructor
        this.pieceNum =pieceNum;
    }
    public static boolean move(int fromI, int fromJ, int toI, int toJ){ // validate move
        boolean cflag = false;
        int x = fromI;
        int y = fromJ;
        int x2 = toI;
        int y2 = toJ;
        
            if (x2 == x+2 && y2 == y+1)
                cflag = true;
            else if (x2 == x+2 && y2 == y-1)
                cflag = true;
            else if (x2 == x+1 && y2 == y+2)
                cflag = true;
            else if (x2 == x-1 && y2 == y+2)
                cflag = true;
            else if (x2 == x-2 && y2 == y+1)
                cflag = true;
            else if (x2 == x-2 && y2 == y-1)
                cflag = true;
            else if (x2 == x+1 && y2 == y-2)
                cflag = true;
            else if (x2 == x-1 && y2 == y-2)
                cflag = true;
            else{
                cflag = false;
            }
        
        return cflag;
    }
    public String toString(){ // get name of the Chevron
        return name+String.valueOf(val)+String.valueOf(pieceNum);
    }
    public String getName(){ // get name of player of the Chevron
        return name;
    }
    public void setName(String name){ // set name of player of the Chevron
        this.name = name;
    }
}
