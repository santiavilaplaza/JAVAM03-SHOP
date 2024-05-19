package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import main.Shop;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CashView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textCashTotal;
	private Shop shop;


	/**
	 * Create the dialog.
	 */
	public CashView(Shop shop) {
		setTitle("Caja");
		
		this.shop = shop;
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblCashMoney = new JLabel("Dinero en caja:");
			lblCashMoney.setBounds(49, 25, 188, 14);
			contentPanel.add(lblCashMoney);
		}
		
		textCashTotal = new JTextField();
		textCashTotal.setEditable(false);
		textCashTotal.setBounds(80, 50, 160, 23);
		contentPanel.add(textCashTotal);
		textCashTotal.setColumns(10);
		takeCash();
	}
	
	public void takeCash(){
		double cash = shop.cash;
		String cashText = String.valueOf(cash);
		textCashTotal.setText(cashText);
	}
}
