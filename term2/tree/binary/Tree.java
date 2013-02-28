package term2.tree.binary;

import java.util.Stack;

/**
 * Дерево бинарного поиска: ключ левого потомка меньше ключа родителя, а ключ
 * правого потомка - больше или равен ключу родителя.
 */
public class Tree {

	// корень дерева
	private Node root;

	// количество узлов
	private int size;

	/**
	 * Класс, описывающий объекты узлов.
	 */
	private class Node {
		// Идентификатор
		private Integer id;
		// Другие данные
		private Double data;
		// Левый потомок узла
		private Node leftChild;
		// Правый потомок узла
		private Node rightChild;

		public Node(Integer id, Double data) {
			this.id = id;
			this.data = data;
		}

		@Override
		public String toString() {
			return "[id=" + id + ", data =" + data + "]";
		}
	}

	/**
	 * Поиск узла с заданным ключом
	 * 
	 * @param id
	 *            Идентификатор искомого элемента
	 * @return Значение искомого элемента
	 */
	public Double find(int id) {
		// если дерево пустое, то элемент не найден
		if (root == null) {
			return null;
		}
		// начинаем поиск с корневого узла
		Node current = root;
		// пока не нашли нужный узел
		while (current.id != id) {
			// решаем двигаться налево или направо
			if (current.id > id) {
				current = current.rightChild;
			} else {
				current = current.leftChild;
			}
			// если нет потомка, то элемент не найден
			if (current == null) {
				return null;
			}
		}
		// элемент найден
		return current.data;
	}

	/**
	 * Вставка узла Сначала создается новый узел на основе данных, переданных в
	 * аргументах. Далее определяется место для вставки нового узла, используя
	 * примерно тот же код, что и при поиске узла. Искомым элементом является
	 * элемент, передаваемый в аргументе id. Цикл while использует условие true,
	 * потому что обнаружение узла с тем же значением, что у id, игнорируется;
	 * узел с совпадающим ключом интерпретируется так, как если бы его ключ был
	 * больше искомого. В зависимости от значения ключа текущего элемента,
	 * принимается решение к какому из потомков должен быть совершен переход.
	 * Если соответствующий потомок равен null, то осуществляется вставка нового
	 * элемента на место этого потомка, и происходит выход из цикла.
	 * 
	 * @param id
	 *            Ключ
	 * @param data
	 *            Значение
	 */
	public void insert(int id, double data) {
		Node newNode = new Node(id, data);
		size++;
		if (root == null) {
			root = newNode;
			return;
		}
		Node current = root;
		while (true) {
			if (current.id > id) {
				if (current.leftChild == null) {
					current.leftChild = newNode;
					return;
				}
				current = current.leftChild;
			} else {
				if (current.rightChild == null) {
					current.rightChild = newNode;
					return;
				}
				current = current.rightChild;
			}
		}
	}

	/**
	 * Прямой обход
	 */
	public void preOrder() {
		// начинаем обход с корневого узла
		preOrder(root);
	}

	/**
	 * Рекурсивный метод прямого обхода
	 * 
	 * @param current
	 *            текущий узел
	 */
	private void preOrder(Node current) {
		if (current != null) {
			// 1. Посещение узла
			System.out.println(current);
			// 2. Вызов самого себя для обхода левого поддерева узла
			preOrder(current.leftChild);
			// 3. Вызов самого себя для обхода правого поддерева узла
			preOrder(current.rightChild);
		}
	}

	/**
	 * Обратный обход
	 */
	public void postOrder() {
		// начинаем обход с корневого узла
		postOrder(root);
	}

	/**
	 * Рекурсивный метод обратного обхода
	 * 
	 * @param current
	 *            текущий узел
	 */
	private void postOrder(Node current) {
		if (current != null) {
			// 1. Вызов самого себя для обхода левого поддерева узла
			postOrder(current.leftChild);
			// 2. Вызов самого себя для обхода правого поддерева узла
			postOrder(current.rightChild);
			// 3. Посещение узла
			System.out.println(current);
		}
	}

	/**
	 * TODO Симметричный обход
	 */
	public void inOrder() {

	}

	/**
	 * Поиск элемента с минимальным значением ключа
	 * 
	 * @return Значение элемента с минимальным значением ключа
	 */
	public Double min() {
		// если дерево пустое, то элемент не найден
		if (root == null) {
			return null;
		}
		// начинаем обход с корневого элемента
		Node current = root;
		while (current.leftChild != null) {
			// переход к левому потомку
			current = current.leftChild;
		}
		return current.data;
	}

