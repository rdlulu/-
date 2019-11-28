import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class LocationParse {
    String[] cities;
    double[] longs;
    double[] lats;
    public static void main(String[] args) {
        String s = "    上海市区经纬度:(121.43333,34.50000)<span class=\"hljs-tag\">&lt;<span class=\"hljs-title\">br</span>&gt;</span>\n" +
                "    天津市区经纬度:(117.20000,39.13333)<span class=\"hljs-tag\">&lt;<span class=\"hljs-title\">br</span>&gt;</span>\n" +
                "    香港经纬度:(114.10000,22.20000)<span class=\"hljs-tag\">&lt;<span class=\"hljs-title\">br</span>&gt;</span>\n" +
                "    广州经纬度:(113.23333,23.16667)<span class=\"hljs-tag\">&lt;<span class=\"hljs-title\">br</span>&gt;</span>\n" +
                "    珠海经纬度:(113.51667,22.30000)<span class=\"hljs-tag\">&lt;<span class=\"hljs-title\">br</span>&gt;</span>\n" +
                "    深圳经纬度:(114.06667,22.61667)<span class=\"hljs-tag\">&lt;<span class=\"hljs-title\">br</span>&gt;</span>\n" +
                "    杭州经纬度:(120.20000,30.26667)<span class=\"hljs-tag\">&lt;<span class=\"hljs-title\">br</span>&gt;</span>\n" +
                "    重庆市区经纬度:(106.45000, 29.56667)<span class=\"hljs-tag\">&lt;<span class=\"hljs-title\">br</span>&gt;</span>\n" +
                "    青岛经纬度:(120.33333,36.06667)<span class=\"hljs-tag\">&lt;<span class=\"hljs-title\">br</span>&gt;</span>\n" +
                "    厦门经纬度:(118.10000,24.46667)<span class=\"hljs-tag\">&lt;<span class=\"hljs-title\">br</span>&gt;</span>\n" +
                "    福州经纬度:(119.30000,26.08333)<span class=\"hljs-tag\">&lt;<span class=\"hljs-title\">br</span>&gt;</span>\n" +
                "    兰州经纬度:(103.73333,36.03333)<span class=\"hljs-tag\">&lt;<span class=\"hljs-title\">br</span>&gt;</span>\n" +
                "    贵阳经纬度:(106.71667,26.56667)<span class=\"hljs-tag\">&lt;<span class=\"hljs-title\">br</span>&gt;</span>\n" +
                "    长沙经纬度:(113.00000,28.21667)<span class=\"hljs-tag\">&lt;<span class=\"hljs-title\">br</span>&gt;</span>\n" +
                "    南京经纬度:(118.78333,32.05000)<span class=\"hljs-tag\">&lt;<span class=\"hljs-title\">br</span>&gt;</span>\n" +
                "    南昌经纬度:(115.90000,28.68333)<span class=\"hljs-tag\">&lt;<span class=\"hljs-title\">br</span>&gt;</span>\n" +
                "    沈阳经纬度:(123.38333,41.80000)<span class=\"hljs-tag\">&lt;<span class=\"hljs-title\">br</span>&gt;</span>\n" +
                "    太原经纬度:(112.53333,37.86667)<span class=\"hljs-tag\">&lt;<span class=\"hljs-title\">br</span>&gt;</span>\n" +
                "    成都经纬度:(104.06667,30.66667)<span class=\"hljs-tag\">&lt;<span class=\"hljs-title\">br</span>&gt;</span>\n" +
                "    拉萨经纬度:(91.00000,29.60000)<span class=\"hljs-tag\">&lt;<span class=\"hljs-title\">br</span>&gt;</span>\n" +
                "    乌鲁木齐经纬度:(87.68333,43.76667)<span class=\"hljs-tag\">&lt;<span class=\"hljs-title\">br</span>&gt;</span>\n" +
                "    昆明经纬度:(102.73333,25.05000)<span class=\"hljs-tag\">&lt;<span class=\"hljs-title\">br</span>&gt;</span>\n" +
                "    西安经纬度:(108.95000,34.26667)<span class=\"hljs-tag\">&lt;<span class=\"hljs-title\">br</span>&gt;</span>\n" +
                "    西宁经纬度:(101.75000,36.56667)<span class=\"hljs-tag\">&lt;<span class=\"hljs-title\">br</span>&gt;</span>\n" +
                "    银川经纬度:(106.26667,38.46667)<span class=\"hljs-tag\">&lt;<span class=\"hljs-title\">br</span>&gt;</span>\n" +
                "    兰浩特经纬度:(122.08333,46.06667)<span class=\"hljs-tag\">&lt;<span class=\"hljs-title\">br</span>&gt;</span>\n" +
                "    哈尔滨经纬度:(126.63333,45.75000)<span class=\"hljs-tag\">&lt;<span class=\"hljs-title\">br</span>&gt;</span>\n" +
                "    长春经纬度:(125.35000,43.88333)<span class=\"hljs-tag\">&lt;<span class=\"hljs-title\">br</span>&gt;</span>\n" +
                "    武汉经纬度:(114.31667,30.51667)<span class=\"hljs-tag\">&lt;<span class=\"hljs-title\">br</span>&gt;</span>\n" +
                "    郑州经纬度:(113.65000,34.76667)<span class=\"hljs-tag\">&lt;<span class=\"hljs-title\">br</span>&gt;</span>\n" +
                "    石家庄经纬度:(114.48333,38.03333)<span class=\"hljs-tag\">&lt;<span class=\"hljs-title\">br</span>&gt;</span>\n" +
                "    三亚经纬度:(109.50000,18.20000)<span class=\"hljs-tag\">&lt;<span class=\"hljs-title\">br</span>&gt;</span>\n" +
                "    海口经纬度:(110.35000,20.01667)<span class=\"hljs-tag\">&lt;<span class=\"hljs-title\">br</span>&gt;</span>\n" +
                "    澳门经纬度:(113.50000,22.20000)<span class=\"hljs-tag\">&lt;/<span class=\"hljs-title\">p</span>&gt;</span></code></pre>";
        String[] strs = s.split("<span class=\"hljs-tag\">&lt;<span class=\"hljs-title\">br</span>&gt;</span>\n");
        System.out.println(strs.length);
        LocationParse l = new LocationParse();
        l.cities = new String[strs.length];
        l.longs = new double[strs.length];
        l.lats = new double[strs.length];
        for(int i = 0; i < strs.length; i++) {
            l.cities[i] = strs[i].split("经纬度")[0].trim();
            l.longs[i] = Double.parseDouble(strs[i].split("\\(|,")[1]);
            l.lats[i] = Double.parseDouble(strs[i].split("\\)|,")[1]);
        }
        double[][] graph = l.buildGraph();
        //System.out.println("length " + graph.length);
        for(int i = 0; i < 1; i++) {
            System.out.print("离" + l.cities[i] + "最近的四个城市是");
            for(int j = 0; j < l.cities.length; j++) {
                if(graph[i][j] == 0) continue;
                System.out.print(l.cities[j] + ",");
            }
            System.out.println(",");
        }
        SearchAlgos sa = new SearchAlgos();
        List<Integer> ans = sa.dijkstra(graph, 16, 33);
        for(int i = 0; i < ans.size() - 1; i++) {
            System.out.print(l.cities[ans.get(i)] + " -> ");
        }
        System.out.println(l.cities[ans.get(ans.size() - 1)]);
        //System.out.print("第一个城市是" + l.cities[0] + "经度" + l.longs[0] + "纬度" + l.lats[0]);
    }
    public double dis(int i, int j) {
        return Math.pow((longs[i] - longs[j]), 2) + Math.pow((lats[i] - lats[j]), 2);
    }
    public double[][] buildGraph(){
        double[][] graph = new double[cities.length][cities.length];
        for(int i = 0; i < this.cities.length; i++) {
            Comp c = new Comp(i);
            PriorityQueue<Integer> pq = new PriorityQueue<>(c);
            for(int j = 0; j < this.cities.length; j++) {
                if(j == i) continue;
                pq.offer(j);
            }
            int k = 5;
            while(k > 0) {
                graph[i][pq.peek()] = this.dis(i, pq.peek());
                //graph[pq.peek()][i] = this.dis(i, pq.peek());
                pq.poll();
                k--;
            }
            pq.clear();
        }
        return graph;
    }
    class Comp implements Comparator<Object> {
        int i;
        public Comp(int i) {
            this.i = i;
        }
        @Override
        public int compare(Object a, Object b) {
            int a1 = (int)a;
            int b1 = (int)b;
            return (dis(a1, i) - dis(b1, i) < 0) ? -1 : 1;
        }
    }
}
