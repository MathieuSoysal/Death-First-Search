package io.github.mathieusoysal;

import java.util.Scanner;

public class Player {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int nbNoeuds = in.nextInt();
        int nbLiens = in.nextInt();
        int nbPasserelle = in.nextInt();

        GameManager gameManager = new GameManager(nbNoeuds);
        for (int i = 0; i < nbLiens; i++) {
            int N1 = in.nextInt();
            int N2 = in.nextInt();
            gameManager.addLink(N1, N2);
        }
        for (int i = 0; i < nbPasserelle; i++) {
            int EI = in.nextInt();
            gameManager.setAsPaserelle(EI);
        }
        while (true) {
            int SI = in.nextInt();
            Link link = gameManager.getNearestClosableLink(SI);
            link.close();
            System.out.println(link);
        }
    }

}
