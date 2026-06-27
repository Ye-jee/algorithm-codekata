import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11659_구간합구하기ver2 {
    public static void main(String[] args) throws IOException{

        // 입력값이 많아 Scanner보다 입력 속도가 빠른 Buffer를 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // BufferedReader는 한 줄 전체를 문자열로 읽기 때문에 공백을 기준으로 데이터를 나누기 위해 사용
        // 첫번째 줄 숫자의 개수를 받아옴
        StringTokenizer st = new StringTokenizer(br.readLine()); 

        // 첫번째 줄: N, M
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 합배열 생성
        /* prefix[0] = 0을 사용하기 위해 배열을 N+1 크기로 생성
            그래야 첫 번째 원소부터의 구간합도 같은 공식으로 계산할 수 있음
         */
        int[] prefix = new int[n+1];       
    
        // 두번째 줄: 배열 입력
        st = new StringTokenizer(br.readLine());   // 한 줄 입력 받음

        /*
        for(숫자 개수만큼 반복){
            합 배열 생성하기: S[i] = S[i-1] - A[i]
        }   
        */
        for(int i=1; i<=n; i++){

            int num = Integer.parseInt(st.nextToken());
            prefix[i] = prefix[i-1] + num;

            // 현재까지의 누적합을 저장
            /* prefix[i-1] = 1번째부터 i-1번째까지의 합
                여기에 현재 숫자를 더하면
                prefix[i] = 1번째부터 i번째까지의 합이 됨
            */
           /* 예시)
                prefix[2] = 9
                현재 숫자 = 3
                prefix[3] = 12
           */
        }
        
        /* 
        for(질의 개수만큼 반복){
            질의 범위 받기: i~j
            구간 합 출력하기: S[j] - S[i-1]
        } 
        */

        // println()을 여러 번 호출하면 느려질 수 있으므로 출력 결과를 모두 저장한 뒤 마지막에 한 번만 출력
        StringBuilder sb = new StringBuilder();

        // M개의 구간합 계산
        for(int q=0; q<m; q++){

            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

           /* prefix[end] = 1 ~ end까지의 합
                prefix[start-1] = 1 ~ start-1까지의 합
                앞부분을 빼주면 start ~ end까지만 남는다 */
           /* 예)
                5 4 3 2 1
                prefix(합배열)
                0 5 9 12 14 15 */
            /* 2~4 구간
                prefix[4] = 14
                prefix[1] = 5
                14 - 5 = 9 */

            int result = prefix[end] - prefix[start-1];

            sb.append(result).append("\n");
        }

        // StringBuilder에 저장한 결과를 마지막에 한 번만 출력함
        System.out.print(sb);

    }
    
}
