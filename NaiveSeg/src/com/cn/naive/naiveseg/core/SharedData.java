package com.cn.naive.naiveseg.core;

import java.util.HashSet;

public class SharedData
{
	public static HashSet<String> NumberSet;
	static
	{
		NumberSet = new HashSet<String>();
		NumberSet.add("０");
		NumberSet.add("一");
		NumberSet.add("二");
		NumberSet.add("三");
		NumberSet.add("四");
		NumberSet.add("五");
		NumberSet.add("六");
		NumberSet.add("七");
		NumberSet.add("八");
		NumberSet.add("九");
		NumberSet.add("十");
		NumberSet.add("百");
		NumberSet.add("千");
		NumberSet.add("万");
		NumberSet.add("亿");
		NumberSet.add("零");
		NumberSet.add("壹");
		NumberSet.add("贰");
		NumberSet.add("叁");
		NumberSet.add("伍");
		NumberSet.add("陆");
		NumberSet.add("柒");
		NumberSet.add("捌");
		NumberSet.add("玖");
		NumberSet.add("拾");
		NumberSet.add("佰");
		NumberSet.add("仟");
	}
}
