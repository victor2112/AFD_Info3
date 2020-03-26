/*
	Utilice esta clase para guardar la informacion de su
	AFD. NO DEBE CAMBIAR LOS NOMBRES DE LA CLASE NI DE LOS 
	METODOS que ya existen, sin embargo, usted es libre de 
	agregar los campos y metodos que desee.
*/
import java.util.*;
import java.io.*;

public class AFD{
	
	/*
		Implemente el constructor de la clase AFD
		que recibe como argumento un string que 
		representa el path del archivo que contiene
		la informacion del afd (i.e. "Documentos/archivo.afd").
		Puede utilizar la estructura de datos que desee
	*/
	String[] alphabet;
	int total;
	int finalStates[];
	int[][] transitions;
	public AFD(String path){
		Scanner scan = new Scanner("Hola");
		try {
			scan = new Scanner(new File(path));
		}catch(Exception e){}

			alphabet = scan.nextLine().split(",");
			total = Integer.parseInt(scan.nextLine());
			//finalStates = Arrays.asList(Arrays.stream(scan.nextLine().split(",")).map(val -> Integer.parseInt(val)).toArray()).toArray(new Integer[0]);
			String[] finalSt = scan.nextLine().split(",");
			finalStates = new int[finalSt.length];
			for(int i = 0; i<finalSt.length;i++){
				finalStates[i] = Integer.parseInt(finalSt[i]);
			}
			transitions = new int[alphabet.length][total];
			int row = 0;
			while(scan.hasNextLine()){
				StringTokenizer linea = new StringTokenizer(scan.nextLine(), ",");
				for (int column = 0; column < total; column++)
					transitions[row][column] = Integer.parseInt(linea.nextToken());
				row++;
			}
			scan.close();	
		//} catch(Exception e){
		//		System.out.println("El archivo no existe…");
		//	}
	}

	private int getTransitionNumber(char symbol){
		System.out.println(Arrays.toString(alphabet)+"abcedario, letra,"+ Character.toString(symbol)+".");
		for(int i = 0; i < alphabet.length; i++){
			if(alphabet[i].charAt(0)==symbol)
				return i;
		}
		return -1;
	}

	private boolean inFinalState(int state){
		System.out.print("Estado final"+ state+"final state"+ Arrays.toString(finalStates));
		for(int i = 0; i < finalStates.length; i++){
			if(finalStates[i]==state)
				return true;
		}
		return false;
	}
	
	private int parserRecursive(int state,String string){
		if(string.length()!=1){
			return parser(parserRecursive(state,string.substring(0,string.length()-1)),string.charAt(string.length()-1));
		} else {
			return parser(state,string.charAt(0));
		}
	}

	private int parser(int state,char symbol){
		if(!(Character.toString(symbol).equals("")||Character.toString(symbol).equals(" ")
		||Character.toString(symbol).equals("\n")||Character.toString(symbol).equals("\r")))
			return getTransition(state, symbol);
		return state;
	}
	/*
		Implemente el metodo transition, que recibe de argumento
		un entero que representa el estado actual del AFD y un
		caracter que representa el simbolo a consumir, y devuelve 
		un entero que representa el siguiente estado
	*/
	public int getTransition(int currentState, char symbol){
		return transitions[getTransitionNumber(symbol)][currentState];
	}

	/*
		Implemente el metodo accept, que recibe como argumento
		un String que representa la cuerda a evaluar, y devuelve
		un boolean dependiendo de si la cuerda es aceptada o no 
		por el afd
	*/
	public boolean accept(String string){
		System.out.println(string+"Cuerda a parsear");
		return inFinalState(parserRecursive(1,string));
	}

	/*
		El metodo main debe recibir como primer argumento el path
		donde se encuentra el archivo ".afd", como segundo argumento 
		una bandera ("-f" o "-i"). Si la bandera es "-f", debe recibir
		como tercer argumento el path del archivo con las cuerdas a 
		evaluar, y si es "-i", debe empezar a evaluar cuerdas ingresadas
		por el usuario una a una hasta leer una cuerda vacia (""), en cuyo
		caso debe terminar. Tiene la libertad de implementar este metodo
		de la forma que desee. 
	*/
	public static void main(String[] args) throws Exception{
	}
}