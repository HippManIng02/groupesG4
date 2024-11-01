package com.archi.project.ihm.vues;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class GestionEntityApp<T> extends JFrame {

    private static final long serialVersionUID = 1L;
    protected DefaultTableModel entityTableModel;
    protected JTable entityTable;
    protected JTextField field1; 
    protected JTextField field2; 
    protected JLabel messageLabel;

    public GestionEntityApp(JFrame mainFrame, String title, String[] columnNames, String field1Label, String field2Label) {
        setTitle(title);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

       
        JMenuBar menuBar = new JMenuBar();
        JMenu optionsMenu = new JMenu("Menu");
        JMenuItem backMenuItem = new JMenuItem("Retour");


        backMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            	dispose();
               
            	
                mainFrame.setVisible(true);
            }
        });

        optionsMenu.add(backMenuItem);
        menuBar.add(optionsMenu);
        setJMenuBar(menuBar); 

      
        entityTableModel = new DefaultTableModel(columnNames, 0);
        entityTable = new JTable(entityTableModel);
        entityTable.setFillsViewportHeight(true);
        entityTable.setGridColor(Color.LIGHT_GRAY);
        entityTable.setShowGrid(true);
        entityTable.setIntercellSpacing(new Dimension(1, 1));
        entityTable.getTableHeader().setReorderingAllowed(false);

        JTableHeader tableHeader = entityTable.getTableHeader();
        tableHeader.setBackground(new Color(100, 149, 237)); 
        tableHeader.setForeground(Color.WHITE); 
        tableHeader.setFont(new Font("Arial", Font.BOLD, 16)); 

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));
        inputPanel.setBackground(new Color(240, 240, 240));

        inputPanel.add(new JLabel(field1Label + ":"));
        field1 = new JTextField();
        inputPanel.add(field1);

        inputPanel.add(new JLabel(field2Label + ":"));
        field2 = new JTextField();
        inputPanel.add(field2);

        JButton addButton = new JButton("Ajouter");
        customizeButton(addButton, Color.GREEN);
        addButton.addActionListener(e -> addEntity());
        inputPanel.add(addButton);

        JButton deleteButton = new JButton("Supprimer");
        customizeButton(deleteButton, Color.RED);
        deleteButton.addActionListener(e -> deleteEntity());
        inputPanel.add(deleteButton);

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(new JScrollPane(entityTable), BorderLayout.CENTER);
        tablePanel.setBackground(Color.WHITE);


        messageLabel = new JLabel("");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setPreferredSize(new Dimension(0, 30));
        messageLabel.setOpaque(true);
        messageLabel.setBackground(new Color(255, 255, 224));
        messageLabel.setForeground(Color.BLACK);

        add(inputPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        add(messageLabel, BorderLayout.SOUTH);
    }

    protected abstract void loadEntities();
    protected abstract void addEntity();
    protected abstract void deleteEntity();

    protected void customizeButton(JButton button, Color backgroundColor) {
        button.setBackground(backgroundColor);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setOpaque(true);
        button.setBorderPainted(false);
    }

    protected void showMessage(String message, Color color) {
        messageLabel.setText(message);
        messageLabel.setForeground(color);
        Timer timer = new Timer(3000, e -> messageLabel.setText(""));
        timer.setRepeats(false);
        timer.start();
    }
}
