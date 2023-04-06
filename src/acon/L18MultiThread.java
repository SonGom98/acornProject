package acon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class L18MultiThread {
    static boolean isClock = true;
    public static void main(String[] args) throws InterruptedException {
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
        System.out.println(date);
        System.out.println(sdf.format(date));

        JFrame f = new JFrame("시계");
        JLabel l = new JLabel();
        JButton stopBtn=new JButton("멈춤");
        f.add(stopBtn, BorderLayout.SOUTH);
        f.add(l);
        f.setBounds(0,0,200,200);
        f.setVisible(true);

        //시계 출력을 비동기 코드로 만들어보세요.

        new Thread(() -> {
            while (isClock) {
                //stopBtn.setText("안녕"); 익명클래스인데 내부클래스에서 참조하는 것은 좀 무리가 있다.
                //좀 힘들고 무리한 일이니가 상수로 정의해 달라!
                Date now = new Date();
                SimpleDateFormat nowSdf = new SimpleDateFormat("hh:mm:ss.SSS");
                l.setText(nowSdf.format(now));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        ).start();
        //특정 매개변수로 추상클래스를 구현하고 객체로 만들어 사용하는 것이 불편해서
        //추살클래스(Runnable)를 바로 객체로 만들 수 있게 자동 완성(익명클래스 생성)을 지원한다.

        stopBtn.addActionListener((e)-> { //버튼을 누르면 호출되는 콜백함수
                isClock =false;
        });
        //Swing 의 frame 이 thread(화면을 그리고 이벤트리스닝을 함)를
        //하나 생성하기 때문에 버튼이 눌린다.
        System.out.println("안녕");
    }
}
