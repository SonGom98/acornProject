package acon;

interface InterTest{
        void a();
}
@FunctionalInterface // 추상메소드가 오직 1개 있는 인터페이스에 정의가능
    // 람다식으로 구현을 대신할 수 있다.(익명 클래스 생성이 안된다.)
interface InterTest2{
    void a();
}

class InterTestImpl implements  InterTest,InterTest2{
    @Override
    public void a() {

    }
}

public class L03Interface {
    public static void main(String[] args) {
        //인터페이스 : 엄청 추상화된 설계도 (이렇게 이렇게 하세요~) ,
        //설계도를 추상화 하는 이유 :
        // 설계도 모두 구체화 되어 있으면 파악하기 어렵다.
        // 추상화된 설계도를 재사용하기 용이하다. => 유지 보수가 용이
        // 다른 설계도와 중복되어도 문제되지 않는다.

        //InterTest1 interTest1 = new InterTest1();
        //설계도를 객체로 생성할 수 없다.
        InterTestImpl interTest = new InterTestImpl(); //1

        // 인터페이스나 추상클래스를 객체로 생성하려면 인스턴스 생성과 동시에 구현하면 된다.
        //클래스를 만들고 추상메서드를 구현하는 행위를 생략해도 jvm이 자동으로 익명클래스를 만들어서 구현함
        InterTest interTest1 = new InterTest() {
            @Override
            public void a() {

            }
        }; //2

        //jvm이 자동으로 하는 일
        //L03Interface 의 내부클래스로 1을 생성(익명 클래스)
        //class 1 implement intertest{public void a(){}}

        //람다식 (문법적 설탕 : 문법만존재하고 실존하지 않는다.)
        //추상메서드가 1개 있는 인터페이스에 @FunctionalInterface 명시하면 작성 가능 (1,2와 동일)
        InterTest2 interTest2 = ()->{

        }; //a함수
    }
}
