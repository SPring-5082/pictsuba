package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class ArrayLogic {
	
	public static String encode(int[] array) {
		return Base64.getEncoder().encodeToString(Arrays.toString(array).getBytes());
	}
	
	public static int[] decode(String encodeText) {
		String decode = new String(Base64.getDecoder().decode(encodeText));
		decode = decode.replace('[', ' ').replace(']', ' ');
		String[] array = decode.split(",");
		int[] id_array = new int[array.length];
		for(int i = 0;i < array.length;i ++) {
			try {
				id_array[i] = Integer.parseInt(array[i].trim());
			}catch (Exception e) {
				return new int[]{};
			}
		}
		return id_array;
	}
	/**
	 * 配列から最初の削除対象値を削除した配列を返す
	 * 対象地がない場合、削除しない
	 * @param array対象配列
	 * @param num 削除値
	 * @return 削除後の配列
	 */
	public static int[] delete(int[] target,int num) {
		if(!exisits(target, num))return target;
		List<Integer> list = new ArrayList<>();
		for(int i : target) {
			if(i != num)list.add(i);
		}
		int[] array = new int[list.size()];
		for(int i = 0;i < array.length;i ++) {
			array[i] = list.get(i);
		}
		return array;
	}
	
	public static boolean exisits(int[] array, int num) {
		for(int i : array) {
			if(i == num) return true;
		}
		return false;
	}
	
	public static int[] addEnd(int[] src, int num) {
		if(src.length == 0 || src == null)return new int[]{num};
		int length = src.length;
		int[] array = new int[length+1];
		for(int i = 0;i < length;i ++) {
			array[i] = src[i];
		}
		array[length] = num;
		return array;
	}
	
}
