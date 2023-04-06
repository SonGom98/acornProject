package acon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.*;

public class L10Lamda {
    static int sum2;


    public static void main(String[] args) {
        //람다식이 무엇인가요? : 추상메서드가 1개있는 인터페이스를 화살표함수를 이용해 객체로 생성하는 것

        //타입 생성 없이 실행 가능한 함수
        //익명 함수를 표현하는 간단한 방법
        //객체지향 문법에서 함수를 매개변수로 사용하기 위한 문법적 설탕 !(람다식)
        //1.객체지향 문법에서 함수를 매개변수로 사용할 수 없나요?
        //  함수는 타입이 아니기 때문에 매개변수로 전달 할 수 없다.
        //2.람다식을 사용한다는 것은 자바가 함수형언어의 특징은 갖는 것이 아닌가요?
        //  아닙니다. 자바가 코드를 줄이기 위해 함수를 매개변수로 보낼 수 있는 척 하는 것 입니다.
        //3.함수형 언어가 무엇인가요?
        //  함수가 타입이되는 언어입니다. 대표적으로 자바스크립트가 있고 프로토타입의 함수형 언어 입니다.
        /*  function  Type(name){
                this.name=name;
            }
            new Type("신동일");
            class Type{
                String name;
                public Type(String name){
                    this.name=name;
                }
            }
            new Type("신동일");
        */

        ActionListener actionListener=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
        ActionListener actionListener1=(event)->{};

        //자바는 타입이 명확한 언어기 때문에 람다식으로 사용하는 인터페이스를 미리 정의해 두었다.(Stream API 에서 대부분 사용됨)
        Function<Integer,String> function = (p)->{return p+"";};
        Consumer<Integer> consumer=(p)->{};
        BiConsumer<Integer,Integer> biConsumer=(p,p1)->{};
        Supplier<String> supplier=()->{return "";};
        Supplier<String> supplier2=()->"";
        BinaryOperator<Integer> sumOperator=(i,i2)->i+i2; //다항연산
        UnaryOperator<Integer> operator=(i)->i*i;//단항연산
        Predicate<String> intPredicate=(s)->{
            boolean p=false;
            try {
                int i=Integer.parseInt(s);
                p=true;
            }catch (Exception e){e.printStackTrace();}
            return p;
        };
        //java lib 개발자는 아주아주 매우매우 게으르다.
        //Runnable(run) : 매개변수와 반환값이 없는 함수 (멀티스레드를 생성할때 정의한다.)
        //Function(apply) : 매개변수와 반환값이 있고 타입이 다른 함수
        //Consumer(accept) : 매개변수만 존재하는 함수
        //Supplier(get) : 리턴만 존재하는 함수 (블럭이 없으면 return 을 자동으로 한다.)
        //BinaryOperator(apply) : 매개변수가 2개고 반환하는 값이 있는 함수로 타입이 모두 같다.(2개의 값을 연산하기 위해 정의)
        //Predicate(test) : 매개변수를 검사한 결과(boolean)를 반환하는 함수
        //(Function,Predicate,Consumer)매개변수가 있는 것들은 Bi를 사용하면 매개변수가 2개인 함수 정의
        //(Double,Int,Long)BinaryOperator : 기본형을 연산의 매개로 사용하는 함수 정의요 (제네릭이 자료형만 정의 가능)
        System.out.println("130+47="+sumOperator.apply(130,47));
        System.out.println(" '삼'은 정수인가요? "+intPredicate.test("삼"));
        System.out.println(" '33'은 정수인가요? "+intPredicate.test("33"));

        String[] strArr={"1","23","삼","101"};
        for(String s:strArr){
            System.out.println("배열의 값은 정수 인가요? "+intPredicate.test(s));
        }
        //Stream API : 내부반복문을 작성해서 반복문을 유지보수하기 유리하도록 작성하기 위해 만들어짐 ???
        //filter(Predicate) : 중간연산으로 검사식을 만족하지 않는 요소를 제외하는 내부 반복문
        //forEach(Consumer) : 최종연산으로 스트림을 소비하는 내부 반복문, 보통 검사할 때 많이 사용

        //함수와 메서드 : 함수는 메서드의 한국말이다. 함수는 수학에서 유래되었고 프로개밍에서는 정의할 수 있는 연산의 단위가 된다.
        //함수와 메서드는 같은 말이다. 함수형 언어에서 함수는 유닛이고 객체지향 언어에서 함수는 유닛의 필드다.
        Arrays.stream(strArr)
                .filter((s)->{
                    try {
                        Integer.parseInt(s);
                        return true;
                    }catch (Exception e){}
                    return false;
                })
                .forEach((s)->{
                    System.out.println(s);
                });

        //Collection(List,Map,Set) 의 메소드를 람다로 재정의 가능
        List<Integer> numList=new ArrayList<Integer>();
        numList.add(77);
        numList.add(7);
        numList.add(777);
        numList.add(7777);
        numList.add(77777);
        int sum=0;
        for (int i=0; i<numList.size(); i++){
            sum+=numList.get(i);
        }
        System.out.println(sum);
        sum=0;
        for(Integer num : numList){
            sum+=num; //num.intValue(); 랩퍼클래스는 연산 시 자동으로 기본형을 반환
        }
        System.out.println(sum);
        //js 같은 생산성이 높은 언어들 내부반복문 사용한다. -> 자바에서도 내부반복문 도입 !
        sum=0;
        //람다식과 익명클래스는 중첩클래스로 정의된 타입으로 지역변수를 참조할 수 없다.
        //하지만 java 가 참조할 수 있도록 도와준다.(컴파일러가 코드변환을 하는 것을 추측)
        //도움에 한계가 있어서 지역변수를 상수로 취급한다.
        numList.forEach((n)->{
            //sum+=n;  //지역변수
            sum2+=n; //필드
        });
        //왜 sum+=n을 할 수 없나요????? :
    }
}
