import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class P15649_N과M {   // 026

  static int N, M;          
  static boolean[] V;       // 숫자 사용 여부 저장 
  static int[] S;           // 수열 정보 저장

  public static void main(String[] args) throws IOException {
    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    M = sc.nextInt();
    S = new int[N];
    V = new boolean[N];    
    backtracking(0);
  }

  private static void backtracking(int length) {
    if(length == M) {   // 수열 크기를 충족하면 출력하고 리턴(되돌아감)
      printArray();
      return;
    }

    for(int i=0; i<N; i++) {
      if(!V[i]) {
        V[i] = true;            // 수 사용 저장
        S[length] = i;          // 수열에 수 사용
        backtracking(length+1);
        V[i] = false;           // 수 반납 저장
      }
    }
  }

  private static void printArray() {    // 수열 내용 출력하기
    for(int i=0; i<M; i++) {
      System.out.print(S[i] + 1 + " ");
    }
    System.out.println();
  }

  
}


