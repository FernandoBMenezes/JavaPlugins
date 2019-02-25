package com.monderdragon.loockeventos.arquivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Leitura {
	public static String Ler(String Local) {
		try {
			File Arquivo = new File(Local);
			FileReader ALido = new FileReader(Arquivo);
			BufferedReader Br = new BufferedReader(ALido);
			StringBuffer Ler = new StringBuffer();
			String line;
			while ((line = Br.readLine()) != null) {
				Ler.append(line);
				Ler.append("\n");
			}
			ALido.close();
			return Ler.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String Ler(File Local) {
		try {
			File Arquivo = Local;
			FileReader ALido = new FileReader(Arquivo);
			BufferedReader Br = new BufferedReader(ALido);
			StringBuffer Ler = new StringBuffer();
			String line;
			while ((line = Br.readLine()) != null) {
				Ler.append(line);
				Ler.append("\n");
			}
			ALido.close();
			return Ler.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String Ler(String local, String prefixo) {
		BufferedReader buff;
		try {
			String leitura = "";
			InputStreamReader iso = new InputStreamReader(new FileInputStream(local), "ISO-8859-1");
			buff = new BufferedReader(iso);
			String linha = "";
			while ((linha = buff.readLine()) != null) {
				if (linha.startsWith(prefixo)) {
					leitura = leitura + linha;
				}
			}
			String retornar = leitura.toString();
			if (retornar.contains(":"))
				retornar = retornar.replaceFirst(":", "");
			if (retornar.contains(";"))
				retornar = retornar.replace(";", "");
			if (retornar.contains(prefixo))
				retornar = retornar.replaceFirst(prefixo, "");
			iso.close();
			buff.close();
			return retornar;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String pegarValor(String chaveValor) {
		String retorno = chaveValor;
		int doisPontos = 0;
		for (int i = 0; i < chaveValor.length(); i++)
			if (chaveValor.toCharArray()[i] == ':') {
				doisPontos = i+1;
				break;
			}
		retorno = retorno.substring(doisPontos);
		retorno = retorno.replace(";", "");
		return retorno;
	}

	public static String Linha(String Local, String Prefixo) {
		try {
			File Arquivo = new File(Local);
			FileReader ALido = new FileReader(Arquivo);
			BufferedReader Br = new BufferedReader(ALido);
			StringBuffer Ler = new StringBuffer();
			String line;
			while ((line = Br.readLine()) != null) {
				if (line.startsWith(Prefixo)) {
					Ler.append(line);
				}
			}
			Br.close();
			ALido.close();
			return Ler.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String[] Linhas(String local) {
		try {
			File arquivo = new File(local);
			FileReader ALido = new FileReader(arquivo);
			BufferedReader Br = new BufferedReader(ALido);
			StringBuffer Ler = new StringBuffer();
			String line;
			while ((line = Br.readLine()) != null) {
				Ler.append(line);
			}
			Br.close();
			ALido.close();
			String[] SLer = Ler.toString().substring(0, Ler.toString().length() - 1).split(";");
			if (SLer.length > 0 && !SLer[0].equalsIgnoreCase(null) && SLer[0] != "" && SLer[0] != " ") {
				return SLer;
			} else {
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String[] Linhas(File arquivo) {
		try {
			FileReader ALido = new FileReader(arquivo);
			BufferedReader Br = new BufferedReader(ALido);
			StringBuffer Ler = new StringBuffer();
			String line;
			while ((line = Br.readLine()) != null) {
				Ler.append(line);
			}
			Br.close();
			ALido.close();
			String[] SLer = Ler.toString().substring(0, Ler.toString().length() - 1).split(";");
			if (SLer.length > 0 && !SLer[0].equalsIgnoreCase(null) && SLer[0] != "" && SLer[0] != " ") {
				return SLer;
			} else {
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void Linher(File dir) {
		if (Arquivo.Existe(dir) == true) {
			if (dir.isDirectory()) {
				String[] children = dir.list();
				for (int i = 0; i < children.length; i++) {
					System.out.println(children[i]);
					Linher(new File(dir, children[i]));
				}
			}
		}
	}
}

