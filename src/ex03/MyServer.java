package ex03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {

    public static void main(String[] args) {

        // 이 프로그램은 동기적 => 1 : 1이고 순서대로 실행됨
        // (요청을 받고 실행됨, 요청을 받기 전까지는 실행 안됨)

        try {

            // 1. 리스너 생성 및 대기 (메인스레드) - while
            ServerSocket serverSocket = new ServerSocket(20000);
            Socket socket = serverSocket.accept();

            // (클라이언트마다 t1, t2, t3 새로운 스레드)
            // 2. 버퍼 달기 (반이중 연결 : 요청하면 응답, 요청하기 전엔 응답 안 함)
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

            // 3. 요청받고 응답하기
            while(true){
                String line = br.readLine();
                String response = parser(line);
                pw.println(response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 위에 만든 프로토콜을 메서드로 만들어서 활용하기 (위 메인 클래스 프로토콜 주석 참고)
    private static String parser(String line) {
        // 프로토콜
        // parser(구문 분석해서 처리(parsing)해줌) / router는 전송해주는 역할임을 참고
        String header = line.split(":")[0];
        String body = line.split(":")[1];

        String response = "404"; // 기본값을 404로 해놓고 조건별로 초기화 시켜놓기

        if(header.equals("buyer")){
            if(body.equals("1")) response = "사과";
            else if(body.equals("2")) response = "라면";
        }

        if(header.equals("seller")){
            if(body.equals("1")) response = "당근";
            else if(body.equals("2")) response = "우유";
        }
        return response;
    }
}