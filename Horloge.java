

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Horloge extends Thread implements Observable {
    //Objet calendrier pour r�cup�rer l'heure courante.
    private Calendar cal;
    //list d'observateur on aurait pu remplacer par une valeur unique
    // car dans le cas pr�sent il n'y a qu'un seul observateur
    private List<Observateur> observers = new ArrayList<>();

    private String hour = "";
//fonction permettant de r�cup�rer l'heure
    public String getHour() {
        return hour;
    }

    public Horloge() {
        //initialisation de l'horloge los de son initialisation
        Thread t = new Thread(this);
        t.start();
    }

    public void run() {
        while (true) {

            //On r�cup�re l'instance d'un calendrier � chaque tour
            //celui-ci va nous permettre de r�cup�rer l'heure actuelle
            this.cal = Calendar.getInstance();
            this.hour =    /* Les heures */
                    this.cal.get(Calendar.HOUR_OF_DAY) + " : "
                            +
                            (        /* Les minutes */
                                    this.cal.get(Calendar.MINUTE) < 10
                                            ? "0" + this.cal.get(Calendar.MINUTE)
                                            : this.cal.get(Calendar.MINUTE)
                            )
                            + " : "
                            +
                            (        /* Les secondes */
                                    (this.cal.get(Calendar.SECOND) < 10)
                                            ? "0" + this.cal.get(Calendar.SECOND)
                                            : this.cal.get(Calendar.SECOND)
                            );
            try {
                Thread.sleep(1000);
                //lancement de la m�thode � chaque changemetn de hour en seconde
                updateObservateur();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
/*
*permet d'attacher l'observateur � l'observable lors de l'initialisation
 */
    @Override
    public void addObservateur(Observateur obs) {
        this.observers.add(obs);
        System.out.println("connection");

    }
/*
*permet mettre � jour l'observateur � chaque changement de l'observable
 */
    @Override
    public void updateObservateur() {
           for (Observateur observer : observers
            ) {
                observer.update(this.hour.toString());
            }
        System.out.println(this.hour);
    }

/*
*permet de d�tacher l'observateur
 */
    @Override
    public void delObservateur() {
        this.observers.clear();
        System.out.println("d�connection");
    }
}