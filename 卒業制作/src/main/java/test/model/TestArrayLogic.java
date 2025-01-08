package test.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.ArrayLogic;

class TestArrayLogic {
	/*
	@Test
	void test() {
		int[] array = {21,90,4};
		String text = ProductArrayLogic.encode(array);
		int[] array2 = ProductArrayLogic.decode(text);
		assertEquals(array.length, array2.length);
		for(int i = 0;i < array.length;i ++) {
			assertEquals(array[i], array2[i]);
			System.out.println(array[i] + " " + array2[i]);
		}
	}
	*/
	@Test
	void testAdd() {
		int[] array = ArrayLogic.addEnd(new int[] {789}, 1);
		assertEquals(array.length, 2);
		assertEquals(array[0], 789);
		assertEquals(array[1], 1);
		for(int i : array){System.out.println(i);}
	}

}
