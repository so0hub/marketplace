package 중고물품거래플랫폼.model.dto;

import java.time.LocalDateTime;

public class BoardDto {
    // DTO 구성하기
    // 멤버변수 : 데이터베이스의 속성명과 일치. +기능에 따라 추가
    private int bno;
    private String writer;
    private String pname;
    private String pcontent;
    private int pprice;
    private String pwd;
    private String phone;
    private String dateTime;
    private boolean forsale = true;



    public BoardDto(int bno, String writer, String pname, String pcontent, int pprice, String pwd, String phone, String dateTime, boolean forsale) {
        this.bno = bno;
        this.writer = writer;
        this.pname = pname;
        this.pcontent = pcontent;
        this.pprice = pprice;
        this.pwd = pwd;
        this.phone = phone;
        this.dateTime = dateTime;
        this.forsale = forsale;
    }

    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPcontent() {
        return pcontent;
    }

    public void setPcontent(String pcontent) {
        this.pcontent = pcontent;
    }

    public int getPprice() {
        return pprice;
    }

    public void setPprice(int pprice) {
        this.pprice = pprice;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isForsale() {
        return forsale;
    }

    public void setForsale(boolean forsale) {
        this.forsale = forsale;
    }

    // true false -> 판매중 판매완료 문자열로 변환시켜주는 메서드

    public String getForsaleStatus(){return this.forsale ? "판매중" : "판매완료";}


    @Override
    public String toString() {
        return "BoardDto{" +
                "bno=" + bno +
                ", writer='" + writer + '\'' +
                ", pname='" + pname + '\'' +
                ", pcontent='" + pcontent + '\'' +
                ", pprice=" + pprice +
                ", pwd='" + pwd + '\'' +
                ", phone='" + phone + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", forsale=" + forsale +
                '}';
    }

}
