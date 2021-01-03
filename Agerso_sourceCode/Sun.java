/**
 * Sun is a class that act as the Model class
 * 
 * @author (Zamfirdaus, Wan Eliasaph)
 */
public class Sun{ 
    private final int val =1;
    private String name;
    private int pieceNum;
    private static boolean sflag = false;
    
    public Sun(int pieceNum){ // constructor
        this.pieceNum =pieceNum;
    }
    public static boolean move(int fromI, int fromJ, int toI, int toJ){  // validate move - Zam,Wan
        int x = fromI;
        int y = fromJ;
        int xMinus = fromI-1;
        int yMinus =  fromJ-1;
        int xPlus  = fromI+1;
        int yPlus  =  fromJ+1;
        sflag = false;
         if((toI==xMinus && toJ==yMinus) || (toI==xMinus && toJ==y) || (toI==xMinus && toJ==yPlus)
        || (toI==x && toJ==yMinus) || (toI==x && toJ==yPlus) || (toI==xPlus && toJ==yMinus) || 
        (toI==xPlus && toJ==y) || (toI==xPlus && toJ==yPlus)){
            sflag = true;
        }
        return sflag;
    }
        
    public String toString(){ // get the name of the Sun
        return name+String.valueOf(val)+String.valueOf(pieceNum);
    }
    public String getName(){ // get name of player of the Sun
        return name;
    }
    public void setName(String name){ // set name of player of the Sun
        this.name = name;
    }
    
    
    
}
