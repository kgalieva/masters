package term2.tree.tree234;

/**
 * Дерево 2-3-4
 */
public class Tree234<K, V> {
	//Корневой узел
	private Node<K, V> root = new Node<K, V>();
	// количество узлов
	private int size; 

	/**
	 * Поиск элемента с заданным ключом
	 * @param key 	Идентификатор искомого элемента
	 * @return	 	Индекс найденного элемента. 
	 * 				-1, если элемент не был найден.
	 */
	public int find(Comparable<? super K> key) {
		Node<K, V> current = root;
		int index;
		while(current != null) {			
			if((index = current.findItem(key)) != -1) {
				return index;
			}
			current = getNextChild(current, key);
		}
		return -1;
	}
	
	/**
	 * Метод определяет к какому из потомков текущего узла следует перейти для продолжения поиска.
	 * @param current 	Текущий узел
	 * @param key		Идентификатор искомого элемента
	 * @return			Потомок текущего узла к которому следует перейти для продолжения поиска.
	 * 					null, если текущий узел является листовым
	 */
	@SuppressWarnings("unchecked")
	private Node<K, V> getNextChild(Node<K, V> current, Comparable<? super K> key) {
		if (current.isLeaf()){
			return null;
		}
		int index = 0;
		while(index < current.getNumItems() && key.compareTo((K)current.getItem(index).getKey()) > 0){
			index++;
		}
		return current.getChild(index);		
	}
	
	/**
	 * Вставка нового элемента в дерево.
	 * @param newItem Новый элемент 
	 */
	public void insert(Comparable<? super K> key, V value) {
		Node<K, V> current = root;
		while(true){
			if(current.isFull()){
				split(current);
				current = current.getParent();
				current = getNextChild(current, key);	
			}
			if(current.isLeaf()){
				current.insertItem(key, value);
				size++;
				return;
			}
			current = getNextChild(current, key);				
		}
		
	}
	
	/**
	 * Разбиение узла
	 * @param node Узел, для которого необходимо выполнить разбиение.
	 * 
	 * Сначала два правых элемента данных извлекаются из узла(node.removeItem()) и сохраняются во временных переменных.
	 * Затем два правых потомка отсоединяются от узла(node.disconnectChild()), ссылки на них тоже временно сохраняются.
	 * 
	 *  Метод создает новый узел с именем newRight. Созданный узел будет размещен справа от разбиваемого узла.
	 *  Обозначим три элемента данных в разбиваемом узле A, B и C. 
	 *  Элемент B вставляется в родительский узел(newRight.setParent(), parent.insertItem()).
	 *  
	 *  Если родительского узла нет, то его необходимо создать, сделать корневым элементом и присоединить к нему 
	 *  разбиваемый узел(parent.connectChild())
	 *  
	 *  При необходимости существующие потомки родителя отсоединяются от него и присоединяются заново на одну позицию правее, 
	 *  чтобы освободить место для нового элемента данных и новых связей. После этого узел newRight присоединяется к родителю
	 *  (parent.connectChild())
	 *  
	 *  Осталось только вставить элемент C в узел newRight(newRight.insertItem()) и присоединить к newRight 
	 *  потомки 2 и 3(newRight.connectChild()), которые были отсоединены от разбиваемого узла.
	 */
	public void split(Node<K, V> node) {
		Node.Entry<K, V> rightItem = node.removeItem();
		Node.Entry<K, V> middleItem = node.removeItem();

		Node<K, V> thirdChild = node.disconnectChild(3);		
		Node<K, V> secondChild = node.disconnectChild(2);

		Node<K, V> newRight = new Node<K, V>();
		if(node.getParent() == null){
			root = new Node<K, V>();
			root.connectChild(0, node);
			node.setParent(root);			
		}
		Node<K, V> parent = node.getParent();
		newRight.setParent(parent);
		int index = parent.insertItem(middleItem.getKey(), middleItem.getValue());
		Node<K, V> temp;
		for(int i = parent.getNumItems() - 1; i > index; i--) {
			temp = parent.disconnectChild(i);
			parent.connectChild(i + 1, temp);
		}
		parent.connectChild(index + 1, newRight);
		newRight.insertItem(rightItem.getKey(), rightItem.getValue());
		newRight.connectChild(0, secondChild);
		newRight.connectChild(1, thirdChild);	
	}
	
	public void displayTree(){
		recDisplayTree(root, 0, 0);
		System.out.println("--------------------------------------------");
	}
	
	public void recDisplayTree(Node<K, V> node, int level, int childNumber){
		System.out.printf("level = %d; child number = %d: ", level, childNumber);
		System.out.println(node);
		if(!node.isLeaf()){
			for(int i = 0; i <= node.getNumItems(); i++) {
				recDisplayTree(node.getChild(i), level + 1, i);
			}
		}						
	}	
}
