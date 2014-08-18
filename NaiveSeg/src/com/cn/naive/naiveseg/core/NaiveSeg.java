package com.cn.naive.naiveseg.core;

import java.util.List;

/**
 * 分词器主类，按照顺序分别进行逆向最大匹配、数字识别、英文识别、日期识别、姓名识别，只需要逆向最大匹配的可以只使用{@link BmmSegment}
 * 
 * @author luogang
 *
 */
public class NaiveSeg {
	public List<String> analyze(String str) {
		BmmSegment segment = new BmmSegment();
		Handler handler = new NumberHandler();
		handler.addSuccessor(new EnglishHandler());
		handler.addSuccessor(new DatetimeHandler());
		handler.addSuccessor(new NameHandler());
		List<String> list = handler.handle(segment.getWordList(str));
		return list;
	}
}
