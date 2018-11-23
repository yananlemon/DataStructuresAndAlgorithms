package algorithms.chapter3.practice;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import algorithms.chapter3.BST;

public class BinaryTreeViewer<Key extends Comparable<Key>,Value> extends JPanel{

	BST<Key, Value> tree;
	private int maxHeight;


	public BinaryTreeViewer(BST<Key, Value> root){
		setBackground(Color.white);
		setForeground(Color.black);
		JFrame f = new JFrame("BinaryTree View");
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


	private void setTree(BST<Key, Value> root) {
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
	private void draw(Graphics g,int minX,int maxX,int y,int yStep,BST<Key,Value>.Node n){
		String s = n.getKey().toString();
        System.out.println(s);
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
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		bst.put(20, 1);
		bst.put(15, 10);
		bst.put(25, 2);
		bst.put(10, 2);
		bst.put(18, 2);
		bst.put(9, 2);
		bst.put(12, 2);
		bst.put(16, 2);
		bst.put(19, 2);
		bst.put(22, 2);
		bst.put(26, 2);
		bst.put(24, 2);
		BinaryTreeViewer<Integer, Integer> viewer = 
				new BinaryTreeViewer<Integer, Integer>(bst);
		viewer.refresh();
	}
}
