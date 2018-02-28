import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by all on 2016-06-01.
 */
public class Server extends JFrame {
    public JPanel panel1;
    private JTextArea textAreaServer;
    private JButton startButton;
    private JLabel labelBJ;
    private JLabel labelK1;
    private JLabel labelK2;
    private JLabel labelK3;
    private JLabel labelK4;
    private JButton dobierzButton;


    private Stol stol;
    private ExecutorService executorService; //do graczy
    private ServerSocket server;
    private SockServer[] sockServ;
    private int licznik = 1; //liczba polaczen
    private String k1, k2;
    private ArrayList<Game> gracze;
    private Game karty;
    private int pozostalych;
    private boolean label3zajety=false;

    public Server() {
        JFrame frame = new JFrame("Server");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(520,700);
        frame.setVisible(true);
        labelBJ.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\blackjack2.png"));
        labelK1.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\back.jpg"));
        labelK2.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\back.jpg"));
        labelK3.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\back.jpg"));
        labelK4.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\back.jpg"));


        gracze = new ArrayList();
        sockServ = new SockServer[100]; //watkow serwera
        executorService = Executors.newFixedThreadPool(100); // pula watkow


        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startButton.setEnabled(false);
                stol = new Stol();


                wyswietlWiadomosc("\nKARTY:  \n");
                RozdajKarty();
            }
        });



    }

    public void ServerStart() {
        try
        {
            server = new ServerSocket(23555, 100); //port,


            while (true) {
                try {
                    sockServ[licznik] = new SockServer(licznik);
                    sockServ[licznik].startPolaczenie();//oczekiwanie na klienta
                    executorService.execute(sockServ[licznik]); //nowy obiekt,oczekiwanie
                } catch (EOFException eofException) {
                    wyswietlWiadomosc("\nPolaczenie zakonczone");
                }
                finally {
                    ++licznik;
                }

            }

        } catch (IOException ioException) {
        }


    }

    private void RozdajKarty() {
        try {
            pozostalych = licznik - 1;
            stol.los();
            k1 = stol.rozdaj();
            k2 = stol.rozdaj();
            wyswietlWiadomosc("\n\n" + k1 + " " + k2);
            ustawKarte(labelK1,k1);
            ustawKarte(labelK2,k2);
            for (int i = 1; i <= licznik; i++) {
                String a1, a2;
                a1 = stol.rozdaj();
                a2 = stol.rozdaj();
                Game g = new Game(a1, a2);
                gracze.add(g);

                sockServ[i].przesDane("Twoje karty:  " + a1 + " ");
                sockServ[i].przesDane(a2);



            }
        } catch (NullPointerException n) {
        }
    }

    private void wynik() {
        try {
            for (int i = 1; i <= licznik; i++) {
                sockServ[i].przesDane("\nRozdajacy posiada " + karty.getSuma());

                if( (karty.getSuma() <= 21) && (gracze.get(i-1).getSuma() <= 21 ) ){

                    if (karty.getSuma() > gracze.get(i-1).getSuma()) {
                        sockServ[i].przesDane("\n Przegrales!");
                        wyswietlWiadomosc("\nWygrales!");
                        wyswietlWiadomosc("\nPrzeciwnik posiadal "+gracze.get(i-1).getSuma());
                    }

                    if (karty.getSuma() < gracze.get(i-1).getSuma()) {
                        sockServ[i].przesDane("\n Wygrales!");
                        wyswietlWiadomosc("\nPrzegrales!");
                        wyswietlWiadomosc("\nPrzeciwnik posiadal "+gracze.get(i-1).getSuma());
                    }

                    if (karty.getSuma() == gracze.get(i-1).getSuma()) {
                        sockServ[i].przesDane("\n Remis!");
                        wyswietlWiadomosc("\nRemis!");
                    }

                }//kiedy gracz i rozdajacy <21

                if(karty.sprawdz()){

                    if(gracze.get(i-1).sprawdz()){
                        sockServ[i].przesDane("\n Remis!");
                    }
                    if(gracze.get(i-1).getSuma() <= 21){
                        sockServ[i].przesDane("\n Wygrales!");
                    }
                }

                if(gracze.get(i-1).sprawdz() && karty.getSuma() <= 21){
                    sockServ[i].przesDane("\n Przegrales!");
                }

            }
        } catch (NullPointerException n) {}
    }

    private void wyswietlWiadomosc(final String wiadomosc) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        textAreaServer.append(wiadomosc);
                    }
                }

        );

    }
    private void ustawKarte(JLabel label1,String k)
    {
        if(k=="2")
    {
        label1.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\2.jpg"));
    }
    else if(k=="3")
    {
        label1.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\3.jpg"));
    }
    else if(k=="4")
    {
        label1.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\4.jpg"));
    }
    else if(k=="5")
    {
        label1.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\5.jpg"));
    }
    else if(k=="6")
    {
        label1.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\6.jpg"));
    }
    else if(k=="7")
    {
        label1.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\7.jpg"));
    }
    else if(k=="8")
    {
        label1.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\8.jpg"));
    }
    else if(k=="9")
    {
        label1.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\9.jpg"));
    }
    else if(k=="10")
    {
        label1.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\10.jpg"));
    }
    else if(k=="J")
    {
        label1.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\J.jpg"));
    }
    else if(k=="Q")
    {
        label1.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\Q.jpg"));
    }
    else if(k=="K")
    {
        label1.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\K.jpg"));
    }
    else if(k=="As")
    {
        label1.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\A.jpg"));
    }

    }


    //Klasa oblugujaca watki

    class SockServer implements Runnable {
        private ObjectOutputStream output;
        private ObjectInputStream input;
        private Socket connection;
        private int myConID;
        //wysylanie, odbieranie i polaczenie z klientem

        public SockServer(int counterIn) {
            myConID = counterIn;
        }

        @Override
        public void run() {
            try {
                try {
                    getStreams();
                    polaczenie();
                } catch (EOFException e) {
                    wyswietlWiadomosc(myConID + " polaczenie zakonczone\n");
                } finally {
                    zakonczPolaczenie();
                }
            } catch (IOException io) {
            }
        }

        private void startPolaczenie() throws IOException {
            wyswietlWiadomosc("Oczekiwanie na polaczenie "+myConID+ "\n");
            connection = server.accept();
            wyswietlWiadomosc("Polaczono  " + myConID+". ");
            connection.getInetAddress().getHostName();
        }


        private void getStreams() throws IOException {
            // strumienie wyjscia i wejscia do klienta
            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();


            input = new ObjectInputStream(connection.getInputStream());

        }

        private void polaczenie() throws IOException
        {
            String wiad = "Polaczenie  udane\n";
            przesDane(wiad);

            do{
                try{
                    if(wiad.contains("Dobierz")){
                        obstaw();
                    }
                    if(wiad.contains("Pasuj")){
                        this.przesDane("Prosze czekac");
                        pozostalych--;
                        sprawdz();
                    }
                    wiad = ( String ) input.readObject();
                }catch(ClassNotFoundException classNotFoundException)
                {
                    wyswietlWiadomosc( "\nBlad obiektu" );
                }
            }while (!wiad.equals("CLIENT>>> TERMINATE"));

        }
        private void rozdajacy()
        {
            karty = new Game(k1,k2);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (karty.getSuma() < 16){
                while(karty.getSuma() < 16){
                    String card1 = stol.rozdaj();
                    karty.Dobranie(card1);
                    if(label3zajety==false) {
                        ustawKarte(labelK3,card1);
                        label3zajety=true;
                    }
                    else
                    {
                        ustawKarte(labelK4,card1);
                    }
                        wyswietlWiadomosc("\nObstawiono: "+ card1 +  "\n" + "Suma kart:" + karty.getSuma() + "\n");
                }
            }
            if(karty.sprawdz()){
                wyswietlWiadomosc("\nPrzekroczono!");
            }
            else{
                wyswietlWiadomosc("\nPosiadane " + karty.getSuma());
            }

            wynik();

        }

        private void obstaw()
        {
            String karta = stol.rozdaj();
            przesDane(karta);
            gracze.get(this.myConID -1).Dobranie(karta);
            //wyswietlWiadomosc("Twoja suma: "+ gracze.get(this.myConID -1).getSuma());
            if(gracze.get(this.myConID -1).sprawdz())
            {
                przesDane("Przekroczono!");
                if(pozostalych==0) {
                    rozdajacy();
                }
            }

        }
        private void sprawdz()
        {
            if(pozostalych==0)
            {
                rozdajacy();
            }
        }
        private void przesDane(String wiadomosc)
        {
            try // do klienta
            {
                output.writeObject(wiadomosc );
                output.flush();
            }
            catch ( IOException ioException )
            {
                textAreaServer.append( "\nBlad obiektu" );
            }

        }
        private void zakonczPolaczenie()
        {
            wyswietlWiadomosc( "\nZakonczenie polaczenia " + myConID + "\n" );

            try
            {
                output.close(); // close output stream
                input.close(); // close input stream
                connection.close(); // close socket
            } // end try
            catch ( IOException ioException )
            {} // end catch

        }




    }

    public static void main(String[] args) {
            Server a= new Server();
            a.ServerStart();

    }
}