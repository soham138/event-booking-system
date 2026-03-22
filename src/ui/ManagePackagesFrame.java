package ui;

import models.EventPackage;
import services.EventPackageService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ManagePackagesFrame extends JFrame {
    public ManagePackagesFrame() {
        setTitle("Event Booking System - Manage Packages");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        
        JPanel formPanel = createFormPanel();
        mainPanel.add(formPanel, BorderLayout.NORTH);

        
        String[] columns = {"Package ID", "Name", "Description", "Price", "Capacity"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.addActionListener(e -> {
            tableModel.setRowCount(0);
            refreshTable(tableModel);
        });
        buttonPanel.add(refreshBtn);

        JButton deleteBtn = new JButton("Delete Selected");
        deleteBtn.addActionListener(e -> {
            int rows = table.getSelectedRow();
            if (rows < 0) {
                JOptionPane.showMessageDialog(this, "Please select a package to delete!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int packageId = (int) tableModel.getValueAt(rows, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this package?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                String result = EventPackageService.deleteEventPackage(packageId);
                JOptionPane.showMessageDialog(this, result, "Info", JOptionPane.INFORMATION_MESSAGE);
                tableModel.setRowCount(0);
                refreshTable(tableModel);
            }
        });
        buttonPanel.add(deleteBtn);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        
        refreshTable(tableModel);

        add(mainPanel);
        setVisible(true);
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Add/Edit Package"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Package Name:"), gbc);

        JTextField nameField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Description:"), gbc);

        JTextField descField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(descField, gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Price:"), gbc);

        JTextField priceField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(priceField, gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Capacity:"), gbc);

        JTextField capacityField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(capacityField, gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        JButton addBtn = new JButton("Add Package");
        addBtn.addActionListener(e -> {
            String result = EventPackageService.addEventPackage(
                nameField.getText(),
                descField.getText(),
                priceField.getText(),
                capacityField.getText()
            );

            if (result.contains("successfully")) {
                JOptionPane.showMessageDialog(panel, result, "Success", JOptionPane.INFORMATION_MESSAGE);
                nameField.setText("");
                descField.setText("");
                priceField.setText("");
                capacityField.setText("");
            } else {
                JOptionPane.showMessageDialog(panel, result, "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(addBtn, gbc);

        return panel;
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
