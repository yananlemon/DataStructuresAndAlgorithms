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

import algorithms.chapter3.RedBlackBST;

public class RedBlackBSTViewer<Key extends Comparable<Key>,Value> extends JPanel{

	RedBlackBST<Key, Value> tree;
	private int maxHeight;

	public RedBlackBSTViewer(RedBlackBST<Key, Value> root){
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


	private void setTree(RedBlackBST<Key, Value> root) {
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
    
	private void draw(Graphics g,int minX,int maxX,int y,int yStep,RedBlackBST<Key,Value>.Node n){
		String s = n.getKey().toString();
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics();
        int width = fm.stringWidth(s);
        int height = fm.getHeight();

        int xSep = Math.min((maxX - minX)/8, 10);
        g.drawString(s, (minX + maxX)/2 - width/2, y + yStep/2);
        if( n.getLeft() != null && n.getLeft().isColor() )
        	g.setColor(Color.red);
        	else
        		g.setColor(Color.black);
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
		RedBlackBST<String, Integer> bst = new RedBlackBST<String, Integer>();
		/*for( int i = 1; i <= 100; i++) {
			bst.put(i, 1);
		}*/
		/*bst.put("A", 1);
		bst.put("C", 1);
		bst.put("E", 1);
		bst.put("H", 1);
		bst.put("L", 1);
		bst.put("M", 1);
		bst.put("P", 1);
		bst.put("R", 1);
		bst.put("S", 1);
		bst.put("X", 1);*/
		bst.put("E", 1);
		bst.put("C", 1);
		bst.put("S", 1);
		bst.put("R", 1);
		bst.put("A", 1);
		bst.put("H", 1);
		//bst.put("H", 1);
		//bst.delete("S");
		//bst.delete("R");
		bst.deleteMax();
		bst.deleteMax();
		RedBlackBSTViewer<String, Integer> viewer = 
				new RedBlackBSTViewer<String, Integer>(bst);
		viewer.refresh();
		System.out.println("是否是2-3树:"+ bst.is23());
		System.out.println("是否是2-3树:"+ bst.is23());
	}
}

