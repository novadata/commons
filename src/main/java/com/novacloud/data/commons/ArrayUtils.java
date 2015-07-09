package com.novacloud.data.commons;

import com.google.common.collect.Lists;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 集合操作工具类.
 * 
 * @author <a href="mailto:wuzhiqiang@novacloud.com">wuzq</a>
 * @date 2013-1-19下午12:39:15
 * @version Revision: 1.1
 */
public class ArrayUtils {
	public static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];

	/**
	 * <p>
	 * Adds all the elements of the given arrays into a new array.
	 * </p>
	 * <p>
	 * The new array contains all of the element of {@code array1} followed by
	 * all of the elements {@code array2}. When an array is returned, it is
	 * always a new array.
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.addAll(null, null)     = null
	 * ArrayUtils.addAll(array1, null)   = cloned copy of array1
	 * ArrayUtils.addAll(null, array2)   = cloned copy of array2
	 * ArrayUtils.addAll([], [])         = []
	 * ArrayUtils.addAll([null], [null]) = [null, null]
	 * ArrayUtils.addAll(["a", "b", "c"], ["1", "2", "3"]) = ["a", "b", "c", "1", "2", "3"]
	 * </pre>
	 * 
	 * @param <T>
	 *            the component type of the array
	 * @param array1
	 *            the first array whose elements are added to the new array, may
	 *            be {@code null}
	 * @param array2
	 *            the second array whose elements are added to the new array,
	 *            may be {@code null}
	 * @return The new array, {@code null} if both arrays are {@code null}. The
	 *         type of the new array is the type of the first array, unless the
	 *         first array is null, in which case the type is the same as the
	 *         second array.
	 * @since 2.1
	 * @throws IllegalArgumentException
	 *             if the array types are incompatible
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] addAll(T[] array1, T... array2) {
		if (array1 == null) {
			return clone(array2);
		} else if (array2 == null) {
			return clone(array1);
		}
		final Class<?> type1 = array1.getClass().getComponentType();
		// OK, because array is of type T
		T[] joinedArray = (T[]) Array.newInstance(type1, array1.length
				+ array2.length);
		System.arraycopy(array1, 0, joinedArray, 0, array1.length);
		try {
			System.arraycopy(array2, 0, joinedArray, array1.length,
					array2.length);
		} catch (ArrayStoreException ase) {
			// Check if problem was due to incompatible types
			/*
			 * We do this here, rather than before the copy because: - it would
			 * be a wasted check most of the time - safer, in case check turns
			 * out to be too strict
			 */
			final Class<?> type2 = array2.getClass().getComponentType();
			if (!type1.isAssignableFrom(type2)) {
				throw new IllegalArgumentException("Cannot store "
						+ type2.getName() + " in an array of "
						+ type1.getName(), ase);
			}
			throw ase; // No, so rethrow original
		}
		return joinedArray;
	}

	// Clone
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Shallow clones an array returning a typecast result and handling
	 * {@code null}.
	 * </p>
	 * 
	 * <p>
	 * The objects in the array are not cloned, thus there is no special
	 * handling for multi-dimensional arrays.
	 * </p>
	 * 
	 * <p>
	 * This method returns {@code null} for a {@code null} input array.
	 * </p>
	 * 
	 * @param <T>
	 *            the component type of the array
	 * @param array
	 *            the array to shallow clone, may be {@code null}
	 * @return the cloned array, {@code null} if {@code null} input
	 */
	public static <T> T[] clone(T[] array) {
		if (array == null) {
			return null;
		}
		return array.clone();
	}

	/**
	 * 转换为String数组
	 * 
	 * @param array
	 * @return
	 */
	public static String[] toStringArray(List<String> array) {
		return array.toArray(new String[] {});
	}

	/**
	 * 返回String数组
	 * 
	 * @return
	 */
	public static String[] toStringArray(String... strings) {
		return strings;
	}

	/**
	 * 填充值返回一个数组.
	 * 
	 * @param value
	 * @param length
	 * @return
	 */
	public static String[] fillArray(String value, int length) {
		String[] result = new String[length];
		Arrays.fill(result, value);
		return result;
	}

	/**
	 * 转换一个beanList到array,只会转换不为null的字段
	 * 
	 * @param entities
	 *            * @return
	 */
	public static <T> Object[][] coverBeansToArray(Collection<T> entities) {
		Validate.checkState(entities.size() > 0, "必须最少有一个对象!");
		List<String> fieldNames = Lists.newArrayList();
		T one = entities.iterator().next();
		Field[] fields = Reflect.getAllFields(one);
		for (Field field : fields) {
			String fieldName = field.getName();
			Object value = Reflect.getFieldValue(one, fieldName);
			if (value != null) {
				fieldNames.add(fieldName);
			}
		}
		Object[][] data = new Object[entities.size()][fieldNames.size()];
		int i = 0;
		for (T entity : entities) {
			for (int j = 0; j < fieldNames.size(); j++) {
				data[i][j] = Reflect.getFieldValue(entity, fieldNames.get(j));
			}
			i++;
		}
		return data;
	}

	public static <T> boolean isNotNullOrEmpty(Collection<T> collection) {
		return collection != null && !collection.isEmpty();
	}

	public static <T> boolean isNullOrEmpty(Collection<T> collection) {
		return collection == null || collection.isEmpty();
	}
}
