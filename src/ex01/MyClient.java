package ex01;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class MyClient {

    public static void main(String[] args) throws IOException {
        // 로컬 호스트와 포트 필요
        Socket socket = new Socket("localhost", 10000);

        // 밑에 주석달아놓은 코드보다 더 편한 방법
        // autoFlush true 해놨으니까 flush 따로 명시 안 해도 됨
        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
        pw.println("Hello World");

        MyWriter mw = new MyWriter(socket.getOutputStream(), true);
        mw.println("Hello World");

//        BufferedWriter bw = new BufferedWriter(
//                new OutputStreamWriter(socket.getOutputStream())
//        );
//
//        // 프로토콜(약속) - 문자열에 \n 꼭 붙이기
//        // MyServer의 bufferdReader가 \n까지 읽기 때문에
//        // bufferdWriter의 문자열에 \n을 꼭 포함해야 읽을 수 있음
//        bw.write("Hello World\n");
//        bw.flush();
    }
}