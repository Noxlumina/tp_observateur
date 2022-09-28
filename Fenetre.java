import javax.swing.*;
import java.awt.*;


public class Fenetre extends JFrame implements Observateur {

    private JLabel label = new JLabel();
    private JPanel context = new JPanel();
    private JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JButton fermer = new JButton("fermer");
    private JButton ajouter = new JButton("ajouter");




    private Horloge horloge;

    public Fenetre(Horloge horloge) {
        this.context.setLayout(new BoxLayout(context, BoxLayout.Y_AXIS));
        this.context.add(label);
        this.context.add(buttons);
        this.setTitle("Horloge");
        /* On initialise notre JFrame  */
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        /* On initialise l'horloge  */
        this.horloge = horloge;
        this.horloge.addObservateur(this);
        /* On initialise notre JLabel  */
        Font police = new Font("DS-digital", Font.TYPE1_FONT, 30);
        this.label.setFont(police);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        /* On ajoute le JLabel à notre JFrame */
        this.getContentPane().add(this.label, BorderLayout.CENTER);
        this.fermer.addActionListener(e->this.horloge.delObservateur());
        this.buttons.add(fermer);
        this.ajouter.addActionListener(e->this.horloge.delObservateur());
        this.buttons.add(ajouter);



    }

    /*
    *permet de mettre à jour l'heure avec la nouvelle valeur donnée pour String hour
     */
    @Override
    public void update(String hour) {
        System.out.println("L'heure est : " + hour);
        this.label.setText(hour);
    }
}