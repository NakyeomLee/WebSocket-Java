package ex01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {

    public static void main(String[] args) {
        
        // MyServer 실행하고 MyClient 실행한 다음 MyServer의 콘솔창 확인하기

        try {
            // 1. 서버 소켓 생성 (포트 번호 10000)
            // 서버는 소켓을 두 개 만듦 => 소켓을 리스너(듣는거)라고 함
            ServerSocket serverSocket = new ServerSocket(10000);

            // 2. 리스닝 (while 돌면서 해당하는 포트 번호 찾아내는 중)
            Socket socket = serverSocket.accept();
            System.out.println("oh! connect?");

            // 버퍼에 소켓 달기
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            // 버퍼에 있는 메시지를 \n(개행)까지 읽음
            String line = br.readLine();
            System.out.println("read : " + line);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}