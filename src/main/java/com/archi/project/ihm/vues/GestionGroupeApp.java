package com.archi.project.ihm.vues;

import com.archi.project.ihm.controlleurs.GroupeControlleur;
import com.archi.project.ihm.ecouteurs.EcouteurAjouterGroupe;
import com.archi.project.ihm.ecouteurs.EcouteurChangerGroupe;
import com.archi.project.ihm.ecouteurs.EcouteurCreerGroupeAleatoire;
import com.archi.project.ihm.ecouteurs.EcouteurSupprimerGroupe;
import com.archi.project.metier.LogiqueMetier;
import com.archi.project.metier.models.Eleve;
import com.archi.project.metier.models.Groupe;
import com.archi.project.metier.models.Sujet;
import com.archi.project.metier.models.UniteEnseignement;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GestionGroupeApp extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTable entityTable;
    private DefaultTableModel entityTableModel;
    private JTextField identifiantField;
    private JComboBox<UniteEnseignement> ueComboBox; 
    private JList<Eleve> eleveList; 
    private JComboBox<Sujet> sujetComboBox; 
    private JButton addButton;
    private JButton deleteButton;
    private JButton createRandomGroupsButton;
    private JButton changeGroupButton;
    
    private final GroupeControlleur groupeControlleur;
    private final LogiqueMetier logiqueMetier;

    private ArrayList<Eleve> selectedEleves;

    private JTextArea recapArea;

    public GestionGroupeApp(LogiqueMetier logiqueMetier) {
    	
    	
        setTitle("Gestion des Groupes");
        setSize(800, 700); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new BorderLayout());
        
        this.logiqueMetier = logiqueMetier;
        groupeControlleur = new GroupeControlleur(this.logiqueMetier);

        selectedEleves = new ArrayList<>();

        entityTableModel = new DefaultTableModel(new String[]{"Groupe", "Unité d'Enseignement", "Élèves", "Sujet"}, 0);
        entityTable = new JTable(entityTableModel);
        loadEntities();
        JScrollPane scrollPane = new JScrollPane(entityTable);
        add(scrollPane, BorderLayout.CENTER);

        TableColumn elevesColumn = entityTable.getColumnModel().getColumn(2);
        elevesColumn.setPreferredWidth(300); 
        
        JPanel inputPanel = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        inputPanel.setLayout(gbl);
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); 
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Identifiant :"), gbc);

        gbc.gridx = 1;
        identifiantField = new JTextField();
        identifiantField.setPreferredSize(new Dimension(150, 25));
        inputPanel.add(identifiantField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Unité d'Enseignement :"), gbc);

        gbc.gridx = 1;
        ueComboBox = new JComboBox<>(); 
        inputPanel.add(ueComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Élèves :"), gbc);

        gbc.gridx = 1;
        eleveList = new JList<>();
        eleveList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        eleveList.setCellRenderer(new EleveListRenderer());

        eleveList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    updateSelectedEleves();
                }
            }
        });

        JScrollPane eleveScrollPane = new JScrollPane(eleveList);
        eleveScrollPane.setPreferredSize(new Dimension(200, 150)); 
        inputPanel.add(eleveScrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(new JLabel("Sujet:"), gbc);

        gbc.gridx = 1;
        sujetComboBox = new JComboBox<>(); 
        inputPanel.add(sujetComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; 
        gbc.fill = GridBagConstraints.BOTH;
        recapArea = new JTextArea(5, 20);
        recapArea.setEditable(false);
        recapArea.setBorder(BorderFactory.createTitledBorder("Récapitulatif des élèves sélectionnés"));
        inputPanel.add(new JScrollPane(recapArea), gbc);

        addButton = new JButton("Ajouter");
        deleteButton = new JButton("Supprimer");
        createRandomGroupsButton= new JButton("Créer Groupe Aléatoire");
        changeGroupButton=new JButton("Changer Groupe");

        attachListeners(); 
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(createRandomGroupsButton);
        buttonPanel.add(changeGroupButton);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        loadComboBoxes();
        createMenuBar(); 

    }
    
    
    
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        JMenu gestionMenu = new JMenu("Gestion");
        
        JMenuItem gestionSujetsItem = new JMenuItem("Gestion Sujets");
        gestionSujetsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cacher la fenêtre actuelle
                GestionGroupeApp.this.setVisible(false);
                GestionSujetApp gestionSujet = new GestionSujetApp(getFrame(), logiqueMetier);
                gestionSujet.setVisible(true);
            }
        });
        
        JMenuItem gestionElevesItem = new JMenuItem("Gestion Élèves");
        gestionElevesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cacher la fenêtre actuelle
                GestionGroupeApp.this.setVisible(false);
                GestionEleveApp gestionEleve = new GestionEleveApp(getFrame(), logiqueMetier);
                gestionEleve.setVisible(true);
            }
        });

        JMenuItem gestionUEItem = new JMenuItem("Gestion UE");
        gestionUEItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cacher la fenêtre actuelle
                GestionGroupeApp.this.setVisible(false);
                GestionUEApp gestionUE = new GestionUEApp(getFrame(), logiqueMetier);
                gestionUE.setVisible(true);
            }
        });

        JMenuItem quitterItem = new JMenuItem("Quitter");
        quitterItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(GestionGroupeApp.this,
                        "Êtes-vous sûr de vouloir quitter ?",
                        "Confirmation de sortie",
                        JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        gestionMenu.add(gestionSujetsItem);
        gestionMenu.add(gestionElevesItem);
        gestionMenu.add(gestionUEItem);
        gestionMenu.addSeparator();
        gestionMenu.add(quitterItem);

        menuBar.add(gestionMenu);
        setJMenuBar(menuBar);
    }


    private void attachListeners() {
        addButton.addActionListener(new EcouteurAjouterGroupe(this, groupeControlleur));
        deleteButton.addActionListener(new EcouteurSupprimerGroupe(this, groupeControlleur));
        createRandomGroupsButton.addActionListener(new EcouteurCreerGroupeAleatoire(this, groupeControlleur));
        changeGroupButton.addActionListener(new EcouteurChangerGroupe(this, groupeControlleur));
    }

    private void loadComboBoxes() {
    	
        for (UniteEnseignement ue : groupeControlleur.getAllUEs()) {
            ueComboBox.addItem(ue);
        }

        ArrayList<Eleve> eleves = groupeControlleur.getAllEleves();
        eleveList.setListData(eleves.toArray(new Eleve[0]));

        for (Sujet sujet : groupeControlleur.getAllSujets()) {
            sujetComboBox.addItem(sujet);
        }

        ueComboBox.setRenderer(new CustomComboBoxRenderer());
        sujetComboBox.setRenderer(new CustomComboBoxRenderer());
    }

    public void loadEntities() {
        entityTableModel.setRowCount(0);
        ArrayList<Groupe> groupeListData = groupeControlleur.getAllGroupes();
        for (Groupe groupe : groupeListData) {
            StringBuilder elevesStringBuilder = new StringBuilder();
            for (Eleve eleve : groupe.getEleves()) {
                if (elevesStringBuilder.length() > 0) {
                    elevesStringBuilder.append(", ");
                }
                elevesStringBuilder.append("ID: ").append(eleve.getId())
                                   .append(" - ").append(eleve.getNom())
                                   .append(" ").append(eleve.getPrenom());
            }

            entityTableModel.addRow(new Object[]{
                groupe.getIdentifiant(),
                groupe.getUe().getCode(),
                elevesStringBuilder.toString(), 
                groupe.getSujet().getIntitule()
            });
        }
    }

    private void updateSelectedEleves() {
        StringBuilder selectedElevesStringBuilder = new StringBuilder(); 

        for (Eleve eleve : eleveList.getSelectedValuesList()) {
            if (!selectedEleves.contains(eleve)) {
                selectedEleves.add(eleve); 
            } else {
                selectedEleves.remove(eleve); 
            }
        }

        for (Eleve eleve : selectedEleves) {
            if (selectedElevesStringBuilder.length() > 0) {
                selectedElevesStringBuilder.append(", ");
            }
            selectedElevesStringBuilder.append(eleve.getNom()).append(" ").append(eleve.getPrenom());
        }

        recapArea.setText(selectedElevesStringBuilder.toString());
    }

    public Eleve getSelectedEleve() {
        int selectedRow = entityTable.getSelectedRow();
        if (selectedRow == -1) {
            return null; 
        }

        String elevesString = (String) entityTableModel.getValueAt(selectedRow, 2); 

        String[] elevesArray = elevesString.split(", "); 
        if (elevesArray.length == 0) {
            return null;
        }

        String firstEleveInfo = elevesArray[0]; 
        String[] parts = firstEleveInfo.split(" - ");
        if (parts.length > 0) {
            String idPart = parts[0]; 
            String id = idPart.substring(4); 

            for (Eleve eleve : groupeControlleur.getAllEleves()) {
                if (String.valueOf(eleve.getId()).equals(id)) { 
                    return eleve; 
                }
            }
        }

        return null; 
    }
    public JFrame getFrame() {
    	return this;
    }

    public JTextField getIdentifiantField() {
        return identifiantField;
    }

    public JComboBox<UniteEnseignement> getUeComboBox() {
        return ueComboBox;
    }

    public JComboBox<Sujet> getSujetComboBox() {
        return sujetComboBox;
    }

    public ArrayList<Eleve> getSelectedEleves() {
        return selectedEleves;
    }

 
    public JTextArea getRecapArea() {
        return recapArea;
    }

    public JTable getEntityTable() {
        return entityTable;
    }

    public DefaultTableModel getEntityTableModel() {
        return entityTableModel;
    }
    

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            GestionGroupeApp app = new GestionGroupeApp();
//            app.setVisible(true);
//        });
//    }
} 