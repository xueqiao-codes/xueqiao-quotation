package test;

import java.util.HashSet;
import java.util.Set;

public class TestMain {

	public static void main(String[] args) {
		Set<String> copySet = new HashSet<String>();
		for (int index = 0; index < 10; ++index) {
			copySet.add(String.valueOf(index));
		}
		
		int testCount = 100000;
		long currentTimestampS = System.currentTimeMillis();
		for (int count = 0; count < testCount; ++count) {
			Set<String> copiedSet = new HashSet<String>();
			copiedSet.addAll(copySet);
		}
		
		System.out.println("every copy time=" + (System.currentTimeMillis() - currentTimestampS)*1000*1000/testCount + "ns");
	}

}
