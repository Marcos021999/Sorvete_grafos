import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;


public class App{
    public static void main(String[] args) {
        HashSet<String> ad = new HashSet<>();
        BufferedReader in = null;
        String arq = "";
        Scanner input = new Scanner(System.in);
        System.out.print("Digite o nome do arquivo a ser lido: ");
        arq = input.next();
        arq += ".txt";

    try {
        in = new BufferedReader(new FileReader(arq));//leitura do documento
        String read = null;
        String[] arr = new String[2];
        while ((read = in.readLine()) != null) {//lê uma linha de cada vez
            arr = read.split(" -> ");//quebra cada linha em duas partes
            ad.add(arr[0]);//adiciona a primeira parte da linha, sabor de origem, no HashSet ad
            ad.add(arr[1]);//adiciona a segunda parte da linha, sabor de destino, no HashSet ad
        }

        HashMap<String, Integer> map = new HashMap<>();//mantém a informação do índice do sabor, tendo como chave o sabor e valor o inteiro       
        int i = 0;
        for (String sh : ad) {//adiciona os sabores de sorvete em um HashMap como chaves
            map.put(sh, i);//Como valor do HashMap é adicionado um número, que será o índice da matriz
            i++;
        }
        Boolean[][] matrix = new Boolean[ad.size()][ad.size()];//Cria uma matriz de boolean tendo como largura e altura o número de sabores contidos no documento
        for(int x = 0; x < ad.size(); x++){
            for (int j = 0; j < ad.size(); j++) {
                matrix[x][j] = false;//inicializa a matriz como false
            }
        }

        in = new BufferedReader(new FileReader(arq));

        while ((read = in.readLine()) != null) {//lê o arquivo novamente

            arr = read.split(" -> ");

            matrix[map.get(arr[0])][map.get(arr[1])] = true;//adiciona as relações entre os sabores


        }



        
    Stack<Integer> aux = new Stack<Integer>();

    for(int w = 0; w < 2 ; w++){//percorre a matriz duas vezes
        for(int x = 0; x < ad.size(); x++){
            for (int j = 0; j < ad.size(); j++) {
                if(matrix[x][j]){// se existeir uma aresta de x para j
                    for(int a = 0; a < ad.size(); a++){
                        if(matrix[j][a]){//verifica se existe de j para a, caso exista, a é colocado na pilha
                            aux.add(a);
                        }
                    }
                    while(!aux.isEmpty()){//enquanto a pilha não extiver vazia
                        int valAux = aux.pop();//retira do topo da pilha o valor de a
                        matrix[x][valAux] = true;//cria a aresta de x para a
                    }
                }
            }
        }
    }//o(v^3)
        
        int sab2 = 0;
        for(int x = 0; x < ad.size(); x++){
            for (int j = 0; j < ad.size(); j++) {
                if(matrix[x][j]){//se existe uma aresta de x para j, então existe uma combinação de 2 sabores
                    sab2++;//o contador de 2 sabores é incrementado
                }
            }
        }
        System.out.println("Combinacoes possiveis de 2 sabores: " + sab2);

        int sab3 = 0;

        for(int x = 0; x < ad.size(); x++){
            for (int j = 0; j < ad.size(); j++) {
                if(matrix[x][j]){//se existe uma aresta de x para j
                    for(int a = 0; a < ad.size(); a++){
                        if(matrix[j][a]){//se existe uma aresta de j para a, então existe uma combinação de 3 sabores
                            sab3++;//o contador de 3 sabores é incrementado
                        }
                    }

                }
            }
        }
        System.out.println("Combinacoes possiveis de 3 sabores: " + sab3);


    } catch (IOException e) {
        System.out.println("There was a problem: " + e);
        e.printStackTrace();
        }        
    }


}


