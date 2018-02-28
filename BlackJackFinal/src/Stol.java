import java.util.Random;

public class Stol {
    private Cards[] stol;
    private int zlicz;
    private static final int LiczbaKart = 13;
    private static final Random rand = new Random();

    public Stol() {
        String[] kart = {"As", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        stol = new Cards[LiczbaKart];
        zlicz = 0;

        for (int i = 0; i < stol.length; i++) {
            stol[i] = new Cards(kart[i % 13]);
        }
    }
    public void los()
    {
        zlicz=0;

        for(int i=0; i<stol.length; i++){
            int random = rand.nextInt(LiczbaKart);
            Cards t = stol[i];
            stol[i] = stol[random];
            stol[random]=t;
        }
    }

    public String rozdaj(){

        if(zlicz<stol.length){
            return stol[zlicz++].toString();
        }
        else{
            return null;
        }
    }






}
