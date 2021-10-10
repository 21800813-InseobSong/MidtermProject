package com.todo.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList l) {
		
		String title, category, desc, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "========== ������ �߰�");
		System.out.print("������ �Է��ϼ��� > ");
		
		title = sc.nextLine();
		if(l.isDuplicate(title)) {
			System.out.println("������ �ߺ��˴ϴ�!");
			return;
		}
		
		System.out.print("ī�װ��� �Է��ϼ��� > ");
		category = sc.nextLine();
		
		System.out.print("������ �Է��ϼ��� > ");
		desc = sc.nextLine();
		
		System.out.print("�������ڸ� �Է��ϼ��� > ");
		due_date = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc, category, due_date);
		if(l.addItem(t) > 0) {
			System.out.println("�߰��Ǿ����ϴ�.");
		} else {
			System.out.println("�����Ͽ����ϴ�.");
		}
	}

	public static void deleteItem(TodoList l) {

		System.out.println("\n"
				+ "========== ������ ����");
		System.out.print("�����ϰ���� �������� ��ȣ�� �Է��ϼ��� > ");
		
		Scanner sc = new Scanner(System.in);
		int index = sc.nextInt();
		if(l.deleteItem(index) > 0) {
			System.out.println("�����Ǿ����ϴ�.");
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "========== ������ ����");
		System.out.print("�����ϰ� ���� �������� ��ȣ�� �Է��ϼ��� > ");
		
		int index = sc.nextInt();

		System.out.print("���ο� ������ �Է��ϼ��� > ");
		String new_title = sc.nextLine().trim();
		new_title = sc.nextLine().trim();
		
		System.out.print("���ο� ī�װ��� �Է��ϼ��� > ");
		String new_category = sc.nextLine();
		
		System.out.print("���ο� ������ �Է��ϼ��� > ");
		String new_description = sc.nextLine().trim();
		
		System.out.print("���ο� �������ڸ� �Է��ϼ��� > ");
		String new_duedate = sc.nextLine();
		TodoItem item = new TodoItem(new_title, new_description, new_category, new_duedate);
		item.setId(index);
		if(l.updateItem(item) > 0) {
			System.out.println("�����Ǿ����ϴ�.");
		}
	}
	
	/*public static void findItem(TodoList l, String keyword) {
		int count = 0;
		for(TodoItem item : l.getList()) {
			if(item.getTitle().contains(keyword) || item.getDesc().contains(keyword)) {
				System.out.println((l.indexOf(item) + 1) + ". [" + item.getCategory() + "] " + item.getTitle() + " - " + item.getDesc() + " - " + item.getDue_date() + " - " +  item.getCurrent_date());
				count++;
			}
		}
		if(count != 0) {
			System.out.println("�� " + count + "���� �׸��� ã�ҽ��ϴ�.");
		} else {
			System.out.println("�׸��� ã�� �� �����ϴ�.");
		}
	}*/
	
	public static void findList(TodoList l, String keyword) {
		int count = 0;
		for(TodoItem item : l.getList(keyword)) {
			System.out.println(item.toString());
			count++;
		}
		if(count != 0) {
			System.out.println("�� " + count + "���� �׸��� ã�ҽ��ϴ�.");
		} else {
			System.out.println("�׸��� ã�� �� �����ϴ�.");
		}
	}
	
	public static void findCate(TodoList l, String keyword) {
		int count = 0;
		for(TodoItem item : l.getList(keyword)) {
			System.out.println(item.toString());
			count++;
		}
		if(count != 0) {
			System.out.println("�� " + count + "���� �׸��� ã�ҽ��ϴ�.");
		} else {
			System.out.println("�׸��� ã�� �� �����ϴ�.");
		}
	}
	
	public static void findCateList(TodoList l, String cate) {
		int count = 0;
		for(TodoItem item : l.getListCategory(cate)) {
			System.out.println(item.toString());
			count++;
		}
		if(count != 0) {
			System.out.println("�� " + count + "���� �׸��� ã�ҽ��ϴ�.");
		} else {
			System.out.println("�׸��� ã�� �� �����ϴ�.");
		}
	}
	
	public static void listCate(TodoList l) {
		HashSet<String> hs = new HashSet<String>();
		for (TodoItem item : l.getList()) {
			hs.add(item.getCategory());
		}
		Iterator iter = hs.iterator();
		while(iter.hasNext()) {
			System.out.print(iter.next() + " ");
			if(iter.hasNext()) {
				System.out.print("/ ");
			}
		}
		System.out.println("\n�� " + hs.size() + "���� ī�װ��� ��ϵǾ� �ֽ��ϴ�.");
	}
	
	public static void listCateAll(TodoList l) {
		int count = 0;
		for(String item : l.getCategories()) {
			System.out.print(item + " ");
			count++;
		}
		System.out.println("\n�� " + count + "���� ī�װ��� ��ϵǾ� �ֽ��ϴ�.");
	}
	
	public static void listAll(TodoList l) {
		System.out.println("[��ü ���, �� " + l.getCount() + "��]");
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}

	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.println("[��ü ���, �� " + l.getCount() + "��]");
		for (TodoItem item : l.getOrderedList(orderby, ordering)) {
			System.out.println(item.toString());
		}
	}
	
	public static void listAll(TodoList l, int key) {
		int count = 0;
		for(TodoItem item : l.getList(key)) {
			System.out.println(item.toString());
			count++;
		}
		if(count != 0) {
			System.out.println("�� " + count + "���� �׸��� �Ϸ�Ǿ����ϴ�.");
		}
	}
	
	public static void completeItem(TodoList l, int key) {
		l.completeItem(key);
	}
	
	public static void saveList(TodoList l, String filename) {
		try {
			Writer w = new FileWriter(filename);
			for(TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			System.out.println("�������� �����Ͽ����ϴ�.");
			w.close();
		} catch(IOException e) {
			System.out.println("������ ���忡 �����߽��ϴ�.");
		}
	}
	
	public static void loadList(TodoList l, String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String oneline;
			int count  = 0;
			while((oneline = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(oneline, "##");
				String category = st.nextToken();
				String title = st.nextToken();
				String desc = st.nextToken();
				String due_date = st.nextToken();
				String current_date = st.nextToken();
				TodoItem t = new TodoItem(title, desc, category, due_date);
				t.setCurrent_date(current_date);
				l.addItem(t);
				count++;
			}
			System.out.println(count + "���� �������� �о����ϴ�.");
			br.close();
		} catch(IOException e) {
			System.out.println(filename + " ������ �����ϴ�.");
		}
	}

	public static void listAll(int i) {
		// TODO Auto-generated method stub
		
	}
}
