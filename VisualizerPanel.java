import java.awt.*;
import javax.swing.*;

public class VisualizerPanel extends JPanel {

    private int[] array;
    private Color[] colors;
    private final int maxValue = 500;
    private int delay = 30;

    public VisualizerPanel(int size) {
        this.array = new int[size];
        this.colors = new Color[size];
        randomizeArray();
    }

    // 🟦 Generate random array values
    public void randomizeArray() {
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * maxValue) + 10;
            colors[i] = new Color(100, 149, 237); // soft blue (default unsorted)
        }
        repaint();
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int[] getArray() {
        return array;
    }

    public Color[] getColors() {
        return colors;
    }

    // 🟨 Helper sleep method
    private void sleep() throws InterruptedException {
        Thread.sleep(delay);
    }

    // 🟩 Draw bars
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        int barWidth = Math.max(1, width / array.length);

        for (int i = 0; i < array.length; i++) {
            int barHeight = (int) ((double) array[i] / maxValue * (height - 20));
            g.setColor(colors[i]);
            g.fillRect(i * barWidth, height - barHeight, barWidth - 1, barHeight);
        }
    }

    // -----------------------------------------------------------
    // 🔹 Sorting Algorithms
    // -----------------------------------------------------------

    // 🧩 Bubble Sort
    public void bubbleSort() throws InterruptedException {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                colors[j] = Color.RED;
                colors[j + 1] = Color.RED;
                repaint();
                sleep();

                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }

                colors[j] = new Color(100, 149, 237);
                colors[j + 1] = new Color(100, 149, 237);
            }
            colors[n - i - 1] = Color.GREEN; // mark last as sorted
        }

        for (int i = 0; i < n; i++) {
            colors[i] = Color.GREEN;
        }
        repaint();
    }

    // 🧩 Selection Sort
    public void selectionSort() throws InterruptedException {
        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            colors[i] = Color.RED;
            repaint();
            sleep();

            for (int j = i + 1; j < n; j++) {
                colors[j] = Color.YELLOW;
                repaint();
                sleep();

                if (array[j] < array[minIdx]) {
                    minIdx = j;
                }
                colors[j] = new Color(100, 149, 237);
            }

            int temp = array[minIdx];
            array[minIdx] = array[i];
            array[i] = temp;

            colors[i] = Color.BLUE;
        }

        for (int i = 0; i < n; i++) {
            colors[i] = Color.GREEN;
        }
        repaint();
    }

    // 🧩 Insertion Sort
    public void insertionSort() throws InterruptedException {
        colors[0] = Color.BLUE;
        repaint();
        sleep();

        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            colors[i] = Color.RED;
            repaint();
            sleep();

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                colors[j] = Color.RED;
                repaint();
                sleep();
                colors[j] = Color.BLUE;
                j--;
            }
            array[j + 1] = key;

            for (int k = 0; k <= i; k++) {
                colors[k] = Color.BLUE;
            }
            repaint();
            sleep();
        }

        for (int k = 0; k < array.length; k++) {
            colors[k] = Color.GREEN;
        }
        repaint();
    }

    // 🧩 Merge Sort
    public void mergeSort(int left, int right) throws InterruptedException {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(left, mid);
            mergeSort(mid + 1, right);
            merge(left, mid, right);
        }
    }

    private void merge(int left, int mid, int right) throws InterruptedException {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = array[left + i];
        for (int j = 0; j < n2; ++j)
            R[j] = array[mid + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            colors[k] = Color.RED;
            repaint();
            sleep();

            if (L[i] <= R[j]) {
                array[k] = L[i++];
            } else {
                array[k] = R[j++];
            }
            k++;
        }

        while (i < n1) {
            array[k++] = L[i++];
            repaint();
            sleep();
        }

        while (j < n2) {
            array[k++] = R[j++];
            repaint();
            sleep();
        }

        for (int x = left; x <= right; x++) {
            colors[x] = Color.BLUE;
        }
        repaint();
    }
    public void updateArraySize(int newSize) {
    this.array = new int[newSize];
    this.colors = new Color[newSize];
    randomizeArray(); // re-randomize immediately
}

}
