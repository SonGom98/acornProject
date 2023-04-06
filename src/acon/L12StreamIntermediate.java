package acon;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class L12StreamIntermediate {
    public static void main(String[] args) {
        int[] intArr = {1, 2, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        //List<Integer> list = List.of(intArr);
        //Collections로 변환해서 사용하지 않는 이유 : 무조건 자료형의 요소만 사용 가능

        // Stream<Integer> stream=Arrays.stream(intArr);
        //기본형 스트림 : IntStream, LongStream, DoubleStream

        //skip(long),limit(long) : 스트림을 잘라내는 연산
        //skip : 건너뛰는 연산
        //limit : 길이만큼 잘라내는 연산


        int[] intArraySkip = Arrays.stream(intArr)
                .skip(3)
                .toArray();
        System.out.println("skip(3) : " + Arrays.toString(intArraySkip));


        int[] intArrayLimit = Arrays.stream(intArr)
                .limit(5)
                .toArray();
        System.out.println("limit(5) : " + Arrays.toString(intArrayLimit));

        int[] intArraySL = Arrays.stream(intArr)
                .skip(4)
                .limit(4)
                .toArray();

        System.out.println("skip(4).limit(4) : " + Arrays.toString(intArraySL));
        Arrays.stream(intArr)
                .skip(3)
                .limit(5)
                .forEach(System.out::println);

        //distinct() : 중복요소 제거 (자료형도 포함)
        // filter(Predicate (e)->Boolean) : ??
        Integer[] intArr2 = {1, 2, 2, null, 3, 3, 4, null, 5, 6, null, 7, 7, 8, 9, 10};

        //Array 의 스트림 반환 : 무조건 Arrays.stream()으로만 변환 가능 (암기!)
        Stream<Integer> intArrStream = Arrays.stream(intArr2);
        List intList2 = intArrStream
                .filter((e) -> e != null)
                .collect(Collectors.toList()); //최종연산 (암기!)
        System.out.println("filter((e)->e!=null) : " + intList2);

        Stream<Integer> intArrStream2 = Arrays.stream(intArr2);
        List intList3 = intArrStream2
                .distinct()
                .filter((e) -> e != null)
                .collect(Collectors.toList());
        System.out.println("위 배열의 중복을 없애고 null을 제거하세요 : " + intList3);

        Stream<Integer> intArrStream3 = Arrays.stream(intArr2);
        //Optional : 결과가 null 일 수 있다고 알려주는 타입
        Optional<Integer> sum = intArrStream3
                .distinct()
                .filter((e) -> e != null)
                .reduce(Integer::sum);

        sum.ifPresent(s -> System.out.println("중복 없애고 null제거 그리고 합 : " + s));

        Stream<Integer> intArrStream4 = Arrays.stream(intArr2);
        Optional<Integer> sum2 = intArrStream4
                .distinct()
                .filter((e) -> e != null)
                .reduce((total, a) -> total + a);
        System.out.println(sum2.get());
        sum2.ifPresent(s -> System.out.println("중복 없애고 null제거 그리고 합 : " + s));


        System.out.println("filter((n)->n!=null&&n>5)");


        Arrays.stream(intArr2)
                .filter((n) -> n != null && n > 5)
                .forEach(System.out::println);

        System.out.println("distinct()"); //null도 중복제거 가능!!
        Arrays.stream(intArr2)
                .distinct()
                .filter((n) -> n != null && n <= 3)
                .forEach(System.out::println);
        //sorted() : 정렬한 Stream을 반환 (Comparator가 정의되어야 사용가능 )

        String[] strArr = {"Car", "ZZ", "Cap", "zz", "ab", "A", "Apple", "aa"};

        // 문자의 정렬 : 유니코드의 번호로
        List<String> strArrSorted = Arrays.stream(strArr)
                .sorted()
                .collect(Collectors.toList());

        System.out.println("sorted() : " + strArrSorted);

        strArrSorted = Arrays.stream(strArr)
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .collect(Collectors.toList());
        System.out.println("sorted(String.CASE_INSENSITIVE_ORDER) : " + strArrSorted);

        Arrays.stream(strArr)
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .forEach(System.out::println);
        System.out.println("sorted().sorted(s.length)");

        Arrays.stream(strArr)
                .sorted()
                .sorted(Comparator.comparing((s) -> s.length()))
                .forEach(System.out::println);
        System.out.println("map((n)->2*n)");
        //map(Function) : 요소를 다른 데이터로 바꾸는 중간 연산
        //mapTo(Int, Double, Long) : 요소를 기본형(값)으로 바꾸고 기본형 스트림을 반환하는 중간 연산
        //IntStream, LongStream,DoubleStream : 기본형 스트림 자료형만을 요소로 사용할 수 있는 컬렉션의 한계를 극복하기 위해 만들어짐
        //기본형(값)을 정의하는 이유 : 수를 정의하는 이유 연산을 위해서 boolean(0,1 1byte 의 기본형으로 1bit 수를 표현) char (유니코드 표 번호)
        //기본형(값) 스트림에 연산에 유용한 최종함수를 제공 (sum,average : reduce 변형)
        //js null=>0, java null=>0 오류;
        String[] strArr2 = {"1", "2", "2", null, "3", "3", "4", null, "5", "6", null, "7", "7", "8", "9", "10"};
        //strArr2 를 기본형 스트림으로 변경하세요
        Optional<Integer> strArr2ToInt = Arrays.stream(strArr2)
                .filter((e) -> e != null)
                .map((e) -> Integer.parseInt(e))
                .reduce((total, a) -> total + a);
        //     .collect(Collectors.toList());
        System.out.println("다음 배열을 정수로 바꾸고 전체의 합을 구하세요! : " + strArr2ToInt.get());
        //기본형이 반환되는 값이 없을때 사용(Optional(Int,Long,Double))
        OptionalDouble sum3 = Arrays.stream(strArr2)
                .filter((e) -> e != null)
//                                .mapToInt((e)->Integer.parseInt(e));
                .mapToInt(Integer::parseInt) //매개변수를 함수가 실행하면서 사용할 때  -> 컴파일러가 상상할 수 있을만큼의 생략
//                .reduce((i1,i2)->i1+i2);
                .average();
        System.out.println("전체의 평균 : " + sum3.getAsDouble());
        Arrays.stream(intArr2)
                .filter((n) -> n != null)
                //.peek(System.out::println)
                .distinct()
                .map((n) -> (double) (2 * n)) //기존의 값을 다른 타입으로 변경 -> 변경된 스트림으로 반환됨
                .forEach(System.out::println);

        Optional<Integer> sum4 = Arrays.stream(strArr2)
                .filter((e) -> e != null)
                .map((e) -> Integer.parseInt(e))
                .reduce((i1, i2) -> i1 + i2);
        System.out.println("정수 합 : " + sum4.get());

        String[] strArr3 = {"1", "2", "2", null, "3", "3", "사", null, "오", "6", null, "칠", "7", "8", "9", "10"};

//        Optional<Integer> sum5 =
                Arrays.stream(strArr3)
                .filter((e) -> e != null)
                        .sorted()
                        .mapToInt((e)->Integer.parseInt(e))
                        .filter((e)-> e>=0 && e<=10 )
                        .forEach(System.out::println);
//        System.out.println("정수 합 : " + sum5.get());

    }
}
