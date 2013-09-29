package Brainfuck_runner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Stack;

import GUI.Main_Window;

public class BF_Runner {
	static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	static Stack<Integer> deep_of_loop = new Stack<Integer>();
	private static char[] prog_arr;
	static int pos = 0;

	public static void main(String programm) {
		// programm += "+++[>++[!-]<-]!";
		prog_arr = programm.toCharArray();
		// int pos_s=0;
		map.put(pos, 0);
		//start();

	}

	/**
	 * @return 
	 * 
	 */
	public static HashMap<Integer, Integer> start(int beg,int cnt) {
		// pos_s -> текущий символ
		//
		int pos_s;
		if(cnt<=0)cnt=prog_arr.length;
		//for (pos_s = 0; pos_s < prog_arr.length; pos_s++) {
		for (pos_s = beg; pos_s < beg+cnt; pos_s++) {
			try {
				pos_s = big_switch(prog_arr, pos_s);
				beg=pos_s;
				//System.out.println(map);
			} catch (Exception e) {
				//System.err.println(e.getMessage());
			}
		}
		if (pos_s > prog_arr.length) {
			if (!deep_of_loop.empty()) {
				System.err
						.println("Очень вероятна проблема с циклами!!!(лишний открытый цикл)");
			}
			
			System.out.println("Programm is over");
			Main_Window.EndOfProgramm();
		}
		return map;
	}

	private static int big_switch(char[] prog_arr, int pos_s)
			throws InvalidCharacterException, IOException {
		char c = prog_arr[pos_s];
		switch (c) {
		case '<':
			shift_left();
			break;
		case '>':
			shift_right();
			break;
		case '-':
			decrement();
			break;
		case '+':
			increment();
			break;
		case '[':
			begin_of_loop(pos_s);
			break;
		case ']':// [004] [003] [002] [001]
			pos_s = end_of_loop(pos_s);
			break;
		case '!':
//			System.out.println(map);
			break;
		case '.':
			System.out.println((char) (int) map.get(pos) + " : "
					+ (int) map.get(pos));
			break;
		/*
		 * case '\'': char chr; while ( (chr=(char)System.in.read())==0){ ; }
		 * System.out.println(chr+" ++ "+(int)chr); map.remove(pos);
		 * map.put(pos, Integer.parseInt(chr+"")); break;
		 */
		case ',':
			map.remove(pos);
			map.put(pos, System.in.read());
			// System.in.read()
			break;
		default:
			throw (new InvalidCharacterException("ERROR in position " + pos_s
					+ ": Unknown character '" + prog_arr[pos_s] + "'"));
		}
		return pos_s;
	}

	/**
	 * @param pos_s
	 * @return
	 */
	private static int end_of_loop(int pos_s) {
		int val;
		try {
			val = map.get(pos);
			// val--;
			if (val > 0) {
				pos_s = deep_of_loop.peek();
			} else {
				deep_of_loop.pop();
			}
		} catch (Exception e) {
			System.err.println("Обнаружен незакрытый цикл");
		}
		return pos_s;
	}

	/**
	 * @param pos_s
	 */
	private static void begin_of_loop(int pos_s) {
		deep_of_loop.push(pos_s);
	}

	/**
	 * 
	 */
	private static void increment() {
		int val;
		val = map.get(pos);
		val++;
		map.remove(pos);
		map.put(pos, val);
	}

	/**
	 * 
	 */
	private static void decrement() {
		int val;
		val = map.get(pos);
		val--;
		map.remove(pos);
		map.put(pos, val);
	}

	/**
	 * 
	 */
	private static void shift_right() {
		pos++;
		if (!map.containsKey(pos))
			map.put(pos, 0);
	}

	/**
	 * @return
	 */
	private static void shift_left() {
		pos--;
		if (!map.containsKey(pos))
			map.put(pos, 0);
		// return pos;
	}
}
