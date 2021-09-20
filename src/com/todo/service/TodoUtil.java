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
				+ "========== 아이템 추가\n"
				+ "제목을 입력하세요\n");
		
		title = sc.nextLine();
		if (list.isDuplicate(title)) {
			System.out.printf("제목은 중복입력할 수 없습니다");
			return;
		}
		
		System.out.println("설명을 입력하세요");
		desc = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {

		System.out.println("\n"
				+ "========== 아이템 삭제\n"
				+ "삭제하고싶은 아이템의 제목을 입력하세요\n"
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
				+ "========== 아이템 수정\n"
				+ "수정하고 싶은 아이템의 제목을 입력하세요\n"
				+ "\n");
		String title = sc.nextLine().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("존재하지 않는 제목입니다");
			return;
		}

		System.out.println("새로운 아이템의 제목을 입력하세요");
		String new_title = sc.nextLine().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("제목은 중복입력할 수 없습니다");
			return;
		}
		
		System.out.println("설명을 입력하세요 ");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("아이템이 수정되었습니다");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("[전체 목록]");
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
			System.out.println("아이템을 저장하였습니다.");
			w.close();
		} catch(IOException e) {
			System.out.println("아이템 저장에 실패했습니다.");
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
			System.out.println(count + "개의 아이템을 읽었습니다.");
			br.close();
		} catch(IOException e) {
			System.out.println(filename + " 파일이 없습니다.");
		}
	}
}
