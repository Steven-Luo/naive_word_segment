package com.cn.naive.naiveseg.core;

import java.util.ArrayList;
import java.util.List;

import com.cn.naive.naiveseg.util.NullValUtil;

public class EnglishHandler extends Handler
{
	@Override
	public List<String> handle(List<String> originalList)
	{
		List<String> result = new ArrayList<String>();
		String tmp = "";
		for (int i = 0; i < originalList.size(); i++)
		{
			String word = originalList.get(i);
			if(isEnglish(word))
			{
				tmp=tmp+word;
			}
			else
			{
				if(!NullValUtil.isEmpty(tmp))
				{
					result.add(tmp);
					tmp="";
				}
				result.add(word);
			}
		}
		if(!NullValUtil.isEmpty(tmp))
		{
			result.add(tmp);
		}
		
		return super.handle(result);
	}
	
	public boolean isEnglish(String str)
	{
		if (str.matches("[0-9a-zA-Z\\\\\\-\\_\\.•\\/]*"))
			return true;
		else
			return false;
	}
}
