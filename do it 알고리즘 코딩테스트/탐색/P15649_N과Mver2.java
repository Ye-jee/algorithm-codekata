import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class P15649_N과Mver2 {   // 026

  static int N, M;          
  static boolean[] used;       // used[i]: 숫자 i + 1을 현재 수열에서 사용하고 있는지 저장
  static int[] sequence;       // 현재 만들고 있는 수열을 저장

  public static void main(String[] args) throws IOException {

    Scanner sc = new Scanner(System.in);

    N = sc.nextInt();
    M = sc.nextInt();

    sequence = new int[M];    // 길이가 M인 수열을 만드니 크기도 M만큼

    /*
    * 숫자는 1부터 N까지 사용하지만, 배열의 인덱스는 0부터 N - 1까지 사용
    * used[0] → 숫자 1의 사용 여부
    * used[1] → 숫자 2의 사용 여부
    */
    used = new boolean[N];    

    backtracking(0);    // 수열에 아직 아무 숫자도 넣지 않았으므로 길이 0부터 시작
  }

  private static void backtracking(int length) {    // length: 현재까지 수열에 저장한 숫자의 개수
                                                    // 동시에 다음 숫자를 저장할 배열의 위치
    if(length == M) {   // 수열 크기를 충족하면 출력하고 리턴(되돌아감)
      printArray();     // 길이가 M인 수열 하나 완성
      return;
    }

    /*
    * 현재 자리에 넣을 숫자를 1부터 N까지 차례대로 확인
    * 배열 인덱스를 사용하므로 i는 0부터 N - 1까지 반복
    */
    for(int i=0; i<N; i++) {

      /*
      * 현재 숫자 i + 1을 아직 사용하지 않았다면 현재 수열에 넣을 수 있음
      * 이미 사용한 숫자라면 중복 수열이 되므로 탐색하지 않음 
      */
      if(!used[i]) {          // 해당 조건이 가지치기 역할
        // 1) 선택: 숫자 i + 1을 현재 수열에서 사용 중이라고 표시
        used[i] = true;         
        sequence[length] = i+1;        // 현재 수열의 length 위치에 실제 숫자를 저장
                                      // i는 0부터 시작하므로 실제 숫자는 i + 1
        
        // 2) 다음 자리 탐색: 현재 자리에 숫자를 하나 넣었으므로 길이를 1 증가시켜 다음 자리를 선택
        backtracking(length+1);
        
        /*
        * 3) 선택 취소: 현재 숫자를 선택한 상태로 만들 수 있는 모든 수열의 탐색이 끝남
        * 이전 단계로 돌아가 다른 수열을 만들 수 있도록
        * 현재 숫자를 다시 사용하지 않은 상태로 변경
        */
        used[i] = false;       
      }
    }
  }

  // 완성된 길이 M의 수열 출력
  private static void printArray() {    
    for(int i=0; i<M; i++) {
      System.out.print(sequence[i] + " ");    // 숫자 사이에 공백을 넣어 출력
    }

    // 수열 하나를 출력한 후 줄바꿈
    System.out.println();
  }

  
}


