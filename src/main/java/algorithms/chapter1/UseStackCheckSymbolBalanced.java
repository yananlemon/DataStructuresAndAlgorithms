package algorithms.chapter1;

public class UseStackCheckSymbolBalanced {

	public static void main(String[] args) {
		/*if(args == null || args.length < 1){
			System.out.println("Useage:algorithms.chapter1.UseStackCheckSymbolBalanced ([])");
		}else{
			System.out.println(checkSymbolBalanced(args));
		}*/
		String[] array = {"[","(","]",")"};
		//String[] array = {"[","(",")","]","{","}","{","[","(",")","(",")","]","(",")","}"};
		System.out.println(checkSymbolBalanced(array));
		
	}
	
	public static boolean checkSymbolBalanced(String[] array){
		Stack<String> stack = new Stack<String>();
		for(String symbol : array){
			if(checkWhetherOpenSymbol(symbol)){
				stack.push(symbol);
			}else if(checkWhetherCloseSymbol(symbol)){
				if(stack.isEmpty()){
					return false;
				}
				else{
					String openSymbol = stack.pop();
					if(!checkWhetherMatch(openSymbol, symbol))
						return false;
				}
			}
		}
		if(stack.isEmpty())
			return true;
		else
			return false;
	}
	
	private static boolean checkWhetherOpenSymbol(String symbol){
		if(symbol.equals("{") || symbol.equals("[") || symbol.equals("("))
			return true;
		return false;
	}
	
	private static boolean checkWhetherCloseSymbol(String symbol){
		if(symbol.equals("}") || symbol.equals("]") || symbol.equals(")"))
			return true;
		return false;
	}
	
	private static boolean checkWhetherMatch(String openSymbol,String closeSymbol){
		if(openSymbol.equals("{") && closeSymbol.equals("}"))
			return true;
		if(openSymbol.equals("[") && closeSymbol.equals("]"))
			return true;
		if(openSymbol.equals("(") && closeSymbol.equals(")"))
			return true;
		return false;
	}

}
