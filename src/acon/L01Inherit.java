package acon;

class A{// A.java 문서를 만드는 것과 같지만 배포할 수 없다.
    public void a(){
        System.out.println("A,a입니다.");
    }
}

class B extends A{
    //빨간줄: 컴파일 에러
    public void b(){}
    @Override // 어노테이션 : 컴파일 시 동작하며 검사 및 세팅을 한다.
    public void a(){
        System.out.println("B,b입니다.");
    }
}

class C extends B{
    public void c(){}
    public void c(int a){} //오버로드 : 함수의 이름은 같은데 매개변수가 달라서(타입과 수) 다르게 동작
}




public class L01Inherit { //java 문서의 주인이면서 배포가능한 클래스
    public L01Inherit() {
        A a= new A(); // 내부 클래스를 정의하는 이유! (배포될 필요는 없고 내부적으로만 정의하는 클래스)
    }
    class A{}


    public static void main(String[] args) {

        //내부 클래스 (inner class)로 L01Inherit&A,class 로 컴파일 된다.
        //부모 클래스를 인스턴스로 생성해야 내부 클래스를 생성할 수 있다.

        //public class : 임폴트 가능한 클래스. java 문서의 주인 (발행되는)
        //java 컴파일이란? (JavaDevelopmentKit): java 문서를 class 파일로 변환
        //JVM : class 파일을 플랫폼에 맞게 해석해서 실행하는 가상 머신(자바는 플랫폼에 독립적이다.)
        //L01Inherit.java를 컴파일하면 생기는 .class 문서는 몇개일까?

//        L01Inherit.A a = new A();
        L01Inherit.A inA = new L01Inherit().new A();
        // 보통 이렇게 사용되지 않고 부모내부에서 내부클래스를 인스턴스로 생성해서 작업한다.

        C c = new C();
        B b = c;
        acon.A a= c;
        Object o = c;
        //타입의 다형성 : 객체가 부모의 타입이 될 수 있는 것

        //객체지향 문법의 상속은 무엇인가요? : 상속받은 부모의 재원을 사용하거나 재정의(오버라이드) 하는 것

        //객체 재사용의 전략? : 추상화, 모듈화

        //객체지향 문법에서 다형성은 무엇인가요? (1개의 이름이 어러가지 일을 하는 것)
        //다형성이란 프로그램 언어 각 요소(식 메소드 매개변수 등)들이 다양한 자료형(type)에 속하는것이 허가되는 성질을 가르킨다.
        //객체지향에서 다형성은 이름 한개에 다양한 역할을 부여해서 코드 작성을 편리하게 하는 문법
        //이중에 오버로드, 오버라이드. 타입의 다형성이 있다.
        //오버로드는 함수의 이름은 1개인데 매개변수를 다르게 지정해서 이름짓기를 편리하게 한것
        //오버라이드는 부모의 매서드를 자식이 재정의 해서 재정의한 자식 매서드가 호출되게 하는 것
        //객체를 부모의 타입으로 참조할 수 있어서 변수의 타입을 복잡하게 정의할 필요가 없다. -> 캐스팅 형변환 시 오류를 야기할 수 있다. -> 제네릭이 등장


        Object iObject = "13";
        int i = (int)iObject;


    }
}
