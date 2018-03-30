package com.chapter4;


import java.util.ArrayList;

import com.chapter4.text.MyStack;

/**
 * <p>Maze</p>  
 * <p>案例分析，迷宫问题 </p>  
 * @author yanan  
 * @date 2018年3月30日
 */
public class Maze {

	static String[][] maze = {
			{"1","1","1","1","1","1","1"},
			{"1","e","0","0","0","0","1"},
			{"1","1","1","0","1","1","1"},
			{"1","0","0","0","0","0","1"},
			{"1","0","0","m","0","0","1"},
			{"1","1","1","1","1","1","1"},
	};
	
	public static void main(String[] args) {
		/*System.out.println("输入一个长方形(4行3列)的迷宫使用下面的字符\n");
		System.out.println("\nm - 入口\ne - 出口\n1 - 墙\n0 - 路\n");
		System.out.println("一次输入一行，以end结束\n");
		String input;
		Scanner in = new Scanner(System.in);
		in.useDelimiter("\n");
		input = in.next();
		System.out.println(input);
		while(!input.equals("end")){
			for(int i=0;i<maze.length;i++){
				for(int i=0;i<maze.length;i++){
					
				}
			}
			input = in.next();
			System.out.println(input);
		}
		in.close();*/
		System.out.println("原始迷宫：");
		for(int i=0;i<maze.length;i++){
			for(int k=0;k<maze[i].length;k++){
				System.out.print(maze[i][k]+"\t");
			}
			System.out.println();
		}
		exitMaze();
		System.out.println("迷宫路径：");
		for(int i=0;i<maze.length;i++){
			for(int k=0;k<maze[i].length;k++){
				System.out.print(maze[i][k]+"\t");
			}
			System.out.println();
		}
	}
	
	static MyStack<Cell> stack = new MyStack<Cell>();
	
	static void exitMaze(){
		ArrayList<Cell> paths = new ArrayList<Cell>();
		Cell exitCell = new Cell(1, 1);//出口
		Cell entryCell = new Cell(4, 3);//入口
		Cell currentCell = entryCell;
		//循环直到到达出口
		while(!currentCell.equals(exitCell)){
			
			int row = currentCell.getRow();
			int col = currentCell.getCol();
			
			//标记当前路径已经访问过
			if(!currentCell.equals(entryCell)){
				maze[row][col] = ".";
				paths.add(new Cell(row,col));
			}
			//按照下，上，左，右，四个方向入栈，也就是按照右，左，下，上这样的方向访问迷宫
			pushUnVisited(row-1, col);
			pushUnVisited(row+1, col);
			pushUnVisited(row, col-1);
			pushUnVisited(row, col+1);
			// 如果栈为空，则没有找到可以到达出口的路径
			if(stack.isEmpty()){
				System.out.println("没有路径可以到达出口！");
				break;
			}else{
				currentCell = stack.pop();
			}
		}
		System.out.println("成功找到出口，路径如下：");
		for (int i = 0; i < paths.size(); i++) {
			System.out.print(paths.get(i));
		}
		
	}
	
	static void pushUnVisited(int row,int col){
		if(maze[row][col] .equals("0") || maze[row][col].equals("e")){
			stack.push(new Cell(row,col));
			Cell cell = new Cell(row, col);
			if((maze[row-1][col].equals("1")||maze[row-1][col].equals(".")||maze[row-1][col].equals("m"))
					&& (maze[row+1][col].equals("1")||maze[row+1][col].equals(".")||maze[row+1][col].equals("m"))
					&& (maze[row][col-1].equals("1")||maze[row][col-1].equals(".")||maze[row][col-1].equals("m"))
					&& (maze[row][col+1].equals("1")||maze[row][col+1].equals(".")||maze[row][col+1].equals("m"))){
				
				System.out.println(cell+"是死胡同");
				
			}
			
		}
	}

}
class Cell{
	int row,col;
	public Cell(int row,int col){
		this.row = row;
		this.col = col;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + row;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		if (col != other.col)
			return false;
		if (row != other.row)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "("+this.row+","+this.col+")";
	}
	
	
}
