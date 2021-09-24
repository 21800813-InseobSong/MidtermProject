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
		
		String title, category, desc, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "========== 아이템 추가");
		System.out.print("제목을 입력하세요 > ");
		
		title = sc.nextLine();
		if (list.isDuplicate(title)) {
			System.out.printf("제목은 중복입력할 수 없습니다");
			return;
		}
		
		System.out.print("카테고리를 입력하세요 > ");
		category = sc.nextLine();
		
		System.out.print("설명을 입력하세요 > ");
		desc = sc.nextLine();
		
		System.out.print("마감일자를 입력하세요 > ");
		due_date = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc);
		t.setCategory(category);
		t.setDue_date(due_date);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {

		System.out.println("\n"
				+ "========== 아이템 삭제");
		System.out.print("삭제하고싶은 아이템의 번호를 입력하세요 > ");
		
		Scanner sc = new Scanner(System.in);
		
		int index = sc.nextInt();
		
		TodoItem item = l.get(index-1);
		System.out.println(index + ". [" + item.getCategory() + "] " + item.getTitle() + " - " + item.getDesc() + " - " + item.getDue_date() + " - " +  item.getCurrent_date());
		System.out.print("위 항목을 삭제하시겠습니까? (y/n) > ");
		String choose = sc.nextLine();
		choose = sc.nextLine();
		if(choose.equalsIgnoreCase("y")) {
			l.deleteItem(index-1);
			System.out.println("삭제되었습니다.");
		} else {
			System.out.println("취소되었습니다.");
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "========== 아이템 수정");
		System.out.print("수정하고 싶은 아이템의 번호를 입력하세요 > ");
		
		int index = sc.nextInt();

		System.out.print("새로운 제목을 입력하세요 > ");
		String new_title = sc.nextLine().trim();
		new_title = sc.nextLine().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("제목은 중복입력할 수 없습니다");
			return;
		}
		
		System.out.print("새로운 카테고리를 입력하세요 > ");
		String new_category = sc.nextLine();
		
		System.out.print("새로운 설명을 입력하세요 > ");
		String new_description = sc.nextLine().trim();
		
		System.out.print("새로운 마감일자를 입력하세요 > ");
		String new_duedate = sc.nextLine();
		
		l.deleteItem(index);
		TodoItem item = new TodoItem(new_title, new_description);
		item.setCategory(new_category);
		item.setDue_date(new_duedate);
		l.addItem(item);
		System.out.println("수정되었습니다.");
	}
	
	public static void findItem(TodoList l, String keyword) {
		String[] key = keyword.split(" ");
		int count = 0;
		for(TodoItem item : l.getList()) {
			if(item.getTitle().contains(key[1]) || item.getDesc().contains(key[1])) {
				System.out.println((l.indexOf(item) + 1) + ". [" + item.getCategory() + "] " + item.getTitle() + " - " + item.getDesc() + " - " + item.getDue_date() + " - " +  item.getCurrent_date());
				count++;
			}
		}
		if(count != 0) {
			System.out.println("총 " + count + "개의 항목을 찾았습니다.");
		} else {
			System.out.println("항목을 찾을 수 없습니다.");
		}
	}
	
	public static void findCate(TodoList l, String keyword) {
		String[] key = keyword.split(" ");
		int count = 0;
		for(TodoItem item : l.getList()) {
			if(item.getCategory().contains(key[1])) {
				System.out.println((l.indexOf(item) + 1) + ". [" + item.getCategory() + "] " + item.getTitle() + " - " + item.getDesc() + " - " + item.getDue_date() + " - " +  item.getCurrent_date());
				count++;
			}
		}
		if(count != 0) {
			System.out.println("총 " + count + "개의 항목을 찾았습니다.");
		} else {
			System.out.println("항목을 찾을 수 없습니다.");
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
		System.out.println("\n총 " + hs.size() + "개의 카테고리가 등록되어 있습니다.");
	}

	public static void listAll(TodoList l) {
		System.out.println("[전체 목록, 총 " + l.size() + "개]");
		for (TodoItem item : l.getList()) {
			System.out.println((l.indexOf(item) + 1) + ". [" + item.getCategory() + "] " + item.getTitle() + " - " + item.getDesc() + " - " + item.getDue_date() + " - " +  item.getCurrent_date());
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
				String category = st.nextToken();
				String title = st.nextToken();
				String desc = st.nextToken();
				String due_date = st.nextToken();
				String current_date = st.nextToken();
				TodoItem t = new TodoItem(title, desc);
				t.setCategory(category);
				t.setDue_date(due_date);
				t.setCurrent_date(current_date);
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
