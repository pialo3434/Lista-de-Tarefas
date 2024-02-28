import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.SystemColor;

public class main {
	
	private JFrame frame;
	private JTextField login_field_p = new JTextField();
	private JTextField login_field_u = new JTextField();
	private JTextField registo_field_u = new JTextField();
	private JTextField registo_field_p1 = new JTextField();
	private JTextField registo_field_p2 = new JTextField();
	
	
	metodos_gerais geral = new metodos_gerais(); //Metodos gerais para utilizar ao longo do codigo (ex: dialog.Show())
	private JTextField titulo_t;
	private JTable table;
	private JTextField id_t;
	
	//VARIAVEIS GLOBAIS + ARRAY LISTS GLOBAIS
	public String currentUser;
	public String currentPass;
	ArrayList<User> storeUsers = new ArrayList<User>(); //Array para armazenar utilizadores


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main window = new main();
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
	public main() {
		initialize();
		
	}

	//Metodo para dar load nos dados da tabela
	public void refresh_m() {
		for (User u : storeUsers) { 
			if (u.getUsername().equals(currentUser)) {
				u.refresh(table); //Metodo para atualizar os dados
			}
		}
	}
	
	private void initialize() {
		
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setBounds(100, 100, 709, 476);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel painel_login = new JPanel();
		painel_login.setBackground(new Color(120, 155, 194));
		frame.getContentPane().add(painel_login, "name_440422340958600");
		painel_login.setLayout(null);
		
		JPanel painel_app = new JPanel();
		painel_app.setBackground(new Color(120, 155, 194));
		frame.getContentPane().add(painel_app, "name_11320851093100");
		painel_app.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Titulo");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(20, 24, 47, 14);
		painel_app.add(lblNewLabel);
		
		titulo_t = new JTextField();
		titulo_t.setFont(new Font("Arial", Font.PLAIN, 13));
		titulo_t.setBackground(new Color(230, 230, 230));
		titulo_t.setBounds(66, 23, 193, 20);
		painel_app.add(titulo_t);
		titulo_t.setColumns(10);
		
		JLabel lblDescrio = new JLabel("Descrição:");
		lblDescrio.setForeground(new Color(255, 255, 255));
		lblDescrio.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescrio.setBounds(20, 71, 78, 14);
		painel_app.add(lblDescrio);
		
		JComboBox tipo_t = new JComboBox();
		tipo_t.setFont(new Font("Arial", Font.PLAIN, 14));
		tipo_t.setModel(new DefaultComboBoxModel(new String[] {"Selecionar...", "Diária", "Semanal", "Mensal", "Indefinida"}));
		tipo_t.setBounds(20, 288, 116, 22);
		painel_app.add(tipo_t);
		
		JComboBox prioridade_t = new JComboBox();
		prioridade_t.setFont(new Font("Arial", Font.PLAIN, 14));
		prioridade_t.setModel(new DefaultComboBoxModel(new String[] {"Selecionar...", "Baixa", "Média", "Alta"}));
		prioridade_t.setBounds(20, 353, 116, 22);
		painel_app.add(prioridade_t);
		
		JLabel lblTipoDeTarefa = new JLabel("Tipo de tarefa");
		lblTipoDeTarefa.setForeground(new Color(255, 255, 255));
		lblTipoDeTarefa.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTipoDeTarefa.setBounds(20, 263, 102, 14);
		painel_app.add(lblTipoDeTarefa);
		
		JLabel lblDescrio_1_1 = new JLabel("Prioridade");
		lblDescrio_1_1.setForeground(new Color(255, 255, 255));
		lblDescrio_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescrio_1_1.setBounds(20, 328, 78, 14);
		painel_app.add(lblDescrio_1_1);
		
		id_t = new JTextField();
		id_t.setFont(new Font("Arial", Font.PLAIN, 14));
		id_t.setText("000");
		id_t.setEditable(false);
		id_t.setColumns(10);
		id_t.setBounds(180, 289, 86, 20);
		painel_app.add(id_t);
		
		JLabel lblId = new JLabel("ID");
		lblId.setForeground(new Color(255, 255, 255));
		lblId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblId.setBounds(180, 263, 36, 14);
		painel_app.add(lblId);
		
		JButton btnCriar = new JButton("Criar");
		btnCriar.setForeground(new Color(255, 255, 255));
		btnCriar.setBackground(new Color(113, 113, 255));
		btnCriar.setFont(new Font("Arial", Font.BOLD, 13));
		btnCriar.setBounds(312, 363, 102, 31);
		painel_app.add(btnCriar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setForeground(new Color(63, 99, 139));
		btnEditar.setBackground(new Color(255, 255, 255));
		btnEditar.setFont(new Font("Arial", Font.BOLD, 13));
		btnEditar.setBounds(437, 363, 102, 31);
		painel_app.add(btnEditar);
		
		JButton btnApagar = new JButton("Apagar");
		btnApagar.setForeground(new Color(255, 255, 255));
		btnApagar.setBackground(new Color(63, 99, 139));
		btnApagar.setFont(new Font("Arial", Font.PLAIN, 13));
		btnApagar.setBounds(562, 362, 102, 31);
		painel_app.add(btnApagar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(312, 11, 352, 331);
		painel_app.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Arial", Font.PLAIN, 13));
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Titulo", "Prioridade"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(42);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(186);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(90);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 96, 239, 147);
		painel_app.add(scrollPane_1);
		
		JTextArea desc_t = new JTextArea();
		desc_t.setBackground(new Color(230, 230, 230));
		desc_t.setWrapStyleWord(true);
		desc_t.setLineWrap(true);
		scrollPane_1.setViewportView(desc_t);
		desc_t.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBackground(new Color(113, 113, 255));
		btnLogin.setFont(new Font("Arial", Font.BOLD, 13));
		btnLogin.setBounds(160, 295, 109, 29);
		painel_login.add(btnLogin);
		
		JLabel titulo = new JLabel("Lista de Tarefas ");
		titulo.setForeground(new Color(255, 255, 255));
		titulo.setFont(new Font("Arial", Font.BOLD, 50));
		titulo.setBounds(155, 56, 399, 49);
		painel_login.add(titulo);
		
		login_field_p = new JTextField();
		login_field_p.setFont(new Font("Arial", Font.PLAIN, 13));
		login_field_p.setColumns(10);
		login_field_p.setBounds(134, 261, 158, 23);
		painel_login.add(login_field_p);
		
		JLabel lb_pass_login = new JLabel("Password:");
		lb_pass_login.setForeground(new Color(255, 255, 255));
		lb_pass_login.setFont(new Font("Arial", Font.PLAIN, 14));
		lb_pass_login.setBounds(56, 261, 68, 16);
		painel_login.add(lb_pass_login);
		
		JLabel lb_username_login = new JLabel("Username:");
		lb_username_login.setForeground(new Color(255, 255, 255));
		lb_username_login.setFont(new Font("Arial", Font.PLAIN, 14));
		lb_username_login.setBounds(56, 227, 68, 16);
		painel_login.add(lb_username_login);
		
		login_field_u = new JTextField();
		login_field_u.setFont(new Font("Arial", Font.PLAIN, 13));
		login_field_u.setColumns(10);
		login_field_u.setBounds(134, 227, 158, 23);
		painel_login.add(login_field_u);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Login");
		lblNewLabel_1_1_1_1.setForeground(new Color(98, 0, 196));
		lblNewLabel_1_1_1_1.setFont(new Font("Arial", Font.BOLD, 22));
		lblNewLabel_1_1_1_1.setBounds(176, 177, 68, 29);
		painel_login.add(lblNewLabel_1_1_1_1);
		
		JLabel lb_username_registo = new JLabel("Username:");
		lb_username_registo.setForeground(new Color(255, 255, 255));
		lb_username_registo.setFont(new Font("Arial", Font.PLAIN, 14));
		lb_username_registo.setBounds(376, 218, 68, 16);
		painel_login.add(lb_username_registo);
		
		JLabel lb_pass_registo = new JLabel("Password:");
		lb_pass_registo.setForeground(new Color(255, 255, 255));
		lb_pass_registo.setFont(new Font("Arial", Font.PLAIN, 14));
		lb_pass_registo.setBounds(376, 252, 68, 16);
		painel_login.add(lb_pass_registo);
		
		registo_field_u = new JTextField();
		registo_field_u.setFont(new Font("Arial", Font.PLAIN, 13));
		registo_field_u.setColumns(10);
		registo_field_u.setBounds(454, 217, 158, 23);
		painel_login.add(registo_field_u);
		
		registo_field_p1 = new JTextField();
		registo_field_p1.setFont(new Font("Arial", Font.PLAIN, 13));
		registo_field_p1.setColumns(10);
		registo_field_p1.setBounds(454, 251, 158, 23);
		painel_login.add(registo_field_p1);
		
		JButton btnRegisto = new JButton("Registo");
		btnRegisto.setForeground(new Color(255, 255, 255));
		btnRegisto.setBackground(new Color(113, 113, 255));
		btnRegisto.setFont(new Font("Arial", Font.BOLD, 13));
		btnRegisto.setBounds(481, 319, 109, 29);
		painel_login.add(btnRegisto);
		
		JLabel lb_confirmar_pass_registo = new JLabel("Confirmar:");
		lb_confirmar_pass_registo.setForeground(new Color(255, 255, 255));
		lb_confirmar_pass_registo.setFont(new Font("Arial", Font.PLAIN, 14));
		lb_confirmar_pass_registo.setBounds(376, 286, 68, 16);
		painel_login.add(lb_confirmar_pass_registo);
		
		registo_field_p2 = new JTextField();
		registo_field_p2.setFont(new Font("Arial", Font.PLAIN, 13));
		registo_field_p2.setColumns(10);
		registo_field_p2.setBounds(454, 285, 158, 23);
		painel_login.add(registo_field_p2);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Registo");
		lblNewLabel_1_1_1_1_1.setForeground(new Color(98, 0, 196));
		lblNewLabel_1_1_1_1_1.setFont(new Font("Arial", Font.BOLD, 22));
		lblNewLabel_1_1_1_1_1.setBounds(496, 177, 78, 29);
		painel_login.add(lblNewLabel_1_1_1_1_1);
		
		JLabel lb_username_login_1 = new JLabel("V0.01");
		lb_username_login_1.setForeground(Color.WHITE);
		lb_username_login_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lb_username_login_1.setBounds(530, 47, 44, 16);
		painel_login.add(lb_username_login_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setEnabled(false);
		textArea.setBounds(337, 160, 2, 208);
		painel_login.add(textArea);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		menuBar.setBackground(new Color(94, 136, 183));
		menuBar.setForeground(new Color(255, 255 ,255));
		frame.setJMenuBar(menuBar);
		
		JMenu menuPrincipal = new JMenu("Opções");
		menuPrincipal.setForeground(new Color(255, 255, 255));
		menuPrincipal.setForeground(new Color(255, 255 ,255));
		menuBar.add(menuPrincipal);
		
		JMenuItem item1 = new JMenuItem("Exportar lista");
		item1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		item1.setForeground(new Color(255, 255 ,255));
		item1.setBackground(new Color(94, 136, 183));
		menuPrincipal.add(item1);
		
		JMenuItem item2 = new JMenuItem("Créditos");
		item2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		item2.setForeground(new Color(255, 255 ,255));
		item2.setBackground(new Color(94, 136, 183));
		menuPrincipal.add(item2);
		
		JMenuItem item3 = new JMenuItem("Logout");
		item3.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		item3.setForeground(new Color(255, 255 ,255));
		item3.setBackground(new Color(94, 136, 183));
		menuPrincipal.add(item3);
		
		JMenuItem item4 = new JMenuItem("Sair");
		item4.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		item4.setForeground(new Color(255, 255 ,255));
		item4.setBackground(new Color(94, 136, 183));
		menuPrincipal.add(item4);
		
		//FAZER OS MENUS DESAPARECER AO INICIO
		item1.setVisible(false);
		item3.setVisible(false);
		
		
		//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		//Mouse Listener utiliazdo para exibir a informação dentro de cada row da tabela (ex: selecionar tarefa com id 001 
		//e depois o que acontece: titulo_t.setText(task.getTitle()); pondo o titulo da tarefa no text field!
		
		table.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		    	//Definir as "Coordenadas da table"
		        int row = table.rowAtPoint(e.getPoint());
		        int col = table.columnAtPoint(e.getPoint());
		        if (row >= 0 && col >= 0) {
		        	
		            String idString = table.getValueAt(row, 0).toString();
		            Integer id = Integer.parseInt(idString);
		            Task task = null;
		            
					for (User u : storeUsers) {
						if (u.getUsername().equals(currentUser)){
							//Caso o user que queremos seja encontrado entao aceder á array list nesse user e retirar 
							//os dados que queremos da determinada tarefa que selecionamos com o rato
							task = u.getTaskById(id);
						}
					}
		            
		            if (task != null) { //Se a tarefa que selecionamos for valida e existir
		            	//carregar dados
		                titulo_t.setText(task.getTitle());
		                desc_t.setText(task.getDescription());
		                tipo_t.setSelectedItem(task.getType());
		                prioridade_t.setSelectedItem(task.getPriority());
		                //formatar id para ter 001
		                String formattedId = String.format("%03d", id);
		                id_t.setText(formattedId);


		            } else {
		                geral.showDialogError("Tarefa nao encontrada erro...", "ERRO");
		            }
		        }
		    }
		});

		
		//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		
		btnApagar.addActionListener(new ActionListener() { //Apagar tarefa
			public void actionPerformed(ActionEvent e) {
		        if (!titulo_t.getText().equals("") & !desc_t.getText().equals("") & !id_t.getText().equals("") & !tipo_t.getSelectedItem().equals("Selecionar...") & !prioridade_t.getSelectedItem().equals("Selecionar...")) {
		            Integer id = Integer.parseInt(id_t.getText());
		            for (User u : storeUsers) {
		                if (u.getUsername().equals(currentUser)) {
		                	//Apagar tarefa depois de encontrar o utilizador
		                	u.deleteTask(id); //Chamar metodo
		                	refresh_m(); //Atualizar dados da tabela
		                	geral.clearTextComponents(painel_app);
		                	String formattedId = String.format("%03d", id);
		                	geral.showDialogInfo("A tarefa com o ID: " + formattedId + " foi APAGADA com sucesso!", "INFORMAÇÃO");
		                }
		            }
		        } else {
		            geral.showDialogError("Por favor preencha todos os campos e selecione todas \nas opeções disponiveis...", "ERRO");
		        }
			}
		});

		
		btnCriar.addActionListener(new ActionListener() { //Criar tarefa
			public void actionPerformed(ActionEvent e) {
				if (!titulo_t.getText().equals("") & !desc_t.getText().equals("") & !tipo_t.getSelectedItem().equals("Selecionar...") & !prioridade_t.getSelectedItem().equals("Selecionar...")) {
					
					String currentUsername = currentUser;//Dar get ao nome de utiliazdor atual
					User storeTempUser = null;

							for(User user: storeUsers) {
							    if(user.getUsername().equals(currentUsername)) { //Caso os utilizadores condizam...
							    	storeTempUser = user;
							        break;
							    }
							}
					
					Task t = new Task(titulo_t.getText(), desc_t.getText(),  (String)tipo_t.getSelectedItem(), (String)prioridade_t.getSelectedItem(), storeTempUser); //Criar a instancia (no caso tarefa)
					
					for (User u : storeUsers) {
						if (u.getUsername().equals(currentUser)) {
							u.addTask(t); //Adicionar a tarefa na array list do utlizador
						
							refresh_m(); //Atualizar a tabela 
							String formattedId = String.format("%03d", t.getId());
							geral.showDialogInfo("A tarefa com o ID: " + formattedId + " foi CRIADA com sucesso!", "INFORMAÇÃO");
						}
					}
					
				} else {
					geral.showDialogError("Por favor preencha todos os campos e selecione todas \nas opeções disponiveis...", "ERRO");
				}
			}
		});
		
		btnEditar.addActionListener(new ActionListener() { //Editar tarefa
		    public void actionPerformed(ActionEvent e) {
		        if (!titulo_t.getText().equals("") & !desc_t.getText().equals("") & !id_t.getText().equals("") & !tipo_t.getSelectedItem().equals("Selecionar...") & !prioridade_t.getSelectedItem().equals("Selecionar...")) {
		            Integer id = Integer.parseInt(id_t.getText()); //Converter o ID do text field ID para um inteiro
		            for (User u : storeUsers) {
		                if (u.getUsername().equals(currentUser)) {
		                    u.editTask(id, titulo_t.getText(), desc_t.getText(), (String)tipo_t.getSelectedItem(), (String)prioridade_t.getSelectedItem()); //Chamar o metodo para editar a tarefa de acordo com o ID
		                    refresh_m(); //Atualizar tabela
		                    String formattedId = String.format("%03d", id);
		                    geral.showDialogInfo("A tarefa com o ID: " + formattedId + " foi ALTERADA com sucesso!", "INFORMAÇÃO");
		                }
		            }
		        } else {
		            geral.showDialogError("Por favor preencha todos os campos e selecione todas \nas opeções disponiveis...", "ERRO");
		        }
		    }
		});

		
		//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		item1.addActionListener(new ActionListener() { //Exportar lista de tarefas
		    public void actionPerformed(ActionEvent e) {
		    	ArrayList<Task> data = new ArrayList<Task>(); //Tarefas para armazenar
		    	for (User u : storeUsers) {
		    		if (u.getUsername().equals(currentUser)) {
		    			data = u.getAllTasks(); //Retornar todas as tarefas e guarda-las
		    		}
		    	}
		    	
		    	//Criar ficheiro para o utilizador escolher o diretorio de onde guardar
		    	JFileChooser fileChooser = new JFileChooser(); 
		    	fileChooser.setDialogTitle("Save file");
		    	fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
		    	fileChooser.setAcceptAllFileFilterUsed(false);
		    	int userSelection = fileChooser.showSaveDialog(null);
		    	//Caso selecione um diretorio...
		    	if (userSelection == JFileChooser.APPROVE_OPTION) {
		    		File fileToSave = fileChooser.getSelectedFile();
		    		try {
		    			FileWriter fw = new FileWriter(fileToSave.getAbsolutePath()+".txt");
		    			for (Task task : data) { 
		    				//Escrever os dados de cada tarefa no ficheiro
		    				String formattedId = String.format("%03d", task.getId());
							fw.write("ID da Tarefa: " + formattedId + System.lineSeparator());
							fw.write("Titulo: " + task.getTitle() + System.lineSeparator());
							fw.write("Descrição: " + task.getDescription() + System.lineSeparator());
							fw.write("Tipo: " + task.getType() + System.lineSeparator());
							fw.write("Prioridade: " + task.getPriority() + System.lineSeparator());
							fw.write(System.lineSeparator());
						}
						fw.close();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
		    }
		});

        
        item2.addActionListener(new ActionListener() { //Créditos
            public void actionPerformed(ActionEvent e) {
                geral.showDialogInfo("Design: Paulo Costa\nBackend: Paulo Costa\nTurma: TPSI 1022", currentPass);
            }
        });
        
        item3.addActionListener(new ActionListener() { //Logout
            public void actionPerformed(ActionEvent e) {
            	
            	int result = JOptionPane.showConfirmDialog(null, "Deseja voltar ao menu inicial?", "INFORMAÇÃO", JOptionPane.YES_NO_OPTION);

            	if (result == JOptionPane.YES_OPTION) {
    	            painel_app.setVisible(false);
    	            painel_login.setVisible(true);
		    		item1.setVisible(false);
		    		item3.setVisible(false);
		    		geral.clearTextComponents(painel_app);
		    		geral.clearTableRows(table);
            	} 

            }
        });
        
        item4.addActionListener(new ActionListener() { //Sair
            public void actionPerformed(ActionEvent e) {
            	int result = JOptionPane.showConfirmDialog(null, "Deseja encerrar a aplicação?", "INFORMAÇÃO", JOptionPane.YES_NO_OPTION);

            	if (result == JOptionPane.YES_OPTION) {
                    System.exit(0);
            	} 
            	
            }
        });
        
		//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        btnRegisto.addActionListener(new ActionListener() { //Registar utilizador
            public void actionPerformed(ActionEvent e) {
                if (!registo_field_u.getText().equals("") & !registo_field_p1.getText().equals("") & !registo_field_p2.getText().equals("")) {
                    if (registo_field_p1.getText().equals(registo_field_p2.getText())) {
                        boolean userExists = false;  //Bool para ver se o user existe
                        for (User u : storeUsers) {
                            if (u.getUsername().equals(registo_field_u.getText())) {
                            	//User existe...
                                userExists = true;
                                break;
                            }
                        }
                        if (userExists) {
                            geral.showDialogError("Utilizador já existe, escolha outro nome de utilizador.", "ERRO");
                        } else {
                        	//Caso nao exista registar os dados de utilizador
                            User newUser = new User(registo_field_u.getText(), registo_field_p1.getText());
                            storeUsers.add(newUser);
                            geral.clearTextComponents(painel_login);
                            geral.showDialogInfo("Utilizador " + newUser.getUsername() + " criado com sucesso!", "INFORMAÇÃO");
                        }
                    } else {
                        geral.showDialogError("Passwords não coicidem...", "ERRO");
                    }
                } else {
                    geral.showDialogError("Porfavor preencha todos os campos!", "ERRO");
                }
            }
        });

		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Login 
				if (!login_field_u.getText().equals("") & !login_field_p.getText().equals("")) {
				    for (User u : storeUsers) {
				        if (u.getUsername().equals(login_field_u.getText()) && u.getPassword().equals(login_field_p.getText())) {
				        	//Definir os utilizadores e pass atuais
				            currentUser = login_field_u.getText();
				            currentPass = login_field_p.getText();
				            
				            refresh_m(); //Atualizar a tabela
				            painel_app.setVisible(true);
				            painel_login.setVisible(false);
		                    geral.clearTextComponents(painel_login);
				            geral.showDialogInfo(" Login bem sucedido.\n\n Bem-Vindo: " + u.getUsername() + "!\n", "INFORMAÇÃO");	
				            //Mostrar os outros itens do menu
				    		item1.setVisible(true);
				    		item3.setVisible(true);
				            break;
				        }
				    }
				    if(currentUser == null) geral.showDialogError("Usuario ou senha incorretos!", "ERRO");
				} else {
				    geral.showDialogError("Porfavor preencha todos os campos!", "ERRO");
				}
			}	
		});
	}
}
