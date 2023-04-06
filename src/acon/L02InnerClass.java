package acon;

//작명 규칙 (숫자로 시작하면 안됨, 연산을 사용하지 않는다, #프라이빗한 이름,$필드, 회사규칙을 따르는것이 좋다 )
//파스칼: 대명서, class, 프로젝트명
//카멜케이스: 변수 (자바)
//스네이크(대문자) : 상수 (모든 프로그래밍 언어)
//스네이크(소문자) : 폴더(대소문자 구분을 못하는 운영체제에서 사용), 패키지명(소문자로만 작성하려고)

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class btnActionHandler implements ActionListener{
    int index;
    @Override
    public void actionPerformed(ActionEvent e) {
//        ActionEvent e : 이벤트와 관련된 정보가 포함되어 있다.
        Object btnObj = e.getSource(); //이벤트가 발생한 대상을 반환
        JButton btn = (JButton)btnObj;
        btn.setText(++index+"명");
        System.out.println("버튼이 눌렸어요!!");
    }
    // 인터페이스 : 모든 메서드가 추상이어야 한다.
    //클래스 모든 메서드가 추상일수 없다. (인터페이스를 implements 하면 모든 추상 메서드를 구현({})해야한다.)
}


public class L02InnerClass {
    static int index = 0;
    /*
    class 1 implement ActionListener{
        @Override
            public void actionPerformed(ActionEvent e) {
                btn.setText(++index[0] +""); // 연산을 하면 index가 참조하는 수가 연산한 값으로 바뀐다. (상수의 규칙에서 벗어난다)
                //자료형 btn의 내부 필드가 바뀌는 것을 상수가 허용한다.
                //내부 클래스로 만들어졌기 때문에 main 함수의 지역변수 btn 을 참조할 수 없는 것이 정상이지만
                //마치 부모 클래스의 필드처럼 접근할 수 있도록 컴파일러가 구현해 놓는다. 하지만 상수여야 한다!!
        }
    }
    */


    public static void main(String[] args) {
        //GUI :
        JFrame f = new JFrame("awt window 안녕");
        JButton btn = new JButton("안뇽");

//        btnActionHandler btnActionHandler = new btnActionHandler();
//        btn.addActionListener(btnActionHandler);
        btn.addActionListener(new ActionListener() { //L02InnerClass%1
            int index = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                btn.setText(++index +"명 입니다.");
            }
        });

        f.add(btn);
        f.setBounds(0,0,200,200);
        f.setVisible(true);
        //하이레벨(인간과 가까운) vs 로우레벨(컴퓨터에 가까운) 언어
        //플랫폼(os)이 하는 일 : 컴퓨터의 재원을 관리 및 동작
        //안드로이드 개발 mac 이 더 빠르다

        //GUI (마우스 이벤트가 중요하다)


        //플랫폼에 독립적이다.
        //window 나 mac에서 자바를 실행시켜서 동작한다(x)
        //자바가 (jvm)이 class 를 플랫폼 위에서 실행하면서 플랫폼의 자원을 동작(o)



        //자바가 유명하게 된 이유와 frame
        //window 에서 GUI 앱을 개발하는 패키지를 제공했는데 어렵고 복잡해서 개발 난이도 높았다.
        //window 용 앱을 만들고 다시 mac 이나 리눅스용 앱을 만드려면 각 플랫폼에서 제공하는 GUI 패키지를 공부해야했다 .. ㅠㅠ
        // java 가 통합 gui 패키지를 제공 (java.awt.*) 하면서 엄청엄청 유명해진다.
        // ==> 시간이 지날수록 java.awt에서 제공하는 컴포넌트가 플랫폼마다 다르게 동작하기 시작
        // -> java.swing 패키지로 업데이트
        // ->javaFX : 컴포넌트의 레이아웃이 제한적으로 배치되고 swing 패키지가 오래되어서 새롭게 등장한  gui패키지 (html처럼 컴포넌트 배치가능)
        // (html 처럼 컴포넌트 배치가능)

    }
}
