import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

/**
*
*@autor José Enrique Vargas Oaxaca 
*/

public class InterfazGrafica extends JFrame{
	private int numeroLetras = 0;
	private JLabel TextoLetras  = new JLabel("Ingrese el numero de letras");
	private JTextField NumeroLetras = new JTextField(15);
	private JButton generar = new JButton("Generar");
	
	
	private JPanel []panelesOrginales;
	private JPanel []panelesRuido;
	private JPanel []panelesRecuperacion;
	
	private JPanel matrices = new JPanel();

	private ArrayList<ArrayList<Cuadrado>> matrizOriginales=new ArrayList<>();
	private ArrayList<ArrayList<Cuadrado>> matrizRuido=new ArrayList<>();
	private ArrayList<ArrayList<Cuadrado>> matrizRecuperacion=new ArrayList<>();
	
	
	
	
	private JButton boton =new JButton("Aprendizaje");	
	private JButton recuperacion =new JButton("Recuperacion");
	private JButton eliminar =new JButton("Eliminar Matrices");
	
	private int tamM=36;	
	private int letraOriginal[][][];
	private int letraRuido[][];
	private int letrasOriginales[][];
	private int letraRecuperacion[][][];
	private int letrasR[][];
	private int letrasRmin[][];
	private int MemoriaMaximos[][]=new int[tamM][tamM];
	private int MemoriaMinimos[][]=new int[tamM][tamM];


