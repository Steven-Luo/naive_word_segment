package com.cn.naive.naiveseg.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.cn.naive.naiveseg.util.NullValUtil;

public class DatetimeHandler extends Handler
{
	public static HashSet<String> datetimeSet;
	
	static
	{
		datetimeSet = new HashSet<String>();
		datetimeSet.add("年");
		datetimeSet.add("年代");
		datetimeSet.add("世纪");
		datetimeSet.add("月");
		datetimeSet.add("日");
		datetimeSet.add("号");
		datetimeSet.add("时");
		datetimeSet.add("分");
		datetimeSet.add("秒");
	}
	
	@Override
	public List<String> handle(List<String> originalList)
	{
		List<String> result = new ArrayList<String>();
		String tmp = "";
		for (int i = 0; i < originalList.size(); i++)
		{
			String word = originalList.get(i);
			if (isNumber(word) || isDateTimeMetric(word))
			{
				tmp = tmp + word;
			}
			else
			{
				// 日期经常连用，因此，有必要做额外处理
				if (word.length() >= 2) // bug 出现二００四年陆月五日3斤白菜
				{
					String firstChar = word.substring(0, 1);
					String lastChar = word.substring(
					    word.length() - 1,
					    word.length());
					if (isNumber(firstChar) && isDateTimeMetric(lastChar))
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
		
		/*if (successor != null)
			result = successor.handle(result);*/
		
		return super.handle(result);
	}
	
	public boolean isNumber(String str)
	{
		if (str.matches("[0-9]*") || SharedData.NumberSet.contains(str))
			return true;
		else
			return false;
	}
	
	public boolean isDateTimeMetric(String str)
	{
		if (datetimeSet.contains(str))
			return true;
		else
			return false;
	}
}
