package br.pro.hashi.ensino.desagil.lucianogic.view;

import java.awt.Graphics;
import java.awt.Image;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

import br.pro.hashi.ensino.desagil.lucianogic.model.Gate;
import br.pro.hashi.ensino.desagil.lucianogic.model.Switch;


public class GateView extends FixedPanel implements ItemListener, MouseListener {

	// Necessario para serializar objetos desta classe.
	private static final long serialVersionUID = 1L;


	private Image image;

	private JCheckBox[] inBoxes;
	private JCheckBox outBox;

	private Switch[] switches;
	private Gate gate;
	
	private int click1;
	
	private int n;
	
	List<Integer> click;
	
	public void criaClick (int size){
		click = new LinkedList<>();
	}
		

	public GateView(Gate gate) {
		super(205, 180);

		this.gate = gate;
		
		super.addMouseListener(this);

		image = loadImage(gate.toString());

		int size = gate.getSize();

		inBoxes = new JCheckBox[size];

		switches = new Switch[size];
		

		for(int i = 0; i < size; i++) {
			inBoxes[i] = new JCheckBox();

			inBoxes[i].addItemListener(this);

			switches[i] = new Switch();

			gate.connect(switches[i], i);
		}

		outBox = new JCheckBox();

		outBox.setEnabled(false);

		//if(size == 1) {
		//	add(inBoxes[0], 0, 60, 20, 20);			
		//}
		//else {
		//	for(int i = 0; i < size; i++) {
		//		add(inBoxes[i], 0, (i + 1) * 40, 20, 20);			
		//	}			
		//}

		add(outBox, 184, 60, 20, 20);

		outBox.setSelected(gate.read());
	}


	@Override
	public void itemStateChanged(ItemEvent event) {
		int i;
		for(i = 0; i < inBoxes.length; i++) {
			if(inBoxes[i] == event.getSource()) {
				break;
			}
		}

		switches[i].setOn(inBoxes[i].isSelected());

		outBox.setSelected(gate.read());
	}


	// Necessario para carregar os arquivos de imagem.
	private Image loadImage(String filename) {
		URL url = getClass().getResource("/img/" + filename + ".png");
		ImageIcon icon = new ImageIcon(url);
		return icon.getImage();
	}


	@Override
	public void paintComponent(Graphics g) {
		int size = gate.getSize();
		
		// Evita bugs visuais em alguns sistemas operacionais.
		super.paintComponent(g);

		g.drawImage(image, 10, 20, 184, 140, null);
		
			if(size == 1) {
				if (click1%2==0){
					g.fillOval(0, 65, 15, 15);
				    g.fillRect(3, 45, 3, 30); 
				    g.fillOval(0, 40, 10, 10);
				}else{
					g.fillOval(0, 65, 15, 15);
				    g.fillRect(9, 45, 3, 30); 
				    g.fillOval(6, 40, 10, 10);
				}
				
			}
			else {
				for(int i = 0; i < size; i++) {
					if (n%2==0){
						g.fillOval(0, (i + 1) * 40 + 5, 15, 15);
						g.fillRect(3, (i + 1) * 40 - 15, 3, 25); 
						g.fillOval(0, (i + 1) * 40 -15, 10, 10);
				}else{
					g.fillOval(0, (i + 1) * 40 + 5, 15, 15);
					g.fillRect(9, (i + 1) * 40 - 15, 3, 25); 
					g.fillOval(6, (i + 1) * 40 -15, 10, 10);
				}
			}
			}
		

		// Evita bugs visuais em alguns sistemas operacionais.
		getToolkit().sync();
		}
    


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
		int size = gate.getSize();
		int x = e.getX();
		int y = e.getY();
		
		if (size == 1){
			if (x >= 0 && x <= 10 && y >= 60 && y <= 90){
				click1++;
				if (click1%2 == 0){
					switches[0].setOn(false);
					repaint();
					outBox.setSelected(gate.read());
				} else{
					switches[0].setOn(true);
					repaint();
					outBox.setSelected(gate.read());
				}
				
			}	
		}else{
			for(int i = 0; i < size; i++) {
				if (x >= 0 && x <= 10 && y >= (i+1)*40 && y <= (i+1)*40+10 ){
					n = click.get(i);
					n++;
					if (n%2 == 0){
						switches[i].setOn(false);
						repaint();
						outBox.setSelected(gate.read());
					} else{
						switches[i].setOn(true);
						repaint();
						outBox.setSelected(gate.read());
					repaint();
				}
			}
		}
		}
			
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
