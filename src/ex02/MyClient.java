package ex02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MyClient {

    public static void main(String[] args) throws IOException {
        
        // client의 socket이 닫히면 server의 socket도 닫힘
        // client의 클라우드가 닫히면 server의 클라우드도 닫힘
        
        // 로컬 호스트와 포트 번호 필요
        Socket socket = new Socket("localhost", 20000);

        // autoFlush true 해놨으니까 flush 따로 명시 안 해도 됨
        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader br = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
        );

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String request = scanner.nextLine();
            pw.println(request); // 키보드로 입력해서 요청 넣고

            String line = br.readLine();
            System.out.println(line); // 읽어서 응답
        }
    }
}