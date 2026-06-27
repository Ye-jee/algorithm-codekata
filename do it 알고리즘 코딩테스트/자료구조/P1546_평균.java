import java.util.Scanner;

public class P1546_평균 {   // 002

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
  
    // 변수 N에 과목의 수 입력받기
    int N = sc.nextInt();

    // 길이가 N인 1차원 배열 A[] 선언하기
    int[] A = new int[N];

    /*
    for(A[] 길이만큼 반복하기){
      A[i]에 각 점수 저장하기
    }
    */
    for(int i=0; i<N; i++) {
      A[i] = sc.nextInt();
    }

    long sum = 0;
    long max = 0;


    /*
    for(A[] 길이만큼 반복하기){
      최고점은 변수 max에, 총점은 변수 sum에 저장하기
    }
    */
    for(int i=0; i<N; i++) {
      if(A[i] > max) max = A[i];
      sum = sum + A[i];
    }

    // sum * 100 / max / N 출력하기
    System.out.println(sum*100.0 / max / N);

  }
}
