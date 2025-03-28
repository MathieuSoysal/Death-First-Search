package io.github.mathieusoysal;

import java.util.Scanner;

import io.github.mathieusoysal.entities.Link;
import io.github.mathieusoysal.strategies.DFS;
import io.github.mathieusoysal.strategies.SearchAlgorithm;

public class Player {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        try {
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

            SearchAlgorithm strategy = new DFS();
            while (true) {
                int SI = in.nextInt();
                Link link = gameManager.getNearestClosableLink(SI, strategy);
                link.close();
                System.out.println(link);
            }
        } catch (Exception e) {
            System.err.println("Error reading input: " + e.getMessage());
        } finally {
            in.close();
        }
    }

}
