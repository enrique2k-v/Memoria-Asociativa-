import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
/**
*
*@autor José Enrique Vargas Oaxaca 
*/
public class Cuadrado{
	public int color=0;
	public JButton btn  = new JButton(".");
	
	public Cuadrado(){
		this.btn.setBackground(Color.WHITE);
		this.btn.setBorderPainted​(false);
		this.btn.setFocusPainted​(false);
		this.btn.setForeground(Color.WHITE);
		
		ActionListener escucha = new ActionListener(){
			@Override
 			public void actionPerformed(ActionEvent e){
	  			if(color>0){
					color = 0;
					btn.setBackground(Color.WHITE);
					btn.setForeground(Color.WHITE);
				}else{
					color = 1;
					btn.setBackground(Color.BLACK);
					btn.setForeground(Color.BLACK);
					
				}
  			}
		};
	
		this.btn.addActionListener(escucha);
	
	}
	
}

