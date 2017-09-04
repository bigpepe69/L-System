package fenetres;


import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JComboBox;

import structure_prog.GrammaireFormelle;
import structure_prog.Sequence;
import dessin.JPanelDessinFractal;
import dessin.JPanelDessinFractalInit;

import java.awt.Color;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class FenetrePrincipale extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel jPanelBasPage = null;
	private JButton jButtonIterationM = null;
	private JButton jButtonIterationP = null;
	private JButton jButtonZoomP = null;
	private JButton jButtonQuitter = null;
	private JPanel jPanelGauche = null;
	private JList jList = null;
	private JPanel jPanelBasGauche = null;
	private JPanel jPanelBasCentre = null;
	private JPanel jPanelBasDroite = null;
	private JButton jButtonZoomM = null;
	private JPanel jPanelGaucheHaut = null;
	private JLabel jLabel = null;
	private JPanel jPanelGaucheHautBas = null;
	private JLabel jLabel1 = null;
	private JComboBox jComboBox = null;
	private JPanelDessinFractalInit jpdfiPanel1 = null;
	private JPanel jPanelGaucheBas = null;
	private JPanel jPanelGaucheBasBas = null;
	private JButton jButtonTracer = null;
	private JPanel jPanelGaucheBasMilieu = null;
	private JPanel jPanel = null;
	private JLabel jLabel2 = null;
	private JTextField jTextFieldAxiome = null;
	private JPanel jPanel1 = null;
	private JPanel jPanel2 = null;
	private JPanel jPanel3 = null;
	private JPanel jPanel5 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel5 = null;
	private JLabel jLabel6 = null;
	private JTextField jTextFieldNbRegle = null;
	private JTextField jTextFieldAngle = null;
	private JTextField jTextFieldIterationDepart = null;
	private JTextField jTextFieldIterationMax = null;
	private JPanel jPanelDroite = null;
	private JLabel jLabel7 = null;
	private JPanel jPanelDroiteBas = null;
	private JLabel jLabelIteration = null;
	private JLabel jLabel9 = null;
	private JPanelDessinFractal jpdfPanel1 = null;
	private JPanel jPanelDroiteBasGauche = null;
	private JPanel jPanelDroiteBasDroite = null;
	private JLabel jLabel10 = null;
	private JLabel jLabelTrait = null;
	private GrammaireFormelle gfMaGF;
	private Sequence seqMaSequence;
	private Sequence seqTempo;
	private JButton jButtonInfo = null;
	private JLabel jLabel8 = null;
	private JButton jButtonZoomMieux = null;
	private String sCaratereAutorise = new String(); //Chaine de caractere qui contient les caractères autorisés
	

	/**
	 * This is the default constructor
	 */
	public FenetrePrincipale() {
		super();
		sCaratereAutorise="FG+-XY[]"; //Définition des caractères qui sont autorisés pour l'utilisateur
		gfMaGF=new GrammaireFormelle(this);
		seqTempo = new Sequence(gfMaGF);
		initialize();		
		try {
			//Remplit la comboBox avec le nom des fractals pré-enregistrés
			lectureFichierName();
		} catch (IOException ex) {}
		jButtonIterationP.setEnabled(false);
		jButtonIterationM.setEnabled(false);
		jButtonZoomM.setEnabled(false);
		jButtonZoomP.setEnabled(false);
		jButtonZoomMieux.setEnabled(false);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		setDefaultCloseOperation(EXIT_ON_CLOSE); //Permet de fermer complétement l'application
		this.setLocationRelativeTo(null);
		this.setSize(1024, 690);
		this.setLocation(new Point(0, 0));
		this.setForeground(Color.black);
		this.setResizable(false); //Empêche le redimensionnement de la fenêtre
		this.setContentPane(getJContentPane());
		this.setTitle("L-Systeme (Julien Veran, Kevin Vittoz, Nicolas Voeltzel, Cédric Salzi)");
	}
	
	// Méthode qui remplit la comboBox avec le nom des fractals pré-enregistrés
	public void lectureFichierName() throws IOException {
		BufferedReader entree = new BufferedReader(new FileReader("FractalEnregistre.txt"));
		String ligne = "";
		while (ligne != null) {
			ligne = entree.readLine();
			if (ligne != null && ligne.substring(0, 4).equals("name")) {
				jComboBox.addItem(ligne.substring(7));
			}
		}
		entree.close();
	}

	// Méthode qui configure les paramètres avec les valeurs du fractal pré-enregistré selectionné
	public void lectureFichierVal()throws IOException {
		BufferedReader entree = new BufferedReader(new FileReader("FractalEnregistre.txt"));
		String ligne = "";
		int iNbRegles;
		gfMaGF = new GrammaireFormelle(this);
		while (ligne != null) {
			ligne = entree.readLine();
			if (ligne != null && ligne.substring(7).equals(jComboBox.getSelectedItem().toString())){
				ligne = entree.readLine();
				jTextFieldAxiome.setEditable(false);
				jTextFieldAxiome.setText(ligne.substring(9));
				
				ligne = entree.readLine();
				jTextFieldNbRegle.setEditable(false);
				iNbRegles= Integer.parseInt(ligne.substring(12).trim());
				jTextFieldNbRegle.setText(ligne.substring(12));

				for (int i=0;i<iNbRegles;i++){
					ligne = entree.readLine();
					gfMaGF.ajouterRegles(ligne.charAt(10), ligne.substring(12));
				}
				
				ligne = entree.readLine();
				jTextFieldAngle.setEditable(false);
				jTextFieldAngle.setText(ligne.substring(8));
				
				ligne = entree.readLine();
				jLabel8.setText("Maxi : " + ligne.substring(21) );
				
				gfMaGF.recupValTempo();
				seqTempo = new Sequence(gfMaGF);
				seqTempo.setAngle(recupValAngle());
				seqTempo.iterer();
				jpdfiPanel1.configValTempo();//Tracer petit dessin
				jpdfiPanel1.repaint();
			}
			if (ligne != null && "Personnaliser...".equals(jComboBox.getSelectedItem().toString())){
				jTextFieldAxiome.setEditable(true);
				jTextFieldAxiome.setText("");
				jTextFieldNbRegle.setEditable(true);
				jTextFieldNbRegle.setText("");
				jTextFieldAngle.setEditable(true);
				jTextFieldAngle.setText("");
				jLabel8.setText("Maxi : --" );
				jpdfiPanel1.configValZero();
				jpdfiPanel1.repaint(); //Tracer petit dessin
				
			}
		}
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			BorderLayout borderLayout = new BorderLayout();
			borderLayout.setHgap(5);
			jContentPane = new JPanel();
			jContentPane.setBackground(new Color(204, 204, 204));
			jContentPane.setLayout(borderLayout);
			jContentPane.add(getJPanelBasPage(), BorderLayout.SOUTH);
			jContentPane.add(getJPanelGauche(), BorderLayout.WEST);
			jContentPane.add(getJPanelDroite(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jPanelBasPage	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelBasPage() {
		if (jPanelBasPage == null) {
			
			FlowLayout flowLayout2 = new FlowLayout();
			flowLayout2.setHgap(100);
			jPanelBasPage = new JPanel();
			jPanelBasPage.setBackground(new Color(204, 204, 204));
			jPanelBasPage.setLayout(flowLayout2);
			jPanelBasPage.setPreferredSize(new Dimension(100,45));
			jPanelBasPage.add(getJPanelBasGauche(), null);
			jPanelBasPage.add(getJPanelBasCentre(), null);
			jPanelBasPage.add(getJList(), null);
			jPanelBasPage.add(getJButtonQuitter(), null);
			jPanelBasPage.add(getJPanelBasDroite(), null);
		}
		return jPanelBasPage;
	}

	/**
	 * This method initializes jButtonIterationM	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonIterationM() {
		if (jButtonIterationM == null) {
			jButtonIterationM = new JButton();
			jButtonIterationM.setText("Itération -");
			jButtonIterationM.setFont(new Font("Dialog", Font.BOLD, 12));
			jButtonIterationM.setPreferredSize(new Dimension(88, 26));
			jButtonIterationM.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					seqMaSequence.setIteration(seqMaSequence.getIteration()-1);
					jLabelIteration.setText(Integer.toString(seqMaSequence.getIteration()));
					jpdfPanel1.configVal();
					jpdfPanel1.repaint();
					if (seqMaSequence.getIteration()==0){
						jButtonIterationM.setEnabled(false);
					}
					jButtonIterationP.setEnabled(true);
				}
			});
		}
		return jButtonIterationM;
	}

	/**
	 * This method initializes jButtonIterationP	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonIterationP() {
		if (jButtonIterationP == null) {
			jButtonIterationP = new JButton();
			jButtonIterationP.setText("Itération +");
			jButtonIterationP.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					seqMaSequence.setIteration(seqMaSequence.getIteration()+1);
					jLabelIteration.setText(Integer.toString(seqMaSequence.getIteration()));
					jpdfPanel1.configVal();
					jpdfPanel1.repaint();
					if (seqMaSequence.getIteration()==seqMaSequence.getSequenceC().size()-1){
						jButtonIterationP.setEnabled(false);
					}
					jButtonIterationM.setEnabled(true);
				}
			});
		}
		return jButtonIterationP;
	}

	/**
	 * This method initializes jButtonZoomP	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonZoomP() {
		if (jButtonZoomP == null) {
			jButtonZoomP = new JButton();
			jButtonZoomP.setText("Zoom +");
			jButtonZoomP.setPreferredSize(new Dimension(96, 26));
			jButtonZoomP.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// On multiplie la taille du trait par 4/3 quand on clique sur le bouton Zoom +
					seqMaSequence.setTailleTrait((float) (seqMaSequence.getTailleTrait() * 4/3));
					jpdfPanel1.configVal();
					//On arrondi à 1 chiffre après la virgule la taille du trait affiché à l'écran
					double dTailleTraitAffiche;
					dTailleTraitAffiche=(seqMaSequence.getTailleTrait()*10);
					dTailleTraitAffiche=Math.round(dTailleTraitAffiche);
					dTailleTraitAffiche /= 10;
					jLabelTrait.setText(Double.toString(dTailleTraitAffiche));
					jpdfPanel1.repaint();
				}
			});
		}
		return jButtonZoomP;
	}

	/**
	 * This method initializes jButtonQuitter	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonQuitter() {
		if (jButtonQuitter == null) {
			jButtonQuitter = new JButton();
			jButtonQuitter.setText("Quitter");
			jButtonQuitter.setPreferredSize(new Dimension(91, 26));
			jButtonQuitter.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
				            System.exit(0);			 
				}
			});
		}
		return jButtonQuitter;
	}

	/**
	 * This method initializes jPanelGauche	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelGauche() {
		if (jPanelGauche == null) {
			GridLayout gridLayout1 = new GridLayout();
			gridLayout1.setRows(2);
			gridLayout1.setColumns(1);
			jPanelGauche = new JPanel();
			jPanelGauche.setPreferredSize(new Dimension(310, 180));
			jPanelGauche.setBackground(new Color(204, 204, 204));
			jPanelGauche.setLayout(gridLayout1);
			jPanelGauche.add(getJPanelGaucheHaut(), null);
			jPanelGauche.add(getJPanelGaucheBas(), null);
		}
		return jPanelGauche;
	}

	/**
	 * This method initializes jList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJList() {
		if (jList == null) {
			jList = new JList();
		}
		return jList;
	}

	/**
	 * This method initializes jPanelBasGauche	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelBasGauche() {
		if (jPanelBasGauche == null) {
			FlowLayout flowLayout1 = new FlowLayout();
			flowLayout1.setHgap(5);
			flowLayout1.setVgap(5);
			jPanelBasGauche = new JPanel();
			jPanelBasGauche.setBackground(new Color(204, 204, 204));
			jPanelBasGauche.setLayout(flowLayout1);
			jPanelBasGauche.add(getJButtonIterationM(), null);
			jPanelBasGauche.add(getJButtonIterationP(), null);
		}
		return jPanelBasGauche;
	}

	/**
	 * This method initializes jPanelBasCentre	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelBasCentre() {
		if (jPanelBasCentre == null) {
			FlowLayout flowLayout9 = new FlowLayout();
			flowLayout9.setHgap(5);
			jPanelBasCentre = new JPanel();
			jPanelBasCentre.setBackground(new Color(204, 204, 204));
			jPanelBasCentre.setLayout(flowLayout9);
			jPanelBasCentre.add(getJButtonZoomM(), null);
			jPanelBasCentre.add(getJButtonZoomP(), null);
			jPanelBasCentre.add(getJButtonZoomMieux(), null);
		}
		return jPanelBasCentre;
	}

	/**
	 * This method initializes jPanelBasDroite	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelBasDroite() {
		if (jPanelBasDroite == null) {
			GridLayout gridLayout2 = new GridLayout();
			gridLayout2.setRows(1);
			jPanelBasDroite = new JPanel();
			jPanelBasDroite.setLayout(gridLayout2);
		}
		return jPanelBasDroite;
	}

	/**
	 * This method initializes jButtonZoomM	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonZoomM() {
		if (jButtonZoomM == null) {
			jButtonZoomM = new JButton();
			jButtonZoomM.setText("Zoom -");
			jButtonZoomM.setPreferredSize(new Dimension(91, 26));
			jButtonZoomM.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					 //On multiplie la taille du trait par 0.75 quand on clique sur Zoom -
					seqMaSequence.setTailleTrait((float) (seqMaSequence.getTailleTrait() * 0.75));
					jpdfPanel1.configVal();
					//On arrondi à 1 chiffre après la virgule la taille du trait affiché à l'écran
					double dTailleTraitAffiche;
					dTailleTraitAffiche=(seqMaSequence.getTailleTrait()*10);
					dTailleTraitAffiche=Math.round(dTailleTraitAffiche);
					dTailleTraitAffiche /= 10;
					jLabelTrait.setText(Double.toString(dTailleTraitAffiche));
					jpdfPanel1.repaint();
				}
			});
		}
		return jButtonZoomM;
	}

	/**
	 * This method initializes jPanelGaucheHaut	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelGaucheHaut() {
		if (jPanelGaucheHaut == null) {
			jLabel = new JLabel();
			jLabel.setText(" Fractal Initial");
			jLabel.setBackground(new Color(204, 204, 204));
			jPanelGaucheHaut = new JPanel();
			jPanelGaucheHaut.setLayout(new BorderLayout());
			jPanelGaucheHaut.setBackground(new Color(204, 204, 204));
			jPanelGaucheHaut.add(jLabel, BorderLayout.NORTH);
			jPanelGaucheHaut.add(getJPanelGaucheHautBas(), BorderLayout.SOUTH);
			jPanelGaucheHaut.add(getJPanelDessinFractalInitPanel(), BorderLayout.CENTER);
		}
		return jPanelGaucheHaut;
	}

	/**
	 * This method initializes jPanelGaucheHautBas	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelGaucheHautBas() {
		if (jPanelGaucheHautBas == null) {
			FlowLayout flowLayout10 = new FlowLayout();
			flowLayout10.setHgap(15);
			jLabel1 = new JLabel();
			jLabel1.setText("Fractal enregistré");
			jPanelGaucheHautBas = new JPanel();
			jPanelGaucheHautBas.setBackground(new Color(204, 204, 204));
			jPanelGaucheHautBas.setLayout(flowLayout10);
			jPanelGaucheHautBas.add(jLabel1, null);
			jPanelGaucheHautBas.add(getJComboBox(), null);
		}
		return jPanelGaucheHautBas;
	}

	/**
	 * This method initializes jComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox() {
		if (jComboBox == null) {
			jComboBox = new JComboBox();
			jComboBox.setPreferredSize(new Dimension(160, 20));
			jComboBox.addItem("Personnaliser...");
			jComboBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {					
					System.out.println(	jComboBox.getSelectedItem());
					try {
						// Configure les paramètres avec les valeurs du fractal pré-enregistré selectionné
						lectureFichierVal();
					} catch (IOException ex) {}
							
				}
			});
		}
		return jComboBox;
	}

	/**
	 * This method initializes jEditorPaneFractalInit	
	 * 	
	 * @return javax.swing.JEditorPane	
	 */
	private JPanelDessinFractalInit getJPanelDessinFractalInitPanel() {
		if (jpdfiPanel1 == null) {
			jpdfiPanel1 = new JPanelDessinFractalInit(this);
			jpdfiPanel1.setEnabled(false);
			jpdfiPanel1.setFont(new Font("Dialog", Font.PLAIN, 12));
		}
		return jpdfiPanel1;
	}
	
	private JPanelDessinFractal getJPanelDessinFractalPanel() {
		if (jpdfPanel1 == null) {
			jpdfPanel1 = new JPanelDessinFractal(this);
			jpdfPanel1.setEnabled(false);
		}
		return jpdfPanel1;
	}

	/**
	 * This method initializes jPanelGaucheBas	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelGaucheBas() {
		if (jPanelGaucheBas == null) {
			jPanelGaucheBas = new JPanel();
			jPanelGaucheBas.setLayout(new BorderLayout());
			jPanelGaucheBas.setBackground(new Color(204, 204, 204));
			jPanelGaucheBas.add(getJPanelGaucheBasBas(), BorderLayout.SOUTH);
			jPanelGaucheBas.add(getJPanelGaucheBasMilieu(), BorderLayout.CENTER);
		}
		return jPanelGaucheBas;
	}

	/**
	 * This method initializes jPanelGaucheBasBas	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelGaucheBasBas() {
		if (jPanelGaucheBasBas == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setVgap(1);
			jPanelGaucheBasBas = new JPanel();
			jPanelGaucheBasBas.setBackground(new Color(204, 204, 204));
			jPanelGaucheBasBas.setLayout(flowLayout);
			jPanelGaucheBasBas.add(getJButtonTracer(), null);
		}
		return jPanelGaucheBasBas;
	}

	/**
	 * This method initializes jButtonTracer	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonTracer() {
		// Lors d'une action sur le bouton tracer, les paramètres utilisateurs sont verifiés, les règles sont demandées à l'utilisateur si necessaire, puis les séquences sont créees et enfin, le fractal est tracé  
		if (jButtonTracer == null) {
			jButtonTracer = new JButton();
			jButtonTracer.setText("Tracer");
			jButtonTracer.setPreferredSize(new Dimension(91, 26));
			jButtonTracer
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							gfMaGF.recupValUtil();
							if (gfMaGF.getValCorrectes() == true) {
								if ("Personnaliser...".equals(jComboBox.getSelectedItem().toString())){
									gfMaGF.creerFenNR();
									gfMaGF.getFenNR().setVisible(true);
									gfMaGF.getFenNR().setLocationRelativeTo(null); // Permet de positionner la fenêtre au centre de l'écran
								} else {
									tracer();
								}
							} 
						}
					});
		}
		return jButtonTracer;
	}

	/**
	 * This method initializes jPanelGaucheBasMilieu	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelGaucheBasMilieu() {
		if (jPanelGaucheBasMilieu == null) {
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(5);
			gridLayout.setColumns(2);
			jPanelGaucheBasMilieu = new JPanel();
			jPanelGaucheBasMilieu.setBackground(new Color(204, 204, 204));
			jPanelGaucheBasMilieu.setLayout(gridLayout);
			jPanelGaucheBasMilieu.add(getJPanel(), null);
			jPanelGaucheBasMilieu.add(getJPanel1(), null);
			jPanelGaucheBasMilieu.add(getJPanel2(), null);
			jPanelGaucheBasMilieu.add(getJPanel3(), null);
			jPanelGaucheBasMilieu.add(getJPanel5(), null);
		}
		return jPanelGaucheBasMilieu;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			FlowLayout flowLayout3 = new FlowLayout();
			flowLayout3.setHgap(10);
			flowLayout3.setVgap(15);
			jLabel2 = new JLabel();
			jLabel2.setText("Axiome :");
			jPanel = new JPanel();
			jPanel.setBackground(new Color(204, 204, 204));
			jPanel.setLayout(flowLayout3);
			jPanel.add(jLabel2, null);
			jPanel.add(getJTextFieldAxiome(), null);
			jPanel.add(getJButtonInfo(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jTextFieldAxiome	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldAxiome() {
		if (jTextFieldAxiome == null) {
			jTextFieldAxiome = new JTextField();
			jTextFieldAxiome.setPreferredSize(new Dimension(120, 20));
		}
		return jTextFieldAxiome;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			FlowLayout flowLayout6 = new FlowLayout();
			flowLayout6.setHgap(38);
			flowLayout6.setVgap(15);
			jLabel3 = new JLabel();
			jLabel3.setText("Nb de Règles :");
			jPanel1 = new JPanel();
			jPanel1.setBackground(new Color(204, 204, 204));
			jPanel1.setLayout(flowLayout6);
			jPanel1.add(jLabel3, null);
			jPanel1.add(getJTextFieldNbRegle(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			FlowLayout flowLayout5 = new FlowLayout();
			flowLayout5.setHgap(65);
			flowLayout5.setVgap(15);
			jLabel4 = new JLabel();
			jLabel4.setText("Angle (°) :");
			jPanel2 = new JPanel();
			jPanel2.setBackground(new Color(204, 204, 204));
			jPanel2.setLayout(flowLayout5);
			jPanel2.add(jLabel4, null);
			jPanel2.add(getJTextFieldAngle(), null);
		}
		return jPanel2;
	}

	/**
	 * This method initializes jPanel3	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			FlowLayout flowLayout4 = new FlowLayout();
			flowLayout4.setHgap(10);
			flowLayout4.setVgap(15);
			jLabel5 = new JLabel();
			jLabel5.setText("Itération de départ :");
			jPanel3 = new JPanel();
			jPanel3.setBackground(new Color(204, 204, 204));
			jPanel3.setLayout(flowLayout4);
			jPanel3.add(jLabel5, null);
			jPanel3.add(getJTextFieldIterationDepart(), null);
		}
		return jPanel3;
	}

	/**
	 * This method initializes jPanel5	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel5() {
		if (jPanel5 == null) {
			jLabel8 = new JLabel();
			jLabel8.setText("Maxi : --");
			FlowLayout flowLayout7 = new FlowLayout();
			flowLayout7.setHgap(20);
			flowLayout7.setVgap(15);
			jLabel6 = new JLabel();
			jLabel6.setText("Nb d'itération maxi :");
			jPanel5 = new JPanel();
			jPanel5.setBackground(new Color(204, 204, 204));
			jPanel5.setLayout(flowLayout7);
			jPanel5.add(jLabel6, null);
			jPanel5.add(getJTextFieldIterationMax(), null);
			jPanel5.add(jLabel8, null);
		}
		return jPanel5;
	}

	/**
	 * This method initializes jTextFieldNbRegle	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldNbRegle() {
		if (jTextFieldNbRegle == null) {
			jTextFieldNbRegle = new JTextField();
			jTextFieldNbRegle.setPreferredSize(new Dimension(50, 20));
		}
		return jTextFieldNbRegle;
	}

	/**
	 * This method initializes jTextFieldAngle	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldAngle() {
		if (jTextFieldAngle == null) {
			jTextFieldAngle = new JTextField();
			jTextFieldAngle.setPreferredSize(new Dimension(50, 20));
		}
		return jTextFieldAngle;
	}

	/**
	 * This method initializes jTextFieldIterationDepart	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldIterationDepart() {
		if (jTextFieldIterationDepart == null) {
			jTextFieldIterationDepart = new JTextField();
			jTextFieldIterationDepart.setPreferredSize(new Dimension(50, 20));
		}
		return jTextFieldIterationDepart;
	}

	/**
	 * This method initializes jTextFieldIterationMax	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldIterationMax() {
		if (jTextFieldIterationMax == null) {
			jTextFieldIterationMax = new JTextField();
			jTextFieldIterationMax.setPreferredSize(new Dimension(50, 20));
		}
		return jTextFieldIterationMax;
	}

	/**
	 * This method initializes jPanelDroite	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelDroite() {
		if (jPanelDroite == null) {
			jLabel7 = new JLabel();
			jLabel7.setText(" Fractal");
			jLabel7.setBackground(new Color(204, 204, 204));
			jPanelDroite = new JPanel();
			jPanelDroite.setLayout(new BorderLayout());
			jPanelDroite.setBackground(new Color(204, 204, 204));
			jPanelDroite.add(jLabel7, BorderLayout.NORTH);
			jPanelDroite.add(getJPanelDroiteBas(), BorderLayout.SOUTH);
			jPanelDroite.add(getJPanelDessinFractalPanel(), BorderLayout.CENTER);
		}
		return jPanelDroite;
	}

	/**
	 * This method initializes jPanelDroiteBas	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelDroiteBas() {
		if (jPanelDroiteBas == null) {
			FlowLayout flowLayout8 = new FlowLayout();
			flowLayout8.setHgap(220);
			jLabel9 = new JLabel();
			jLabel9.setText("Itération :");
			jLabelIteration = new JLabel();
			jLabelIteration.setText("--");
			jPanelDroiteBas = new JPanel();
			jPanelDroiteBas.setBackground(new Color(204, 204, 204));
			jPanelDroiteBas.setLayout(flowLayout8);
			jPanelDroiteBas.add(getJPanelDroiteBasGauche(), null);
			jPanelDroiteBas.add(getJPanelDroiteBasDroite(), null);
		}
		return jPanelDroiteBas;
	}

	/**
	 * This method initializes jPanelDroiteBasGauche	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelDroiteBasGauche() {
		if (jPanelDroiteBasGauche == null) {
			jPanelDroiteBasGauche = new JPanel();
			jPanelDroiteBasGauche.setLayout(new FlowLayout());
			jPanelDroiteBasGauche.setBackground(new Color(204, 204, 204));
			jPanelDroiteBasGauche.add(jLabel9, null);
			jPanelDroiteBasGauche.add(jLabelIteration, null);
		}
		return jPanelDroiteBasGauche;
	}

	/**
	 * This method initializes jPanelDroiteBasDroite	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelDroiteBasDroite() {
		if (jPanelDroiteBasDroite == null) {
			jLabelTrait = new JLabel();
			jLabelTrait.setText("--");
			jLabel10 = new JLabel();
			jLabel10.setText("Taille du trait :");
			jPanelDroiteBasDroite = new JPanel();
			jPanelDroiteBasDroite.setLayout(new FlowLayout());
			jPanelDroiteBasDroite.setBackground(new Color(204, 204, 204));
			jPanelDroiteBasDroite.add(jLabel10, null);
			jPanelDroiteBasDroite.add(jLabelTrait, null);
		}
		return jPanelDroiteBasDroite;
	}
	
	/**
	 * This method initializes jButtonInfo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonInfo() {
		if (jButtonInfo == null) {
			jButtonInfo = new JButton();
			jButtonInfo.setText("Info...");
			jButtonInfo.setPreferredSize(new Dimension(85, 26));
			jButtonInfo.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					FenetreRegles fRegle = new FenetreRegles(gfMaGF.getFenP());
					fRegle.setVisible(true);//On affiche la fenêtre sur les informations des règles
					fRegle.setLocationRelativeTo(null); //Permet de positionner la fenêtre au centre de l'écran
				}
			});
		}
		return jButtonInfo;
	}
	
	private JButton getJButtonZoomMieux() {
		if (jButtonZoomMieux == null) {
			jButtonZoomMieux = new JButton();
			jButtonZoomMieux.setText("Zoom 100%");
			jButtonZoomMieux.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					float fMeilleurTaille;
					fMeilleurTaille= jpdfPanel1.zoomMieux(seqMaSequence.getTailleTrait());
					seqMaSequence.setTailleTrait (fMeilleurTaille); 
					jpdfPanel1.configVal();
					//On arrondi à 1 chiffre après la virgule la taille du trait affiché à l'écran
					double dTailleTraitAffiche;
					dTailleTraitAffiche=(seqMaSequence.getTailleTrait()*10);
					dTailleTraitAffiche=Math.round(dTailleTraitAffiche);
					dTailleTraitAffiche /= 10;
					jLabelTrait.setText(Double.toString(dTailleTraitAffiche));
					jpdfPanel1.repaint();
				}
			});
		}
		return jButtonZoomMieux;
	}	
	
	public Sequence getMaSequence(){
		return seqMaSequence;
	}
	
	public Sequence getSequenceTempo(){
		return seqTempo;
	}

	//Méthode qui permet de récupérer les caractères entrés dans la case "Axiome" et de gérer les mauvais caractères entrés par l'utilisateur
	public String recupValAxiome(){
		String sAxiome;
	
		try {
			sAxiome = jTextFieldAxiome.getText();
		} catch (Exception e) {
			sAxiome = "w"; //w sera la valeur renvoye en cas d'erreur
		}
		
		//Permet de vérifier si l'utilisateur a entré que les symboles possibles
		for(int i=0; i<sAxiome.length();i++){
			if(sCaratereAutorise.contains(Character.toString( sAxiome.charAt(i)))==false){
				JOptionPane.showMessageDialog(null,"Un symbole dans l'axiome n'est pas valide, vérifiez les symboles possibles grâce au boutton Info...");
				sAxiome = "w";
			}
		}
		
		//Verifie si l'utilisateur a bien entré une valeur dans l'Axiome
		if(sAxiome.equals("")){
			JOptionPane.showMessageDialog(null,"Veuillez entrer une valeur dans l'axiome !");
			sAxiome = "w";
		}
		return sAxiome;
	}

	//Méthode qui permet de récupérer la valeur entré dans la case "Angle" et de gérer les mauvaises valeurs entrées par l'utilisateur	
	public float recupValAngle() {
		float fAngle;
		try {
			//Récupere la valeur dans jTextFieldAngle et la convertie en float
			fAngle = Float.parseFloat(jTextFieldAngle.getText().trim());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Entrer un angle correct !");
			fAngle = -1;
		}
		//Permet de vérifier si l'utilisateur a entré un angle positif
		if (fAngle<0 && fAngle != -1){
			JOptionPane.showMessageDialog(null,"Entrer une valeur d'angle positive !");
			fAngle = -1;
		}
		return fAngle;
	}
	
	//Méthode qui permet de récupérer la valeur entré dans la case "Nb de règle" et de gérer les mauvaises valeurs entrées par l'utilisateur	
	public int recupValNbRegle() {
		int iNbRegle;
		try {
			//Récupère la valeur dans jTextFieldNbRegle et la convertie en entier
			iNbRegle = Integer.parseInt(jTextFieldNbRegle.getText().trim());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Entrer un nombre de règles correct !");
			iNbRegle = -1;
		}
		//Permet de vérifier si l'utilisateur a entré un nombre positif
		if (iNbRegle<=0&& iNbRegle != -1){
			JOptionPane.showMessageDialog(null,"Entrer un nombre de regles strictement positif !");
			iNbRegle = -1;
		}
		return iNbRegle;
	}
	
	//Méthode qui permet de récupérer la valeur entré dans la case "Nb d'itération maxi" et de gérer les mauvaises valeurs entrées par l'utilisateur	
	public int recupValIterationMax() {
		int iIterationMax;
		try {
			//Récupère la valeur dans jTextFieldIterationMax et la convertie en entier
			iIterationMax = Integer.parseInt(jTextFieldIterationMax.getText().trim());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Entrer un nombre d'itération maximum correct !");
			iIterationMax = -1;
		}
		//Permet de vérifier si l'utilisateur a entré un nombre positif
		if (iIterationMax<0 && iIterationMax != -1){
			JOptionPane.showMessageDialog(null,"Entrer un nombre d'itération maximum positif !");
			iIterationMax = -1;
		}
		//Permet de vérifier si le nombre entré n'est pas trop grand
		if (("Personnaliser...".equals(jComboBox.getSelectedItem().toString()) == false) && (iIterationMax>Integer.parseInt(jLabel8.getText().substring(7).trim()))){
			JOptionPane.showMessageDialog(null,"La valeur d'itération maximum est trop grande.");
			iIterationMax = -1;
		}
		return iIterationMax;
	}
	
	//Méthode qui permet de récupérer la valeur entré dans la case "Itération de départ" et de gérer les mauvaises valeurs entrées par l'utilisateur	
	public int recupValIterationDep() {
		int iIterationDep;
		try {
			//Récupère la valeur dans jTextFieldIterationDepart et la convertie en entier
			iIterationDep = Integer.parseInt(jTextFieldIterationDepart.getText().trim());			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Entrer un nombre d'itération de départ correct !");
			iIterationDep = -1;
		}
		//Permet de vérifier si l'utilisateur a entré un nombre plus grand que celui entré dans la case "Nb d'itération maxi"
		if (iIterationDep>recupValIterationMax()){
			JOptionPane.showMessageDialog(null,"L'itération de départ est plus grande que l'itération maximum !");
			iIterationDep=-1;
		}
		//Permet de vérifier si l'utilisateur a entré un nombre positif
		if (iIterationDep < 0 && iIterationDep!=-1){
			JOptionPane.showMessageDialog(null,"Entrer un nombre d'itération de départ positif !");
			iIterationDep=-1;
		}
		
		return iIterationDep;
	}
	
	public void tracer(){
		seqMaSequence = new Sequence(gfMaGF);
		seqMaSequence.setIteration(recupValIterationDep());
		seqMaSequence.setAngle(recupValAngle());
		seqMaSequence.iterer();
		jpdfPanel1.configVal();//Tracer grand dessin avec IterarationDep
		jLabelTrait.setText(Float.toString(seqMaSequence.getTailleTrait()));
		jpdfPanel1.repaint();
		jpdfiPanel1.configValFinal();//Tracer petit dessin pour le cas où il n'a pas été trace avant (dans le cas d'un fractal personalisé)
		jpdfiPanel1.repaint();
		jLabelIteration.setText(Integer.toString(seqMaSequence.getIteration()));
		jButtonZoomP.setEnabled(true);
		jButtonZoomM.setEnabled(true);
		jButtonZoomMieux.setEnabled(true);
		jButtonIterationP.setEnabled(false);
		jButtonIterationM.setEnabled(false);
		if (recupValIterationDep()<recupValIterationMax()){
			jButtonIterationP.setEnabled(true);
		}
		if (recupValIterationDep()>0){
			jButtonIterationM.setEnabled(true);
		}
	}
	
}