package view;

import java.io.IOException;

import Controller.FifaController;
import Controller.IFifaController;
public class Menu {

	public static void main(String[] args) {
		IFifaController arqFifa = new FifaController();

		String path = "C:\\TEMP";
		String name = "data.csv";

		try {
			arqFifa.desmpilhaBonsBrasileiros(arqFifa.empilhaBrasileiros(path, name));
			arqFifa.buscaListaBonsJovens(arqFifa.listaRevelacoes(path, name));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	}


