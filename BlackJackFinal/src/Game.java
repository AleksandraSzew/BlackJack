
import javax.swing.*;
import java.security.SecureRandom;
import java.util.ArrayList;

public class Game extends JFrame{

    private boolean przegrana = false;
    private int suma=0;

    private ArrayList<String> karty;
    private ArrayList<String> as;

    public Game(String a, String b)
    {
        karty = new ArrayList();
        as = new ArrayList();

        if (a == "As") {
            as.add(a);
        }
        else{
            karty.add(a);
        }

        if (b == "As") {
            as.add(b);
        }
        else {
            karty.add(b);
        }

        ustawSume();
    }

        public int getSuma() {
        return suma;
    }

    public void setSuma(int suma) {
        this.suma = suma;
    }
    public void Dobranie(String c)
    {
        if(c== "As")
        {
                as.add("As");

        }
        else{
            karty.add(c);
        }

        if(as.size() != 0){
            ustawSume();
        }

        else if (c == "J" || c =="Q" || c =="K"){

            suma += 10;
        }

        else {
            suma += Integer.parseInt(c);
        }


        sprawdz();

    }

    private void ustawSume() {

        suma = 0;
        for(String c : karty){
            if (c == "J" || c =="Q" || c =="K"){
                suma += 10;
            }

            else{
                suma += Integer.parseInt(c);
            }

        }

        for(String a : as){
            if (suma <= 10){
                suma += 11;
            }
            else {
                suma += 1;
            }

        }
    }

    public boolean sprawdz(){
        if(suma > 21){
            przegrana = true;
        }

        else {
            przegrana = false;
        }

        return przegrana;
    }
}
