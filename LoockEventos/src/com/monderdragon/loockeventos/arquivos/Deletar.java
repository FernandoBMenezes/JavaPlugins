package com.monderdragon.loockeventos.arquivos;

import java.io.File;

public class Deletar {
	public static void Del(File dir) {
		if (Arquivo.Existe(dir) == true) {
			if (dir.isDirectory()) {
				String[] children = dir.list();
				for (int i = 0; i < children.length; i++) {
					Del(new File(dir, children[i]));
				}
			}
			dir.delete();
		}
	}
}
