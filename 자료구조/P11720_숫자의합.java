import java.util.Scanner;

public class P11720_숫자의합 {

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
  
    // N값 입력받기
    int N = sc.nextInt();

    // String 변수로 받아 배열 변수로 변환하기
    // 길이 N의 숫자를 입력받아, String 변수 sNum에 저장하기
    String sNum = sc.next();

    // sNum을 다시 char[]형 변수 cNum에 변환하여 저장하기
    char[] cNum = sNum.toCharArray();

    // 숫자의 합을 저장할 int형 변수 sum 선언하기
    int sum = 0;

    /*
    for(cNum 길이만큼 반복하기){
      배열의 각 자릿값을 정수형으로 변환하며 sum에 더하여 누적하기
    }
    */
    for (int i = 0; i < cNum.length; i++) {
      // 문자 값(cNum[i])을 정수 형으로 변환하여 더해주기
      sum += cNum[i] - '0'; 

      // sum = sum + cNum[i] - '0';
    }
    System.out.print(sum);
  }
}
