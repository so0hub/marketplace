package 중고물품거래플랫폼.controller;

import 중고물품거래플랫폼.model.dao.BoardDao;
import 중고물품거래플랫폼.model.dto.BoardDto;

import java.util.ArrayList;

public class BoardController {
    // 싱글톤 생성
    private BoardController(){}
    private static final BoardController instance = new BoardController();
    public static BoardController getInstance(){return instance;}

    // controller에서 dao 싱글톤 호출
    private BoardDao bd = BoardDao.getInstance();

    // [1] 중고 물품 등록 (Create)
    public boolean create(String writer, String pname , String pcontent ,
                          int pprice , String pwd , String phone ) {
        boolean result = bd.create(writer, pname, pcontent, pprice, pwd, phone);
        return result;
    }

    // [2] 전체물품 목록 조회(Read)
    public ArrayList<BoardDto> read(){
        ArrayList<BoardDto> result = bd.read();
        return result;
    }
}
