package com.acorn.javascriptstudy;

import java.util.Date;

/*
+----------+-------------+------+-----+---------+-------+
| Field    | Type        | Null | Key | Default | Extra |
+----------+-------------+------+-----+---------+-------+
| EMPNO    | int         | NO   | PRI | NULL    |       |
| ENAME    | varchar(10) | YES  |     | NULL    |       |
| JOB      | varchar(9)  | YES  |     | NULL    |       |
| MGR      | int         | YES  | MUL | NULL    |       |
| HIREDATE | date        | YES  |     | NULL    |       |
| SAL      | float(7,2)  | YES  |     | NULL    |       |
| COMM     | float(7,2)  | YES  |     | NULL    |       |
| DEPTNO   | int         | YES  | MUL | NULL    |       |
+----------+-------------+------+-----+---------+-------+
* */
public class EmpDto {
    //DB 의 자료를 java 에 처리하기 위해 받아오는 역할
    //이때 db의 type 과 유사한 java 의 type 을 선택해야 한다.
    private int empno; //pk
    private String ename;
    private String job;
    private int mgr; //fk Emp.Empno
    private Date hiredate;
    private double sal;
    private double comm; //is null 기본형은 null을 참조할 수 없다.
    private  int deptno; //is null fk Dept.deptno

    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getMgr() {
        return mgr;
    }

    public void setMgr(int mgr) {
        this.mgr = mgr;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public double getSal() {
        return sal;
    }

    public void setSal(double sal) {
        this.sal = sal;
    }

    public double getComm() {
        return comm;
    }

    public void setComm(double comm) {
        this.comm = comm;
    }

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    @Override
    public String toString() {
        return "com.acorn.javascriptstudy.EmpDto{" +
                "empno=" + empno +
                ", ename='" + ename + '\'' +
                ", job='" + job + '\'' +
                ", mgr=" + mgr +
                ", hiredate=" + hiredate +
                ", sal=" + sal +
                ", comm=" + comm +
                ", deptno=" + deptno +
                '}';
    }
}
