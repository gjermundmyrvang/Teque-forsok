import java.util.Scanner;

public class Teque {

    private class Node {
        private Node neste, forrige;
        private int E;

        Node(int x) {
            E = x;
        }
    }

    Node start, slutt, midten;
    int teller = 0;

    public void push_front(int x)  {
        Node ny = new Node(x);
        if (teller == 0) {
            start = ny;
            slutt = ny;
            midten = ny;
            teller++;
            return;
        }

        if (teller % 2 == 0) {
            midten = midten.forrige;
        }

        ny.neste = start;
        start.forrige = ny;
        start = ny;
        teller++;


    }

    public void push_back(int x)  {
        Node ny = new Node(x);
        if (teller == 0) {
            start = ny;
            slutt = ny;
            midten = ny;
            teller++;
            return;
        }
        if (teller == 1) {
            slutt.neste = ny;
            ny.forrige = slutt;
            slutt = ny;
            midten = ny;
            teller++;
            return;
        }

        if (teller % 2 == 0) {
            midten = midten.neste;
        }

        slutt.neste = ny;
        ny.forrige = slutt;
        slutt = ny;
        teller++;
    }

    public void push_middle(int x)  {
        Node ny = new Node(x);

        if (teller == 0) {
            start = ny;
            slutt = ny;
            midten = ny;
            teller++;
            return;
        }
        if (teller == 1) {
            slutt.neste = ny;
            ny.forrige = slutt;
            slutt = ny;
            midten = ny;
            teller++;
            return;
        }
        if (teller == 2) {
            ny.neste = slutt;
            slutt.forrige = ny;
            ny.forrige = start;
            start.neste = ny;
            midten = ny;
            teller++;
            return;
        }

        ny.neste = midten.neste;
        midten.neste.forrige = ny;
        midten.neste = ny;
        ny.forrige = midten;
        midten = ny;
        teller++;

        if (teller % 3 == 0) {
            midten = midten.forrige;
        }



        /*
        if (midtPos == 1 && start != slutt) {
            ny.neste = slutt;
            slutt.forrige = ny;
            ny.forrige = start;
            start.neste = ny;
            teller++;
            return;
        }

        if (midtPos == 1) {
            ny.forrige = start;
            start.neste = ny;
            slutt = ny;
            teller++;
            return;
        }

        Node peker = start;
        for (int i = 0; i < midtPos; i++) {
            peker = peker.neste;
        }
        ny.neste = peker;
        ny.forrige = peker.forrige;
        peker.forrige.neste = ny;
        peker.forrige = ny;
        teller++;
        */

    }

    public void get(int x) {
        if (x < 0 || x >= teller) {
            throw new IllegalArgumentException();
        }

        if (x < teller/ 2) {
            Node peker = start;
            for (int i = 0; i < x; i++) {
                peker = peker.neste;
            }
            System.out.println(peker.E);
            return;
        }
        Node peker = slutt;
        for (int i = teller-1; i > x; i--) {
            peker = peker.forrige;
        }
        System.out.println(peker.E);

    }

    @Override
    public String toString() {
        String streng = "";
        Node peker = start;
        for (int i = 0; i < teller; i++) {
            streng += peker.E + ", ";
            peker = peker.neste;
        }
        return streng;

    }

    public static void main(String[] args) {
        Teque teque = new Teque();

        Scanner minSc = new Scanner(System.in);

        int N = minSc.nextInt();
        int runder = 0;
        minSc.nextLine();
        while (runder < N) {

            String kommando = minSc.nextLine();
            String[] biter = kommando.split(" ");

            int tall = Integer.parseInt(biter[1]);

            if (biter[0].equalsIgnoreCase("push_front")) {
                teque.push_front(tall);
            } else if (biter[0].equalsIgnoreCase("push_back")) {
                teque.push_back(tall);
            } else if (biter[0].equalsIgnoreCase("push_middle")) {
                teque.push_middle(tall);
            } else {
                teque.get(tall);
            }

            runder++;

        }

    }



}
