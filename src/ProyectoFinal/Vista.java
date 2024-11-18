package ProyectoFinal;
 
import java.awt.Color;
import java.awt.EventQueue;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JLabel;
 
public class Vista {
 
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista window = new Vista();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
 
	/**
	 * Create the application.
	 */
	public Vista() {
		initialize();
	}
 
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(56, 56, 56));
		frame.setBounds(100, 100, 493, 415);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		Gestor gestor = new Gestor();
		
		
		JButton btnNewButton = new JButton("PAINT");
		btnNewButton.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String archivo = textField_1.getText();
				gestor.abrirPrograma("mspaint.exe",archivo);
			}
		});
		
		btnNewButton.setBackground(new Color(176, 253, 203));
		btnNewButton.setBounds(22, 19, 135, 39);
		
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("NOTEPAD");
		btnNewButton_1.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String archivo = textField_1.getText();
				gestor.abrirPrograma("notepad",archivo);
			}
		});
		
		btnNewButton_1.setBackground(new Color(176, 253, 203));
		btnNewButton_1.setBounds(175, 19, 142, 39);
		
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("EXPLORER");
		btnNewButton_2.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String archivo = textField_1.getText();
				gestor.abrirPrograma("explorer.exe", archivo);
			}
		});
		
		btnNewButton_2.setBackground(new Color(176, 253, 203));
		btnNewButton_2.setBounds(327, 19, 137, 39);
		frame.getContentPane().add(btnNewButton_2);
		
		textField = new JTextField();
		textField.setBackground(new Color(255, 255, 255));
		textField.setBounds(22, 159, 282, 29);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		JTextArea textArea = new JTextArea();
		gestor.leer(textArea);
		
		
		JButton btnNewButton_3 = new JButton("NAVEGAR");
		btnNewButton_3.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		btnNewButton_3.setBackground(new Color(176, 253, 203));
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String url = textField.getText();
				gestor.navegar(url);
				gestor.leer(textArea);
			}
		});
		
		btnNewButton_3.setBounds(307, 158, 117, 29);
		frame.getContentPane().add(btnNewButton_3);
		
		
		textArea.setBounds(22, 199, 402, 114);
		frame.getContentPane().add(textArea);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBackground(Color.WHITE);
		textField_1.setBounds(22, 103, 282, 29);
		frame.getContentPane().add(textField_1);
		
		JLabel lblNewLabel = new JLabel("Introduce el nombre del archivo que quieres abrir:");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(22, 69, 295, 23);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton_4 = new JButton("BORRAR HISTORIAL");
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				gestor.borrarHistorial(textArea);
			}
		});
		btnNewButton_4.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		btnNewButton_4.setBackground(new Color(176, 253, 203));
		btnNewButton_4.setBounds(22, 323, 159, 23);
		frame.getContentPane().add(btnNewButton_4);
		textArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    // Obtener la posición del clic
                    int offset = textArea.viewToModel2D(e.getPoint());
 
                    // Obtener el número de línea del clic
                    int line = textArea.getLineOfOffset(offset);
 
                    // Obtener el texto de la línea
                    int start = textArea.getLineStartOffset(line);
                    int end = textArea.getLineEndOffset(line);
                    String lineText = textArea.getText(start, end - start).trim();
 
                    // Copiar la línea al textField
                    textField.setText(lineText);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
	}
}
 