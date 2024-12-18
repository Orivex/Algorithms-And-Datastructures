import java.util.*;
import java.util.PriorityQueue;

public class Dijkstra {

    public static void main(String[] args) {
        Knoten knotenA = new Knoten("A");
        Knoten knotenB = new Knoten("B");
        Knoten knotenC = new Knoten("C");
        Knoten knotenD = new Knoten("D");
        Knoten knotenE = new Knoten("E");
        Knoten knotenF = new Knoten("F");
        Knoten knotenG = new Knoten("G");

        knotenA.verbindeZu(knotenB, 20);
        knotenA.verbindeZu(knotenC, 30);

        knotenB.verbindeZu(knotenD, 25);

        knotenC.verbindeZu(knotenB, 10);
        knotenC.verbindeZu(knotenD, 5);

        knotenD.verbindeZu(knotenE, 10);
        knotenD.verbindeZu(knotenF, 25);

        knotenE.verbindeZu(knotenB, 15);
        knotenE.verbindeZu(knotenF, 10);

        knotenF.verbindeZu(knotenG, 5);

        dijkstra(knotenA, knotenG);
    }

    static void dijkstra(Knoten start, Knoten ziel) {
        Queue<Knoten> pq = new PriorityQueue<>();
        Set<Knoten> besuchteKnoten = new HashSet<>();

        start.kosten = 0;
        pq.add(start);

        while (!pq.isEmpty()) {
            Knoten aktuellerKnoten = pq.poll();

            for(Kante kante : aktuellerKnoten.kanten) { //Iteriere durch alle Nachbarn

                if(besuchteKnoten.contains(kante.verbundenerKnoten))
                    continue;

                if( (aktuellerKnoten.kosten + kante.gewicht) < kante.verbundenerKnoten.kosten ) {
                    kante.verbundenerKnoten.kosten = aktuellerKnoten.kosten + kante.gewicht;
                    kante.verbundenerKnoten.vorgaenger = aktuellerKnoten;
                    pq.add(kante.verbundenerKnoten);
                }
            }

            besuchteKnoten.add(aktuellerKnoten);
        }

        Knoten aktuellerKnoten = ziel;
        StringBuilder weg = new StringBuilder();

        while(aktuellerKnoten.vorgaenger != null) {
            weg.append(aktuellerKnoten.wert);
            aktuellerKnoten = aktuellerKnoten.vorgaenger;
        }

        if(aktuellerKnoten != start)
            System.out.println("Es gibt keinen Weg zum Knoten " + ziel.wert);
        else {
            weg.append(start.wert);
            System.out.println("Kürzester Weg: " + weg.reverse() + " mit den insgesamten Kosten von " + ziel.kosten);
        }

    }

}

class Knoten implements Comparable<Knoten> {
    String wert;
    Knoten vorgaenger = null;
    int kosten = Integer.MAX_VALUE;
    List<Kante> kanten = new ArrayList<>();

    public Knoten(String wert) {
        this.wert = wert;
    }

    void verbindeZu(Knoten knoten, int gewicht) {
        kanten.add(new Kante(knoten, gewicht));
    }

    @Override
    public int compareTo(Knoten other) {
        return Integer.compare(this.kosten, other.kosten);
    }

}

class Kante {
    int gewicht;
    Knoten verbundenerKnoten;

    public Kante(Knoten knoten, int gewicht) {
        this.verbundenerKnoten = knoten;
        this.gewicht = gewicht;
    }
}