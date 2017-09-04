package fenetres;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;

public class FenetreRegles extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JPanel jPanelBas = null;
	private JButton jButtonOk = null;
	private JPanel jPanelMilieu = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel5 = null;
	private JLabel jLabel6 = null;
	private JLabel jLabel7 = null;
	private JLabel jLabel8 = null;
	private JPanel jPanel = null;
	private JFrame fenF;
	
	/**
	 * This is the default constructor
	 */
	public FenetreRegles(JFrame fenFParam) {
		super();
		initialize();
		fenF=fenFParam;
		fenF.setEnabled(false);
		this.setAlwaysOnTop(true);	
	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(255, 280);
		this.setResizable(false); //Empêche le redimensionnement de la fenêtre
		this.setContentPane(getJContentPane());
		this.setTitle("Information");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel = new JLabel();
			jLabel.setText(" Signification des symboles ");
			jLabel.setBackground(new Color(204, 204, 204));
			jLabel.setFont(new Font("Dialog", Font.BOLD, 13));
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.setBackground(new Color(204, 204, 204));
			jContentPane.add(jLabel, BorderLayout.NORTH);
			jContentPane.add(getJPanelBas(), BorderLayout.SOUTH);
			jContentPane.add(getJPanelMilieu(), BorderLayout.CENTER);
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
			jPanelBas = new JPanel();
			jPanelBas.setLayout(new FlowLayout());
			jPanelBas.setBackground(new Color(204, 204, 204));
			jPanelBas.add(getJButtonOk(), null);
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
					fenF.setEnabled(true);
					setVisible(false) ; //Permet de cacher la fenêtre quand on clique sur OK					
					}
			});
		}
		return jButtonOk;
	}

	/**
	 * This method initializes jPanelMilieu	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelMilieu() {
		if (jPanelMilieu == null) {
			jLabel8 = new JLabel();
			jLabel8.setText(" ]  : revenir à la dernière position stockée");
			jLabel8.setBackground(new Color(204, 204, 204));
			jLabel7 = new JLabel();
			jLabel7.setText(" [  : stocker la position sur le plan");
			jLabel7.setBackground(new Color(204, 204, 204));
			jLabel6 = new JLabel();
			jLabel6.setText(" Y : rien");
			jLabel6.setBackground(new Color(204, 204, 204));
			jLabel5 = new JLabel();
			jLabel5.setText(" X : rien");
			jLabel5.setBackground(new Color(204, 204, 204));
			jLabel4 = new JLabel();
			jLabel4.setText("  - : tourner à gauche d'un angle a");
			jLabel4.setBackground(new Color(204, 204, 204));
			jLabel3 = new JLabel();
			jLabel3.setText("  F : avancer (en dessinant un trait)");
			jLabel3.setBackground(new Color(204, 204, 204));
			jLabel3.setFont(new Font("Dialog", Font.BOLD, 12));
			jLabel2 = new JLabel();
			jLabel2.setText(" + : tourner à droite d'un angle a");
			jLabel2.setBackground(new Color(204, 204, 204));
			jLabel1 = new JLabel();
			jLabel1.setText(" G : avancer (en dessinant un trait)");
			jLabel1.setBackground(new Color(204, 204, 204));
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(10);
			jPanelMilieu = new JPanel();
			jPanelMilieu.setBackground(new Color(204, 204, 204));
			jPanelMilieu.setLayout(gridLayout);
			jPanelMilieu.add(getJPanel(), null);
			jPanelMilieu.add(jLabel3, null);
			jPanelMilieu.add(jLabel1, null);
			jPanelMilieu.add(jLabel2, null);
			jPanelMilieu.add(jLabel4, null);
			jPanelMilieu.add(jLabel5, null);
			jPanelMilieu.add(jLabel6, null);
			jPanelMilieu.add(jLabel7, null);
			jPanelMilieu.add(jLabel8, null);
		}
		return jPanelMilieu;
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
			jPanel = new JPanel();
			jPanel.setBackground(new Color(204, 204, 204));
			jPanel.setLayout(gridLayout1);
		}
		return jPanel;
	}

}
