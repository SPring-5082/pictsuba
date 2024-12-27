package test.beans;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import beans.Auto_Increment;

class TestAuto_Increment {

	@Test
	void testAll() {
		Auto_Increment ai = new Auto_Increment();
		for(int i = 1;i < 10; i ++) {
			assertEquals(i, ai.next());
			System.out.println(i);
		}
	}

}
