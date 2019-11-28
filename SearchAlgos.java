import java.util.*;

public class SearchAlgos {
    public List<Integer> dijkstra(double[][] matrix, int start, int end) {
        double[] cost = new double[matrix.length];
        Arrays.fill(cost, (double)Integer.MAX_VALUE);
        PriorityQueue<Integer> pq = new PriorityQueue<>((Integer a, Integer b) -> (int)(cost[a] - cost[b]));
        Set<Integer> visited = new HashSet<>();
        int[] path = new int[matrix.length];
        Arrays.fill(path, -1);
        pq.offer(start);
        cost[start] = 0;
        path[start] = start;
        while(!pq.isEmpty()) {
            int cur = pq.poll();
            if(cur == end) {
                return generatePath(path, cur);
            }
            for(int i = 0; i < matrix[cur].length; i++) {
                if(matrix[cur][i] == 0 || visited.contains(i)) continue;
                if(cost[cur] + matrix[cur][i] < cost[i]) {
                    cost[i] = cost[cur] + matrix[cur][i];
                    if(pq.contains(i)) pq.remove(i);
                    pq.offer(i);
                    path[i] = cur;
                }
            }
            visited.add(cur);
        }
        return null;
    }
    private List<Integer> generatePath(int[] path, int cur) {
        int temp = cur;
        List<Integer> ans = new ArrayList<>();
        while(path[temp] != temp) {
            ans.add(0, temp);
            temp = path[temp];
        }
        ans.add(0,temp);
        return ans;
    }

}
