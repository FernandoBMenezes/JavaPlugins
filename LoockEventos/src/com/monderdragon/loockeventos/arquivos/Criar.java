package com.monderdragon.loockeventos.arquivos;

import java.io.File;
import java.util.Formatter;

public class Criar {
	public static void Pasta(File Arq) {
		try {
			if (Arquivo.Existe(Arq) == false) {
				Arq.mkdirs();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void Bloco(String Local) {
		Formatter b;
		try {
			b = new Formatter(Local);
			b.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void Bloco(File Local) {
		Formatter b;
		try {
			b = new Formatter(Local);
			b.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
