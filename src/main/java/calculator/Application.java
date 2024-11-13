package calculator;

import java.io.*;
import java.util.*;

public class Application {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static String input_value;
    static int result;
    public static void main(String[] args) throws IOException {
        input();
        String separator = grammerCheck();
        calculrator(separator);
        output();
    }



    // 문자열 입력 기능
    private static void input() throws IOException {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        input_value = br.readLine();
    }



    // 입력 검사 기능
    private static String grammerCheck(){
        String customDelimiter = "";
        // 커스텀문자가 없는 경우.
        if(input_value.charAt(0)>='0' && input_value.charAt(0)<='9'){
            String[] splitInput = input_value.split(",|:");
// + 구분자사이에 숫자가 안들어간 경우 -> 오류 처리해야함
            for (String s : splitInput) {
                if (!isNumeric(s)) {
                    error();
                }
            }
            customDelimiter = ":";
            return customDelimiter;
        }

        // 커스텀 문자가 있는 경우
        if(input_value.charAt(0)=='/' && input_value.charAt(1)=='/'){
            // \n이 존재하는지 확인
            int delimiterIndex = input_value.indexOf("\\n");
            if(delimiterIndex == -1){
                error();
            }
            // 구분자가 존재하는지 확인.
            customDelimiter = input_value.substring(2, delimiterIndex);
            if(customDelimiter.isEmpty()){
                error();
            }

            // 구분자가 안에 있는 값으로 잘 사용되었는지 확인
            String numbers = input_value.substring(delimiterIndex + 2);
            String[] splitNumbers = numbers.split(customDelimiter);
            for(String num : splitNumbers){
                if(!isNumeric(num)){
                    error();
                }
            }
        }else{
            error();
        }
        return customDelimiter;
    }

    // 숫자 여부 확인 함수
    private static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)<'0' || str.charAt(i)>'9')
                return false;
        }
        return true;
    }



    // 덧셈 기능.
    private static void calculrator(String separator){
        if(separator.equals(":")){
            separator += "|,";
            String[] splitInput = input_value.split(separator);
            for (String s : splitInput) {
                result += Integer.parseInt(s);
            }
        }else{
            int delimiterIndex = input_value.indexOf("\\n");
            String numbers = input_value.substring(delimiterIndex + 2);
            String[] splitInput = numbers.split(separator);
            for (String s : splitInput) {
                result += Integer.parseInt(s);
            }
        }
    }


    // 에러 기능.
    private static void error(){
        System.out.println("[ERROR]\n");
        System.exit(1);
    }


    // 출력 기능.
    private static void output() throws IOException {
        bw.write("결과 : "+ result);
        bw.flush();
        bw.close();
    }
}
