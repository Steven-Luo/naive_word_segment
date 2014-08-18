package com.cn.naive.naiveseg.core;

import java.util.ArrayList;
import java.util.List;

import com.cn.naive.naiveseg.db.DatabaseService;
import com.cn.naive.naiveseg.util.NullValUtil;

public class NameHandler extends Handler
{
	@Override
	public List<String> handle(List<String> originalList)
	{
		DatabaseService.init();
		List<String> result = new ArrayList<String>();
		String tmp = "";
		for (int i = 0; i < originalList.size(); i++)
		{
			String word = originalList.get(i);
			if (isFamilyName(word))
			{
				tmp = tmp + word;
			}
			else
			{
				if (!NullValUtil.isEmpty(tmp)) // tmp非空，说明可能已经加入了姓氏，下面开始判断是不是人名
				{
					if (word.length() > 2) // tmp后面一个词的长度>2，此时认为不可能是人名了，默认支持的最长名字为2（不包括姓氏）
					{
						result.add(tmp);
						tmp = "";
					}
					else
					{ // 加入tmp整个长度的判断
						if (word.length() == 1)
						{
							if (isDoubleName1(word))
							{
								if (i < originalList.size() - 1) // 还没有到最后一个字
								{
									String nextWord = originalList.get(++i);
									if (isDoubleName2(nextWord)) // 是双字名的第二个字
									{
										tmp = tmp + word + nextWord; // 将姓氏，名字第一个字、第二个字拼接起来
										result.add(tmp);
										tmp = "";
										continue;
									}
									else
									// 不是双字名的第二个字
									{
										result.add(tmp);
										tmp = "";
										--i;
									}
								}
								else
								// 已经是最后一个字了
								{
									result.add(tmp);
									tmp = "";
								}
							}
							else
							{
								if (isSingleName(word))
								{
									tmp = tmp + word;
									result.add(tmp);
									tmp = "";
									continue;
								}
								else
								{
									result.add(tmp);
									tmp = "";
								}
							}
						}
						else
						{
							String str1 = word.substring(0, 1);
							String str2 = word.substring(1, 2);
							if (isDoubleName1(str1) && isDoubleName2(str2))
							{
								tmp = tmp + word;
								result.add(tmp);
								tmp = "";
								continue;
							}
							else
							{
								result.add(tmp);
								tmp = "";
							}
						}
					}
				}
				result.add(word);
			}
		}
		if (!NullValUtil.isEmpty(tmp))
		{
			result.add(tmp);
		}
		DatabaseService.close();
		
		return super.handle(result);
	}
	
	public boolean isFamilyName(String str)
	{
		return DatabaseService.query("chinese_family_name", "name", str);
	}
	
	public boolean isDoubleName1(String str)
	{
		return DatabaseService.query("chinese_double_name1", "name", str);
	}
	
	public boolean isDoubleName2(String str)
	{
		return DatabaseService.query("chinese_double_name2", "name", str);
	}
	
	public boolean isSingleName(String str)
	{
		return DatabaseService.query("chinese_single_name", "name", str);
	}
}
