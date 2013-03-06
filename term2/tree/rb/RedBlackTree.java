package term2.tree.rb;

import java.util.Comparator;
import java.util.Stack;

public class RedBlackTree<K, V> {

	private static final boolean RED = true;
	private static final boolean BLACK = false;

	// корень дерева
	private Node root; 
	// количество узлов
	private int size; 
	private final Comparator<? super K> comparator;

	/**
	 * Класс, описывающий объекты узлов.
	 */
	private class Node {
		// Идентификатор
		private K key;
		// Другие данные
		private V value;
		//Родительский узел
		private Node parent;
		// Левый потомок узла
		private Node leftChild;
		// Правый потомок узла
		private Node rightChild;
		// цвет узла
		private boolean color;	

		public Node(K key, V value, boolean color, Node parent) {
			this.key = key;
			this.value = value;
			this.color = color;
			this.parent = parent;
		}

		public V setValue(V value) {
			V oldValue = this.value;
			this.value = value;
			return oldValue;
		}

	}

	public RedBlackTree() {
		comparator = null;
	}

	/* Если пользователь хочет, чтобы для сравнения объектов использовался comparator,
	 * он может задать его, используя данный конструктор. В противном случае ключи 
	 * элементов дерева должны реализовывать интерфейс Comparable*/
	public RedBlackTree(Comparator<? super K> comparator) {
		this.comparator = comparator;
	}

	/*************************************************************************
	 * Вставка элемента в красно-черное дерево
	 *************************************************************************/
	@SuppressWarnings("unchecked")
	public V put(K key, V value) {
		Node t = root;
		if (t == null) {
			root = new Node(key, value, BLACK, null);
			size = 1;
			return null;
		}
		int cmp;
		Node parent;
		// решаем, как производить сравнение: используя comparator или comparable
		if (comparator != null) {
			do {
				parent = t;
				cmp = comparator.compare(key, t.key);
				/* решаем двигаться налево или направо для поиска позиции вставки */
				if (cmp < 0) {
					t = t.leftChild;
				} else if (cmp > 0) {
					t = t.rightChild;
				} else {
					/*
					 * Если элемент с данным ключом присутствует в дереве, то старое
					 * значение перезаписывается на новое
					 */
					return t.setValue(value);
				}
			} while (t != null);
		} else {
			if (key == null) {
				throw new NullPointerException();
			}
			//TODO аналогичная вставка для ключей реализующих Comparable
			cmp = 0;
			parent = null;
		}
		Node e = new Node(key, value, RED, parent);
		if (cmp < 0) {
			parent.leftChild = e;
		} else {
			parent.rightChild = e;
		}
		// фиксим нарушения правил красно-черных деревьев
		fixAfterInsertion(e);
		size++;
		return null;
	}

	/* Исправления нарушений правил красно-черных деревьев
	 * */
	private void fixAfterInsertion(Node x) {
		/* если родитель узла x черного цвета, то ни одно их правил
		 * красно-черных деревьев не может быть нарушего 
		 */
		while (x != null && x != root && x.parent.color == RED) {
			if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
				//дядя x
				Node y = rightOf(parentOf(parentOf(x)));				
				if (colorOf(y) == RED) {
					// фиксим правило 3
					flipColors(parentOf(parentOf(x)));
					x = parentOf(parentOf(x));
				} else {
					
					if (x == rightOf(parentOf(x))) {
						//x - внутренний внук
						x = parentOf(x);
						rotateLeft(x);
					}
					//x - внешний внук
					flipColors(parentOf(parentOf(x)));
					rotateRight(parentOf(parentOf(x)));
				}
			} else {
				//TODO симметричный случай
			
			}
		}
		root.color = BLACK;
	}

	/*************************************************************************
	 * вспомогательные методы
	 *************************************************************************/

	@SuppressWarnings("unchecked")
	private int compare(K k1, K k2) {
		return comparator == null ? ((Comparable<? super K>) k1).compareTo(k2)
				: comparator.compare(k1, k2);
	}

	/**
	 * @param x узел, для которого требуется определить цвет
	 * @return true, если узел красный; false, если узел черный или null
	 */
	private boolean colorOf(Node x) {
		if (x == null) {
			return BLACK;
		}
		return x.color;
	}

	private Node parentOf(Node x) {
		if (x == null) {
			return null;
		}
		return x.parent;
	}

	private void setColor(Node p, boolean c) {
		if (p != null) {
			p.color = c;
		}
	}

	private Node leftOf(Node p) {
		return (p == null) ? null : p.leftChild;
	}

	private Node rightOf(Node p) {
		return (p == null) ? null : p.rightChild;
	}

	/**
	 * Поворот влево
	 * @param p узел, относительно которого осуществляется поворот
	 */
	private void rotateLeft(Node p) {
		if (p != null) {
			Node r = p.rightChild;
			p.rightChild = r.leftChild;
			if (r.leftChild != null) {
				r.leftChild.parent = p;
			}
			r.parent = p.parent;
			if (p.parent == null) {
				root = r;
			} else if (p.parent.leftChild == p) {
				p.parent.leftChild = r;
			} else {
				p.parent.rightChild = r;
			}
			r.leftChild = p;
			p.parent = r;
		}
	}
	
	/**
	 * TODO
	 * Поворот право
	 * @param p узел, относительно которого осуществляется поворот
	 */
	private void rotateRight(Node p) {
	
	}
	
	/**
	 * Переключение цветов
	 * Родительский узел меняет цвет на красный, а его потоки - на черный
	 * @param h родительский узел
	 */
	private void flipColors(Node h) {
		setColor(h, RED);
		setColor(h.leftChild, BLACK);
		setColor(h.rightChild, BLACK);
	}	

	/*************************************************************************
	 * Стандартные методы деревьев двоичного поиска
	 *************************************************************************/

	/**
	 * Поиск элемента по ключу
	 * */
	public V get(K key) {
		return get(root, key);
	}

	public V get(Node x, K key) {
		while (x != null) {
			int cmp = compare(key, x.key);
			if (cmp < 0) {
				x = x.leftChild;
			} else if (cmp > 0) {
				x = x.rightChild;
			} else {
				return x.value;
			}
		}
		return null;
	}


	/**
	 * Отображение двоичного дерева
	 */
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
					System.out.print(temp.value + (temp.color? "R":"B"));
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

	
	public boolean isEmpty() {
		return size == 0;
	}
	

}