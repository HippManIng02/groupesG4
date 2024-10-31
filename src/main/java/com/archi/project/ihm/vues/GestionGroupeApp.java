package com.archi.project.ihm.vues;

import com.archi.project.interfaces.GroupeInterface;
import com.archi.project.ihm.ecouteurs.EcouteurAjouterGroupe;
import com.archi.project.ihm.ecouteurs.EcouteurChangerGroupe;
import com.archi.project.ihm.ecouteurs.EcouteurCreerGroupeAleatoire;
import com.archi.project.ihm.ecouteurs.EcouteurSupprimerGroupe;
import com.archi.project.interfaces.EleveInterface;
import com.archi.project.interfaces.UniteEnseignementInterface;
import com.archi.project.interfaces.SujetInterface;
import com.archi.project.metier.models.Eleve;
import com.archi.project.metier.models.UniteEnseignement;
import com.archi.project.metier.models.Sujet;
import com.archi.project.metier.models.Groupe;
import com.archi.project.metier.services.GroupeService;
import com.archi.project.metier.services.EleveService;
import com.archi.project.metier.services.UniteEnseignementService;
import com.archi.project.metier.services.SujetService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;

public class GestionGroupeApp extends JFrame {

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
    private GroupeInterface groupeInterface;
    private UniteEnseignementInterface ueInterface;
    private EleveInterface eleveInterface;
    private SujetInterface sujetInterface;

    private ArrayList<Eleve> selectedEleves;

    private JTextArea recapArea;

    public GestionGroupeApp() {
        setTitle("Gestion des Groupes");
        setSize(800, 700); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new BorderLayout());

        groupeInterface = new GroupeService();
        ueInterface = new UniteEnseignementService();
        eleveInterface = new EleveService();
        sujetInterface = new SujetService();

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
        inputPanel.add(new JLabel("Identifiant:"), gbc);

        gbc.gridx = 1;
        identifiantField = new JTextField();
        identifiantField.setPreferredSize(new Dimension(150, 25));
        inputPanel.add(identifiantField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Unité d'Enseignement:"), gbc);

        gbc.gridx = 1;
        ueComboBox = new JComboBox<>(); 
        inputPanel.add(ueComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Élèves:"), gbc);

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
    }

    private void attachListeners() {
        addButton.addActionListener(new EcouteurAjouterGroupe(this));
        deleteButton.addActionListener(new EcouteurSupprimerGroupe(this));
        createRandomGroupsButton.addActionListener(new EcouteurCreerGroupeAleatoire(this));
        changeGroupButton.addActionListener(new EcouteurChangerGroupe(this));
    }

    private void loadComboBoxes() {
        ArrayList<UniteEnseignement> ues = ueInterface.listUEs();
        for (UniteEnseignement ue : ues) {
            ueComboBox.addItem(ue);
        }

        ArrayList<Eleve> eleves = eleveInterface.eleves();
        eleveList.setListData(eleves.toArray(new Eleve[0]));

        ArrayList<Sujet> sujets = sujetInterface.listSujets();
        for (Sujet sujet : sujets) {
            sujetComboBox.addItem(sujet);
        }

        ueComboBox.setRenderer(new CustomComboBoxRenderer());
        sujetComboBox.setRenderer(new CustomComboBoxRenderer());
    }

    private static class CustomComboBoxRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                       boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof UniteEnseignement) {
                UniteEnseignement ue = (UniteEnseignement) value;
                label.setText(ue.getCode() + " - " + ue.getDesignation()); 
            } else if (value instanceof Sujet) {
                Sujet sujet = (Sujet) value;
                label.setText(sujet.getIntitule()); 
            }
            return label;
        }
    }

    private static class EleveListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, 
                                                      boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Eleve) {
                Eleve eleve = (Eleve) value;
                label.setText("ID: " + eleve.getId() + " - " + eleve.getNom() + " " + eleve.getPrenom());
            }
            return label;
        }
    }

    public void loadEntities() {
        entityTableModel.setRowCount(0);
        ArrayList<Groupe> groupeListData = groupeInterface.listGroupes();
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

    public GroupeInterface getGroupeInterface() {
        return groupeInterface;
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GestionGroupeApp app = new GestionGroupeApp();
            app.setVisible(true);
        });
    }
}
