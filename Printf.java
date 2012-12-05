import java.util.Date;

public class Printf {
	/*
Float, Double.

%f → Десятичное  число с точкой.
       %e → Десятичное  число с точкой и экспонентой.
       %.10f → С точностью 10 знаков после запятой.
       printf("%.10f", Math.PI); //  "3,1415926536"
Date, Calendar.

%tF → Дата в формате "год-месяц-день".
printf("%tF", new Date()); //  "2011-01-27"
%tT → Время в формате "час:минута:секунда".
printf("%tT", new Date()); //  "22:42:37"*/

	public static void main(String[] args) {

		//%s - String или toString()
		System.out.printf("Hello %s!", "World");

		//%n - Перенос строки.
		System.out.printf("Hello %s! %n", "World");

		/*** Целочисленные типы byte, short, int, long. ***/

		//%d - Число в десятичной системе счисления.
		System.out.printf("%d %n", 123);

		//%x - Число в шестнадцатеричной системе счисления.
		System.out.printf("%x %n", 0x123);

		//%5d - Число в десятичной системе счисления. Минимальная длина строки 5 знаков.
		System.out.printf("%5d %n", 123);

		//%05d - Минимальная длина строки 5 знаков. Начало заполняется нулями.
		System.out.printf("%05d %n", 123);

		/*** Числа с плавающей точкой float, double ***/
		
		//%f - Десятичное число с плавающей точкой.
		System.out.printf("%f %n", 2.5);

		//%e - Десятичное число с плавающей точкой и экспонентой.
		System.out.printf("%e %n", 0.000005);

		//%.5f - С точностью 5 знаков после запятой.
		System.out.printf("%.5f %n", Math.PI); //  "3,14159"

		/*** Date, Calendar. ***/

		//%tF - Дата в формате "год-месяц-день".
		System.out.printf("%tF %n", new Date()); //  "2012-12-06"
		
		//%tT - Время в формате "час:минута:секунда".
		System.out.printf("%tT %n", new Date()); //  "01:54:25"
	}

}