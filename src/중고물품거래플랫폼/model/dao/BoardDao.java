package 중고물품거래플랫폼.model.dao;

import 중고물품거래플랫폼.model.dto.BoardDto;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;

public class BoardDao {
    // 싱글톤 생성
    private BoardDao(){connect();}
    private static final BoardDao instance = new BoardDao();
    public static BoardDao getInstance(){return instance;}

    // 데이터베이스 연동 구성한다.
    // 1) 연동할 db 서버의 정보
    private String url = "jdbc:mysql://localhost:3306/boardservice";
    private String user = "root"; private String password = "1234";
    // 2) 연동 인터페이스 변수 선언
    private Connection conn;
    // 3) 연동 함수 정의 , dao에 생성자에서 함수 실행(dao싱글톤이 생성되면서 db연동 실행)
    private void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // mysql 라이브러리 객체 메모리 할당/불러오기
            conn = DriverManager.getConnection(url, user, password); // mysql 구현체로 db연동 후 연동 인터페이스에 반환
            System.out.println("[시스템준비] 데이터베이스 연동 성공");
        } catch (Exception e) {
            System.out.println("[시스템경고] 데이터베이스 연동 실패:관리자에게 문의하십시오.");
        }
    } // m END


    // [1] 중고 물품 등록(Create) dao
    public boolean create(String writer, String pname , String pcontent ,
                          int pprice , String pwd , String phone ){
        try {
            // 1) SQL 작성한다. ? : 와일드카드 기호로 변수값이 들어갈 자리를 뜻한다.
            String sql = "insert into board(writer,pname,pcontent,pprice,pwd,phone) values(?,?,?,?,?,?)";
            // 2) 연동된(Conn) 인터페이스에 내가 작성한 sql 기재한다. 반환 preparedStatement 준비된
            PreparedStatement ps = conn.prepareStatement(sql);
            // 3) SQL이 기재된(ps) 인터페이스에서 sql 매개변수를 대입한다. // ps.set타입명( ?순서번호 , 값 );
            ps.setString(1, writer); // String 타입으로 sql내 1번 ?에 writer 값 대입한다.
            ps.setString(2, pname);
            ps.setString(3, pcontent);
            ps.setInt(4, pprice);
            ps.setString(5, pwd);
            ps.setString(6, phone);
            // 4) 매개변수까지 대입하여 준비가 끝났으면 sql 실행 , ps.executeUpdate(); : 반영된 레코드수
            int count = ps.executeUpdate();
            // 5) sql 실행 후 반영된 레코드 수에 따른 결과 제어
            if (count == 1) {
                return true;
            } // 등록한 레코드수가 1이면 등록 성공
            else {
                return false;
            } // 아니면 등록 실패
    }catch ( SQLException e ){
            System.out.println("[시스템오류] SQL 문법 문제 발생 : "+e);
        }
        return false; // 실패

    } // f END

    // [2] 전체 물품 목록 조회(Read) dao
    public ArrayList<BoardDto> read() {
        ArrayList<BoardDto> boards = new ArrayList<>(); // [*] 조회 결과인 레코드(dto)들을 저장할 리스트/배열 선언
        try{ String sql = "select * from board"; // 1] SQL 작성한다.
        PreparedStatement ps = conn.prepareStatement(sql); // 2] SQL 기재한다.
        // 3] SQL 매개변수 대입한다. ? 없으므로 생략
        // 4] 실행 후 몇개 조회했는지가 아닌 조회 결과 테이블 제어
        ResultSet rs = ps.executeQuery();
        // executeUpdate():insert/update/delete vs executeQuery() : select
        // ResultSet : select 결과물을 제어 하는 인터페이스 ,
        // rs.next() : 조회 결과에서 다음 레코드 1번 이동 , 만약에 레코드가 10개이면 next 10번
        while (rs.next()) { // while(논리){} 반복문 , * 레코드 1개씩 순회 * 1개 레코드 -> 1개 DTO
            // rs.get타입명(SQL 속성명) : 현재 레코드의 속성값 호출
            int bno = rs.getInt("bno");
            String writer = rs.getString("writer");
            String pname = rs.getString("pname");
            String pcontent = rs.getString("pcontent");
            int pprice = rs.getInt("pprice");
            String pwd = rs.getString("pwd");
            String phone = rs.getString("phone");
            String datetime = rs.getString("datetime");
            boolean forsale = rs.getBoolean("forsale");
            BoardDto boardDto = new BoardDto(bno,writer,pname,pcontent,pprice,pwd,phone,datetime,forsale); // DTO(객체) 만들기
            boards.add(boardDto); // 리스트(배열)에 생성한 DTO(레코드) 저장
        } // while END
    }catch(SQLException e){
        System.out.println("[시스템 오류] SQL 문법 문제 발생 : "+e);}
    return boards; // 리스트(배열) 반환한다.
    } // m END


    }
