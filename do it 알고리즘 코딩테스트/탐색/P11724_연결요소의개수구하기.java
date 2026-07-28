import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class P11724_연결요소의개수구하기 {   // 023

  static ArrayList<Integer>[] A;
  static boolean visited[];

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());

    A = new ArrayList<>[n+1];
    visited = new boolean[n+1];

    for(int i=1;i<n+1; i++){    // 1부터 시작, 인접리스트 초기화
      A[i] = new ArrayList<Integer>();
    }

    for(int i=0; i<m; i++){
      st = new StringTokenizer(br.readLine());
      int s = Integer.parseInt(st.nextToken());
      int e = Integer.parseInt(st.nextToken());

      A[s].add(e);    // 양방향 에지이므로 양쪽에 에지를 더하기
      A[e].add(s);
    }

     int count = 0;
     for(int i=1; i<n+1; i++){
      if(!visited[i]){          // 방문하지 않은 노드가 없을 때까지 반복
           count++;
           DFS(i);
      }
     }
     System.out.println(count);
  }

  static void DFS(int v) {
  if(visited[v]){
    return;
  }
  visited[v] = true;
  for(int i : A[v]){
    if(visited[i] == false) {   // 연결노드 중 방문하지 않았던 노드만 탐색하기
      DFS(i);
    }
  }
}
}