	/**
	 * Поиск элемента с максимальным значением ключа
	 * 
	 * @return Значение элемента с максимальным значением ключа
	 */
	public Double max() {
		// если дерево пустое, то элемент не найден
		if (root == null) {
			return null;
		}
		// начинаем обход с корневого элемента
		Node current = root;
		while (current.rightChild != null) {
			// переход к правому потомку
			current = current.rightChild;
		}
		return current.data;
	}

	/**
	 * Удаление узла с заданным идентификатором
	 * 
	 * @param id
	 *            Значение идентификатора
	 * @return true, если удаление прошло успешно, false - в противном случае.
	 */
	public boolean delete(int id) {
		if (root == null) {
			return false;
		}
		Node current = root;
		Node parent = root;
		boolean isLeftChild = true;
		// поиск узла
		while (current.id != id) {
			parent = current;

			if (id < current.id) {
				// двигаться налево
				isLeftChild = true;
				current = current.leftChild;
			} else {
				// двигаться направо
				isLeftChild = false;
				current = current.rightChild;
			}
			if (current == null) {
				// узел не найден
				return false;
			}
		}
		// если узел не имеет потомков, то он просто удаляется
		if (current.leftChild == null && current.rightChild == null) {
			if (current == root) {
				root = null;
			} else if (isLeftChild) {
				parent.leftChild = null;
			} else {
				parent.rightChild = null;
			}
		}
		// если нет правого потомка, узел заменяется левым поддеревом
		else if (current.rightChild == null) {
			if (current == root) {
				root = current.leftChild;
			} else if (isLeftChild) {
				parent.leftChild = current.leftChild;
			} else {
				parent.rightChild = current.leftChild;
			}
		}
		// если нет левого потомка, узел заменяется правым поддеревом
		else if (current.leftChild == null) {
			if (current == root) {
				root = current.rightChild;
			} else if (isLeftChild) {
				parent.leftChild = current.rightChild;
			} else {
				parent.rightChild = current.rightChild;
			}
		}
		// два потомка, узел заменяется переемником
		else {
			// поиск переемника для удаляемого узла(current)
			Node successor = getSuccessor(current);
			// родитель current связывается с переемником
			if (current == root) {
				root = successor;
			} else if (isLeftChild) {
				parent.leftChild = successor;
			} else {
				parent.rightChild = successor;
			}			 
			//переемник связывается с левым потомком current
	         successor.leftChild = current.leftChild;
		}
		return true;
	}

	/**
	 * Метод возвращает узел со следующим значенем после delNode. Для этого он
	 * сначала переходит к правому потомку, а затем отслеживает цепочку левых
	 * потомков этого узла.
	 * 
	 * @param delNode
	 *            узел, который требуется удалить
	 * @return возвращает переемника удаляемого узла
	 */
	private Node getSuccessor(Node delNode) {
		Node successorParent = delNode;
		Node successor = delNode;
		// переход к правому потомку
		Node current = delNode.rightChild;
		// пока не есть левые потомки
		while (current != null) {
			successorParent = successor;
			successor = current;
			// переход к левому потомку
			current = current.leftChild;
		}
		// если переемник не является правым потомком, создать связи между
		// узлами
		if (successor != delNode.rightChild) {
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = delNode.rightChild;
		}
		return successor;
	}

	public void displayTree() {
		Stack<Node> globalStack = new Stack<>();
		globalStack.push(root);
		int nBlanks = 32;
		boolean isRowEmpty = false;
		System.out
				.println("......................................................");
		while (isRowEmpty == false) {
			Stack<Node> localStack = new Stack<>();
			isRowEmpty = true;

			for (int j = 0; j < nBlanks; j++)
				System.out.print(' ');

			while (globalStack.isEmpty() == false) {
				Node temp = (Node) globalStack.pop();
				if (temp != null) {
					System.out.print(temp.data);
					localStack.push(temp.leftChild);
					localStack.push(temp.rightChild);

					if (temp.leftChild != null || temp.rightChild != null)
						isRowEmpty = false;
				} else {
					System.out.print("--");
					localStack.push(null);
					localStack.push(null);
				}
				for (int j = 0; j < nBlanks * 2 - 2; j++)
					System.out.print(' ');
			}
			System.out.println();
			nBlanks /= 2;
			while (localStack.isEmpty() == false)
				globalStack.push(localStack.pop());
		}
		System.out
				.println("......................................................");
	}

	public int size() {
		return size;
	}
}
