package com.colin.utils;

/**
 * Created by colin on 2018/3/30 9:45 .
 */
public class ArrayUtil {
	public static int indexOf(Object o, Object[] elementData) {
		if (o == null) {
			for (int i = 0; i < elementData.length; i++)
				if (elementData[i]==null)
					return i;
		} else {
			for (int i = 0; i < elementData.length; i++)
				if (o.equals(elementData[i]))
					return i;
		}
		return -1;
	}

}