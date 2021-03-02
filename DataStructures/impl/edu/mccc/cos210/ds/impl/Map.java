package edu.mccc.cos210.ds.impl;

import edu.mccc.cos210.ds.IDoublyLinkedList;
import edu.mccc.cos210.ds.IMap;
import edu.mccc.cos210.ds.ISet;

public class Map<K, V> implements IMap<K, V> {

	private ISet<Entry<K, V>> theBucketArray = new Set<>();

	@Override
	public boolean containsKey(K key) {
		Entry<K, V> e = new Entry<>(key, null);
		return theBucketArray.contains(e);
	}
	
	@Override
	public V get(K key) {
		V v = null;
		IDoublyLinkedList<Entry<K, V>> dll = theBucketArray.asList();
		for (Entry<K, V> e : dll) {
			if (e.getKey().equals(key)) {
				v = e.getValue();
				break;
			}
		}
		return v;
	}

	@Override
	public void remove(K key) {
		theBucketArray.remove(new Map.Entry<K, V>(key, null));
	}
	
	@Override
	public ISet<K> keySet() {
		ISet<K> keySet = new Set<>();
		IDoublyLinkedList<Entry<K, V>> dll = theBucketArray.asList();
		for (Entry<K, V> e : dll) {
			keySet.add(e.getKey());
		}
		return keySet;
	}

	@Override
	public void put(K key, V value) {
		Entry<K, V> e = new Entry<>(key, value);
		theBucketArray.add(e);
	}

	@Override
	public int getSize() {
		return theBucketArray.getSize();
	}

	@Override
	public boolean isEmpty() {
		return theBucketArray.isEmpty();
	}

	@Override
	public String toString() {
		return theBucketArray.toString();
	}

	public static class Entry<K, V> implements IMap.Entry<K, V> {

		private K key;
		private V value;

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public void setKey(K key) {
			this.key = key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public void setValue(V value) {
			this.value = value;
		}

		@Override
		public int hashCode() {
			return key.hashCode();
		}

		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object o) {
			return this.key.equals(((Entry<K, V>) o).getKey());
		}

		@Override
		public String toString() {
			return key + "=" + value;
		}
	}
}
