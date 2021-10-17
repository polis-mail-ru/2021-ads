import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class TaskC {
    private static void solve(final FastScanner in, final PrintWriter out) {
        MaxHeap lHeap = new MaxHeap();
        MinHeap rHeap = new MinHeap();
        String line = "";
        while ((line = in.nextLine()) != null) {
            int el = Integer.parseInt(line);
            if (lHeap.getSize() <= rHeap.getSize()) {
                if (rHeap.getSize() != 0 && el <= rHeap.seeRoot()) {
                    lHeap.insert(el);
                } else {
                    if(rHeap.getSize() != 0) {
                        lHeap.insert(rHeap.extract());
                    }
                    rHeap.insert(el);
                }
            } else {
                if (el >= lHeap.seeRoot()) {
                    rHeap.insert(el);
                } else {
                    rHeap.insert(lHeap.extract());
                    lHeap.insert(el);
                }
            }

            if ((lHeap.getSize() + rHeap.getSize()) % 2 == 0) {
                System.out.println((lHeap.seeRoot() + rHeap.seeRoot()) / 2);
            }
            else if(lHeap.getSize() > rHeap.getSize()) {
                System.out.println(lHeap.seeRoot());
            }
            else {
                System.out.println(rHeap.seeRoot());
            }
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
                    return null;
                }
            }
            return tokenizer.nextToken();
        }

        String nextLine() {
            try {
                return reader.readLine();
            } catch (IOException e) {
                return null;
            }
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

class MaxHeap extends Heap {

    @Override
    protected void swim(int elemInd) {
        while (elemInd > 1 && elems.get(elemInd) > elems.get(elemInd / 2)) {
            Collections.swap(elems, elemInd, elemInd / 2);
            elemInd = elemInd / 2;
        }
    }

    @Override
    protected void sink(int elemInd) {
        while (2 * elemInd <= size) {
            int childInd = 2 * elemInd;
            if (childInd < size && elems.get(childInd) < elems.get(childInd + 1)) {
                childInd++;
            }

            if (elems.get(elemInd) >= elems.get(childInd)) {
                break;
            }

            Collections.swap(elems, elemInd, childInd);
            elemInd = childInd;
        }
    }
}

class MinHeap extends Heap {

    @Override
    protected void swim(int elemInd) {
        while (elemInd > 1 && elems.get(elemInd) < elems.get(elemInd / 2)) {
            Collections.swap(elems, elemInd, elemInd / 2);
            elemInd = elemInd / 2;
        }
    }

    @Override
    protected void sink(int elemInd) {
        while (2 * elemInd <= size) {
            int childInd = 2 * elemInd;
            if (childInd < size && elems.get(childInd) > elems.get(childInd + 1)) {
                childInd++;
            }

            if (elems.get(elemInd) <= elems.get(childInd)) {
                break;
            }

            Collections.swap(elems, elemInd, childInd);
            elemInd = childInd;
        }
    }
}

abstract class Heap {
    protected int size;
    protected ArrayList<Integer> elems;

    Heap() {
        size = 0;
        elems = new ArrayList<>();
        //First element won't be used
        elems.add(Integer.MIN_VALUE);
    }

    public int getSize() {
        return size;
    }

    public int seeRoot() {
        return elems.get(1);
    }

    public void insert(int elem) {
        size++;
        // This if need to because actual array size may be != size of heap (I don't want copy arrays)
        if (elems.size() <= size) {
            elems.add(elem);
        } else {
            elems.set(size, elem);
        }

        swim(size);
    }

    public int extract() {
        int max = elems.get(1);
        Collections.swap(elems, 1, size--);
        sink(1);
        return max;
    }

    protected abstract void swim(int elemInd);

    protected abstract void sink(int elemInd);
}

