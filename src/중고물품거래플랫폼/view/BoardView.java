package 중고물품거래플랫폼.view;

import 중고물품거래플랫폼.controller.BoardController;
import 중고물품거래플랫폼.model.dto.BoardDto;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BoardView {
    // 싱글톤 생성
    private BoardView(){} // 생성자 private
    private static final BoardView instance = new BoardView(); // 현재 객체를 new해서 instance 하나 만든다. private로 막기.
    public static BoardView getInstance(){return instance;} // public으로 해서 해당하는 instace 반환함.

    // view에서 controller 싱글톤 호출
    private BoardController bc = BoardController.getInstance();

    // 메인페이지 화면 구현
    private Scanner scan = new Scanner(System.in);
    public void index() {
        for (; ; ) {
            try {
                System.out.println("===========================Market Place===========================");
                System.out.println("1.중고 물품 등록 2.전체 물품 목록 조회 3.물품 정보 수정 4. 등록 물품 삭제");
                System.out.println("==================================================================");
                System.out.println("선택>");
                int ch = scan.nextInt();
                if (ch == 1) { create();
                } else if (ch == 2) { read();
                } else if (ch == 3) { update();
                } else if (ch == 4) { delete();
                } else {
                    System.out.println("[경고] 없는 기능 번호입니다.");
                }
            } catch (InputMismatchException e) {
                System.out.println("[경고] 잘못된 입력 방식입니다. 재입력하십시오.");
                scan = new Scanner(System.in); // 입력 객체 초기화 ( 잘못된 입력값 제거 )
            } catch (Exception e) {
                // Exception 예외 중 슈퍼클래스로 모든 예외처리 가능
                System.out.println("[시스템오류] 관리자에게 문의하십시오.");
            }
        } // for END
    } // m END

    // [1] 중고 물품 등록(Create) view
    public void create(){
        scan.nextLine();
        System.out.println("이름 : "); String writer = scan.nextLine();
        System.out.println("물품명 : "); String pname = scan.nextLine();
        System.out.println("설명 : "); String pcontent = scan.nextLine();
        System.out.println("가격 : "); int pprice = scan.nextInt();
        System.out.println("비밀번호(등록용) : "); String pwd = scan.next();
        System.out.println("연락처 입력 : "); String phone = scan.next();

        boolean result = bc.create(writer,pname,pcontent,pprice,pwd,phone);
        if(result){
            System.out.println("[안내] 게시물 등록 완료");
        }else{
            System.out.println("[경고] 게시물 등록 실패");
        }

    }

    // [2] 전체 물품 목록 조회(Read)
    public void read(){
        ArrayList<BoardDto> boards = bc.read();
        for(BoardDto board : boards){
            System.out.printf("번호 : %3d , 닉네임 : %s , 물품명 : %s ,물품설명 : %s, 가격 : %3d , 비밀번호 : %s , 연락처 : %s , 등록일 : %s , 판매여부 : %s \n",
                    board.getBno() , board.getWriter(),  board.getPname() , board.getPcontent(), board.getPprice() , board.getPwd(), board.getPhone(), board.getDateTime(), board.getForsaleStatus());
        }

    }

    // [3] 물품 정보 수정(Update)
    public void update(){
        Scanner scan = new Scanner(System.in);
        System.out.println("수정할 게시물 번호 : "); int bno = scan.nextInt();
        System.out.println("수정할 내용 : "); String pcontent = scan.next();
        boolean result = bc.update(bno,pcontent);
        if(result){
            System.out.println("[안내] 물품 정보 수정 완료");}
        else{
            System.out.println("[경고] 물품 정보 수정 실패 또는 없는 게시물 번호입니다.");
        }
    }

    // [4] 등록 물품 삭제(Delete)
    public void delete(){
        System.out.println("삭제할 게시물 번호 : "); int bno = scan.nextInt();
        boolean result = bc.delete(bno);
        if(result){
            System.out.println("[안내] 게시물 삭제 완료");
        }else{
            System.out.println("[경고] 게시물 삭제 실패 또는 없는 게시물 번호입니다.");
        }
    }
} // class END
