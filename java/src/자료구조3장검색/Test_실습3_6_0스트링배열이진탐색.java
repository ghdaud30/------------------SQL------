package 자료구조3장검색;

//3장 - 1번 실습 과제 > 2번 실습: 스트링 객체의 정렬과 이진 탐색 > 3번 실습: 객체 정렬과 이진 탐색
//comparator 구현 실습
import java.util.Arrays;

public class Test_실습3_6_0스트링배열이진탐색 {

	static void showData(String[] data) {
		System.out.println("data is");
		for (int i =0; i < data.length; i++) {
			System.out.println(data[i]);
		}
	}
	
	static void sortData(String[] data) {
		for(int i =0; i < data.length; i ++) {
			for(int j = i + 1; j < data.length; j++) {
				if(data[i].compareTo(data[j]) > 0)
					swap(data,i,j);
			}
		}
		//Arrays.sort(data);
		System.out.println("\nSorting is finished!! \n");
	}
	
	static void swap(String[] data, int i, int j) {
		String tmp = data[i];
		data[i] = data[j];
		data[j] = tmp;
	}
	
	static int linearSearch(String[] data, String key) {
		return 0;
	}
	
	static int binarySearch(String[] data, String key) {
		return 0;
	}
	
	public static void main(String[] args) {
		String []data = {"apple","grape","persimmon", "감", "배", "사과", "포도", "pear","blueberry", "strawberry", "melon", "oriental melon"};

		showData(data);
		sortData(data);
		showData(data);
		
		String key = "감";
		int result = linearSearch(data, key);
		System.out.println("\nlinearSearch(): result = " + result);

		key = "배";
		result = binarySearch(data, key);
		System.out.println("\nbinarySearch(): result = " + result);
		int idx = Arrays.binarySearch(data, key);
		System.out.println("\nArrays.binarySearch(): result = " + idx);

	}


}