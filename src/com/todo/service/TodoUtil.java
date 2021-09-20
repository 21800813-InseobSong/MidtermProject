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
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "========== ������ �߰�\n"
				+ "������ �Է��ϼ���\n");
		
		title = sc.nextLine();
		if (list.isDuplicate(title)) {
			System.out.printf("������ �ߺ��Է��� �� �����ϴ�");
			return;
		}
		
		System.out.println("������ �Է��ϼ���");
		desc = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {

		System.out.println("\n"
				+ "========== ������ ����\n"
				+ "�����ϰ���� �������� ������ �Է��ϼ���\n"
				+ "\n");
		
		Scanner sc = new Scanner(System.in);
		
		String title = sc.nextLine();
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "========== ������ ����\n"
				+ "�����ϰ� ���� �������� ������ �Է��ϼ���\n"
				+ "\n");
		String title = sc.nextLine().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("�������� �ʴ� �����Դϴ�");
			return;
		}

		System.out.println("���ο� �������� ������ �Է��ϼ���");
		String new_title = sc.nextLine().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("������ �ߺ��Է��� �� �����ϴ�");
			return;
		}
		
		System.out.println("������ �Է��ϼ��� ");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("�������� �����Ǿ����ϴ�");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("[��ü ���]");
		for (TodoItem item : l.getList()) {
			System.out.println("[" + item.getTitle() + "] " + item.getDesc() + " - " + item.getCurrent_date());
		}
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
				String title = st.nextToken();
				String desc = st.nextToken();
				String date = st.nextToken();
				TodoItem t = new TodoItem(title, desc);
				t.setCurrent_date(date);
				l.addItem(t);
				count++;
			}
			System.out.println(count + "���� �������� �о����ϴ�.");
			br.close();
		} catch(IOException e) {
			System.out.println(filename + " ������ �����ϴ�.");
		}
	}
}
