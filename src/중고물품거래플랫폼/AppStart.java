package 중고물품거래플랫폼;

import 중고물품거래플랫폼.view.BoardView;

public class AppStart {
    public static void main(String[] args) {
        // 메인페이지 실행 : 최초로 보여줄 view 메소드를 view 싱글톤을 통해 호출한다.
        BoardView.getInstance().index();
    }
}