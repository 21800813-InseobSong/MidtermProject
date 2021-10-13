package com.todo.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList l) {
		
		String title, category, desc, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "========== 아이템 추가");
		System.out.print("제목을 입력하세요 > ");
		
		title = sc.nextLine();
		if(l.isDuplicate(title)) {
			System.out.println("제목이 중복됩니다!");
			return;
		}
		
		System.out.print("카테고리를 입력하세요 > ");
		category = sc.nextLine();
		
		System.out.print("설명을 입력하세요 > ");
		desc = sc.nextLine();
		
		System.out.print("장소를 입력하세요 > ");
		String place = sc.nextLine();
		
		System.out.print("시작일자를 입력하세요 > ");
		String start_date = sc.nextLine().trim();
		
		System.out.print("마감일자를 입력하세요 > ");
		due_date = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc, category, start_date, due_date);
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
		Date date = new Date();
		t.setCurrent_date(format.format(date));
		t.setPlace(place);
		if(l.addItem(t) > 0) {
			System.out.println("추가되었습니다.");
		} else {
			System.out.println("실패하였습니다.");
		}
	}

	public static void deleteItem(TodoList l) {

		System.out.println("\n"
				+ "========== 아이템 삭제");
		System.out.print("삭제하고싶은 아이템의 번호를 입력하세요 > ");
		
		Scanner sc = new Scanner(System.in);
		String index = sc.nextLine();
		int count = 0;
		String[] deletenum = index.split(",");
		for(int i = 0; i<deletenum.length; i++) {
			int del = Integer.parseInt(deletenum[i].trim());
			count+=l.deleteItem(del);
		}
				
		if(count > 0) {
			System.out.println("삭제되었습니다.");
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
		
		System.out.print("새로운 카테고리를 입력하세요 > ");
		String new_category = sc.nextLine();
		
		System.out.print("새로운 설명을 입력하세요 > ");
		String new_description = sc.nextLine().trim();
		
		System.out.print("새로운 장소를 입력하세요 > ");
		String new_place = sc.nextLine();
		
		System.out.print("새로운 시작일자를 입력하세요 > ");
		String new_startdate = sc.nextLine().trim();
		
		System.out.print("새로운 마감일자를 입력하세요 > ");
		String new_duedate = sc.nextLine();
		
		TodoItem item = new TodoItem(new_title, new_description, new_category, new_startdate, new_duedate);
		item.setId(index);
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
		item.setCurrent_date(format.format(new Date()));
		item.setPlace(new_place);
		if(l.updateItem(item) > 0) {
			System.out.println("수정되었습니다.");
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
			System.out.println("총 " + count + "개의 항목을 찾았습니다.");
		} else {
			System.out.println("항목을 찾을 수 없습니다.");
		}
	}*/
	
	public static void findList(TodoList l, String keyword) {
		int count = 0;
		for(TodoItem item : l.getList(keyword)) {
			System.out.println(item.toString());
			count++;
		}
		if(count != 0) {
			System.out.println("총 " + count + "개의 항목을 찾았습니다.");
		} else {
			System.out.println("항목을 찾을 수 없습니다.");
		}
	}
	
	public static void findCate(TodoList l, String keyword) {
		int count = 0;
		for(TodoItem item : l.getList(keyword)) {
			System.out.println(item.toString());
			count++;
		}
		if(count != 0) {
			System.out.println("총 " + count + "개의 항목을 찾았습니다.");
		} else {
			System.out.println("항목을 찾을 수 없습니다.");
		}
	}
	
	public static void findCateList(TodoList l, String cate) {
		int count = 0;
		for(TodoItem item : l.getListCategory(cate)) {
			System.out.println(item.toString());
			count++;
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
	
	public static void listCateAll(TodoList l) {
		int count = 0;
		for(String item : l.getCategories()) {
			System.out.print(item + " ");
			count++;
		}
		System.out.println("\n총 " + count + "개의 카테고리가 등록되어 있습니다.");
	}
	
	public static void listAll(TodoList l) {
		System.out.println("[전체 목록, 총 " + l.getCount() + "개]");
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}

	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.println("[전체 목록, 총 " + l.getCount() + "개]");
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
			System.out.println("총 " + count + "개의 항목이 완료되었습니다.");
		}
	}
	
	public static void completeItem(TodoList l) {
		System.out.println("\n"
				+ "========== 완료처리");
		System.out.print("완료처리하고싶은 아이템의 번호를 입력하세요 > ");
		
		Scanner sc = new Scanner(System.in);
		String index = sc.nextLine();
		int count = 0;
		String[] updatenum = index.split(",");
		for(int i = 0; i<updatenum.length; i++) {
			int key = Integer.parseInt(updatenum[i].trim());
			count+=l.completeItem(key);
		}
				
		if(count > 0) {
			System.out.println("완료 체크하였습니다.");
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
				String start_date = st.nextToken();
				String current_date = st.nextToken();
				TodoItem t = new TodoItem(title, desc, category, start_date, due_date);
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
