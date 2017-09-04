package fenetres;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JTextField;

import structure_prog.GrammaireFormelle;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Color;
import java.awt.GridBagLayout;

public class FenetreNbRegles extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel jPanelBas = null;
	private JButton jButtonOk = null;
	private JButton jButtonAnnuler = null;
	private JPanel jPanelMilieu = null;
	private JPanel jPanelMilieuHaut = null;
	private JPanel jPanelMilieuBas = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JTextField jTextField = null;
	private JLabel jLabel3 = null;
	private JTextField jTextField1 = null;
	private JButton jButtonInfo = null;
	private JPanel jPanel = null;
	private JLabel jLabel = null;
	private JLabel jLabel4 = null;
	private JPanel jPanel1 = null;
	private JPanel jPanel2 = null;
	private JPanel jPanel3 = null;
	private JPanel jPanel5 = null;
	private GrammaireFormelle gfMaGF;
	private char cCaractInit; //Valeur de Caractere initial à renvoyer à la GrammaireFormelle
	private String sTransfo; //Valeur de Transfo initial à renvoyer à la GrammaireFormelle
	private int iNbReglesRest; //Compteur à décrémenter pour maintenir la fenêtre ouverte tant qu'il y a des règles à rentrer
	private int iNumRegleCurrent; //Compteur à  incrémenter pour savoir à quelle règle on en est
	private String sCaratereAutorise = new String(); //Chaine de caractère qui contient les caractères autorisés 

	/**
	 * This is the default constructor
	 */
	public FenetreNbRegles(int iNbReglesP, GrammaireFormelle gfMaGFP) {
		super();
		sCaratereAutorise="FG+-XY[]"; //Définition des caractères qui sont autorisés pour l'utilisateur
		iNumRegleCurrent=1;
		initialize();
		iNbReglesRest = iNbReglesP;
		cCaractInit = 'w';
		sTransfo = "w";
		gfMaGF = gfMaGFP;
		gfMaGF.getFenP().setEnabled(false);
		this.setAlwaysOnTop(true);
	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(350, 180);
		this.setLocation(new Point(465, 422));
		this.setResizable(false); // Empêche le redimensionnement de la fenêtre
		this.setContentPane(getJContentPane());
		this.setTitle("Définition des règles");
	}
	
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJPanelBas(), BorderLayout.SOUTH);
			jContentPane.add(getJPanelMilieu(), BorderLayout.CENTER);
			jContentPane.add(getJPanel(), BorderLayout.NORTH);
		}
		return jContentPane;
	}
	
	/**
	 * This method initializes jPanelBas
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelBas() {
		if (jPanelBas == null) {
			FlowLayout flowLayout2 = new FlowLayout();
			flowLayout2.setHgap(30);
			jPanelBas = new JPanel();
			jPanelBas.setBackground(new Color(204, 204, 204));
			jPanelBas.setLayout(flowLayout2);
			jPanelBas.add(getJButtonOk(), null);
			jPanelBas.add(getJButtonAnnuler(), null);
			jPanelBas.add(getJButtonInfo(), null);
		}
		return jPanelBas;
	}
	
	/**
	 * This method initializes jButtonOk
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonOk() {
		if (jButtonOk == null) {
			jButtonOk = new JButton();
			jButtonOk.setText("OK");
			jButtonOk.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					cCaractInit = recupValCarac();
					sTransfo = recupValTransfo();
					if (cCaractInit != 'w' && sTransfo != "w") {
						gfMaGF.ajouterRegles(cCaractInit, sTransfo);
						jTextField.setText("");
						jTextField1.setText("");
						iNbReglesRest--;
						iNumRegleCurrent++;
						jLabel.setText(Integer.toString(iNumRegleCurrent));
					}
					if (iNbReglesRest <= 0) {
						gfMaGF.getFenP().setEnabled(true);
						setVisible(false);						
						gfMaGF.getFenP().tracer();
					}
				}
			});
		}
		return jButtonOk;
	}
	
	/**
	 * This method initializes jButtonAnnuler
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonAnnuler() {
		if (jButtonAnnuler == null) {
			jButtonAnnuler = new JButton();
			jButtonAnnuler.setText("Annuler");
			// On cache la fenêtre quand on clique sur Annuler
			jButtonAnnuler.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							gfMaGF.getFenP().setEnabled(true);
							setVisible(false);
						}
					});
		}
		return jButtonAnnuler;
	}
	
	/**
	 * This method initializes jPanelMilieu
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelMilieu() {
		if (jPanelMilieu == null) {
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(2);
			gridLayout.setColumns(4);
			jPanelMilieu = new JPanel();
			jPanelMilieu.setLayout(gridLayout);
			jPanelMilieu.add(getJPanelMilieuHaut(), null);
			jPanelMilieu.add(getJPanelMilieuBas(), null);
		}
		return jPanelMilieu;
	}
	
	/**
	 * This method initializes jPanelMilieuHaut
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelMilieuHaut() {
		if (jPanelMilieuHaut == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setVgap(20);
			flowLayout.setHgap(45);
			jLabel2 = new JLabel();
			jLabel2.setText("Règle :");
			jLabel1 = new JLabel();
			jLabel1.setText("Symbol initial :");
			jPanelMilieuHaut = new JPanel();
			jPanelMilieuHaut.setBackground(new Color(204, 204, 204));
			jPanelMilieuHaut.setLayout(flowLayout);
			jPanelMilieuHaut.add(jLabel1, null);
			jPanelMilieuHaut.add(jLabel2, null);
			jPanelMilieuHaut.add(getJPanel5(), null);
			jPanelMilieuHaut.add(getJPanel3(), null);
		}
		return jPanelMilieuHaut;
	}
	
	/**
	 * This method initializes jPanelMilieuBas
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelMilieuBas() {
		if (jPanelMilieuBas == null) {
			FlowLayout flowLayout1 = new FlowLayout();
			flowLayout1.setHgap(10);
			flowLayout1.setVgap(10);
			jLabel3 = new JLabel();
			jLabel3.setText("---->");
			jPanelMilieuBas = new JPanel();
			jPanelMilieuBas.setBackground(new Color(204, 204, 204));
			jPanelMilieuBas.setLayout(flowLayout1);
			jPanelMilieuBas.add(getJTextField(), null);
			jPanelMilieuBas.add(jLabel3, null);
			jPanelMilieuBas.add(getJTextField1(), null);
		}
		return jPanelMilieuBas;
	}
	
	/**
	 * This method initializes jTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setPreferredSize(new Dimension(50, 20));
		}
		return jTextField;
	}
	
	/**
	 * This method initializes jTextField1
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
			jTextField1.setPreferredSize(new Dimension(130, 20));
		}
		return jTextField1;
	}
	
	/**
	 * This method initializes jButtonInfo
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonInfo() {
		if (jButtonInfo == null) {
			jButtonInfo = new JButton();
			jButtonInfo.setText("Information...");
			jButtonInfo.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					FenetreRegles fRegle = new FenetreRegles(gfMaGF.getFenNR());
					fRegle.setVisible(true);//On affiche la fenêtre sur les informations des règles
					fRegle.setLocationRelativeTo(null); //Permet de positionner la fenêtre au centre de l'écran
				}
			});
		}
		return jButtonInfo;
	}

	/**
	 * This method initializes jPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			GridLayout gridLayout1 = new GridLayout();
			gridLayout1.setRows(1);
			jLabel4 = new JLabel();
			jLabel4.setText("Définir la règle ");
			jLabel4.setFont(new Font("Dialog", Font.BOLD, 12));
			jLabel = new JLabel();
			jLabel.setText(Integer.toString(iNumRegleCurrent));
			jPanel = new JPanel();
			jPanel.setLayout(gridLayout1);
			jPanel.add(getJPanel2(), null);
			jPanel.add(getJPanel1(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jPanel1
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			GridLayout gridLayout2 = new GridLayout();
			gridLayout2.setRows(1);
			jPanel1 = new JPanel();
			jPanel1.setBackground(new Color(204, 204, 204));
			jPanel1.setLayout(gridLayout2);
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
			jPanel2 = new JPanel();
			jPanel2.setLayout(new FlowLayout());
			jPanel2.setBackground(new Color(204, 204, 204));
			jPanel2.add(jLabel4, null);
			jPanel2.add(jLabel, null);
		}
		return jPanel2;
	}

	//Méthode qui permet de récupérer la valeur du caractère entré dans la case "Symbol initial" et de gérer les mauvais caractères entrés par l'utilisateur
	public char recupValCarac() {
		String sValTextD;
		try {
			sValTextD = jTextField.getText();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Entrer un caractere initial correct !");
			sValTextD = "w"; //Renvoie "w" comme reference pour valeur erronée
		}
		//Permet de verifier si l'utilisateur entre que les symboles possibles
		int i;
		for(i=0; i<sValTextD.length();i++){
			if(sCaratereAutorise.contains(Character.toString( sValTextD.charAt(i)))==false){
				JOptionPane.showMessageDialog(null,"Un symbole dans la case Symbole initial n'est pas valide, vérifier les symboles possibles grâce au boutton Information...");
				sValTextD = "w";
			}
		}
		
		//On vérifie qu'on n'a bien qu'un caractère et pas plusieurs dans la case
		for (i=0; i<sValTextD.length();i++){
		}
		System.out.println(i);
		if (i>1){
			JOptionPane.showMessageDialog(null,"Vous avez entré trop de caractères dans la case Symbole initial !");
			sValTextD = "w"; 
		}
		
		//Verifie si l'utilisateur a bien entré une valeur dans la case Symbole initial
		if(sValTextD.equals("")){
			JOptionPane.showMessageDialog(null,"Veuillez entrer une valeur dans la case Symbole initial !");
			sValTextD = "w";
		}
		return sValTextD.charAt(0);
	}
	
	//Méthode qui permet de récupérer les caractères entrés dans la case "Règle" et de gérer les mauvais caractères entrés par l'utilisateur
	public String recupValTransfo() {
		String sValTextF;
		try {
			sValTextF = jTextField1.getText();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Entrer une chaine de transformation correct !");
			sValTextF = "w"; //Renvoie "w" comme reference pour valeur erronée
		}
		
		//Permet de verifier si l'utilisateur entre que les symboles possibles
		for(int i=0; i<sValTextF.length();i++){
			if(sCaratereAutorise.contains(Character.toString( sValTextF.charAt(i)))==false){
				JOptionPane.showMessageDialog(null,"Un symbole dans la règle entrée n'est pas valide, vérifiez les symboles possibles grâce au boutton Information...");
				sValTextF = "w";
			}
		}
		//Verifie si l'utilisateur a bien entré une valeur dans la case Règle
		if(sValTextF.equals("")){
			JOptionPane.showMessageDialog(null,"Veuillez entrer une valeur dans la case règle !");
			sValTextF = "w";
		}
		return sValTextF;
	}
	
	/**
	 * This method initializes jPanel3	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jPanel3 = new JPanel();
			jPanel3.setLayout(new GridBagLayout());
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
			jPanel5 = new JPanel();
			jPanel5.setLayout(new GridBagLayout());
		}
		return jPanel5;
	}

}
