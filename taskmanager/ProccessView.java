package taskmanager;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import helper.Table;

public class ProccessView extends JFrame {

	private JPanel leftPanel;
	private JPanel rightPanel;
	
	private GridBagConstraints gbc;

	// Left Side
	private JButton list;
	private JButton clear;
	private JButton kill;
	private JTable proccessTable;
	private JTextField numExec;

	// Right Side
	private JButton threads;
	private JTextField proccessNumber;
	private JTable threadTable;
	private JTextField numThread;

	public ProccessView(){
		this.setFonts();
		this.setLayoutManager();
		this.initComponents();
		this.resizeColumnWidth(this.proccessTable);
		this.initListeners();
		this.setDefaultConfigs();
	}

	protected void setFonts(){
		UIManager.put("Label.font", new Font("SansSerif", Font.TRUETYPE_FONT, 12));  
		UIManager.put("TextField.font", new Font("SansSerif", Font.TRUETYPE_FONT, 12));
	}

	protected void initComponents(){
		this.startLeftComponents();
		this.startRightComponents();
	}

	protected void startLeftComponents(){
		this.gbc = new GridBagConstraints();
		// Line 1
		this.list = new JButton("Listar");
		this.clear = new JButton("Clear All");
		this.kill = new JButton("Kill Process");
		this.gbc.fill = GridBagConstraints.HORIZONTAL;
		this.gbc.weighty = 0.1;
		this.gbc.gridx = 0;
		this.gbc.gridy = 0;
		this.leftPanel.add(this.list, this.gbc);
		this.gbc.gridx = 1;
		this.gbc.gridy = 0;
		this.leftPanel.add(this.clear, this.gbc);
		this.gbc.gridx = 2;
		this.gbc.gridy = 0;
		this.leftPanel.add(this.kill, this.gbc);
		//Line 2
		String[] columns = {"PID", "%CPU", "%MEM", "Usuário", "Início", "Tempo", "Nome"};
		Object[][] data ={
			{"223", 	"2,9", "0,2", "_coreaudiod", 		"11:39", "3:07.82", 	"coreaudiod"	},
			{"136", 	"1,3", "1,7", "guilhermecoltro", 	"12:52", "1:43.13", 	"WindowServer"	},
			{"619", 	"0,5", "0,9", "guilhermecoltro", 	"12:50", "0:54.43", 	"Spotify"		},
			{"595", 	"0,3", "2,3", "_windowserver", 		"11:39", "1:14.16", 	"Terminal"		},
			{"976", 	"0,1", "2,4", "guilhermecoltro", 	"1:28",  "2:06.80", 	"Spotify Helper"},
			{"1081",	"0,0", "0,2", "guilhermecoltro", 	"1:41",  "0:00.33", 	"nbstated"		},
			{"1074",	"0,0", "0,1", "root", 				"1:40",  "0:00.03", 	"nbagent"		},
			{"1073",	"0,0", "0,3", "guilhermecoltro", 	"1:39",  "0:00.28", 	"iTunesHelper"	}
		};
		
		this.proccessTable = new JTable(data, columns);
		Table.gen(this.proccessTable, data, columns);
		JScrollPane scroll = new JScrollPane(this.proccessTable);
		scroll.setPreferredSize(new Dimension(250, 250));
		this.gbc.weighty = 2.8;
		this.gbc.ipady = 40;
		this.gbc.weightx = 1.0;
		this.gbc.fill = GridBagConstraints.BOTH;
		this.gbc.gridwidth = 3;
		this.gbc.gridx = 0;
		this.gbc.gridy = 1;
		this.leftPanel.add(scroll, this.gbc);
		// Line 3
		String qtd_processos = Integer.toString(data.length);
		JLabel label = new JLabel(qtd_processos+" Processos em execução.");
		this.gbc.fill = GridBagConstraints.HORIZONTAL;
		this.gbc.weighty = 0.1;
		this.gbc.ipady = 20;
		this.gbc.weightx = 0.0;
		this.gbc.gridwidth = 2;
		this.gbc.gridx = 0;
		this.gbc.gridy = 2;
		this.leftPanel.add(label, this.gbc);
	}

