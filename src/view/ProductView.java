package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Product;
import main.Shop;
import utils.Constants;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ProductView extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textProductName;
	private JTextField textStockProduct;
	private JTextField textProductPrice;
	private JButton cancelButton;
	private JButton okButton;
	private Shop shop;
	private int mode;


	/**
	 * Create the dialog.
	 */
	public ProductView(Shop shop, int mode) {
		
		this.shop = shop;
		this.mode = mode;
		
		
		if (mode == Constants.OPTION_ADD_PRODUCT) {
			setTitle("Añadir Producto");

		}
		if (mode == Constants.OPTION_ADD_STOCK) {
			setTitle("Añadir Stock");

		}
		if (mode == Constants.OPTION_DELETE_PRODUCT) {
			setTitle("Eliminar Producto");

		}
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblProductName = new JLabel("Nombre Producto:");
		lblProductName.setBounds(46, 35, 120, 14);
		contentPanel.add(lblProductName);
		
		textProductName = new JTextField();
		textProductName.setBounds(160, 35, 130, 20);
		contentPanel.add(textProductName);
		textProductName.setColumns(10);
		{
			JLabel lblStockProduct = new JLabel("Stock Producto:");
			lblStockProduct.setBounds(46, 70, 120, 14);
			if (mode == Constants.OPTION_ADD_PRODUCT || mode == Constants.OPTION_ADD_STOCK) {
				contentPanel.add(lblStockProduct);				
			}
		}
		{
			textStockProduct = new JTextField();
			textStockProduct.setBounds(160, 70, 130, 20);
			if (mode == Constants.OPTION_ADD_PRODUCT || mode == Constants.OPTION_ADD_STOCK) {
				contentPanel.add(textStockProduct);				
			}
				textStockProduct.setColumns(10);
		}
		{
			JLabel lblProductPrice = new JLabel("Precio Producto:");
			lblProductPrice.setBounds(46, 105, 120, 14);
			if (mode == Constants.OPTION_ADD_PRODUCT) {
				contentPanel.add(lblProductPrice);				
			}
		}
		{
			textProductPrice = new JTextField();
			textProductPrice.setBounds(160, 105, 130, 20);
			if (mode == 2) {
				contentPanel.add(textProductPrice);
			}
			textProductPrice.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);

			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	    String actionCommand = e.getActionCommand();
	    
		
		if (e.getSource() == okButton){
			if (mode == Constants.OPTION_ADD_PRODUCT) {
				addProduct();
			}
			if (mode == Constants.OPTION_ADD_STOCK) {
				addStock();
			}
			if (mode == Constants.OPTION_DELETE_PRODUCT) {
				deleteProduct();
			}
		}
		 else if (actionCommand.equals("Cancel")) {
			 cancelOperation();	
	}
	
	}
	
	public void addProduct(){
		
		String productNameStr = textProductName.getText();
	    String stockProductStr = textStockProduct.getText();
	    String productPriceStr = textProductPrice.getText();
	    
	    int stockProduct;
	    int productPrice;
	    
        try {
        	stockProduct = Integer.parseInt(stockProductStr);
        	productPrice = Integer.parseInt(productPriceStr);
        } catch (NumberFormatException ex) {
        	JOptionPane.showMessageDialog(this, "stock o precio no válidos", "Error", JOptionPane.ERROR_MESSAGE);
        	return; 
        }
        
		
        Product product = shop.findProduct(productNameStr);

		if (product == null) {
    		product = new Product(productNameStr, productPrice, true, stockProduct);
    		shop.addProduct(product);
    		JOptionPane.showMessageDialog(this, "Producto añadido correctamente", "Info", JOptionPane.INFORMATION_MESSAGE);
        	dispose();
        } else {
        	JOptionPane.showMessageDialog(this, "El producto ya existe", "Error", JOptionPane.ERROR_MESSAGE);
        }
		
		shop.showInventory();
		
	}
	
	public void addStock() {
		String productNameStr = textProductName.getText();
	    String stockProductStr = textStockProduct.getText();
	    
	    int stockProduct;
	    
        try {
        	stockProduct = Integer.parseInt(stockProductStr);
        } catch (NumberFormatException ex) {
        	JOptionPane.showMessageDialog(this, "stock o precio no válidos", "Error", JOptionPane.ERROR_MESSAGE);
        	return; 
        }
        
        
        
        Product product = shop.findProduct(productNameStr);
        
        if (product != null) {
        	product.setStock(product.getStock() + stockProduct);
        	JOptionPane.showMessageDialog(this, "Stock añadido correctamente", "Info", JOptionPane.INFORMATION_MESSAGE);
        	dispose();
        } else {
        	JOptionPane.showMessageDialog(this, "El producto no existe", "Error", JOptionPane.ERROR_MESSAGE);
        }

        shop.showInventory();
		
        
	}
	
	public void deleteProduct() {
		String productNameStr = textProductName.getText();
        
        Product product = shop.findProduct(productNameStr);
        if (product != null) {
			int index = shop.getInventory().indexOf(product);
			shop.getInventory().remove(index);
			JOptionPane.showMessageDialog(this, "Producto eliminado correctamente", "Info", JOptionPane.INFORMATION_MESSAGE);
			dispose();
		} else {
        	JOptionPane.showMessageDialog(this, "El producto no existe", "Error", JOptionPane.ERROR_MESSAGE);
		}
        
		shop.showInventory();
        
	}
	
	public void cancelOperation() {
		dispose();
	}

}
