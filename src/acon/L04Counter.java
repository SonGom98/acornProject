package acon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//Swing gui Component
//Component > Container(레이아웃 매니저: 다른 컴포넌트 배치) > Window(Frame : 종료버튼 메뉴가 있는 창), Panel(Frame에 포함되어야만 함)
//Component > JComponent > JList,JButton,JLabel,JTable .... (컨테이너에 포함될 수 있는 컴포넌트)
//gui 를 구성하는 컴포넌트는 상시 이벤트 발생을 대기한다.

class Counter extends JFrame{ // Window 필드인 JFrame이 모든 컴포넌트를 포함 할 수 있는 창이기 때문에 상속받고 구현한다.
    private final JButton upBtn;//1늘리는 버튼
    private final JButton downBtn; //1줄이는 버튼
    private final JButton resetBtn; //초기화
    private final JLabel screen; // 카운터 화면

    private int count=0;
    public Counter(String title) {
        super(title); //생성자는 부모와 자식을 구분한다.
        upBtn = new JButton("Up");
        downBtn = new JButton("Down");
        resetBtn = new JButton("Reset");
        screen = new JLabel(count+"명 ");
        screen.setHorizontalAlignment(JLabel.CENTER);


//        this.setLayout(new BorderLayout());
        this.add(screen);
        this.add(upBtn,BorderLayout.WEST);
        this.add(downBtn,BorderLayout.EAST);
        this.add(resetBtn,BorderLayout.SOUTH);

//        addWindowListener : window의 테두리의 버튼을 누르는것을 (이벤트) 재정의
//        this.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
////                Counter.this.dispose(); //frame을 종료하는 함수
//                System.exit(0);
//            }
//        });

        this.addWindowListener(new WindowHandler());
        upBtn.addActionListener(new UpBtnhandler());
        downBtn.addActionListener(new DownBtnhandler());
        resetBtn.addActionListener(new ResetBtnhandler());
        this.setBounds(0,0,200,200);
        this.setVisible(true);

    }
    class UpBtnhandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            screen.setText(++count+"명");
        }
    }
    class DownBtnhandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(count>0)
                screen.setText(--count+"명");
            else
                screen.setText(count+"명");
        }
    }
    class ResetBtnhandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

                screen.setText((count=0)+"명");

        }
    }
}



class WindowHandler implements WindowListener{

    @Override
    public void windowOpened(WindowEvent e) {} //열릴때

    @Override
    public void windowClosing(WindowEvent e) {
        //            this.dispose(); // WindowHandler의 필드 접근자 this
        System.exit(0);
    } //닫힘버튼을 누를때

    @Override
    public void windowClosed(WindowEvent e) {
        System.out.println("윈도우가 종료됩니다.");
        System.exit(0);
    }//윈도우가 종료될 떄

    @Override
    public void windowIconified(WindowEvent e) {}//윈도우가 아이콘으로 바뀔때

    @Override
    public void windowDeiconified(WindowEvent e) {}//아이콘에서 창으로 바뀔때

    @Override
    public void windowActivated(WindowEvent e) {} //창을 마우스로 누르고 있을때

    @Override
    public void windowDeactivated(WindowEvent e) {} //
}


public class L04Counter {
    public static void main(String[] args) {
        new Counter("카운터");
    }

}
