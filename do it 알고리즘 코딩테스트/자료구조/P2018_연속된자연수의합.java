import java.util.Scanner;

public class P2018_연속된자연수의합 {  // 04-3  

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
  
    // N값 입력받기
    int N = sc.nextInt();

    int count = 1;
    int start_index = 1;
    int end_index = 1;
    int sum = 1;

    while(end_index != N) {
      if(sum == N) {          // 현재 연속 합이 N과 같은 경우
        count++;
        end_index++;
        sum += end_index;     // sum = sum + end_index;
      }
      else if(sum > N){       // 현재 연속 합이 N보다 더 큰 경우
        sum -= start_index;   // sum = sum - start_index;
        start_index++;
      }
      else {                  // 현재 연속 합이 N보다 작은 경우
        end_index++;      
        sum += end_index;     // sum = sum + end_index;
      }
    }

    System.out.print(count);
  }
}
