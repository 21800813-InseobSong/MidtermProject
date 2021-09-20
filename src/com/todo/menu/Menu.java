package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("1. 새로운 아이템을 추가합니다 ( add )");
        System.out.println("2. 아이템 하나를 삭제합니다 ( del )");
        System.out.println("3. 아이템을 수정합니다  ( edit )");
        System.out.println("4. 모든 아이템을 표시합니다 ( ls )");
        System.out.println("5. 이름순서대로 정렬합니다 ( ls_name_asc )");
        System.out.println("6. 이름의 역순으로 정렬합니다 ( ls_name_desc )");
        System.out.println("7. 시간 순서대로 정렬합니다 ( ls_date )");
        System.out.println("8. 종료 ( exit )");
    }

	public static void prompt() {
        System.out.println("명령어를 입력해주세요 ( 도움말 - help) >");
	}
}
