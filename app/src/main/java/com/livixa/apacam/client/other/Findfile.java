package com.livixa.apacam.client.other;

/**
 * @author : �Թ���
 * @version ��2012-12-28 ����10:49:01 
 */
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Findfile {
	List<String> list = new ArrayList<String>();

	public Findfile(String init) {
		System.out.println(init);
		start(init); // ��ʼ���� �Ǵ������Ĳ���
	}

	public void start(String yiji) {
		File file = new File(yiji);
		if (file.exists() && file.canRead()) // ��һ��������linux�µģ����Զ�Ҫ�ж����Ƿ��ܶ�
		{
			File[] files = new File[] {};
			files = file.listFiles();
			if (files.length == 0)
				return;// ����,���һ����Ϊ�յ�ʱ�򣬾�ֱ�ӷ��أ�
			for (int i = 0; i < files.length; i++) {

				list.add(files[i].getAbsolutePath());
				// �����jpg��png�ͼ���list��

				if (files[i].isDirectory() && files[i].canRead()) {
					Find(files[i].getAbsolutePath());
					// �����Ŀ¼������Find����ȥ�ң� ����ڶ���
				}
			}
		} else {
			return;
		}

	}

	public List<String> getList() {
		return list;
	}

	public int getSize() {
		return list.size();
	}

	public void Find(String xx) {
		File file = new File(xx);
		if (file.exists()) {
			File[] files = new File[] {};
			files = file.listFiles();
			if (files.length == 0)
				return; // ����ѭ���鿴��ʱ�򣬿��Ƿ��ǿյģ�����ǿյģ���ֱ�ӷ��ؽ�����һ��
			for (int i = 0; i < files.length; i++) {

				list.add(files[i].getAbsolutePath());

				if (files[i].isDirectory() && files[i].canRead()) // ��Ȩ�ޣ������ж����Ƿ��ܶ�
				{
					Find(files[i].getAbsolutePath());
					// �õ��ǵݹ�ķ����������Ŀ¼�Ļ����ͽ�����һ���� �ٽ���.... N��
				}
			}
		} else {
			return;
		}
	}
}
