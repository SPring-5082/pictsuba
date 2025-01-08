package test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import model.EncryptionLogic;
import model.KeyStorage;

public class Main {
	public static void main(String[] args) throws IllegalBlockSizeException, BadPaddingException {
		KeyStorage.init("O52HO0G5C3NtkegLeQE0Kg==", "jxCxl18YNgUEY97WEhbE/Q==");
		System.out.println(EncryptionLogic.enc("Takatori5082"));
	}
}
