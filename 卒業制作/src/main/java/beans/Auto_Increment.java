package beans;

/**
 * @author 高鳥
 */
public class Auto_Increment {
	/***/
	private int counter;
	
	/**
	 * 初期値を0とするコンストラクタ
	 */
	public Auto_Increment() {
		this.counter = 0;
	}
	/**
	 * 引数値を初期値とするコンストラクタ
	 * @param counter 初期値に設定する値
	 */
	public Auto_Increment(int counter) {
		this.counter = counter;
	}
	
	/**
	 * counterをインクリメントした値を返すメソッド
	 * @return counterの次の値
	 */
	public int next() {
		counter ++;
		return counter;
	}
	
}
