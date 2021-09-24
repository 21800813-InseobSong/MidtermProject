package com.todo.dao;

import java.util.*;

import com.todo.service.TodoSortByDate;
import com.todo.service.TodoSortByName;

public class TodoList {
	private List<TodoItem> list;

	public TodoList() {
		this.list = new ArrayList<TodoItem>();
	}

	public void addItem(TodoItem t) {
		list.add(t);
	}

	public void deleteItem(int index) {
		list.remove(index);
	}

	void editItem(TodoItem t, TodoItem updated) {
		int index = list.indexOf(t);
		list.remove(index);
		list.add(updated);
	}
	
	public int size() {
		return list.size();
	}

	public ArrayList<TodoItem> getList() {
		return new ArrayList<TodoItem>(list);
	}
	
	public TodoItem get(int index) {
		return list.get(index);
	}

	public void sortByName() {
		Collections.sort(list, new TodoSortByName());
	}

	public void listAll() {
		System.out.println("\n"
				+ "모든 아이템을 표시합니다\n");
		for (TodoItem myitem : list) {
			System.out.println(myitem.getTitle() + myitem.getDesc());
		}
	}
	
	public void reverseList() {
		Collections.reverse(list);
	}

	public void sortByDate() {
		Collections.sort(list, new TodoSortByDate());
	}

	public int indexOf(TodoItem t) {
		return list.indexOf(t);
	}

	public Boolean isDuplicate(String title) {
		for (TodoItem item : list) {
			if (title.equals(item.getTitle())) return true;
		}
		return false;
	}
}
