package edu.mccc.cos210.ds.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import edu.mccc.cos210.ds.IArray;
import edu.mccc.cos210.ds.IDoublyLinkedList;
import edu.mccc.cos210.ds.ISinglyLinkedList;
import edu.mccc.cos210.ds.impl.Array;
import edu.mccc.cos210.ds.impl.DoublyLinkedList;
import edu.mccc.cos210.ds.impl.SinglyLinkedList;

public class Utility {
	
	public static <T> ArrayCollector<T> toArray() {
		  return new ArrayCollector<T>();
	}
	
	public static <T> SinglyLinkedListCollector<T> toSinglyLinkedList() {
		  return new SinglyLinkedListCollector<T>();
	}
	
	public static <T> DoublyLinkedListCollector<T> toDoublyLinkedList() {
		  return new DoublyLinkedListCollector<T>();
	}

	static class ArrayCollector<T> implements
		Collector<T, ISinglyLinkedList<T>, IArray<T>> {

		@Override
		public Supplier<ISinglyLinkedList<T>> supplier() {
			return SinglyLinkedList::new;
		}
		
		@Override
		public BiConsumer<ISinglyLinkedList<T>, T> accumulator() {
			return (list, value) -> list.addLast(value);
		}

		@Override
		public BinaryOperator<ISinglyLinkedList<T>> combiner() {
			return (list1, list2) -> {
				for (T d : list2) {
					list1.addLast(d);
				}
				return list1;
			};
		}

		@Override
		public Function<ISinglyLinkedList<T>, IArray<T>> finisher() {
			return (list) -> {
				IArray<T> theArray = new Array<>(list.getSize());
				int i = 0;
				Iterator<T> it = list.iterator();
				while (it.hasNext()) {
					theArray.set(i++, it.next());
				}
				return theArray;
			};
		}

		@Override
		public Set<Characteristics> characteristics() {
			Set<Characteristics> characteristics = new HashSet<Characteristics>();
			return characteristics;
		}
	}

	static class SinglyLinkedListCollector<T> implements
		Collector<T, ISinglyLinkedList<T>, ISinglyLinkedList<T>> {

		@Override
		public Supplier<ISinglyLinkedList<T>> supplier() {
			return SinglyLinkedList::new;
		}
		
		@Override
		public BiConsumer<ISinglyLinkedList<T>, T> accumulator() {
			return (list, value) -> list.addLast(value);
		}

		@Override
		public BinaryOperator<ISinglyLinkedList<T>> combiner() {
			return (list1, list2) -> {
				for (T d : list2) {
					list1.addLast(d);
				}
				return list1;
			};
		}

		@Override
		public Function<ISinglyLinkedList<T>, ISinglyLinkedList<T>> finisher() {
			return Function.identity();
		}

		@Override
		public Set<Characteristics> characteristics() {
			Set<Characteristics> characteristics = new HashSet<Characteristics>();
			characteristics.add(Characteristics.IDENTITY_FINISH);
			return characteristics;
		}
	}
		
	static class DoublyLinkedListCollector<T> implements
		Collector<T, IDoublyLinkedList<T>, IDoublyLinkedList<T>> {

		@Override
		public Supplier<IDoublyLinkedList<T>> supplier() {
			return DoublyLinkedList::new;
		}
		
		@Override
		public BiConsumer<IDoublyLinkedList<T>, T> accumulator() {
			return (list, value) -> list.addLast(value);
		}

		@Override
		public BinaryOperator<IDoublyLinkedList<T>> combiner() {
			return (list1, list2) -> {
				for (T d : list2) {
					list1.addLast(d);
				}
				return list1;
			};
		}

		@Override
		public Function<IDoublyLinkedList<T>, IDoublyLinkedList<T>> finisher() {
			return Function.identity();
		}

		@Override
		public Set<Characteristics> characteristics() {
			Set<Characteristics> characteristics = new HashSet<Characteristics>();
			characteristics.add(Characteristics.IDENTITY_FINISH);
			return characteristics;
		}
	}	

}
