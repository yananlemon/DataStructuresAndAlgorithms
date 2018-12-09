package algorithms.chapter3.practice;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import algorithms.chapter3.AVLBST;

public class AVLBSTViewer<Key extends Comparable<Key>,Value> extends JPanel{

	AVLBST<Key, Value> tree;
	private int maxHeight;

	public AVLBSTViewer(AVLBST<Key, Value> root){
		setBackground(Color.white);
		setForeground(Color.black);
		JFrame f = new JFrame("左倾红黑树");
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		f.getContentPane().add(this, BorderLayout.CENTER);
		f.setSize(new Dimension(400, 400));
		f.setVisible(true);

		// install initial tree.
		setTree(root);
	}


	private void setTree(AVLBST<Key, Value> root) {
		this.tree = root;
		this.maxHeight = tree.height();
	}

	public void refresh() {
        paintImmediately(0,0, getWidth(), getHeight());
    }

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);      //clears the background
		int width = getWidth();
		int height = getHeight();
		maxHeight = Math.max(tree.height(), maxHeight);
		int treeHeight = maxHeight;

		draw(g, 0, width, 0, height / (treeHeight + 1), tree.root);

	} 
	/* The font for the tree nodes. */
    protected Font font = new Font("Roman", 0, 14);
    
	private void draw(Graphics g,int minX,int maxX,int y,int yStep,AVLBST<Key,Value>.Node n){
		String s = n.getKey().toString() + " ,bf:" + tree.balanceFactor(n);
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics();
        int width = fm.stringWidth(s);
        int height = fm.getHeight();

        int xSep = Math.min((maxX - minX)/8, 10);
        g.drawString(s, (minX + maxX)/2 - width/2, y + yStep/2);
        if (n.getLeft() != null) {
            // if left tree not empty, draw line to it and recursively
            // draw that tree
            g.drawLine((minX + maxX)/2 - xSep, y + yStep/2 + 5,
                       (minX + (minX + maxX)/2) / 2, 
                       y + yStep + yStep/2 - height);
            draw(g, minX, (minX + maxX)/2, y + yStep, yStep, n.getLeft());
        }
        if (n.getRight() != null) {
            // same thing for right subtree.
            g.drawLine((minX + maxX)/2 + xSep, y + yStep/2 + 5,
                       (maxX + (minX + maxX)/2) / 2, 
                       y + yStep + yStep/2 - height);
            draw(g, (minX + maxX)/2, maxX, y + yStep, yStep, n.getRight());
        }
	}

	public static void main(String[] args) {
		AVLBST<Integer, Integer> avl = new AVLBST<Integer, Integer>();
		/*avl.put("F", 1);
		avl.put("B", 1);
		avl.put("M", 1);
		avl.put("G", 1);
		avl.put("H", 1);*/
		//avl.delete("E");
		//avl.delete("E");
		/*for( int i = 0; i < 10000; i++)
			avl.put(i, i);
		avl.put(0, 0);
		avl.put(1, 1);
		avl.put(2, 2);
		avl.put(3, 3);
		avl.put(4, 4);
		avl.put(5, 5);
		avl.put(6, 6);
		avl.put(7, 6);
		avl.put(8, 6);
		avl.put(9, 6);
		avl.put(10, 10);
		avl.delete(56);
		avl.delete(55);
		avl.delete(5700);
		avl.deleteMin();
		System.out.println("findMin:"+avl.findMin());
		System.out.println("findMax:"+avl.findMax());
		avl.deleteMax();*/
		avl.put(10, 1);
		avl.put(20, 1);
		avl.put(30, 1);
		avl.put(19, 1);
		avl.put(15, 1);
		avl.put(25, 1);
		avl.put(27, 1);
		System.out.println("findMax:"+avl.findMax());
		AVLBSTViewer<Integer, Integer> viewer = 
				new AVLBSTViewer<Integer, Integer>(avl);
		viewer.refresh();
		System.out.println("是AVL树吗？"+avl.isAVL());
		System.out.println("height:" + avl.height());
		
	}
}

