package com.cn.naive.naiveseg.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.cn.naive.naiveseg.db.DatabaseService;

/**
 * 逆向最大匹配实现类，最大匹配长度为10，可以修改MAX_LENGTH更改匹配长度
 * 
 * @author luogang
 *
 */
public class BmmSegment {
	public static final int MAX_LENGTH = 10;

	/**
	 * 反向最大匹配的实现
	 * 
	 * @param str
	 * @return
	 */
	public List<String> getWordList(String str) {
		List<String> list = new ArrayList<String>();
		int currentLast = str.length();
		int currentPosition = currentLast - MAX_LENGTH;
		DatabaseService.init();
		while (true) {
			if (currentLast == 0)
				break;
			else {
				if (currentPosition < 0)
					currentPosition = 0;
				String key = str.substring(currentPosition, currentLast);
				if (DatabaseService.query("words", "word", key)) // 在词典中找到了单词，直接添加
				{
					list.add(key);
					currentLast = currentPosition;
					currentPosition = currentLast - MAX_LENGTH;
					if (currentPosition < 0)
						currentPosition = 0;
				} else { // 没有找到
					if (currentLast == currentPosition + 1) // 当前已经只剩一个长度为1的词了
					{
						list.add(key);
						currentLast = currentPosition;
						currentPosition = currentLast - MAX_LENGTH;
						if (currentPosition < 0)
							currentPosition = 0;
					} else { // 当前长度不为1，缩短查找长度
						currentPosition++;
						continue;
					}
				}
			}
		}
		DatabaseService.close();
		Collections.reverse(list);
		return list;
	}
}
