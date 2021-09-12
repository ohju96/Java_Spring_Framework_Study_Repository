package code;

import java.util.Scanner;

public class ch01 {

	public static void main(String[] args) {

		   Scanner p = new Scanner(System.in);

		   long a = p.nextLong();
		   long b = p.nextLong();

		   System.out.println(solution(a, b));

		}

		public static long solution(long a, long b) {

		   long x = a;
		   long y = b;
		   long max = 0;
		   long min = 0;
		   long rtn = 0;

		   if (a > b) {
		      max = a;
		      min = b;
		   } else if (a == b) {
		      max = a;
		      min = a;
		   } else {
		      max = b;
		      min = a;
		   }
		   long p =min;

		   if(a!=b) {
		   for (long i = p+1; i <= max; i++) {
		      min += i;
		   }
		   }
		   else {
		      rtn = min;
		   }

		   rtn = min;
		   return rtn;
		}
}
