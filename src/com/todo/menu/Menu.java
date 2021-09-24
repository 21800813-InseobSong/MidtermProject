package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("1. 새로운 아이템을 추가합니다 ( add )");
        System.out.println("2. 아이템 하나를 삭제합니다 ( del )");
        System.out.println("3. 아이템을 수정합니다  ( edit )");
        System.out.println("4. 모든 아이템을 표시합니다 ( ls )");
        System.out.println("5. 이름 순서대로 정렬합니다 ( ls_name_asc )");
        System.out.println("6. 이름 역순으로 정렬합니다 ( ls_name_desc )");
        System.out.println("7. 시간 순서대로 정렬합니다 ( ls_date )");
        System.out.println("8. 시간 역순으로 정렬합니다 ( ls_date_desc )");
        System.out.println("9. 카테고리 목록을 표시합니다 ( ls_cate )");
        System.out.println("10. 제목이나 내용을 찾습니다 ( find 키워드 )");
        System.out.println("11. 카테고리를 찾습니다 ( find_cate 키워드 )");
        System.out.println("12. 종료 ( exit )");
    }

	public static void prompt() {
        System.out.print("\n명령어를 입력해주세요 ( 도움말 - help) > ");
	}
}
