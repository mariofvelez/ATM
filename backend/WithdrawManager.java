package backend;

import java.util.ArrayList;

public final class WithdrawManager {
	
	private static final int[] m = {100, 50, 20, 10, 5, 1};
	
	public static int[] withdraw(int amount)
	{
		ArrayList<Integer> change = new ArrayList<>();
		int total = 0;
		
		for(int i = 0; i < m.length; i++)
		{
			while((total + m[i]) <= amount)
			{
				total += m[i];
				change.add(m[i]);
			}
		}
		
		int[] money = new int[change.size()];
		for(int i = 0; i < money.length; i++)
			money[i] = change.get(i);
		
		return money;
	}

}
