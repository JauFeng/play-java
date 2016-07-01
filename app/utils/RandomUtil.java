package utils;

import java.util.List;
import java.util.Random;

/**
 * 随机数工具
 * 
 * @author crazyjohn
 * 
 */
public class RandomUtil {

	private static Random random = new Random();

	
	public static String createRandomName(String title, long userId){
		String id = userId + "";
		int startIndex = id.length() >= 4 ? id.length() - 4 : 0;
		return title + randomEnChar(2) + id.substring(startIndex);
	}
	
	public static String randomEnChar(int num){
		char[] chars = new char[num];
		for (int i = 0; i < num; i++) {
			if (RandomUtil.randomSwich()){
				chars[i] = (char)RandomUtil.randomRange((int)'a', (int)'z');
			}else {
				chars[i] = (char)RandomUtil.randomRange((int)'A', (int)'Z');
			}
		}
		return new String(chars);
	}
	
	/**
	 * 获取一个范围内的随机值
	 * @param randomMax
	 *            区间结束值
	 * @return 返回区间的一个随机值
	 */
	public static int nextInt(int randomMax) {
		return random.nextInt(randomMax);
	}
	
	/**
	 * 获取一个范围内的随机值
	 * 
	 * @param randomMin
	 *            区间起始值
	 * @param randomMax
	 *            区间结束值
	 * @return 返回区间的一个随机值
	 */
	public static boolean randomSwich() {
		int value = randomRange(0, 1);
		return value == 1;
	}
	
	/**
	 * 获取一个范围内的随机值
	 * 
	 * @param randomMin
	 *            区间起始值
	 * @param randomMax
	 *            区间结束值
	 * @return 返回区间的一个随机值
	 */
	public static int nextInt(int randomMin, int randomMax) {
		int randomBase = randomMax - randomMin;
		if (randomBase < 0) {
			throw new IllegalArgumentException(
					"randomMax must be bigger than randomMin");
		} else if (randomBase == 0) {
			return randomMin;
		} else {
			return (random.nextInt(randomBase) + randomMin);
		}
	}
	/**
	 * 在最小值和最大值之间产生一个随机数，包括[最大值,最小值]
	 * @param randomMin 最小值
	 * @param randomMax 最大值
	 * @return 随机数
	 */
	public static int randomRange(int randomMin, int randomMax){
		int randomBase = randomMax - randomMin;
		if (randomBase < 0) {
			throw new IllegalArgumentException(
					"randomMax must be bigger than randomMin");
		} else if (randomBase == 0) {
			return randomMin;
		} else {
			return (random.nextInt(randomBase + 1) + randomMin);
		}
	}
	
	public static int randomWeight(int... values){
		int sum = 0;
		for (int value : values) {
			sum += value;
		}
		int randInt = 0;
		//说明配有权重
		if(sum > 0){
			randInt = nextInt(0, sum);
			int lsum = 0;
			for (int i = 0; i < values.length; i++) {
				lsum += values[i];
				if(randInt < lsum){
					return i;
				}
			}
		}
		return nextInt(0, values.length);
	}
	
	public static int randomWeight(List<Integer> values){
		int sum = 0;
		for (int value : values) {
			sum += value;
		}
		int randInt = 0;
		//说明配有权重
		if(sum > 0){
			randInt = nextInt(0, sum);
			int lsum = 0;
			for (int i = 0; i < values.size(); i++) {
				lsum += values.get(i);
				if(randInt < lsum){
					return i;
				}
			}
		}
		return nextInt(0, values.size());
	}
	
	public static int randomPer(int maxPer, List<Integer> list){
		int randInt = nextInt(0, maxPer);
		int lsum = 0;
		for (int i = 0; i < list.size(); i++) {
			lsum += list.get(i);
			if(randInt < lsum){
				return i;
			}
		}
		return nextInt(0, list.size());
	}
	
	public static int randomPer(int maxPer, int... values){
		int randInt = nextInt(0, maxPer);
		int lsum = 0;
		for (int i = 0; i < values.length; i++) {
			lsum += values[i];
			if(randInt < lsum){
				return i;
			}
		}
		return nextInt(0, values.length);
	}
	
	public static int randomValue(int... values){
		int randInt = nextInt(0, values.length);
		return values[randInt];
	}
}
