package com.chapter6.navl;

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

public class AvlViewer<AnyType extends Comparable<AnyType>> extends JPanel{

	AvlTree<AnyType> tree;
	private int maxHeight;

	public AvlViewer(AvlTree<AnyType> root){
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


	private void setTree(AvlTree<AnyType> root) {
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
    
	private void draw(Graphics g,int minX,int maxX,int y,int yStep,AvlTree<AnyType>.AvlNode<AnyType> n){
		String s = n.getElement().toString() ;
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
		AvlTree<Integer> avl = new AvlTree<Integer>();
		for (int i = 0; i < 1000000; i++) {
			avl.insert(i);
		}
		//avl.insert("I");
		/*AvlViewer<Integer> viewer = 
				new AvlViewer<Integer>(avl);
		viewer.refresh();*/
		System.out.println(avl.isAVL());
		System.out.println(avl.height());
	}
}

