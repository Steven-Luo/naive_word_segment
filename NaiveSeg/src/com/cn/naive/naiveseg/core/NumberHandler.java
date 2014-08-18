package com.cn.naive.naiveseg.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.cn.naive.naiveseg.util.NullValUtil;

public class NumberHandler extends Handler
{
	public static HashSet<String> metricSet;
	
	static
	{
		metricSet = new HashSet<String>();
		metricSet.add("纳米");
		metricSet.add("微米");
		metricSet.add("毫米");
		metricSet.add("厘米");
		metricSet.add("分米");
		metricSet.add("米");
		metricSet.add("千米");
		metricSet.add("公里");
		metricSet.add("英里");
		metricSet.add("海里");
		metricSet.add("平方厘米");
		metricSet.add("平方米");
		metricSet.add("平方千米");
		metricSet.add("平方公里");
		metricSet.add("立方厘米");
		metricSet.add("立方分米");
		metricSet.add("立方米");
		metricSet.add("立方千米");
		metricSet.add("毫克");
		metricSet.add("克");
		metricSet.add("千克");
		metricSet.add("厘");
		metricSet.add("分");
		metricSet.add("寸");
		metricSet.add("尺");
		metricSet.add("丈");
		metricSet.add("亩");
		metricSet.add("公顷");
		metricSet.add("担");
		metricSet.add("斤");
		metricSet.add("钱");
		metricSet.add("斗");
		metricSet.add("升");
		metricSet.add("加仑");
		metricSet.add("合");
		metricSet.add("石");
		metricSet.add("级");
		metricSet.add("型");
		metricSet.add("岁");
	}
	
	public List<String> handleNum(List<String> originalList)
	{
		List<String> result = new ArrayList<String>();
		String tmp = "";
		for (int i = 0; i < originalList.size(); i++)
		{
			String word = originalList.get(i);
			if (isNumber(word) || isMetric(word))
			{
				tmp = tmp + word;
			}
			else
			{
				if (word.length() >= 2) // bug 出现二００四年陆月五日3斤白菜
				{
					String firstChar = word.substring(0, 1);
					String lastChar = word.substring(
					    word.length() - 1,
					    word.length());
					if (isNumber(firstChar) && isMetric(lastChar))
					{
						tmp = tmp + word;
						continue;
					}
				}
				
				if (!NullValUtil.isEmpty(tmp))
				{
					result.add(tmp);
					tmp = "";
				}
				result.add(word);
			}
		}
		if (!NullValUtil.isEmpty(tmp))
		{
			result.add(tmp);
		}
		
		return result;
	}
	
	public List<String> handleChineseNum(List<String> originalList)
	{
		List<String> result = new ArrayList<String>();
		String tmp = "";
		for (int i = 0; i < originalList.size(); i++)
		{
			String word = originalList.get(i);
			if (isChineseNum(word) || isMetric(word))
			{
				tmp = tmp + word;
			}
			else
			{
				if (word.length() >= 2)
				{
					String firstChar = word.substring(0, 1);
					String lastChar = word.substring(
					    word.length() - 1,
					    word.length());
					if (isChineseNum(firstChar) && isMetric(lastChar))
					{
						tmp = tmp + word;
						continue;
					}
				}
				
				if (!NullValUtil.isEmpty(tmp))
				{
					result.add(tmp);
					tmp = "";
				}
				result.add(word);
			}
		}
		if (!NullValUtil.isEmpty(tmp))
		{
			result.add(tmp);
		}
		
		return result;
	}
	
	@Override
	public List<String> handle(List<String> originalList)
	{
		List<String> result = handleNum(originalList);
		result = handleChineseNum(result);
		
		return super.handle(result);
	}
	
	public boolean isNumber(String str)
	{
		if (str.matches("[0-9]*"))
			return true;
		else
			return false;
	}
	
	public boolean isMetric(String str)
	{
		if (metricSet.contains(str))
			return true;
		else
			return false;
	}
	
	public boolean isChineseNum(String str)
	{
		if (SharedData.NumberSet.contains(str))
			return true;
		else
			return false;
	}
}
