import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

public class ManagingDifficulties {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int t = scanner.nextInt();

        for (int testNumber = 0; testNumber < t; testNumber++) {
            int n = scanner.nextInt();
            int[] a = new int[n];
            for (int aNumber = 0; aNumber < n; aNumber++) {
                a[aNumber] = scanner.nextInt();
            }

            int answer = 0;
            Map<Integer, Integer> C = new HashMap<>();
            for (int j = n - 1; j >= 1; j--) {
                for (int i = 0; i < j; i++) {
                    int v = 2 * a[j] - a[i];
                    if (C.containsKey(v)) {
                        answer += C.get(v);
                    }
                }
                C.put(a[j], C.getOrDefault(a[j], 0) + 1);
            }

            System.out.println(answer);
        }
    }
}
