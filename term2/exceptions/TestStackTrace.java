package term2.exceptions;
/*Трассировка стека*/
public class TestStackTrace {
	
	public static void f() {
		try {
			throw new Exception();
		} catch (Exception e) {		
			//Печать стека вызовов, приведших к исключению
			e.printStackTrace();		
			System.out.println("---------------------------------------");
			/* Информацию, предоставляемую методом printStackTrace(), также можно получить напрямую вызовом метода getStackTrace().
			 * Метод возвращает массив элементов трассировки, каждый из которых представляет 1 кадр стека.
			 * Нулевой элемент представляет вершину стека, то есть последний вызванный метод последовательности
			 * (точка, в которой было создано исключение). Соответственно, последний элемент массива представляет "низ" стека,
			 * т.е. первый вызванный элемент последовательности.*/
			for (StackTraceElement el : e.getStackTrace()) {
				System.out.println(el.getMethodName());
			}		
		}
	}

	public static void g() {
		f();
	}

	public static void h() {
		g();
	}

	public static void y() {
		h();
	}

	public static void main(String[] args) {
		y();
	}

}
