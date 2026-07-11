import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class P12891_DNA비밀번호 {  // 04-4

  static int checkArr[];    // 비밀번호 체크 배열
  static int myArr[];       // 현재 상태 배열
  static int checkSecret;   // 몇 개의 문자가 개수 조건을 충족하는지 나타내는 변수

  public static void main(String[] args) throws NumberFormatException, IOException{
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(bf.readLine());

    int S = Integer.parseInt(st.nextToken());
    int P = Integer.parseInt(st.nextToken());

    int Result = 0;

    char A[] = new char[S];

    checkArr = new int[4];
    myArr = new int[4];

    checkSecret = 0;

    A = bf.readLine().toCharArray();
    st = new StringTokenizer(bf.readLine());

    for(int i=0; i<4; i++){
      checkArr[i] = Integer.parseInt(st.nextToken());
      if(checkArr[i] == 0)
        checkSecret++;
    }

    for(int i=0; i<P; i++){   // 초기 P 부분 문자열 처리 부분
      Add(A[i]);
    }

    if(checkSecret == 4)
      Result++;

    // 슬라이딩 윈도우 처리 부분
    // j가 슬라이딩 윈도우 앞부분(왼쪽)을 의미하고 
    // i가 슬라이딩 윈도우 뒷부분(오른쪽)을 의미함
    for(int i=P; i<S; i++){
      int j = i-P;
      Add(A[i]);
      Remove(A[j]);
      if(checkSecret == 4)    // 4종류 문자의 개수 조건을 모두 충족하면 유효한 비밀번호
          Result++;
    }
    System.out.println(Result);
    bf.close();
  }

  // 별도 함수
  // 새로 들어온 문자를 처리하는 함수
  private static void Add(char c){    
    switch (c) {
      case 'A':
        myArr[0]++;
        if(myArr[0] == checkArr[0])
          checkSecret++;      
        break;
      
      case 'C':
        myArr[1]++;
        if(myArr[1] == checkArr[1])
          checkSecret++;      
        break;

      case 'G':
        myArr[2]++;
        if(myArr[2] == checkArr[2])
          checkSecret++;      
        break;

      case 'T':
        myArr[3]++;
        if(myArr[3] == checkArr[3])
          checkSecret++;      
        break;
    }
  }

  // 제거되는 문자를 처리하는 함수
  private static void Remove(char c){
    switch (c) {
      case 'A':
        if (myArr[0] == checkArr[0])
          checkSecret--;
        myArr[0]--;
        break;
      
      case 'C':
        if (myArr[1] == checkArr[1])
          checkSecret--;
        myArr[1]--;
        break;

      case 'G':
        if (myArr[2] == checkArr[2])
          checkSecret--;
        myArr[2]--;
        break;

      case 'T':
        if (myArr[3] == checkArr[3])
          checkSecret--;
        myArr[3]--;
        break;
    }
  }

}