	public InterfazGrafica(){
		JScrollPane scrollPane=new JScrollPane();
		setLayout(new FlowLayout(FlowLayout.LEFT, 10, 20));
		this.matrices.setLayout(new GridLayout(numeroLetras, 3, 15, 15));
		add(this.TextoLetras);
		add(this.NumeroLetras);
		add(this.generar);
		add(this.boton);
		add(this.recuperacion);
		add(this.eliminar);
		scrollPane.setPreferredSize(new java.awt.Dimension(800, 500));
       		
		
		ActionListener escuchaEliminar = new ActionListener(){
			@Override
 			public void actionPerformed(ActionEvent e){	
				Eliminar_Matrices();
				RepintarE();
  			}
		};

		ActionListener escuchaRecuperacion = new ActionListener(){
			@Override
 			public void actionPerformed(ActionEvent e){	
				LLenarMatricesRuido();
				LLenarMatricesOriginales();
				ImprimeMatricesRuido();				
				Verificacion_Recuperacion();
				
  			}
		};


		ActionListener escucha = new ActionListener(){
			@Override
 			public void actionPerformed(ActionEvent e){	
				LlenarMatrices();
				System.out.println("******Fase de aprendizaje Matrices*****");
				ImprimeLetras();
				ObtenerMemoriaMaximos();				
				ImprimeMemoriaMaximos();
				ObtenerMemoriaMinimos();				
				ImprimeMemoriaMinimos();
				PintarRuido();
				
				
  			}
		};

		ActionListener escucha1 = new ActionListener(){
			@Override
 			public void actionPerformed(ActionEvent e){
			
				numeroLetras = Integer.parseInt(NumeroLetras.getText());
				panelesOrginales = new JPanel[numeroLetras];
				panelesRuido = new JPanel[numeroLetras];
				panelesRecuperacion = new JPanel[numeroLetras];

				letraOriginal = new int[numeroLetras][tamM][tamM];
				letraRuido = new int[numeroLetras][tamM];
				letrasOriginales=new int[numeroLetras][tamM];
				letraRecuperacion= new int[numeroLetras][tamM][tamM];
				letrasR = new int[numeroLetras][tamM];
				letrasRmin = new int[numeroLetras][tamM];
				//agregamos su layout a cada panel y los agregamos a la ventana				
				for(int i=0;i<numeroLetras;i++){
					panelesOrginales[i] = new JPanel();
					panelesRuido[i] = new JPanel();
					panelesRecuperacion[i] = new JPanel();
				}
				
				for(int i=0;i<numeroLetras;i++){
					
					panelesOrginales[i].setLayout(new GridLayout(6, 6, 1, 1));
					panelesRuido[i].setLayout(new GridLayout(6, 6, 1, 1));
					panelesRecuperacion[i].setLayout(new GridLayout(6, 6, 1, 1));
				}

				for(int i = 0; i < numeroLetras; i++){	
					matrizOriginales.add(new ArrayList<Cuadrado>());
					matrizRuido.add(new ArrayList<Cuadrado>());
					matrizRecuperacion.add(new ArrayList<Cuadrado>());
					for(int j=0;j<tamM;j++){					
						matrizOriginales.get(i).add(new Cuadrado());
						matrizRuido.get(i).add(new Cuadrado());
						matrizRecuperacion.get(i).add(new Cuadrado());
						
					}
				 }
								
				
				for(int i = 0; i < numeroLetras; i++){
					for(int j=0;j<tamM;j++){					
						panelesOrginales[i].add(matrizOriginales.get(i).get(j).btn);
						panelesRuido[i].add(matrizRuido.get(i).get(j).btn);
						panelesRecuperacion[i].add(matrizRecuperacion.get(i).get(j).btn);
					}
					
				}	
				for(int i = 0; i < numeroLetras; i++){
					matrices.add(panelesOrginales[i]);
					matrices.add(panelesRuido[i]);
					matrices.add(panelesRecuperacion[i]);
					
				}
				Repintar();
			}
		};
		this.generar.addActionListener(escucha1);
		this.boton.addActionListener(escucha);
		this.recuperacion.addActionListener(escuchaRecuperacion);
		this.eliminar.addActionListener(escuchaEliminar);
				
		scrollPane.setViewportView(this.matrices);
		add(scrollPane);
		
		setTitle("Memoria Asociativa");
		setSize(830,660);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void Repintar(){
		this.getContentPane().validate();
        	this.getContentPane().repaint();	
		
	}
	public void RepintarE(){
		this.matrices.removeAll();
		Repintar();	
		
	}
	public void PintarRuido(){
		for(int k=0;k<numeroLetras;k++){
				for(int j=0;j<tamM;j++){
					if(matrizOriginales.get(k).get(j).color==1){	
						matrizRuido.get(k).get(j).color=1;
						matrizRuido.get(k).get(j).btn.setBackground(Color.BLACK);
						matrizRuido.get(k).get(j).btn.setForeground(Color.BLACK);
						
					}
				}
			
		}	
			
	}

	
	public void LLenarMatricesRuido(){
			for(int i=0;i<numeroLetras;i++){
				for(int j=0;j<tamM;j++){
					this.letraRuido[i][j] = matrizRuido.get(i).get(j).color;
				}
			}
		
	}

	public void LLenarMatricesOriginales(){
			for(int i=0;i<numeroLetras;i++){
				for(int j=0;j<tamM;j++){
					this.letrasOriginales[i][j] =  matrizOriginales.get(i).get(j).color;
				}
			}
		
	}

	public void ImprimeMatricesRuido(){
		System.out.println("************************Matrices Ruido***************************");
			for(int i=0;i<numeroLetras;i++){
				for(int j=0;j<tamM;j++){
					System.out.print(" "+ this.letraRuido[i][j]+" ");
				}
				System.out.println();
			}
			System.out.println("****************");
		
	}


	public void LlenarMatrices(){
		for(int k=0;k<numeroLetras;k++){
			for(int i=0;i<tamM;i++){
				for(int j=0;j<tamM;j++){
					if(matrizOriginales.get(k).get(i).color == 0 && matrizOriginales.get(k).get(j).color==0){
						this.letraOriginal[k][i][j] = 1;
					}
					else if(matrizOriginales.get(k).get(i).color == 0 && matrizOriginales.get(k).get(j).color==1){
						this.letraOriginal[k][i][j] = 0;
					}	
					else if(matrizOriginales.get(k).get(i).color == 1 && matrizOriginales.get(k).get(j).color==0){
						this.letraOriginal[k][i][j] = 2;
					}	
					else if(matrizOriginales.get(k).get(i).color == 1 && matrizOriginales.get(k).get(j).color==1){
					
						this.letraOriginal[k][i][j] = 1;
					}	
									
				}
			}
		}

	}
	

	public void ObtenerMemoriaMaximos(){
		int contador = 0;
		int mayor =0;
		for(int i=0;i<tamM;i++){ 
			for(int j=0;j<tamM;j++){ 
				while(contador<numeroLetras){ 
					if(letraOriginal[contador][i][j]>mayor){   
					     mayor = letraOriginal[contador][i][j];
					}
					
					contador++;
				}
				
				MemoriaMaximos[i][j]=mayor;
				contador = 0;
				mayor=0;
				
			}
		}

	}	

	public void ObtenerMemoriaMinimos(){
		int contador = 0;
		int menor =0;
		for(int i=0;i<tamM;i++){ 
			for(int j=0;j<tamM;j++){ 
				while(contador<numeroLetras){ 
					if(contador == 0){
						 menor = letraOriginal[contador][i][j];
					}					
					else if(letraOriginal[contador][i][j]<menor){   
					     menor = letraOriginal[contador][i][j];
					}
					
					contador++;
				}
				
				MemoriaMinimos[i][j]=menor;
				contador = 0;
				menor=0;
				
			}
		}

	}	
	public void ImprimeMemoriaMaximos(){
		System.out.println("***************Memoria de maximos**************");
		for(int i=0;i<tamM;i++){
			for(int j=0;j<tamM;j++){
				System.out.print(" "+ this.MemoriaMaximos[i][j]+" ");
			}
			System.out.println();
		}
	}
	public void ImprimeMemoriaMinimos(){
		System.out.println("***************Memoria de minimos**************");
		for(int i=0;i<tamM;i++){
			for(int j=0;j<tamM;j++){
				System.out.print(" "+ this.MemoriaMinimos[i][j]+" ");
			}
			System.out.println();
		}
	}
	public void ImprimeLetras(){
		for(int k=0;k<this.numeroLetras;k++){
			for(int i=0;i<tamM;i++){
				for(int j=0;j<tamM;j++){
					System.out.print(" " +  letraOriginal[k][i][j] + " "); 
				}
				System.out.println();
			}
		System.out.println();
		System.out.println();
		System.out.println("******************************************");
		}	
	}

	public void Fase_Recuperacion_Maximos(){
		for(int k=0;k<numeroLetras;k++){		
			for(int i=0;i<tamM;i++){
				for(int j=0;j<tamM;j++){
					if(MemoriaMaximos[i][j]==0 && letraRuido[k][j]==0){
						letraRecuperacion[k][i][j] = 0;
					}
					else if(MemoriaMaximos[i][j]==0 &&letraRuido[k][j]==1){
						letraRecuperacion[k][i][j] =0 ;
					}else if(MemoriaMaximos[i][j]==1 &&letraRuido[k][j]==0){
						letraRecuperacion[k][i][j] =0 ;
					}else if(MemoriaMaximos[i][j]==1 &&letraRuido[k][j]==1){
						letraRecuperacion[k][i][j] =1 ;
					}else if(MemoriaMaximos[i][j]==2 &&letraRuido[k][j]==0){
						letraRecuperacion[k][i][j] =1 ;
					}else if(MemoriaMaximos[i][j]==2 &&letraRuido[k][j]==1){
						letraRecuperacion[k][i][j] =1 ;
					}
					System.out.print(" " +letraRecuperacion[k][i][j] + " ");
					//System.out.print("  "+"B("+MemoriaMaximos[i][j]+","+letraRuido[k][j]+")"+"   ");//
				}
				System.out.println();
			}
			System.out.println();
			System.out.println();
		}
	}

	
	public void Fase_Recuperacion_Minimos(){
		System.out.println("min");
		for(int k=0;k<numeroLetras;k++){		
			for(int i=0;i<tamM;i++){
				for(int j=0;j<tamM;j++){
					if(MemoriaMinimos[i][j]==0 && letraRuido[k][j]==0){
						letraRecuperacion[k][i][j] = 0;
					}
					else if(MemoriaMinimos[i][j]==0 &&letraRuido[k][j]==1){
						letraRecuperacion[k][i][j] =0 ;
					}else if(MemoriaMinimos[i][j]==1 &&letraRuido[k][j]==0){
						letraRecuperacion[k][i][j] =0 ;
					}else if(MemoriaMinimos[i][j]==1 &&letraRuido[k][j]==1){
						letraRecuperacion[k][i][j] =1 ;
					}else if(MemoriaMinimos[i][j]==2 &&letraRuido[k][j]==0){
						letraRecuperacion[k][i][j] =1 ;
					}else if(MemoriaMinimos[i][j]==2 &&letraRuido[k][j]==1){
						letraRecuperacion[k][i][j] =1 ;
					}
					//System.out.print("   "+"( B "+MemoriaMaximos[i][j]+","+letraRuido[k][j]+")"+"   ");//
				}
				//System.out.println();
			}
			//System.out.println();
			//System.out.println();
		}
		
	}
	
	public void Verificacion_Recuperacion(){
		int contador=0;
		int conOriginal=0;
		for(int i=0;i<numeroLetras;i++){ 
			for(int j=0;j<tamM;j++){
				if(this.letrasOriginales[i][j]==1){
					conOriginal++; 
				} 
				if(this.letraRuido[i][j] == 1 && this.letrasOriginales[i][j]==1){
					contador++;
				}
				if(conOriginal==contador && j==tamM-1){
					Fase_Recuperacion_Maximos();
					Recuperacion_Maximos(i);
					Pintar_Recuperacion_Max();
				}else if(conOriginal!=contador && j==tamM-1){
					System.out.println("min");
					Fase_Recuperacion_Minimos();
					Recuperacion_Minimos(i);
					Pintar_Recuperacion();
				}
			}
			contador=0;
			conOriginal=0;
		}	
		
	}

	public void Limpiar_Matrices(){
		

	}

	public void Eliminar_Matrices(){
		matrizOriginales.clear();
		matrizRuido.clear();
	 	matrizRecuperacion.clear();
	}
	public void Recuperacion_Maximos(int k){
		int contador = 0;
		int menor =0;
			for(int i=0;i<tamM;i++){ 
				for(int j=0;j<tamM;j++){ 
					if(j==0){
						menor = letraRecuperacion[k][i][j];
					}		
					else if(letraRecuperacion[k][i][j]<menor){   
						menor = letraRecuperacion[k][i][j];     
					}
				}
				letrasR[k][i]=menor;
				System.out.print(" " + menor + " ");
				menor=0;
			}
		
	}

	public void Recuperacion_Minimos(int k){
		int contador = 0;
		int mayor =0;
			
			for(int i=0;i<tamM;i++){ 
				for(int j=0;j<tamM;j++){ 
					if(j==0){
						mayor = letraRecuperacion[k][i][j];
					}		
					else if(letraRecuperacion[k][i][j]>mayor){   
						mayor = letraRecuperacion[k][i][j];     
					}
				}
				letrasRmin[k][i]=mayor;
				System.out.print(" " + mayor + " ");
				mayor=0;
			}
		
	}
	
	public void Pintar_Recuperacion(){
		for(int k=0;k<numeroLetras;k++){	
			for(int j=0;j<tamM;j++){ 
				if(letrasRmin[k][j]==1){		matrizRecuperacion.get(k).get(j).btn.setBackground(Color.BLACK);			
matrizRecuperacion.get(k).get(j).btn.setForeground(Color.BLACK);					
				}
			}
			
		}		
	}
	public void Pintar_Recuperacion_Max(){
		for(int k=0;k<numeroLetras;k++){	
			for(int j=0;j<tamM;j++){ 
				if(letrasR[k][j]==1){		matrizRecuperacion.get(k).get(j).btn.setBackground(Color.BLACK);			
matrizRecuperacion.get(k).get(j).btn.setForeground(Color.BLACK);					
				}
			}
			
		}		
	}
	
}	
