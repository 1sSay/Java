import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class IdealPyramid {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        List<Obelisk> obelisks = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int h = scanner.nextInt();
            obelisks.add(new Obelisk(x, y, h));
        }

        int xLeft = Integer.MAX_VALUE;
        int xRight = Integer.MIN_VALUE;
        int yLeft = Integer.MAX_VALUE;
        int yRight = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            Obelisk currentObelisk = obelisks.get(i);
            xLeft = Integer.min(xLeft, currentObelisk.x - currentObelisk.h);
            xRight = Integer.max(xRight, currentObelisk.x  + currentObelisk.h);
            yLeft = Integer.min(yLeft, currentObelisk.y - currentObelisk.h);
            yRight = Integer.max(yRight, currentObelisk.y  + currentObelisk.h);
        }

        int hAnswer = (Integer.max(xRight - xLeft, yRight - yLeft) - 1) / 2 + 1;
        int xAnswer = (xRight + xLeft) / 2;
        int yAnswer = (yRight + yLeft) / 2;

        System.out.print(xAnswer);
        System.out.print(' ');
        System.out.print(yAnswer);
        System.out.print(' ');
        System.out.print(hAnswer);
    }

    private static class Obelisk {
        public int x;
        public int y;
        public int h;

        public Obelisk(int x, int y, int h) {
            this.x = x;
            this.y = y;
            this.h = h;
        }
    }
}