	protected void startRightComponents(){
		this.gbc = new GridBagConstraints();
		// Line 1
		this.threads = new JButton("Threads");
		JLabel proccess_id = new JLabel("Thread ID");
		this.proccessNumber = new JTextField();
		this.proccessNumber.setEditable(false);
		this.gbc.fill = GridBagConstraints.HORIZONTAL;
		this.gbc.weighty = 0.1;
		this.gbc.gridx = 0;
		this.gbc.gridy = 0;
		this.rightPanel.add(this.threads, this.gbc);
		this.gbc.gridx = 1;
		this.gbc.gridy = 0;
		this.rightPanel.add(proccess_id, this.gbc);
		this.gbc.gridx = 2;
		this.gbc.gridy = 0;
		this.rightPanel.add(this.proccessNumber, this.gbc);
		//Line 2
		String[] columns = {"PID", "Tempo", "Nome"};
		Object[][] data ={
			{"595", "1:14.16", "Terminal"}
		};
		this.threadTable = new JTable(data, columns);
		Table.gen(this.threadTable, data, columns);
		JScrollPane scroll = new JScrollPane(this.threadTable);
		scroll.setPreferredSize(new Dimension(250, 250));
		this.gbc.fill = GridBagConstraints.BOTH;
		this.gbc.weighty = 2.8;
		this.gbc.ipady = 40;
		this.gbc.weightx = 1.0;
		this.gbc.gridwidth = 3;
		this.gbc.gridx = 0;
		this.gbc.gridy = 1;
		this.rightPanel.add(scroll, this.gbc);
		// Line 3
		String qtd_threads = Integer.toString(data.length);
		JLabel label = new JLabel("Número de Threads: "+qtd_threads);
		this.gbc.fill = GridBagConstraints.HORIZONTAL;
		this.gbc.weighty = 0.1;
		this.gbc.ipady = 20;
		this.gbc.weightx = 0.0;
		this.gbc.gridwidth = 2;
		this.gbc.gridx = 0;
		this.gbc.gridy = 2;
		this.rightPanel.add(label, this.gbc);
	}

	protected void setLayoutManager(){
		this.setLayout(new GridBagLayout());
		this.leftPanel  = new JPanel(new GridBagLayout());
		this.rightPanel = new JPanel(new GridBagLayout());
		this.leftPanel.setBorder(BorderFactory.createTitledBorder("Processo em Ação"));
		this.rightPanel.setBorder(BorderFactory.createTitledBorder("Processo Escolhido"));
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 1.0;
		c.weightx = 2.8;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		this.add(leftPanel, c);
		c.weighty = 1.0;
		c.weightx = 0.2;
		c.gridx = 3;
		c.gridy = 0;
		this.add(rightPanel, c);
	}

	protected void resizeColumnWidth(JTable table) {
	    final TableColumnModel columnModel = table.getColumnModel();
	    for (int column = 0; column < table.getColumnCount(); column++) {
	        int width = 50; // Min width
	        for (int row = 0; row < table.getRowCount(); row++) {
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width +1 , width);
	        }
	        columnModel.getColumn(column).setPreferredWidth(width);
	    }
	}

	protected void initListeners(){
		/*zero.addActionListener(this.input_zero);
		one.addActionListener(this.input_one);
		two.addActionListener(this.input_two);
		tree.addActionListener(this.input_tree);
		four.addActionListener(this.input_four);
		five.addActionListener(this.input_five);
		six.addActionListener(this.input_six);
		seven.addActionListener(this.input_seven);
		eight.addActionListener(this.input_eight);
		nine.addActionListener(this.input_nine);*/
	}

	protected void setDefaultConfigs(){
		this.setSize(860, 700);
		this.setTitle("Task Manager");
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                if(JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja sair?", "Sair", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION){
                	System.exit(0);
                }
            }
        });
	}

}