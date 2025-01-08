package model;

import java.util.Arrays;
import java.util.Base64;

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
			id_array[i] = Integer.parseInt(array[i].trim());
		}
		return id_array;
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
