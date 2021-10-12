import java.io.*;
import java.util.StringTokenizer;

public class task2 {
    public static class Heap {
        private int[] heapArray; // массив со всеми вершинами
        private int maxSize; // размер массива
        private int currentSize = 0; // количество узлов массиве
        public Heap(int maxSize) {
            this.maxSize = maxSize;
            heapArray = new int[maxSize];
        }
        public void insert(int n) {
            heapArray[currentSize] = n;
            displaceUp(currentSize++);
        }

        private void displaceUp(int index) { //смещение вверх
            int parentIndex = (index - 1) / 2; // узнаем индекс родителя
            int bottom = heapArray[index]; // берем элемент
            while (index > 0 && heapArray[parentIndex] < bottom) {// если родительский элемент меньше
                heapArray[index] = heapArray[parentIndex];// то меняем его местами с рассматриваемым
                index = parentIndex;
                parentIndex = (parentIndex - 1) / 2;
            }
            heapArray[index] = bottom;
        }

        // достать из Heap наибольшее число (удалив его при этом)
        public int extract() {
            int top = heapArray[0];
            heapArray[0] = heapArray[currentSize - 1];
            heapArray[currentSize - 1] = 0;
            currentSize--;
            displaceDown(0);
            return top;
        }

        private void displaceDown(int index) {// смещение вниз
            int largerChild;
            int top = heapArray[index]; // сохранение корня, пока у узла есть хотя бы один потомок
            while (index < currentSize / 2) {// если данное условие не выполняется то элемент уже в самом низу пирамиды
                int leftChild = 2 * index + 1; // вычисляем индексы в массиве для левого узла ребенка
                int rightChild = leftChild + 1;// и правого

                if (rightChild < currentSize && heapArray[leftChild] < heapArray[rightChild]) {
                    largerChild = rightChild;
                }// вычисляем ребенка вершину с наибольшим числовым значением
                else {
                    largerChild = leftChild;
                }

                if (top >= heapArray[largerChild]) {// если значение вершины больше или равно
                    //значени его наибольшего ребенка
                    break;// то выходим из метода
                }
                heapArray[index] = heapArray[largerChild];// заменяем вершину, большей дочерней вершиной
                index = largerChild; // текущий индекс переходит вниз
            }
            heapArray[index] = top; // задаем конечное местоположение для элемента
        }

    }
    private static void solve(final FastScanner in, final PrintWriter out) {
        int number = in.nextInt();
        Heap obj = new Heap(number);
        int command = -1;
        for (int i = 0; i < number; i++) {
            command = in.nextInt();
            if (command == 0) {
                obj.insert(in.nextInt());
            }
            else if (command == 1) {
                out.println(obj.extract());
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
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
