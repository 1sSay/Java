import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class IntScanner {
    Reader reader;
    char[] buffer;
    int charsRead;
    int charsInBuffer;
    StringBuilder token;

    public IntScanner(InputStream stream) {
        this.reader = new InputStreamReader(
                stream,
                StandardCharsets.UTF_8
        );
        this.buffer = new char[8192];
    }

    public int next() throws IOException {
        this.token = new StringBuilder();

        this.checkBuffer();
        while (this.charsRead < this.charsInBuffer) {
            char lastChar = buffer[this.charsRead];

            if (!Character.isWhitespace(lastChar)) {
                this.token.append(lastChar);
            } else if (token.length() > 0) {
                break;
            }


            this.charsRead++;
            this.checkBuffer();
        }

        return Integer.parseInt(token.toString());
    }

    private void checkBuffer() throws IOException {
        if (this.charsRead == this.charsInBuffer) {
            this.charsRead = 0;
            this.charsInBuffer = this.reader.read(buffer);
        }
    }
}

public class Equidistant {
    private static void DFS(int v, int[] depth, int[] parent, boolean[] used, List<List<Integer>> graph, int[] c) {
        used[v] = true;

        for (int u : graph.get(v)) {
            if (!used[u]) {
                parent[u] = v;
                depth[u] = depth[v] + 1;
                DFS(u, depth, parent, used, graph, c);
            }
        }
    }

    public static void main(String[] args) {
        try {
            IntScanner scanner = new IntScanner(System.in);

            int n = scanner.next();
            int m = scanner.next();

            List<List<Integer>> graph = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<Integer>());
            }

            for (int i = 0; i < n - 1; i++) {
                int v = scanner.next() - 1;
                int u = scanner.next() - 1;

                graph.get(v).add(u);
                graph.get(u).add(v);
            }

            int[] c = new int[m];
            for (int i = 0; i < m; i++) {
                c[i] = scanner.next() - 1;
            }

            int[] depth = new int[n];
            int[] parent = new int[n];
            boolean[] used = new boolean[n];
            DFS(c[0], depth, parent, used, graph, c);

            int maxDepth = 0;
            int cWithMaxDepthIndex = 0;
            for (int v = 0; v < m; v++) {
                if (depth[c[v]] > maxDepth) {
                    cWithMaxDepthIndex = v;
                    maxDepth = depth[c[v]];
                }
            }

            int minDepthVertex = c[cWithMaxDepthIndex];
            int midDepth = maxDepth / 2;
            for (int i = 0; i < midDepth; i++) {
                minDepthVertex = parent[minDepthVertex];
            }

            depth = new int[n];
            used = new boolean[n];
            DFS(minDepthVertex, depth, parent, used, graph, c);

            for (int i : c) {
                if (depth[i] != midDepth) {
                    System.out.println("NO");
                    return;
                }
            }

            System.out.println("YES");
            System.out.println(minDepthVertex + 1);
        } catch (IOException e) {
            System.err.println("Trying to read non-existing integer");
        }
    }
}
