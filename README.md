naive_word_segment
==================

###基于逆向最大匹配的中文分词器
基于逆向最大匹配的中文分词器，增加了对英文、特殊符号、数字的处理，对中文姓名也利用规则匹配的方式进行了识别。

####用法
	NaiveSeg naiveSeg=new NaiveSeg();
	List<String> list = naiveSeg.analyze(str);
		
	for (int i = 0; i < list.size(); i++) {
		System.out.print(list.get(i));
		System.out.print("/");
	}
	
####示例
	创新工场董事长李开复曾于1998年到2005年供职于Microsoft公司，与Bill.Gates等公司高管都有过深入交流，曾参与开发过Vista。2005年7月加入Google公司。
	
使用上面的方法进行分词，结果为

	创新/工场/董事长/李开复/曾于/1998年/到/2005年/供职/于/Microsoft/公司/，/与/Bill.Gates/等/公司/高管/都/有过/深入/交流/，/曾/参与/开/发过/Vista/。/2005年7月/加入/Google/公司/。/
	
####配置方法
**需要安装MySQL**
>
首先导入sql文件夹下的word_segment.sql到MySQL，然后修改src目录下db.properties配置文件