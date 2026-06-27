import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11659_구간합구하기 {
    public static void main(String[] args) throws IOException{

        // 입력값이 100,000(십만)이라 버퍼 사용
        BufferedReader bufferedReader = 
            new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = 
            new StringTokenizer(bufferedReader.readLine()); // 첫번째 줄 숫자의 개수를 받아옴

        // suNo(숫자 개수), quizNo(질의 개수) 저장하기
        int suNo = Integer.parseInt(stringTokenizer.nextToken());
        int quizNo = Integer.parseInt(stringTokenizer.nextToken());

        // 합배열 S 생성
        long[] S = new long[suNo+1];       
    
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());   // 한 줄 입력 받음

        /*
        for(숫자 개수만큼 반복){
            합 배열 생성하기: S[i] = S[i-1] - A[i]
        }   
        */
        for(int i=1; i<=suNo; i++){
            S[i] = S[i-1] + Integer.parseInt(stringTokenizer.nextToken());
        }
        
        /* 
        for(질의 개수만큼 반복){
            질의 범위 받기: i~j
            구간 합 출력하기: S[j] - S[i-1]
        } 
        */
        for(int q=0; q<quizNo; q++){
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int i = Integer.parseInt(stringTokenizer.nextToken());
            int j = Integer.parseInt(stringTokenizer.nextToken());
            System.out.println(S[j] - S[i-1]);
        }

    }
    
}
