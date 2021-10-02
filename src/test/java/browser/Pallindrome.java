package browser;

import java.util.Scanner;

public class Pallindrome {

	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter the number:");
		int a=scan.nextInt();
		int r,sum=0,temp;
		
		//reverse the number
	     temp=a;
		while(a>0) {
			r=a%10; //getting the reminder
			sum=(sum*10)+r;
			a=a/10;
			
		}
		System.out.println(sum);
		
		if(temp==sum) {
			System.out.println("given number : " +temp + " is pallindrome");
		}else {
			System.out.println("given number : " +temp + " is not pallindrome");
		}
		
	}

}
