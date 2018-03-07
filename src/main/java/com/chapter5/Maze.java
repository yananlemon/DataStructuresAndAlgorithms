package com.chapter5;

/**
 * <p>Title: Maze</p>  
 * <p>Description:利用递归可以极大地简化程序。使用递归求解迷宫。</p>  
 * @author yanan  
 * @date 2018年3月7日
 */
public class Maze {

	static int[][] maze={
		{1,1,1,1,1},
		{1,0,0,0,1},
		{1,0,1,0,1},
		{1,0,0,0,1},
		{1,1,1,1,1},
	};
	
	static int[] entry={1,1};//入口
	static int[] exit={3,3};//出口
	static boolean successed=false;
	
	public static void main(String[] args) {
		exitMaze(entry[0], entry[1]);
		if(successed==false){
			System.out.println("没有找到可以到达出口的迷宫路径");
		}
	}
	
	static void exitMaze(int x,int y){
		
		//标识已经走过
		maze[x][y]=2;
		
		//判断是否找到出口
		if(x==exit[0] && y==exit[1]){
			System.out.println("发现一条路径:");
			//打印迷宫路径
			for(int i=0;i<5;i++){
				for(int k=0;k<5;k++){
					if(maze[i][k]==2){
						System.out.print("*");
					}else{
						System.out.print(maze[i][k]);
					}
				}
				System.out.println();
			}
			successed=true;
		}
		
		//递归尝试右,下，左，上四个方向
		if(maze[x][y+1]==0)
			exitMaze(x, y+1);
		if(maze[x+1][y]==0)
			exitMaze(x+1, y);
		if(maze[x][y-1]==0)
			exitMaze(x, y-1);
		if(maze[x-1][y]==0)
			exitMaze(x-1, y);
		
		//回退一步求解其他路径
		maze[x][y]=0;
	}

}
