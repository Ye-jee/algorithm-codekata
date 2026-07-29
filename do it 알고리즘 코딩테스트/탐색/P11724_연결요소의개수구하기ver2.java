import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P11724_연결요소의개수구하기ver2 {   // 023

  // graph[i]: i번 노드와 연결된 노드들의 목록
  static ArrayList<Integer>[] graph;

  // visited[i]: i번 노드들이 이미 방문했는지 저장
  static boolean[] visited;

  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // 노드 개수 N과 간선 개수 M 입력
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());

    /*
    * 노드 번호가 1부터 N까지이므로
    * 인덱스를 노드 번호와 동일하게 사용하기 위해 n + 1 크기로 생성
    */
    graph = new ArrayList[n+1];
    visited = new boolean[n+1];


    /*
    * graph 배열만 생성하면 각 칸(요소)는 null 상태
    * 따라서 1번부터 N번 위치에 각각 빈 ArrayList를 생성
    */
    for(int i=1; i<=n; i++) {
        graph[i] = new ArrayList<>();
    }

  
    // M개의 간선 정보를 입력받아 인접 리스트에 저장
    for(int i=0; i<m; i++){
      st = new StringTokenizer(br.readLine());

      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());

      /*
      * 이 문제는 무방향 그래프이므로 
      *start -> end 관계와 end -> start로 가는 관계를 모두 저장
      */ 
      graph[start].add(end);
      graph[end].add(start);

    }

    // 발견한 연결 요소의 개수
    int count = 0;

    // 모든 노드를 1번부터 N까지  확인
    for(int i=1; i<=n; i++){

      /*
      * 현재 노드를 아직 방문하지 않았다면
      * 기존에 탐색한 노드들과 연결되지 않은 새로운 연결 요소의 시작점
      */
     if(!visited[i]) {    // false 라면
        // 새로운 연결 요소를 발견했으니 개수 증가
        count++;

        // 현재 노드에서 DFS를 시작해 같은 연결 요소에 속한 모든 노드를 방문 처리
        dfs(i);
     }
    }

    // DFS를 새로 시작한 횟수 = 연결 요소 개수
    System.out.println(count);
  }

  // current 노드에서 시작해 연겨된 모든 노드를 방문하는 DFS
  static void dfs(int current) {
    // 현재 노드를 방문 처리
    visited[current] = true;

    // 현재 노드와 연결된 모든 노드를 하나씩 확인
    for(int next : graph[current]) {

      // 연결된 노드 중 아직 방문하지 않은 노드가 있다면 해당 노드로 더 깊이 들어 탐색
      if(!visited[next]) {
        dfs(next);
      }

    }
    
  }
  
}


