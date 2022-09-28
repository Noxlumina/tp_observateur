public class Main {
/*
*méthode permettant de lancer le programme
 */
    public static void main(String[] args) {
        Horloge horloge = new Horloge();
        Fenetre fen = new Fenetre(horloge);
        fen.setVisible(true);
        fen.setSize(300, 100);

    }
}
