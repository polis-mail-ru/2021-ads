import java.util.Comparator;
import java.util.Scanner;

public final class Week2Task1 {
    private Week2Task1() {
        // Should not be instantiated
    }

    public static void main(final String[] arg) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        Participant[] ps = new Participant[n];
        for (int i = 0; i < n; i++)
        {
            String[] pInfo = in.nextLine().split(" ");
            Participant p = new Participant(Integer.parseInt(pInfo[0]), Integer.parseInt(pInfo[1]));
            ps[i] = p;
        }
        Comparator<Participant> comparePs = new ParticipantScoreComparator();
        ps = Sort(ps, comparePs);
        for (int i = 0; i < n; i++)
        {
            System.out.println(ps[i].getId() + " " + ps[i].getScore());
        }
    }

    static class Participant {
        private final int ID;
        private final int SCORE;

        public Participant(int id, int score) {
            this.ID = id;
            this.SCORE = score;
        }

        public int getId() {
            return ID;
        }

        public int getScore() {
            return SCORE;
        }
    }

    static class ParticipantScoreComparator implements Comparator<Participant> {
        public int compare(Participant a, Participant b) {
            if (a.getScore() > b.getScore())
            {
                return -1;
            }
            else if (a.getScore() < b.getScore())
            {
                return 1;
            }
            else if (a.getId() > b.getId())
            {
                return 1;
            }
            else if (a.getId() < b.getId())
            {
                return -1;
            }
            return 0;
        }
    }

    static Participant[] Sort(Participant[] ps, Comparator<Participant> comparePs) {
        int n = ps.length;
        Participant[] psBuf = ps;
        for (int i = 1; i < n; i++)
        {
            Participant buf = psBuf[i];
            int j = i - 1;
            while (j >= 0 && comparePs.compare(psBuf[j], buf) > 0) {
                psBuf[j + 1]= psBuf[j];
                j--;
            }
            psBuf[j + 1] = buf;
        }
        return psBuf;
    }
}