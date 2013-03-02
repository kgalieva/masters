package term2.tree.huffman;

public class HuffmanCodeTest {

	public static void main(String[] args) {
		String text = "it is itis";

		// для простоты допустим, что количество символов не превосходит 256
		int[] charFrequencies = new int[256];
		// считываем символы и считаем их частоту
		for (char c : text.toCharArray()) {
			charFrequencies[c]++;
		}

		// строим дерево
		HuffmanTree tree = HuffmanTree.buildHuffmanTree(charFrequencies);
		System.out.printf("size before compression = %d%n", text.length() * 8);
		String incoded = tree.incode(text);
		System.out.println("incoded result = " + incoded);
		System.out.printf("size after compression = %d%n", incoded.length());
		String decoded = tree.decode(incoded);
		System.out.println(decoded);

		// вывод кодовой таблицы
		tree.printCodes();
	}

}
