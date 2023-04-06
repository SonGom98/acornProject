package acon;

import javax.swing.*;
import javax.swing.text.html.HTML;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SwingTimer extends JFrame{
    int count = 100;
    int touch = 0;
    boolean start =true;
    JButton startBtn = new JButton("시작");
    JButton stopBtn = new JButton("멈춤");
    JButton resetBtn = new JButton("초기화");
    Panel btnP = new Panel();
    JLabel screen = new JLabel(this.count/10.0+"");

    public SwingTimer(){
        screen.setHorizontalAlignment(JLabel.CENTER);
        this.add(screen);
        btnP.add(startBtn);
        btnP.add(stopBtn);
        btnP.add(resetBtn);
        btnP.setLayout(new GridLayout());
        this.add(btnP, BorderLayout.SOUTH);
        this.setBounds(0,0,300,300);
        this.setVisible(true);
        startBtn.addActionListener(new StartBtnHandler());

        stopBtn.addActionListener(new StopBtnHandler());

        resetBtn.addActionListener((e)->{
            start = false;
                count = 100;
                touch = 0;
                screen.setText(count/10.0+"\n리셋됨");
        });
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    class StartBtnHandler implements ActionListener{
//        @Override
//        public void run() {
//                    while(count >0 && start){
//                        try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException ex) {
//                        throw new RuntimeException(ex);
//                    }
//                        if(touch == 1) //멈추고 바로 카운트를 멈춰줌
//                            screen.setText((--count)+"");
//                    }
//                    if(count ==0) { //안해주면 리셋시 1초후 타이머 종료가 뜸
//                        screen.setText(count + "<html><br>타이머종료</html>");
//                    }
//                }
        @Override
        public void actionPerformed(ActionEvent e) {
            //버튼을 여러번 누르면 스레드가 여러개 생겨서 카운트를 여러번 증감시킨다.
            Runnable run = ()->{    //람다식 사용
                while(count >0 && start){
                        try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                        if(touch == 1) //멈추고 바로 카운트를 멈춰줌
                            screen.setText((--count/10.0)+"");
                    }
                    if(count ==0) { //안해주면 리셋시 1초후 타이머 종료가 뜸
                        screen.setText(count + "<html><br>타이머종료</html>");
                    }
            };

            if(touch == 0) {
                new Thread(run).start();
                start = true;
                touch =1;
            }
        }
    }

        class StopBtnHandler implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                start = false;
                touch =0;
                screen.setText(count/10.0 + "\n타이머 멈춤");
            }
        }
//        class ResetBtnHandler implements ActionListener{
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                start = false;
//                count = 100;
//                touch = 0;
//                screen.setText(count/10.0+"\n리셋됨");
//            }
//        }
}




public class L09Timer {
    public static void main(String[] args) {
        new SwingTimer();
    }
}
