package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("1. ���ο� �������� �߰��մϴ� ( add )");
        System.out.println("2. ������ �ϳ��� �����մϴ� ( del )");
        System.out.println("3. �������� �����մϴ�  ( edit )");
        System.out.println("4. ��� �������� ǥ���մϴ� ( ls )");
        System.out.println("5. �̸� ������� �����մϴ� ( ls_name_asc )");
        System.out.println("6. �̸� �������� �����մϴ� ( ls_name_desc )");
        System.out.println("7. �ð� ������� �����մϴ� ( ls_date )");
        System.out.println("8. �ð� �������� �����մϴ� ( ls_date_desc )");
        System.out.println("9. ī�װ� ����� ǥ���մϴ� ( ls_cate )");
        System.out.println("10. �����̳� ������ ã���ϴ� ( find Ű���� )");
        System.out.println("11. ī�װ��� ã���ϴ� ( find_cate Ű���� )");
        System.out.println("12. ���� ( exit )");
    }

	public static void prompt() {
        System.out.print("\n��ɾ �Է����ּ��� ( ���� - help) > ");
	}
}
