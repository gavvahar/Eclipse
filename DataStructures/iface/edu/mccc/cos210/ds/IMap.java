package edu.mccc.cos210.ds;

public interface IMap<K, V> {
	public boolean containsKey(K key);
	public V get(K key);
	public void remove(K key);
	public ISet<K> keySet();
	public void put(K key, V value);
	public int getSize();
	public boolean isEmpty();
	public interface Entry<K, V>
	{
		public K getKey();
		public void setKey(K Key);
		public V getValue();
		public void setValue(V value);
		public int hashCode();
		public boolean equals(Object o);
	}
}