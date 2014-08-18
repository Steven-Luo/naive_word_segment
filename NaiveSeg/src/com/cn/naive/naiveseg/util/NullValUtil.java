package com.cn.naive.naiveseg.util;

import java.util.List;

public class NullValUtil
{
	public static boolean isEmpty(String str)
	{
		return str == null ? true : "".equals(str.trim());
	}
	
	public static boolean isEmpty(List<?> list)
	{
		return list == null ? true : list.size() == 0;
	}
}
