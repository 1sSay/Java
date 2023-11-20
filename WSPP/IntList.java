import java.util.Arrays;

class IntList {
    int[] numbers;
    int size;
    int true_size;

    public IntList() {
        this.numbers = new int[16];
        this.size = 0;
        this.true_size = 0;
    }

    public IntList push(int x) {
        this.numbers[this.size] = x;
        this.size++;
        this.true_size++;
        if (this.size == this.numbers.length) {
            this.numbers = Arrays.copyOf(this.numbers, this.size * 2);
        }

        return this;
    }

    public IntList increaseTrueSize() {
        this.true_size++;
        return this;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append(this.true_size);

        for (int i = 0; i < this.size; i++) {
            if (this.numbers[i] > 0) {
                str.append(' ');
                str.append(this.numbers[i]);
            }
        }

        return str.toString();
    }
}