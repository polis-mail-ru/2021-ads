import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class OlympiadResult {
  private OlympiadResult() {
    // Should not be instantiated
  }

  private static class Participant {
    private int identificationNumber_;
    private int result_;

    Participant(int identificationNumber, int result) {
      identificationNumber_ = identificationNumber;
      result_ = result;
    }

    public boolean isMoreThan(Participant other) {
      return result_ > other.result_ ||
          (result_ == other.result_ && identificationNumber_ < other.identificationNumber_);
    }
  }

  private static void solve(final FastScanner in, final PrintWriter out) {
    Participant[] participants = new Participant[in.nextInt()];
    readParticipants(participants, in);
    sortParticipants(participants);
    printParticipants(participants, out);
  }

  private static void readParticipants(Participant[] participants, final FastScanner in) {
    for (int i = 0; i < participants.length; i++) {
      participants[i] = new Participant(in.nextInt(), in.nextInt());
    }
  }

  private static void sortParticipants(Participant[] participants) {
    // Используем шейкерную сортировку
    int high = 0;
    int low = participants.length - 1;
    int indexOfExchanging = participants.length - 1;
    while (high < low)
    {
      for (int i = high; i < low; ++i)
      {
        if (participants[i + 1].isMoreThan(participants[i])) {
          Participant tmp = participants[i];
          participants[i] = participants[i + 1];
          participants[i + 1] = tmp;
          indexOfExchanging = i;
        }
      }
      low = indexOfExchanging;
      for (int i = low; i > high; --i)
      {
        if (participants[i].isMoreThan(participants[i - 1]))
        {
          Participant tmp = participants[i - 1];
          participants[i - 1] = participants[i];
          participants[i] = tmp;
          indexOfExchanging = i;
        }
      }
      high = indexOfExchanging;
    }
  }

  private static void printParticipants(Participant[] participants, final PrintWriter out) {
    for (Participant p: participants) {
      out.println(p.identificationNumber_ + " " + p.result_);
    }
  }

  private static class FastScanner {
    private final BufferedReader reader;
    private StringTokenizer tokenizer;

    FastScanner(final InputStream in) {
      reader = new BufferedReader(new InputStreamReader(in));
    }

    String next() {
      while (tokenizer == null || !tokenizer.hasMoreTokens()) {
        try {
          tokenizer = new StringTokenizer(reader.readLine());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      return tokenizer.nextToken();
    }

    int nextInt() {
      return Integer.parseInt(next());
    }
  }

  public static PrintWriter createPrintWriterForLocalTests() {
    return new PrintWriter(System.out, true);
  }

  public static void main(final String[] arg) {
    final FastScanner in = new FastScanner(System.in);
    try (PrintWriter out = new PrintWriter(System.out)) {
      solve(in, out);
    }
  }
}
