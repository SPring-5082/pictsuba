package model;

public class DirectoryLogic {
	
	public static boolean safety(String path) {
		if(path == null)return false;
		int count = 0;
		for(char c: path.toCharArray()) {
			if(c == '.')count ++;
		}
		return count <= 1;
	}
	
}
