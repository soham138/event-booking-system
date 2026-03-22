package ui;

import models.EventPackage;
import services.EventPackageService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewPackagesFrame extends JFrame {
    public ViewPackagesFrame() {
        setTitle("Event Booking System - View Packages");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel searchLabel = new JLabel("Search Package:");
        JTextField searchField = new JTextField(20);
        JButton searchBtn = new JButton("Search");

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchBtn);

        
        String[] columns = {"Package ID", "Package Name", "Description", "Price", "Capacity"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        
        refreshTable(tableModel);

        
        searchBtn.addActionListener(e -> {
            String searchTerm = searchField.getText();
            tableModel.setRowCount(0);
            List<EventPackage> packages = EventPackageService.searchEventPackages(searchTerm);
            for (EventPackage pkg : packages) {
                tableModel.addRow(new Object[]{
                    pkg.getPackageId(),
                    pkg.getPackageName(),
                    pkg.getDescription(),
                    "$" + pkg.getPrice(),
                    pkg.getCapacity()
                });
            }
        });

        
        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.addActionListener(e -> {
            tableModel.setRowCount(0);
            searchField.setText("");
            refreshTable(tableModel);
        });

        searchPanel.add(refreshBtn);

        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
        setVisible(true);
    }

    private void refreshTable(DefaultTableModel tableModel) {
        List<EventPackage> packages = EventPackageService.getAllEventPackages();
        for (EventPackage pkg : packages) {
            tableModel.addRow(new Object[]{
                pkg.getPackageId(),
                pkg.getPackageName(),
                pkg.getDescription(),
                "$" + pkg.getPrice(),
                pkg.getCapacity()
            });
        }
    }
}
