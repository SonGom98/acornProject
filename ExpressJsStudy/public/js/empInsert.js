const empInsertForm = document.forms["empInsertForm"];
empInsertForm.empno.onchange= empnoCheck;
empInsertForm.mgr.onblur= mgrCheck;
empInsertForm.deptno.onblur= deptnoCheck;

    async function empnoCheck (){
    //AJAX (XMLHttpRequest, fetch)
    let val = empInsertForm.empno.value;
    //empno 부모중에 .inputCont가 있으면 가져오겠다
    const parentNode=(empInsertForm.empno.closest(".inputCont"));
    //3글자 이상 수만 입력 가능!!
    if(val.length<3 || isNaN(val)){
        empnoMsg.innerText="3글자 이상의 수만 입력하세요!"
        parentNode.classList.remove("success");
        parentNode.classList.add("error");
        return false;
    }




    let url = "/emp/empnoCheck.do?empno=" + val;
    const res = await fetch(url); //.then((res)=>{return res.json()})
    if (res.status === 200) {
    const obj = await res.json(); //.then((obj)=>{...})
        if(obj.checkId){
            empnoMsg.innerText=obj.emp.ENAME + "님이 사용 중인 사번입니다."
            parentNode.classList.remove("success");
            parentNode.classList.add("error");
        }else {
            empnoMsg.innerText="사용 가능한 사번입니다."
            parentNode.classList.remove("error");
            parentNode.classList.add("success");
            return true;
        }

    }else if(res.status===400){
        this.value="";
        empnoMsg.innerText="정수를 입력하세요"
        parentNode.classList.remove("success");
        parentNode.classList.add("error");
    }
    else {
        alert(res.status+" 오류입니다. 다시 시도")
    }
}

    async function mgrCheck (e){
    let val=empInsertForm.mgr.value;
    if(val==="") {
        mgrMsg.innerText="상사가 null 처리 됩니다."
        return true;
    } //상사가 없는 경우 null 처리

    const parentNode=(empInsertForm.mgr.closest(".inputCont"));
    let url="/emp/empnoCheck.do?empno="+val;
    const res=await fetch(url);
    if(res.status==200){
        const obj=await res.json();
        if(obj.checkId){
            mgrMsg.innerText=obj.emp.ENAME+"님이 상사로 지정됩니다."
            parentNode.classList.remove("error");
            parentNode.classList.add("success");
            return true;
        }else{
            mgrMsg.innerText="존재하지 않는 사원입니다."
            parentNode.classList.remove("success");
            parentNode.classList.add("error");
        }
    }else if(res.status==400){
        this.value="";
        mgrMsg.innerText="정수만 입력하세요!"
        parentNode.classList.remove("success");
        parentNode.classList.add("error");
    }else {
        this.value="";
        mgrMsg.innerText=res.status+" 오류입니다. 다시 시도!"
        parentNode.classList.remove("success");
        parentNode.classList.add("error");
    }
}

    async function deptnoCheck(e){
    let val=empInsertForm.deptno.value;
    if(!val){
        deptnoMsg.innerText="부서가 null 처리 됩니다."
        return true;
    }

    const parentNode=(empInsertForm.deptno.closest(".inputCont"));
    let url="/dept/deptnoCheck.do?deptno="+val;
    const res=await fetch(url);
    if(res.status==200){
        const obj=await res.json();
        if(obj.checkId){
            deptnoMsg.innerText=`${obj.dept.DNAME}(${obj.dept.LOC}) 부서로 배치됩니다.`
            parentNode.classList.remove("error");
            parentNode.classList.add("success");
            return true;
        }else{
            deptnoMsg.innerText="존재하지 않는 부서입니다."
            parentNode.classList.remove("success");
            parentNode.classList.add("error");
        }
    }else if(res.status==400){
        this.value="";
        deptnoMsg.innerText="정수만 입력하세요!"
        parentNode.classList.remove("success");
        parentNode.classList.add("error");
    }else {
        this.value="";
        deptnoMsg.innerText=res.status+" 오류입니다. 다시 시도!"
        parentNode.classList.remove("success");
        parentNode.classList.add("error");
    }
}
empInsertForm.ename.onchange=enameCheck;
function  enameCheck(){
    const parentNode=empInsertForm.ename.closest(".inputCont")
        if(empInsertForm.ename.length<2){
            enameMsg="이름은 2글자 이상 입력하세요!"
            parentNode.classList.remove("success");
            parentNode.classList.add("error");
            return false;
        }else{
            parentNode.classList.remove("error");
            parentNode.classList.add("success");
            return true;
    }
}
empInsertForm.job.onchange=jobCheck;
function  jobCheck(){
    const parentNode=empInsertForm.job.closest(".inputCont")
    if(empInsertForm.job.length<2){
        enameMsg="직책은 2글자 이상 입력하세요!"
        parentNode.classList.remove("success");
        parentNode.classList.add("error");
        return false;
    }else{
        parentNode.classList.remove("error");
        parentNode.classList.add("success");
        return true;
    }
}
empInsertForm.sal.onchange=salCheck;
function  salCheck(){
    let val = empInsertForm.sal.value;
    const parentNode=empInsertForm.sal.closest(".inputCont")
    if(val.trim() && !isNaN(val)){
        salMsg.innerText="";
        parentNode.classList.remove("error");
        parentNode.classList.add("success");
        return true;
    }else{
        salMsg.innerText="급여는 수만 입력 가능합니다.";
        parentNode.classList.remove("success");
        parentNode.classList.add("error");
        return false;
    }
}
empInsertForm.comm.onchange=commCheck;
function commCheck(){
    let val = empInsertForm.comm.value;
    const parentNode=empInsertForm.comm.closest(".inputCont")
    if(!val){
        commMsg.innerText="상여금이 null 처리됩니다."
        return true;
    }
    if(val.trim() && !isNaN(val)){
        commMsg.innerText="";
        parentNode.classList.remove("error");
        parentNode.classList.add("success");
        return true;
    }else{
        commMsg.innerText="상여급은 수만 입력 가능합니다.";
        parentNode.classList.remove("success");
        parentNode.classList.add("error");
        return false;
    }
}
//form submit 버튼을 누르면 무슨 이벤트? form.onsubmit() 이벤트가 발생하면서
//form 양식(input)에 작성한 내역을 액션에 작성한 동적페이지에 제출
//유효성 검사 : 액션페이지에서 처리하지 못하는 값을 미리 검출하고 경고하는 일!
//1.양식의 제출을 막아야한다.
empInsertForm.onsubmit = async (e)=>{
    e.preventDefault(); //이벤트를 막는다!
    //async 함수에서 반환하는 값은 무조건 프라미스화가 된다.
    //return true => return new Promise((res)=>{res(true)});
    let empnoState =await empnoCheck();
    let mgrState =await mgrCheck();
    let deptnoState =await deptnoCheck();
    let enameState = await enameCheck();
    let jobState = await jobCheck();
    let salState = await salCheck();
    let commState = await commCheck();
    if(empnoState && mgrState && deptnoState && enameState && jobState && salState && commState){
        empInsertForm.submit();
    }
}