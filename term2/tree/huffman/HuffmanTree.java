package term2.tree.huffman;

import java.util.PriorityQueue;
/**
 * Дерево Хаффмана
 */
public class HuffmanTree implements Comparable<HuffmanTree>{

	// корень дерева
	private Node root;
		
	public HuffmanTree(Node root) {
		this.root = root;
	}

	/**
	 * Класс, описывающий объекты узлов.
	 */
	private static class Node {
		// Частота символа
		private Integer frequency;
		// Символ
		private Character character;
		// Левый потомок узла
		private Node leftChild;
		// Правый потомок узла
		private Node rightChild;

		public Node(Integer frequency, Character character) {
			this.frequency = frequency;
			this.character = character;
		}

		public Node(HuffmanTree left, HuffmanTree right) {
			frequency = left.root.frequency + right.root.frequency;
			leftChild = left.root;
			rightChild = right.root;
		}

		@Override
		public String toString() {
			return "[id=" + frequency + ", data =" + character + "]";
		}
	}
	/**
	 * Построение дерева Хаффмана
	 * Алгоритм:
	 * 1. Создать объект Node для каждого символа, используемого в сообщении.
	 * 2. Создать объект дерева для каждого из этих узлов. Узел становится корнем дерева.
	 * 3. Вставить эти деревья в приоритетную очередь. Деревья упорядочиваются по частоте, 
	 * при этом наименьшая частота обладает наибольшим приоритетом. Таким образом, 
	 * при извлечении всегда выбирается дерево с наименее часто используемым символом.
	 * 4. Извлечь два дерева из приоритетной очереди и сделать их потомками нового узла.
	 * Частота нового узла является суммой частот потомков. Поле символа может остаться пустым.
	 * 5. Вставить новое дерево из двух узлов обратно в приоритетную очередь.
	 * 6. Продолжить выполнение шагов 4 и 5. Деревья постепенно увеличиваются, а их 
	 * количество постепенно сокращается. Когда в очереди останется всего одно дерево, оно 
	 * представляет собой дерево Хаффмана. Работа алгоритма при этом завершается. 
	 * 
	 * @param charFrequencies Массив содержащий частоту символов в сообщении. Номер ячейки соответствует 
	 * коду символа в ASCII.
	 * @return дерево Хаффмана
	 */
	public static HuffmanTree buildHuffmanTree(int[] charFrequencies) {
		  PriorityQueue<HuffmanTree> trees = new PriorityQueue<>();
	      //TODO 1.-6.
	        /*Когда в очереди останется всего одно дерево, оно представляет собой дерево Хаффмана. */
	        return trees.poll();
	}
	
	/**
	 * Преобразование последовательности битов в текстовую строку.
	 * Декодирование каждого символа начинается с корневого узла.
	 * Если в потоке обнаружен бит 0, вы переходите налево к следующему узлу, 
	 * а если 1 - то направо. 
	 * Например, для кода 010 нужно двигаться налево, направо, потом снова налево. 
	 * Если существует символ с таким кодом, то вы окажитесь в листовом узле.
	 * При достижении листового узла начинается поиск нового символа.
	 * @param code массив байт, который требуется декодировать.
	 * @param tree дерево Хаффмана
	 * @return метод возвращает декодированную строку
	 */
	public String decode(String bytes) {
		StringBuilder result = new StringBuilder();
		//TODO
		return result.toString();
	}
	
	/**
	 * Кодирование сообщения
	 * @param text текст сообщения
	 * @return бинарное представление сообщения
	 */
	public String incode(String text) {		
      /*Для кодирования сообщения необходимо создать кодовую таблицу, 
       * в которой для каждого символа приводится соответствующий код Хаффмана.*/
	  String[] codes = codeTable();
      StringBuilder result = new StringBuilder();
      /*Далее коды Хаффмана раз за разом присоединяются к кодированному сообщению, 
       * пока оно не будет завершено.*/
    //TODO
      return result.toString();
	}
	
	/**
	 * Создание кодовой таблицы по данному дереву Хаффмана
	 * @param tree дерево Хаффмана
	 * @return возвращает кодовую таблицу, в которой для каждого символа 
	 * приводится соответствующий код Хаффмана.
	 */
	private String[] codeTable() {
		String[] codeTable = new String[256]; 
		codeTable(root, new StringBuilder(), codeTable);
		return codeTable;
	}
	
	/**
	 * Постороение кодовой таблицы реализовано посредством вызова метода, 
	 * который начинается от корня таблицы, а затем рекурсивно вызывает себя 
	 * для каждого потомка. Через некоторое время алгоритм обойдет все пути 
	 * ко всем листовым узлам, и кодовая таблица будет построена. 
	 * @param node текущий узел 
	 * @param code код из 0(лево) и 1(право), отражающий путь от корня до текущего узла
	 * @param codeTable кодовая таблица
	 */
	private void codeTable(Node node, StringBuilder code, String[] codeTable) {
		//TODO
	}
	
	public void printCodes() {
		System.out.println("char\t frequency\t binary code");
		printCodes(root, new StringBuilder());
	}	
	
	/**
	 * Рекурсивный метод печати кодовой таблицы
	 * @param current текущий узел
	 * @param code код пути до текущего узла
	 */
	private void printCodes(Node current, StringBuilder code) {
		//если узел листовой
        if (current.character != null) { 
            // выводим символ, частоту и код пути от корня до текущкго узла
            System.out.println(current.character + "\t " + current.frequency + "\t\t " + code); 
        } else {            
            // обходим левое поддерево
            printCodes(current.leftChild, code.append('0'));
            code.deleteCharAt(code.length() - 1); 
            // обходим правое поддерево
            printCodes(current.rightChild, code.append('1'));
            code.deleteCharAt(code.length() - 1); 
        }
	}
	
	@Override
	public int compareTo(HuffmanTree tree) {
		return root.frequency - tree.root.frequency;
	}

}
