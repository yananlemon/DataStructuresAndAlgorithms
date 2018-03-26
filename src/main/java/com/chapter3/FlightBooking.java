package com.chapter3;

import java.util.Scanner;

import com.chapter3.text.DoublyLinkedList;

/**
 * <p>FlightBooking</p>  
 * <p>机票预订程序</p>  
 * @author yanan  
 * @date 2018年3月26日
 */
public class FlightBooking {

	static Flight flight1=new Flight("波音747", 3,"上海","台北");
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
        char ch;
        do
        {
            System.out.println("\n机票预订程序菜单\n");
            System.out.println("1. 预订机票");
            System.out.println("2. 取消预订");
            System.out.println("3. 查看某人是否预订了机票");
            System.out.println("4. 显示乘客");
            int choice = scan.nextInt();            
            switch (choice){
	            case 1 : 
	            	System.out.println("请输入您的姓名(中文请使用拼音)");
	            	String name=scan.next();
	            	flight1.bookFlight(new User(name));
	                break;                          
	            case 2 : 
	            	System.out.println("请输入您的姓名(中文请使用拼音)");
	            	String nameOfCannel=scan.next();
	            	if(flight1.checkWhetherUserBookingFlight(nameOfCannel)){
	            		System.out.printf("确定取消吗？(输入 y or n)\n");
	            		String input=scan.next();
	            		if(input.equals("y")){
	            			flight1.cancelBookFlight(nameOfCannel);
	            		}
	            	}else{
	            		System.out.printf("很抱歉，您没有预订过当前航班，不能执行取消操作！\n");
	            	}
	                break;                         
	            case 3 : 
	            	System.out.println("请输入您要查询的客户姓名(中文请使用拼音)");
	            	String nameOfQuery=scan.next();
	            	if(flight1.checkWhetherUserBookingFlight(nameOfQuery)){
	            		System.out.printf("%s已经预订了当前航班\n",nameOfQuery);
	            	}else{
	            		System.out.printf("%s目前尚未预订当前航班\n",nameOfQuery);
	            	}
	                break;                                          
	            case 4 : 
	            	flight1.showUsers();
	                break;
	            default : 
	                System.out.println("错误的输入！ \n ");
	                break;   
            }
            System.out.println("\n是否继续(输入 y or n) \n");
            ch = scan.next().charAt(0);            
        } while (ch == 'Y'|| ch == 'y'); 
        System.out.println("欢迎再次使用！");
        scan.close();
	}

}
/**
 * 航班
 * @author andy
 */
class Flight{
	
	/*航班名称*/
	private String name;
	
	/*出发地*/
	private String from;
	
	/*目的地地*/
	private String to;
	
	/*乘客链表*/
	private DoublyLinkedList<User> users=new DoublyLinkedList<User>();
	
	/*机票数量*/
	private int count;
	
	public Flight(String name,int count,String from,String to){
		this.name=name;
		this.count=count;
		this.from=from;
		this.to=to;
	}
	
	/**
	 * 预订机票
	 * @param user
	 */
	public void bookFlight(User user){
		if(this.users.size()>=count){
			System.out.printf("很抱歉，由%s飞往%s的%s机票目前已经销售完毕，请您换乘其他航班。\n",this.from,this.to,this.name);
			return;
		}
		this.users.addToTail(user);
		System.out.printf("恭喜%s，您已成功预订了由%s飞往%s的%s机票\n",user.getName(),this.from,this.to,this.name);
	}
	
	/**
	 * 取消预订机票
	 * @param user
	 */
	public void cancelBookFlight(String name){
		for(int i=0;i<this.users.size();i++){
			if(users.get(i).getName().equals(name)){
				this.users.deleteNode(i);
				System.out.printf("%s您好，您已成功取消了由%s飞往%s的%s机票。\n",name,this.from,this.to,this.name);
				break;
			}
		}
	}
	
	/**
	 * 检查用户是否预订了航班
	 * @param name
	 * @return
	 */
	public boolean checkWhetherUserBookingFlight(String name){
		for(int i=0;i<this.users.size();i++){
			if(users.get(i).getName().equals(name)){
				return true;
			}
		}
		return false;
	}
	
	
	
	public void showUsers(){
		System.out.printf("由%s飞往%s的%s航班乘客信息如下\n",this.from,this.to,this.name);
		for(int i=0;i<this.users.size();i++){
			System.out.printf("%s\t",users.get(i).getName());
		}
		
	}
	
}
/**
 * 乘客
 * @author andy
 */
class User{
	private String name;
	
	public User(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
