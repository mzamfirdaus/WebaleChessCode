/**
* Plus is a class that act as the Model class
*
* @author (Hadya)
*/
public class Plus {
    private static int val =4;
    private static boolean status = false;
    private String name;
    private int pieceNum;
    public Plus(int pieceNum){ // constructor
        this.pieceNum =pieceNum;
    }
    public static boolean move(int fromI, int fromJ, int toI, int toJ, Object[][] a){ // validate move - Hadya
        
        String direction = " ";
        if (toI<fromI && toJ==fromJ)
            direction = "utara";           
        if (toI>fromI && toJ==fromJ)
            direction = "selatan";           
        if (toI==fromI && toJ>fromJ)
            direction = "timur";           
        if (toI==fromI && toJ<fromJ)
            direction = "barat";
        
        //check if move vertical or not
        if((fromI!=toI) && (fromJ==toJ)) {
           status = true;
           toI = Math.abs(toI-fromI);
            switch(direction){
                case "utara" :
                        for(int i=1;i<toI; i++){
                            if (a[fromI - i][toJ]!=null){
                                status = false;
                            }
                        }
                        break;
                case "selatan" :
                        for(int i=1;i<toI; i++){
                            if (a[fromI + i][toJ]!=null){
                                status = false;
                            }
                        }
                        break;
           }
        }

        //check if going horizontal or not
        else if ((fromI==toI) && (fromJ!=toJ)) {
            status = true;
            toJ = Math.abs(toJ-fromJ);
            switch(direction){
                case "timur" :
                        for(int i=1;i<toJ; i++){
                            if (a[fromI][fromJ + i]!=null){
                                status = false;
                            }
                        }
                        break;
                case "barat" :
                        for(int i=1;i<toJ; i++){
                            if (a[fromI][fromJ-i]!=null){
                                status = false;
                            }
                        }
                        break;
           }
        }
        else 
            status = false;
        return status;
    } 
    public String toString(){ // get name of the Plus
        return name+String.valueOf(val)+String.valueOf(pieceNum);
    }
    public String name(){ // get name of player of the Plus
        return name;
    }
    public void setName(String name){ // set the name of the player of the Plus
        this.name = name;
    }
}
