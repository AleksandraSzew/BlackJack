import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;


public class Client extends JFrame {

    public JPanel panel1;
    private JButton dobierzButton;
    private JButton pasujButton;


    private JTextArea textAreaClient;
    public JPanel buttons;
    public JPanel Jpanel1;
    public JPanel Jpanel2;
    public JPanel Jpanel3;
    public JPanel Jpanel4;
    private JButton nowaGraButton;
    private JPanel PanelKart;
    public JLabel label2;
    public JLabel label3;
    public JLabel label4;
    public JLabel label1;
    private JLabel labelBJ;
    private JLabel labelWygrana;


    private ObjectOutputStream output; // do servera
    private ObjectInputStream input; // od servera
    private String wiadOdServ = ""; // wiadomość od servera
    private String chatServer; //host
    private Socket client;
    private int karta=0;
    Client(String host)
    {


        chatServer =host; //ustawienie serwera

        dobierzButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                przesDane( "Dobierz" );
            }
        });
        pasujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                przesDane("Pasuj");
            }
        });


        JFrame frame = new JFrame("Client");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(520,700);
        frame.setVisible(true);
        labelBJ.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\blackjack2.png"));
        label1.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\back.jpg"));
        label2.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\back.jpg"));
        label3.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\back.jpg"));
        label4.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\back.jpg"));
    }

    public void runClient()
    {

        try
        {
            polaczServer(); //Socket
            getStreams(); // input i output
            polaczenie();
        }
        catch ( EOFException eofException )
        {
            wiadomosc( "\nZakonczenie polaczenia" );
        }
        catch ( IOException ioException )
        {}
        finally
        {
            zakonczPolaczenie();
        }
    }

    public void polaczServer() throws IOException
    {
        wiadomosc("Oczekiwanie na polaczenie\n");
        client= new Socket(InetAddress.getByName(chatServer),23555);

    }
    public void getStreams() throws IOException
    {
        output = new ObjectOutputStream( client.getOutputStream() );
        output.flush();


        input = new ObjectInputStream( client.getInputStream() );


    }

    public void polaczenie() throws IOException
    {
        do
        {
            try // czytanie wiadomosci
            {
                wiadOdServ = ( String ) input.readObject();
                wiadomosc( "\n" + wiadOdServ ); // wyswietlenie wiadomosci
                if (wiadOdServ.contains("Niepowodzenie") || wiadOdServ.contains("Prosze czekac")){
                    buttons.setVisible(false);
                }
                else if(wiadOdServ.contains("Przekroczono"))
                {
                    dobierzButton.setEnabled(false);
                }
                else if(wiadOdServ.contains("2"))
                {
                    if(karta==0)
                    {
                        label1.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\2.jpg"));
                        karta++;
                    }
                        else if(karta==1)
                    {
                        label2.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\2.jpg"));
                        karta++;
                    }
                    else if(karta==2)
                    {
                        label3.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\2.jpg"));
                        karta++;
                    }
                    else if(karta==3)
                    {
                        label4.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\2.jpg"));
                        karta++;
                    }
                }
                else if(wiadOdServ.contains("3"))
                {
                    if(karta==0)
                    {
                        label1.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\3.jpg"));
                        karta++;
                    }
                    else if(karta==1)
                    {
                        label2.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\3.jpg"));
                        karta++;
                    }
                    else if(karta==2)
                    {
                        label3.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\3.jpg"));
                        karta++;
                    }
                    else if(karta==3)
                    {
                        label4.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\3.jpg"));
                        karta++;
                    }
                }
                else if(wiadOdServ.contains("4"))
                {
                    if(karta==0)
                    {
                        label1.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\4.jpg"));
                        karta++;
                    }
                    else if(karta==1)
                    {
                        label2.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\4.jpg"));
                        karta++;
                    }
                    else if(karta==2)
                    {
                        label3.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\4.jpg"));
                        karta++;
                    }
                    else if(karta==3)
                    {
                        label4.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\4.jpg"));
                        karta++;
                    }
                }
                else if(wiadOdServ.contains("5"))
                {
                    if(karta==0)
                    {
                        label1.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\5.jpg"));
                        karta++;
                    }
                    else if(karta==1)
                    {
                        label2.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\5.jpg"));
                        karta++;
                    }
                    else if(karta==2)
                    {
                        label3.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\5.jpg"));
                        karta++;
                    }
                    else if(karta==3)
                    {
                        label4.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\5.jpg"));
                        karta++;
                    }
                }
                else if(wiadOdServ.contains("6"))
                {
                    if(karta==0)
                    {
                        label1.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\6.jpg"));
                        karta++;
                    }
                    else if(karta==1)
                    {
                        label2.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\6.jpg"));
                        karta++;
                    }
                    else if(karta==2)
                    {
                        label3.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\6.jpg"));
                        karta++;
                    }
                    else if(karta==3)
                    {
                        label4.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\6.jpg"));
                        karta++;
                    }
                }
                else if(wiadOdServ.contains("7"))
                {
                    if(karta==0)
                    {
                        label1.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\7.jpg"));
                        karta++;
                    }
                    else if(karta==1)
                    {
                        label2.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\7.jpg"));
                        karta++;
                    }
                    else if(karta==2)
                    {
                        label3.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\7.jpg"));
                        karta++;
                    }
                    else if(karta==3)
                    {
                        label4.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\7.jpg"));
                        karta++;
                    }
                }
                else if(wiadOdServ.contains("8"))
                {
                    if(karta==0)
                    {
                        label1.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\8.jpg"));
                        karta++;
                    }
                    else if(karta==1)
                    {
                        label2.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\8.jpg"));
                        karta++;
                    }
                    else if(karta==2)
                    {
                        label3.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\8.jpg"));
                        karta++;
                    }
                    else if(karta==3)
                    {
                        label4.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\8.jpg"));
                        karta++;
                    }
                }
                else if(wiadOdServ.contains("9"))
                {
                    if(karta==0)
                    {
                        label1.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\9.jpg"));
                        karta++;
                    }
                    else if(karta==1)
                    {
                        label2.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\9.jpg"));
                        karta++;
                    }
                    else if(karta==2)
                    {
                        label3.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\9.jpg"));
                        karta++;
                    }
                    else if(karta==3)
                    {
                        label4.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\9.jpg"));
                        karta++;
                    }
                }
                else if(wiadOdServ.contains("10"))
                {
                    if(karta==0)
                    {
                        label1.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\10.jpg"));
                        karta++;
                    }
                    else if(karta==1)
                    {
                        label2.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\10.jpg"));
                        karta++;
                    }
                    else if(karta==2)
                    {
                        label3.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\10.jpg"));
                        karta++;
                    }
                    else if(karta==3)
                    {
                        label4.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\10.jpg"));
                        karta++;
                    }
                }
                else if(wiadOdServ.contains("J"))
                {
                    if(karta==0)
                    {
                        label1.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\J.jpg"));
                        karta++;
                    }
                    else if(karta==1)
                    {
                        label2.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\J.jpg"));
                        karta++;
                    }
                    else if(karta==2)
                    {
                        label3.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\J.jpg"));
                        karta++;
                    }
                    else if(karta==3)
                    {
                        label4.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\J.jpg"));
                        karta++;
                    }
                }
                else if(wiadOdServ.contains("Q"))
                {
                    if(karta==0)
                    {
                        label1.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\Q.jpg"));
                        karta++;
                    }
                    else if(karta==1)
                    {
                        label2.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\Q.jpg"));
                        karta++;
                    }
                    else if(karta==2)
                    {
                        label3.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\Q.jpg"));
                        karta++;
                    }
                    else if(karta==3)
                    {
                        label4.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\Q.jpg"));
                        karta++;
                    }
                }
                else if(wiadOdServ.contains("K"))
                {if(karta==0)
                {
                    label1.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\K.jpg"));
                    karta++;
                }
                else if(karta==1)
                {
                    label2.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\K.jpg"));
                    karta++;
                }
                else if(karta==2)
                {
                    label3.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\K.jpg"));
                    karta++;
                }
                else if(karta==3)
                {
                    label4.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\K.jpg"));
                    karta++;
                }
                }
                else if(wiadOdServ.contains("As"))
                {
                    if(karta==0)
                    {
                        label1.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\A.jpg"));
                        karta++;
                    }
                    else if(karta==1)
                    {
                        label2.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\A.jpg"));
                        karta++;
                    }
                    else if(karta==2)
                    {
                        label3.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\A.jpg"));
                        karta++;
                    }
                    else if(karta==3)
                    {
                        label4.setIcon(new ImageIcon("C:\\Users\\all\\IdeaProjects\\BlackJackFinal\\res\\A.jpg"));
                        karta++;
                    }
                }

            }
            catch ( ClassNotFoundException classNotFoundException )
            {
                wiadomosc( "\nNieznany typ obiektu" );
            }

        } while ( !wiadOdServ.equals( "SERVER>>> TERMINATE" ) );
    }

    public void wiadomosc(final String massage)
    {
        SwingUtilities.invokeLater(
                new Runnable()
                {
                    public void run()
                    {
                        textAreaClient.append( massage );
                    }
                }
        );
    }


    public void zakonczPolaczenie()
    {
        wiadomosc( "\nZakonczenia polaczenia" );
        try
        {
            output.close();
            input.close();
            client.close();
        }
        catch ( IOException ioException )
        {}

    }


    private void przesDane( String message )
    {
        try
        {
            output.writeObject(  message );
            output.flush();

        }
        catch ( IOException ioException )
        {
            textAreaClient.append( "\nBlad zapisu obiektu" );
        }
    }

    public static void main( String[] args )
    {
        Client application; // declare client application

        // if no command line args
        if ( args.length == 0 )
            application = new Client( "127.0.0.1" ); // connect to localhost
        else
            application = new Client( args[ 0 ] ); // use args to connect

        application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        application.runClient(); // run client application
    }
}
