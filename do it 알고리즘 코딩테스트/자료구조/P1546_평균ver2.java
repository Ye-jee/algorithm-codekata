import java.util.Scanner;

public class P1546_평균ver2 { 
  // A[] 배열을 만들지 않고 temp 변수 사용

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
  
    // N값 입력받기
    int N = sc.nextInt();

    long temp = 0;

    long sum = 0;
    long max = 0;


    for(int i=0; i<N; i++) {
      temp = sc.nextInt();
      if(temp > max) max = temp;
      sum = sum + temp;
    }

    System.out.println(sum*100.0 / max / N);

  }
}
