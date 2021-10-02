package browser;

import java.util.Scanner;

public class Numbersdivisble {

	public static void main(String[] args) {
		Scanner myInput = new Scanner( System.in );
		System.out.println("Enter first number");
		int a=myInput.nextInt();
		System.out.println("Enter second number");
		int b=myInput.nextInt();
		for (int i=a;i<=b;i++) {
			if(i%3==0) {
				System.out.println("Fuzz");
				if(i%5==0) {
					System.out.println("Fuzz buzz");
				}
			}else if(i%5==0) {
				System.out.println("buzz");
			}else {
				System.out.println(i);
			}
		}
	}

}
