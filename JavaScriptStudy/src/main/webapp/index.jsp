<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
    <h1> JavaScript 에 대해 배워보자</h1>
    <p>js의 역사 : 브라우저를 동적(브라우저가 이벤트에 반응)으로 만들기 위해 개발된 언어로
        java 가 굉장히 인기 있는 언어라 이름에 java 을 붙여서 자바스크립트가 되었다.
        java 에서 이름사용을 못하게 하여 ECMAScript es로 불린다. es6는 표준화된 버전을 의미한다.
    </p>
    <p>바닐라 js : js로 된 라이브러리나 프레임워크(jquery,react,view ...)을 사용하지않고 순수한 js 문법만 사용</p>
    <p>옛날옛날 10년전에는 바닐라 js로 개발이 불가능 했다. es6로 표준화 되면서 가능해 졌다.(크로스 브라우징)</p>

    <nav>
        <!--ul>(li>a{$})*10-->
        <ul>
            <li><a href="l00_variable_const.html">0.변수(var, let, const)</a></li>
            <li><a href="l01_var_hoisting.html">1.window 필드 var 변수의 hoisting 현상(*)</a></li>
            <li><a href="l02_primitive_type.html">2.기본데이터타입(number, bigint, string, boolean, symbol, null, undefined)</a></li>
            <li><a href="l03_object_type.html">3.자료형 (Object {}, ProtoType)</a></li>
            <li><a href="L04_array_list.html">4.배열 (ArrayList [])</a></li>
            <li><a href="L05_function.html">5.함수</a></li>
            <li><a href="L06_for.html">6.반복문 (for, 내부반복문, iterator for)</a></li>
            <li><a href="L07_if.html">7.if, switch</a></li>
            <li><a href="L08_array_iteration.html">8.배열의 내부 반복문(Iteration)과 정렬</a></li>
            <li><a href="L09_document_traveling.html">9.document DOMTree 와 node 객체</a></li>
            <li><a href="L10_event.html">10.event.html</a></li>
            <li><a href="L12_class.html">12.자바스크립트의 클래스 문법(static sugar)</a></li>
            <li><a href="L13_getter_setter.html">13.class의 getter setter 와 캡슐화</a></li>
            <li><a href="L14_emp_list.do">14.자바에서 Dto 사용해보기(Emp.List)</a></li>
            <li><a href="L15_timing_function.html">15.타이밍 함수 setTimeout 와 setInterval(멀티스레드 new Thread)</a></li>
            <li><a href="L16_this_bind.html">16.함수의 binding 과 this와 화살표 함수</a></li>
            <li><a href="L16__bind2.html">16.5바인드 예제2</a></li>
            <li><a href="L17_promise.html">17.Promise 멀티스레드 동기화</a></li>
            <li><a href="L18_promise_chainning.html">18.Promise 체이닝(비동기 코드를 계속 동기화)</a></li>
            <li><a href="L19_ajax_xmlhttprequest.html">19.Ajax와 XMLHttpRequest 객체(front + back)</a></li>
            <li><a href="L20_ajax_fetch.html">20.Ajax 와 fetch api 와 Promise</a></li>
            <li><a href="L20_5_ajax_fetch_exam.html">20.5.ajax예제 과제</a></li>
            <li><a href="L21+async_function.html">21.async 함수</a></li>
            <li><a href="L22_node_append.html">22.node의 생성과 추가</a></li>
            <li><a href="L14_imgSlide1.html">19.이미지 슬라이더 문제1</a></li>
            <li><a href="L15_imgSlide2.html">20.이미지 슬라이더 문제2</a></li>
            <li><a href="example_1.html">21.잡다한거 체크하는 곳</a></li>
        </ul>
    </nav>
    <script>
        //HTML 에서 js의 실행은 DOMTree 와 연관있다.
        //HTML 문서가 브라우저에서 로딩되면서 스크립트 태그가 있거나 실행한다.
        //만약 스크립트 문서가 참조되고 있으면 다운받고 실행한다.
        //alert("안녕하세요 스크립트로 경고창을 생성했습니다!")
        console.log("안녕하세요 스크립트로 경고창을 생성했습니다!");
    </script>

</body>
</html>