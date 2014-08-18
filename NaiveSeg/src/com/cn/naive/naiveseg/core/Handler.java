package com.cn.naive.naiveseg.core;

import java.util.ArrayList;
import java.util.List;

public abstract class Handler
{
	protected List<Handler> successor;
	
	public void addSuccessor(Handler successor)
	{
		if(this.successor==null)
			this.successor=new ArrayList<Handler>();
		this.successor.add(successor);
	}
	
	public List<String> handle(List<String> originalList)
	{
		List<String> result = originalList;
		if (successor != null)
		{
			for (int i = 0; i < successor.size(); i++)
			{
				result = successor.get(i).handle(result);
			}
		}
		return result;
	}
}
