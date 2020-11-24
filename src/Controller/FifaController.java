package Controller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;


public class FifaController implements IFifaController {
	
	
	public FifaController ()  {
		super();
	}

	public Stack<String> empilhaBrasileiros(String caminho, String nome) throws IOException {

		Stack<String> pilha = new Stack<String>();

		File arq = new File(caminho, nome);
		if (arq.exists() && arq.isFile()) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			// Ignorando cabeçalho
			if (linha.contains("Line,ID,Name")) {
				linha = buffer.readLine();
			}
			while (linha != null) { // procurando End Of File (EOF)
				String[] jogador = linha.split(",");
				if (jogador[5].equals("Brazil")) {
					pilha.push(linha);
				}
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} else {
			throw new IOException("Arquivo inválido");
		}

		return pilha;
	}



	
	public void desmpilhaBonsBrasileiros(Stack<String> pilha) throws IOException {

		System.err.println("LISTA BONS BRASILEIROS");
		
		// verifica tamanho da pilha
		int tamanhoPilha = pilha.size();
		// desempilha
		for (int i = 0; i < tamanhoPilha; i++) {
			String jogador = pilha.pop();
			String[] jogadorBr = jogador.split(",");
			int overall= Integer.parseInt(jogadorBr[7]);
			if(overall>80) {
				System.out.println(jogadorBr[2] + ", Overall: " +jogadorBr[7]);
			}
		}
	}

	
	public List<String> listaRevelacoes(String caminho, String nome) throws IOException {
		List<String> lista = new LinkedList<String>();

		File arq = new File(caminho, nome);
		if (arq.exists() && arq.isFile()) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			// Ignorando cabeçalho
			if (linha.contains("Line,ID,Name")) {
				linha = buffer.readLine();
			}
			while (linha != null) { // procurando End Of File (EOF)
				String[] jogador = linha.split(",");
				int idade=Integer.parseInt(jogador[3]);
				if (idade<=20) {
					lista.add(linha);
				}
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} else {
			throw new IOException("Arquivo inválido");
		}

		return lista;
	}

	
	public void buscaListaBonsJovens(List<String> lista) throws IOException {
		
		System.err.println("LISTA BONS JOVENS");
		
		// percorre lista
		Iterator<String> it = lista.iterator();
		while (it.hasNext()){
			String jogador = it.next();
			String[] jogadorNovo = jogador.split(",");
			int overall= Integer.parseInt(jogadorNovo[7]);
			if(overall>80) {
				System.out.println(jogadorNovo[2] + ", Idade: "+jogadorNovo[3] +", Overall: " +jogadorNovo[7]);
			}
		}
		}	
}
