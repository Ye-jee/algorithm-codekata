import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class P12891_DNA비밀번호ver2 {  // 04-4
  // 자세한 설명 추가

  // 백준 12891번: DNA 비밀번호

  /*
    * 배열 인덱스와 DNA 문자 대응
    *
    * 0 → A
    * 1 → C
    * 2 → G
    * 3 → T
    */

  // 유효한 비밀번호가 되기 위해 필요한
  // A, C, G, T의 최소 개수를 저장하는 배열
  static int[] checkArr;

  // 현재 슬라이딩 윈도우 안에 들어 있는
  // A, C, G, T의 실제 개수를 저장하는 배열
  static int[] myArr;

  // A, C, G, T 중에서
  // 현재 최소 개수 조건을 충족한 문자의 종류 수
  //
  // checkSecret이 4라면 네 문자 조건을 모두 만족한 상태이다.
  static int checkSecret;

  public static void main(String[] args) throws IOException {

      BufferedReader bf =
              new BufferedReader(new InputStreamReader(System.in));

      /*
        * 첫 번째 줄 입력
        *
        * S: 전체 DNA 문자열 길이
        * P: 비밀번호로 사용할 부분 문자열 길이
        */
      StringTokenizer st = new StringTokenizer(bf.readLine());

      int S = Integer.parseInt(st.nextToken());
      int P = Integer.parseInt(st.nextToken());

      // 조건을 만족하는 부분 문자열의 개수
      int result = 0;

      // 전체 DNA 문자열을 문자 배열로 저장한다.
      char[] dna = bf.readLine().toCharArray();

      // 각 배열은 A, C, G, T 네 종류만 관리하므로 크기가 4이다.
      checkArr = new int[4];
      myArr = new int[4];

      // 처음에는 아직 조건을 충족한 문자가 없다고 가정한다.
      checkSecret = 0;

      /*
        * 세 번째 줄 입력
        *
        * A, C, G, T가 각각 최소 몇 개 이상 있어야 하는지 입력받는다.
        */
      st = new StringTokenizer(bf.readLine());

      for (int i = 0; i < 4; i++) {
          checkArr[i] = Integer.parseInt(st.nextToken());

          /*
            * 최소 필요 개수가 0개라면
            * 해당 문자는 처음부터 조건을 만족한 상태이다.
            *
            * 예:
            * C가 최소 0개 필요하다면
            * 현재 윈도우에 C가 없어도 조건을 만족한다.
            */
          if (checkArr[i] == 0) {
              checkSecret++;
          }
      }

      /*
        * 첫 번째 슬라이딩 윈도우를 만든다.
        *
        * 길이가 P인 첫 번째 부분 문자열은
        * 인덱스 0부터 P - 1까지이다.
        *
        * 예:
        * P = 4라면 dna[0], dna[1], dna[2], dna[3]을 추가한다.
        */
      for (int i = 0; i < P; i++) {
          add(dna[i]);
      }

      /*
        * 첫 번째 윈도우가
        * A, C, G, T의 최소 개수 조건을 모두 만족하는지 확인한다.
        */
      if (checkSecret == 4) {
          result++;
      }

      /*
        * 슬라이딩 윈도우를 오른쪽으로 한 칸씩 이동한다.
        *
        * 첫 번째 윈도우에서 이미 0부터 P - 1까지 처리했으므로
        * 새로 들어올 첫 번째 문자의 위치는 P이다.
        */
      for (int i = P; i < S; i++) {

          /*
            * i: 새로 윈도우에 들어오는 문자의 위치
            * j: 기존 윈도우에서 빠지는 문자의 위치
            *
            * 예: P = 4일 때
            *
            * i = 4이면 새로 dna[4]가 들어오고
            * j = 4 - 4 = 0이므로 dna[0]이 빠진다.
            */
          int j = i - P;

          // 오른쪽에 새롭게 들어오는 문자를 현재 윈도우에 추가한다.
          add(dna[i]);

          // 왼쪽에서 벗어나는 문자를 현재 윈도우에서 제거한다.
          remove(dna[j]);

          /*
            * 문자를 추가하고 제거한 뒤에도
            * 네 종류의 문자 조건을 모두 충족한다면
            * 유효한 비밀번호이므로 결과를 증가시킨다.
            */
          if (checkSecret == 4) {
              result++;
          }
      }

      // 조건을 만족하는 부분 문자열의 총개수를 출력한다.
      System.out.println(result);

      bf.close();
  }

  /**
   * 슬라이딩 윈도우에 새롭게 들어오는 문자를 처리하는 함수
   *
   * 1. 해당 문자의 현재 개수를 증가시킨다.
   * 2. 증가한 결과가 최소 필요 개수와 같아졌다면
   *    해당 문자 조건을 새롭게 만족한 것이므로 checkSecret을 증가시킨다.
   */
  private static void add(char c) {

      switch (c) {

          case 'A':

              // 현재 윈도우 안의 A 개수를 1 증가시킨다.
              myArr[0]++;

              /*
                * A의 개수가 최소 필요 개수에 정확히 도달한 순간에만
                * A 조건을 만족했다고 판단한다.
                *
                * 예:
                * 최소 필요 개수가 2일 때
                *
                * 1 → 2: 조건을 새롭게 만족하므로 checkSecret 증가
                * 2 → 3: 이미 조건을 만족하고 있으므로 증가하지 않음
                */
              if (myArr[0] == checkArr[0]) {
                  checkSecret++;
              }

              break;

          case 'C':

              // 현재 윈도우 안의 C 개수를 1 증가시킨다.
              myArr[1]++;

              // C의 최소 개수 조건을 새롭게 만족하면 증가시킨다.
              if (myArr[1] == checkArr[1]) {
                  checkSecret++;
              }

              break;

          case 'G':

              // 현재 윈도우 안의 G 개수를 1 증가시킨다.
              myArr[2]++;

              // G의 최소 개수 조건을 새롭게 만족하면 증가시킨다.
              if (myArr[2] == checkArr[2]) {
                  checkSecret++;
              }

              break;

          case 'T':

              // 현재 윈도우 안의 T 개수를 1 증가시킨다.
              myArr[3]++;

              // T의 최소 개수 조건을 새롭게 만족하면 증가시킨다.
              if (myArr[3] == checkArr[3]) {
                  checkSecret++;
              }

              break;
      }
  }

  /**
   * 슬라이딩 윈도우에서 빠지는 문자를 처리하는 함수
   *
   * 1. 현재 개수가 최소 필요 개수와 같은지 먼저 확인한다.
   * 2. 같다면 문자를 제거한 후 조건을 만족하지 못하게 되므로
   *    checkSecret을 먼저 감소시킨다.
   * 3. 해당 문자의 현재 개수를 감소시킨다.
   */
  private static void remove(char c) {

      switch (c) {

          case 'A':

              /*
                * 현재 A 개수가 최소 필요 개수와 같다면
                * A 하나가 빠지는 순간 조건을 만족하지 못하게 된다.
                *
                * 따라서 실제 개수를 감소시키기 전에
                * checkSecret을 먼저 감소시킨다.
                *
                * 예:
                * A 최소 필요 개수 = 2
                * 현재 A 개수 = 2
                *
                * A를 제거하면 2 → 1이 되어 조건을 만족하지 못한다.
                */
              if (myArr[0] == checkArr[0]) {
                  checkSecret--;
              }

              // 현재 윈도우 안의 A 개수를 1 감소시킨다.
              myArr[0]--;

              break;

          case 'C':

              // C를 제거하면 최소 조건이 깨지는지 먼저 확인한다.
              if (myArr[1] == checkArr[1]) {
                  checkSecret--;
              }

              // 현재 윈도우 안의 C 개수를 1 감소시킨다.
              myArr[1]--;

              break;

          case 'G':

              // G를 제거하면 최소 조건이 깨지는지 먼저 확인한다.
              if (myArr[2] == checkArr[2]) {
                  checkSecret--;
              }

              // 현재 윈도우 안의 G 개수를 1 감소시킨다.
              myArr[2]--;

              break;

          case 'T':

              // T를 제거하면 최소 조건이 깨지는지 먼저 확인한다.
              if (myArr[3] == checkArr[3]) {
                  checkSecret--;
              }

              // 현재 윈도우 안의 T 개수를 1 감소시킨다.
              myArr[3]--;

              break;
      }
  }
}