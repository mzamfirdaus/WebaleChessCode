/**
* Triangle is a class that act as the Model class
*
* @author (Hadya)
*/
public class Triangle {
    private static int val =3;
    private static boolean Tflag = false;
    private static boolean Dflag = false;
    private String name;
    private int pieceNum;
    
    public Triangle(int pieceNum){ // constructor
        this.pieceNum =pieceNum;
    }
    public static boolean move(int fromI, int fromJ, int toI, int toJ, Object[][] a){ //validate move 

        toI = Math.abs(toI-fromI);
        toJ = Math.abs(toJ-fromJ);       
        String direction = " ";
        if (toI<fromI && toJ>fromJ)
            direction = "atas_kanan_dan_kiri";           
        if (toI>fromI && toJ<fromJ)
            direction = "bawah_kiri";
        if(toI>fromI && toJ>fromJ)
            direction = "bawah_kanan";      
        if (toI == toJ){
            Tflag = true;
            
            switch(direction){
            case "atas_kanan_dan_kiri":
                  for (int i = 1; i < toI; i++){
                   for (int y = 0; y < toJ; y++){
                    if (a[fromI - i][fromJ + y]!=null){
                        Tflag = false;
                    }
                  }
                }
                break;
            case "bawah_kiri":
                  for (int i = 1; i < toI; i++){
                   for (int y = 0; y < toJ; y++){
                    if (a[fromI + i][fromJ - y]!=null){
                        Tflag = false;
                    }
                   }
                 }
                break;
            case "bawah_kanan":
                  for (int i = 1; i < toI; i++){
                   for (int y = 0; y < toJ; y++){
                    if (a[fromI + i][fromJ + y]!=null){
                        Tflag = false;
                    }
                   }
                  }
                break;                
            }
        }
        else{
            Tflag = false;
        }
        return Tflag;
    }
    public String toString(){ // get name of the Triangle
        return name+String.valueOf(val)+String.valueOf(pieceNum);
    }
    public String getName(){ // get name of player of the Triangle
        return name;
    }
    public void setName(String name){ // set name of player of the Triangle
        this.name = name;
    }
}
